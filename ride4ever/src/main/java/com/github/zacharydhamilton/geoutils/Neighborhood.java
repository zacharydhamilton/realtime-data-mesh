package com.github.zacharydhamilton.geoutils;

import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import com.google.gson.JsonObject;

public class Neighborhood {
    private String city;
    private MultiPolygon multiPolygon;
    private Polygon polygon;
    private JsonObject properties;

    public Neighborhood(String city, MultiPolygon multiPolygon, JsonObject properties) {
        this.city = city;
        this.multiPolygon = multiPolygon;
        this.properties = properties;
    }
    public Neighborhood(String city, Polygon polygon, JsonObject properties) {
        this.city = city;
        this.polygon = polygon;
        this.properties = properties;
    }

    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public MultiPolygon getMultiPolygon() {
        return this.multiPolygon;
    }
    public void setMultiPolygon(MultiPolygon multiPolygon) {
        this.multiPolygon = multiPolygon;
    }
    public Polygon getPolygon() {
        return this.polygon;
    }
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
    public JsonObject getProperties() {
        return this.properties;
    }
    public void setProperties(JsonObject properties) {
        this.properties = properties;
    }
}
