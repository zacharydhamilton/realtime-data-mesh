-----------------------------------------------------------------------------------
CREATE OR REPLACE STREAM "inventory_metrics_high" (
  station_id STRING,
  name STRING,
  region_name STRING,
  lat DOUBLE, lon DOUBLE,
  is_virtual_station BOOLEAN,
  capacity INT,
  num_bikes_available INT,
  num_bikes_disabled INT,
  num_docks_available INT,
  num_docks_disabled INT,
  num_ebikes_available INT,
  num_scooters_available INT,
  num_scooters_unavailable INT,
  vehicle_docks_available ARRAY<STRUCT<
    vehicle_type_ids ARRAY<STRING>,
    count INT
  >>,
  vehicle_types_available ARRAY<STRUCT<
    vehicle_type_id STRING,
    count INT
  >>,
  region_id STRING,
  last_reported BIGINT,
  is_installed BOOLEAN,
  is_renting BOOLEAN,
  is_returning BOOLEAN 
) WITH (kafka_topic='stations.enriched', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE TABLE "inventory_availability_high"
  WITH (kafka_topic='inventory.availability.high', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT
  station_id key,
  latest_by_offset(as_value(station_id)) station_id, 
  latest_by_offset(name) name,
  latest_by_offset(region_name) region_name, 
  latest_by_offset(lat) lat, latest_by_offset(lon) lon,
  latest_by_offset(capacity) capacity,
  latest_by_offset(num_bikes_available) num_bikes_available,
  latest_by_offset(num_docks_available) num_docks_available,
  latest_by_offset(num_bikes_available - FLOOR(CAST(capacity AS DOUBLE)/2.0)) surplus,
  latest_by_offset(ROUND(CAST(num_bikes_available AS DOUBLE) / CAST(capacity AS DOUBLE), 2)) perecent_available
  FROM "inventory_metrics_high"
  WHERE (capacity + num_bikes_available + num_docks_available) != 0
  AND num_bikes_available >= ROUND(CAST(capacity AS DOUBLE)*0.75)
  GROUP BY station_id;

CREATE OR REPLACE STREAM "inventory_availability_high-changelog" (
    station_id STRING,
    name STRING,
    region_name STRING,
    lat DOUBLE, lon DOUBLE,
    surplus INT,
    perecent_available DOUBLE
) WITH (kafka_topic='inventory.availability.high', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE STREAM "inventory_availability_high-keyed"
  WITH (kafka_topic='inventory.availability.high-keyed', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT * FROM "inventory_availability_high-changelog" PARTITION BY region_name;

CREATE OR REPLACE STREAM "inventory_metrics_low" (
  station_id STRING,
  name STRING,
  region_name STRING,
  lat DOUBLE, lon DOUBLE, 
  is_virtual_station BOOLEAN,
  capacity INT,
  num_bikes_available INT,
  num_bikes_disabled INT,
  num_docks_available INT,
  num_docks_disabled INT,
  num_ebikes_available INT,
  num_scooters_available INT,
  num_scooters_unavailable INT,
  vehicle_docks_available ARRAY<STRUCT<
    vehicle_type_ids ARRAY<STRING>,
    count INT
  >>,
  vehicle_types_available ARRAY<STRUCT<
    vehicle_type_id STRING,
    count INT
  >>,
  region_id STRING,
  last_reported BIGINT,
  is_installed BOOLEAN,
  is_renting BOOLEAN,
  is_returning BOOLEAN 
) WITH (kafka_topic='stations.enriched', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE TABLE "inventory_availability_low"
  WITH (kafka_topic='inventory.availability.low', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT 
  station_id key,
  latest_by_offset(as_value(station_id)) station_id, 
  latest_by_offset(name) name,
  latest_by_offset(region_name) region_name,
  latest_by_offset(lat) lat, latest_by_offset(lon) lon, 
  latest_by_offset(capacity) capacity,
  latest_by_offset(num_bikes_available) num_bikes_available,
  latest_by_offset(num_docks_available) num_docks_available,
  latest_by_offset(FLOOR(CAST(capacity AS DOUBLE)/2.0) - num_bikes_available) shortage,
  latest_by_offset(ROUND(CAST(num_bikes_available AS DOUBLE) / CAST(capacity AS DOUBLE), 2)) perecent_available
  FROM "inventory_metrics_low" 
  WHERE (capacity + num_bikes_available + num_docks_available) != 0
  AND num_bikes_available <= ROUND(CAST(capacity AS DOUBLE)*0.25)
  AND num_docks_available >= (FLOOR(CAST(capacity AS DOUBLE)/2.0) - num_bikes_available)
  GROUP BY station_id;

CREATE OR REPLACE STREAM "inventory_availability_low-changelog" (
    station_id STRING,
    name STRING,
    region_name STRING,
    lat DOUBLE, lon DOUBLE,
    shortage INT,
    perecent_available DOUBLE
) WITH (kafka_topic='inventory.availability.low', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE STREAM "inventory_availability_low-keyed"
  WITH (kafka_topic='inventory.availability.low-keyed', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT * FROM "inventory_availability_low-changelog" PARTITION BY region_name;

CREATE OR REPLACE TABLE "inventory_redistribution"
  WITH (kafka_topic='inventory.redistribution', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT 
  concat(high.station_id, '-', low.station_id) pair, 
  latest_by_offset(high.name) `source_name`, 
  latest_by_offset(low.name) `dest_name`,
  latest_by_offset(high.surplus) `source_surplus`,
  latest_by_offset(low.shortage) `dest_shortage`,
  latest_by_offset(low.shortage) `redistribution`,
  latest_by_offset(ROUND(GEO_DISTANCE(high.lat, high.lon, low.lat, low.lon, 'miles'), 2)) `distance`
  FROM "inventory_availability_high-keyed" high
  INNER JOIN "inventory_availability_low-keyed" low
  WITHIN 1 MINUTE GRACE PERIOD 1 MINUTE
  ON high.region_name = low.region_name
  WHERE high.surplus >= low.shortage
  GROUP BY concat(high.station_id, '-', low.station_id);

  -- ON high.KSQL_COL_0 = low.KSQL_COL_0

-- -- Drop Everything
-- DROP TABLE IF EXISTS "inventory_redistribution";
-- DROP STREAM IF EXISTS "inventory_availability_low-keyed";
-- DROP STREAM IF EXISTS "inventory_availability_low-changelog";
-- DROP STREAM IF EXISTS "inventory_availability_high-keyed";
-- DROP STREAM IF EXISTS "inventory_availability_high-changelog";
-- DROP TABLE IF EXISTS "inventory_availability_low";
-- DROP TABLE IF EXISTS "inventory_availability_high";
-- DROP STREAM IF EXISTS "inventory_metrics_low";
-- DROP STREAM IF EXISTS "inventory_metrics_high";

