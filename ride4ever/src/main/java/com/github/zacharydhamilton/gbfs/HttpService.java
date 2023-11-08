package com.github.zacharydhamilton.gbfs;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zacharydhamilton.objects.station_info.StationInformation;
import com.github.zacharydhamilton.objects.station_status.StationStatus;
import com.github.zacharydhamilton.objects.system_regions.SystemRegions;

public class HttpService {
    static String station_information_url = "https://gbfs.lyft.com/gbfs/2.3/bkn/en/station_information.json"; // "https://gbfs.citibikenyc.com/gbfs/en/station_information.json";
    static String station_status_url = "https://gbfs.lyft.com/gbfs/2.3/bkn/en/station_status.json"; // "https://gbfs.citibikenyc.com/gbfs/en/station_status.json";
    static String system_regions_url = "https://gbfs.lyft.com/gbfs/2.3/bkn/en/system_regions.json";

    public static StationInformation getStationInformation() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(station_information_url);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    ObjectMapper mapper = new ObjectMapper();
                    StationInformation stationInformation = mapper.readValue(result, StationInformation.class);

                    // Do something
                    return stationInformation;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static StationStatus getStationStatus() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(station_status_url);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    ObjectMapper mapper = new ObjectMapper();
                    StationStatus stationStatus = mapper.readValue(result, StationStatus.class);
                    
                    // Do something
                    return stationStatus;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static SystemRegions getSystemRegions() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(system_regions_url);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    ObjectMapper mapper = new ObjectMapper();
                    SystemRegions systemRegions = mapper.readValue(result, SystemRegions.class);
                    
                    // Do something
                    return systemRegions;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
