package com.github.zacharydhamilton.geoutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.index.strtree.STRtree;

import com.github.zacharydhamilton.objects.Centroid;
import com.github.zacharydhamilton.objects.StationNeighborhood;
import com.github.zacharydhamilton.objects.station_info.Station;
import com.google.gson.JsonObject;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializerConfig;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig;
import io.confluent.kafka.streams.serdes.json.KafkaJsonSchemaSerde;

public class NeighborhoodGeofenceStream {
    private static final Logger logger = LogManager.getLogger(NeighborhoodGeofenceStream.class);
    static String configType = (System.getenv("CONFIG_TYPE") != null) ? System.getenv("CONFIG_TYPE") : "FILE"; 
    static String configFile = (System.getenv("CONFIG_FILE") != null) ? System.getenv("CONFIG_FILE") : "../client.properties";
    static String sourceTopic = "stations.info";
    static String sinkTopic = "stations.info.neighborhoods";
    static String applicationId = "neighborhood-geofence-enricher";
    static String clientId = "neighborhood-geofence-enricher";
    static final GeometryFactory geometryFactory = new GeometryFactory();
    static STRtree index;

    public static void main(String[] args) throws IOException, ConfigException {
        index = new STRtree();
        index = Geofencer.addNYCGeoJsonToSTRtreeIndex(index);
        index = Geofencer.addJCGeoJsonToSTRtreeIndex(index);
        KafkaStreams streams = createStreams();
        streams.start();
    }
    public static KafkaStreams createStreams() throws IOException, ConfigException {
        Properties props = new Properties();
        if (configType.equals("FILE")) {
            addPropsFromFile(props, configFile);
        } else {
            preInitChecks();
            props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, System.getenv("BOOTSTRAP_SERVERS"));
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
            props.put(SaslConfigs.SASL_JAAS_CONFIG, System.getenv("SASL_JAAS_CONFIG"));
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            props.put(KafkaJsonSchemaSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, System.getenv("SCHEMA_REGISTRY_URL"));
            props.put(KafkaJsonSchemaSerializerConfig.USER_INFO_CONFIG, System.getenv("SCHEMA_REGISTRY_KEY")+":"+System.getenv("SCHEMA_REGISTRY_SECRET"));
            props.put(KafkaJsonSchemaSerializerConfig.BASIC_AUTH_CREDENTIALS_SOURCE, "USER_INFO");
        }
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        props.put(StreamsConfig.CLIENT_ID_CONFIG, clientId);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, KafkaJsonSchemaSerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        return new KafkaStreams(buildStreams(props).build(), props);
    }
    public static StreamsBuilder buildStreams(Properties props) throws IOException {
        final StreamsBuilder builder = new StreamsBuilder();
        KafkaJsonSchemaSerde<Station> stationInfoSerde = createStationInfoSerde(props);
        final KStream<String, Station> stationInfoStream = builder.stream(sourceTopic, Consumed.with(Serdes.String(), stationInfoSerde));
        KafkaJsonSchemaSerde<StationNeighborhood> stationNeighborhoodSerde = createStationNeighborhoodSerde(props);
        final KStream<String, StationNeighborhood> stationNeighborhoodStream = stationInfoStream.map(new KeyValueMapper<String, Station, KeyValue<String, StationNeighborhood>>() {
            public KeyValue<String, StationNeighborhood> apply(String key, Station value) {
                StationNeighborhood stationNeighborhood = new StationNeighborhood();
                // Very interesting to note that these coordinates need to be in long, lat for some reason....
                Point coordinate = geometryFactory.createPoint(new Coordinate(value.getLon(), value.getLat()));
                List<Neighborhood> candidates = new ArrayList<>();
                for (Object candidate : index.query(coordinate.getEnvelopeInternal())) {
                    candidates.add((Neighborhood) candidate);
                }
                for (Neighborhood n : candidates) {
                    if (n.getCity() == "nyc" && n.getMultiPolygon().contains(coordinate)) {
                        JsonObject properties = n.getProperties();
                        stationNeighborhood.setRegionId(value.getRegionId());
                        stationNeighborhood.setStationId(value.getStationId());
                        stationNeighborhood.setZone(properties.get("boro_name").getAsString());
                        stationNeighborhood.setNeighborhood(properties.get("ntaname").getAsString());
                        stationNeighborhood.setAreaSqFt(properties.get("shape_area").getAsString());
                        Centroid centroid = new Centroid();
                        Point p = n.getMultiPolygon().getCentroid();
                        centroid.setLat(String.valueOf(p.getCoordinate().getY()));
                        centroid.setLon(String.valueOf(p.getCoordinate().getX()));
                        stationNeighborhood.setCentroid(centroid);
                        return new KeyValue<>(value.getStationId(), stationNeighborhood);
                    }
                    if (n.getCity() == "jc" && n.getPolygon().contains(coordinate)) {
                        JsonObject properties = n.getProperties();
                        stationNeighborhood.setRegionId(value.getRegionId());
                        stationNeighborhood.setStationId(value.getStationId());
                        stationNeighborhood.setZone(properties.get("area").getAsString());
                        stationNeighborhood.setNeighborhood(properties.get("neighborho").getAsString());
                        stationNeighborhood.setAreaSqFt(properties.get("area_sq_ft").getAsString());
                        Centroid centroid = new Centroid();
                        Point p = n.getPolygon().getCentroid();
                        centroid.setLat(String.valueOf(p.getCoordinate().getY()));
                        centroid.setLon(String.valueOf(p.getCoordinate().getX()));
                        stationNeighborhood.setCentroid(centroid);
                        return new KeyValue<>(value.getStationId(), stationNeighborhood);
                    }
                }
                // These *should* all be Hoboken stations (there's no geojson for Hoboken that I can find).
                // These values are filled with a single generic set of values that I found on google
                stationNeighborhood.setRegionId(value.getRegionId());
                stationNeighborhood.setStationId(value.getStationId());
                stationNeighborhood.setZone("Hoboken");
                stationNeighborhood.setNeighborhood("Hoboken");
                stationNeighborhood.setAreaSqFt("35126784");
                Centroid centroid = new Centroid();
                centroid.setLat("40.74400000000000");
                centroid.setLon("-74.03240000000000");
                stationNeighborhood.setCentroid(centroid);
                return new KeyValue<>(value.getStationId(), stationNeighborhood);
            }
        });
        stationNeighborhoodStream.to(sinkTopic, Produced.with(Serdes.String(), stationNeighborhoodSerde));
        return builder;
    }

    private static KafkaJsonSchemaSerde<Station> createStationInfoSerde(Properties props) {
        KafkaJsonSchemaSerde<Station> stationInfoSerde = new KafkaJsonSchemaSerde<>();
        Map<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE, Station.class.getName());
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, props.getProperty("schema.registry.url"));
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.BASIC_AUTH_CREDENTIALS_SOURCE, props.getProperty("basic.auth.credentials.source"));
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.USER_INFO_CONFIG, props.getProperty("basic.auth.user.info"));
        stationInfoSerde.configure(serdeConfig, false);
        return stationInfoSerde;
    }
    private static KafkaJsonSchemaSerde<StationNeighborhood> createStationNeighborhoodSerde(Properties props) {
        KafkaJsonSchemaSerde<StationNeighborhood> stationNeighborhoodSerde = new KafkaJsonSchemaSerde<>();
        Map<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(KafkaJsonSchemaDeserializerConfig.JSON_VALUE_TYPE, StationNeighborhood.class.getName());
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, props.getProperty("schema.registry.url"));
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.BASIC_AUTH_CREDENTIALS_SOURCE, props.getProperty("basic.auth.credentials.source"));
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.USER_INFO_CONFIG, props.getProperty("basic.auth.user.info"));
        stationNeighborhoodSerde.configure(serdeConfig, false);
        return stationNeighborhoodSerde;
    }

    /**
     * Check for the necessary configurations to initialize the client. If any are missing, fails. 
     * 
     * @throws ConfigException
     */
    private static void preInitChecks() throws ConfigException {
        ArrayList<String> requiredProps = new ArrayList<String>(Arrays.asList("BOOTSTRAP_SERVERS", "SASL_JAAS_CONFIG", "SCHEMA_REGISTRY_URL", "SCHEMA_REGISTRY_KEY", "SCHEMA_REGISTRY_SECRET"));
        ArrayList<String> missingProps = new ArrayList<String>();
        for (String prop : requiredProps) {
            if (System.getenv(prop) == null) {
                missingProps.add(prop);
            }
        }
        if (missingProps.size() > 0) {
            throw new ConfigException("Missing required properties: " + missingProps.toString());
        }
    }

    /**
     * Load properties from an application properties file.
     * 
     * @param props - An existing Properties object to add the properties to.
     * @param file - An existing file containing properties to add to the Properties object. 
     * @throws IOException
     */
    private static void addPropsFromFile(Properties props, String file) throws IOException {
        if (!Files.exists(Paths.get(file))) {
            System.out.println("Current working environment: " + System.getProperty("user.dir"));
            throw new IOException("Config file (" + file + ") does not exist or was not found.");
        }
        try (InputStream inputStream = new FileInputStream(file)) {
            props.load(inputStream);
        }
    }
}
