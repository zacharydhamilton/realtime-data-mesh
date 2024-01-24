package com.github.zacharydhamilton.geoutils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.index.strtree.STRtree;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Geofencer {
    public static final Logger logger = LogManager.getLogger(Geofencer.class);
    public static final GeometryFactory geometryFactory = new GeometryFactory();
    public static String geojsonResource = "NYC_Neighborhood.geojson";
    public static String nycNeighborhoodsGeoJsonFile = "nyc-neighborhoods.geojson";
    public static String jcNeighborhoodsGeoJsonFile = "jersey-city-neighborhoods.geojson";

    public static STRtree addNYCGeoJsonToSTRtreeIndex(STRtree index) throws IOException {
        try (Reader reader = new InputStreamReader(Geofencer.class.getClassLoader().getResourceAsStream(nycNeighborhoodsGeoJsonFile))) {
            JsonObject geojson = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray features = geojson.get("features").getAsJsonArray();
            for (JsonElement element : features) {
                JsonObject feature = element.getAsJsonObject();
                MultiPolygon multiPolygon = createMultiPolygonFromCoordinates(feature.get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray());
                Neighborhood neighborhood = new Neighborhood("nyc", multiPolygon, feature.get("properties").getAsJsonObject());
                index.insert(multiPolygon.getEnvelopeInternal(), neighborhood);
            }
            return index;
        }
    }
    public static STRtree addJCGeoJsonToSTRtreeIndex(STRtree index) throws IOException {
        try (Reader reader = new InputStreamReader(Geofencer.class.getClassLoader().getResourceAsStream(jcNeighborhoodsGeoJsonFile))) {
            JsonObject geojson = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray features = geojson.get("features").getAsJsonArray();
            for (JsonElement element : features) {
                JsonObject feature = element.getAsJsonObject();
                Polygon polygon = createPolygonFromCoordinates(feature.get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray());
                Neighborhood neighborhood = new Neighborhood("jc", polygon, feature.get("properties").getAsJsonObject());
                index.insert(polygon.getEnvelopeInternal(), neighborhood);
            }
            return index;
        }
    }
    public static MultiPolygon createMultiPolygonFromCoordinates(JsonArray coordinates) {
        Polygon[] polygons = new Polygon[coordinates.size()];
        // Polygons
        for (int p=0; p<coordinates.size(); p++) {
            LinearRing shell = geometryFactory.createLinearRing();
            LinearRing[] holes = new LinearRing[coordinates.get(p).getAsJsonArray().size()-1];
            // Rings
            for (int r=0; r<coordinates.get(p).getAsJsonArray().size(); r++) {
                // Outer ring
                if (r == 0) {
                    Coordinate[] boundary = new Coordinate[coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().size()];
                    for (int c=0; c<coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().size(); c++) {
                        boundary[c] = new Coordinate(coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().get(c).getAsJsonArray().get(0).getAsDouble(),
                                                     coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().get(c).getAsJsonArray().get(1).getAsDouble());
                    }
                    shell = geometryFactory.createLinearRing(boundary);
                } 
                // Inner rings
                else {
                    Coordinate[] hole = new Coordinate[coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().size()];
                    for (int c=0; c<coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().size(); c++) {
                        hole[c] = new Coordinate(coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().get(c).getAsJsonArray().get(0).getAsDouble(),
                                                 coordinates.get(p).getAsJsonArray().get(r).getAsJsonArray().get(c).getAsJsonArray().get(1).getAsDouble());
                    }
                    holes[r-1] = geometryFactory.createLinearRing(hole);
                }
            }
            polygons[p] = geometryFactory.createPolygon(shell, holes);
        }
        MultiPolygon multiPolygon = geometryFactory.createMultiPolygon(polygons);
        return multiPolygon;
    }
    public static Polygon createPolygonFromCoordinates(JsonArray coordinates) {
        // Polygon
        LinearRing shell = geometryFactory.createLinearRing();
        LinearRing[] holes = new LinearRing[coordinates.size()-1];
        // Rings 
        for (int r=0; r<coordinates.size(); r++) {
            // Outer ring
            if (r == 0) {
                Coordinate[] boundary = new Coordinate[coordinates.get(r).getAsJsonArray().size()];
                for (int c=0; c<coordinates.get(r).getAsJsonArray().size(); c++) {
                    boundary[c] = new Coordinate(coordinates.get(r).getAsJsonArray().get(c).getAsJsonArray().get(0).getAsDouble(),
                                                 coordinates.get(r).getAsJsonArray().get(c).getAsJsonArray().get(1).getAsDouble());
                }
                shell = geometryFactory.createLinearRing(boundary);
            }
            // Inner rings
            else {
                Coordinate[] hole = new Coordinate[coordinates.get(r).getAsJsonArray().size()];
                for (int c=0; c<coordinates.get(r).getAsJsonArray().size(); c++) {
                    hole[c] = new Coordinate(coordinates.get(r).getAsJsonArray().get(c).getAsJsonArray().get(0).getAsDouble(),
                                             coordinates.get(r).getAsJsonArray().get(c).getAsJsonArray().get(1).getAsDouble());
                }
                holes[r-1] = geometryFactory.createLinearRing(hole);
            }
        }
        Polygon polygon = geometryFactory.createPolygon(shell, holes);
        return polygon;
    }
}
