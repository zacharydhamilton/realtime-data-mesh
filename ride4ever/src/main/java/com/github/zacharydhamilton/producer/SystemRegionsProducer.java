package com.github.zacharydhamilton.producer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.zacharydhamilton.gbfs.HttpService;
import com.github.zacharydhamilton.objects.system_regions.Region;
import com.github.zacharydhamilton.objects.system_regions.SystemRegions;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;
import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializerConfig;

public class SystemRegionsProducer {
    private static final Logger logger = LogManager.getLogger(SystemRegionsProducer.class);
    static String configType = (System.getenv("CONFIG_TYPE") != null) ? System.getenv("CONFIG_TYPE") : "FILE"; 
    static String configFile = (System.getenv("CONFIG_FILE") != null) ? System.getenv("CONFIG_FILE") : "../client.properties";
    static String topic = "system.regions";
    static String clientId = "system-regions-producer";

    public static void main(String[] args) throws IOException {
            KafkaProducer<String, Region> producer = createProducer();
            while (true) {
                try {
                    SystemRegions systemRegions = HttpService.getSystemRegions();
                    if (systemRegions != null) {
                        for (Region region : systemRegions.getData().getRegions()) {
                            ProducerRecord<String, Region> record = new ProducerRecord<String, Region>(topic, region.getRegionId(), region);
                            producer.send(record);
                        }
                        producer.flush();
                        logger.info(String.format("Sending a bulk of systemRegion '%s' records", systemRegions.getData().getRegions().size()));
                    }
                    Thread.sleep(5*60*1000);
                } catch (Exception exception) {
                    logger.error(exception.getMessage(), exception);
                }
            }
    }

    public static KafkaProducer<String, Region> createProducer() throws IOException {
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
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5); 
        return new KafkaProducer<String, Region>(props);
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
