-----------------------------------------------------------------------------------
CREATE OR REPLACE STREAM "stations_info" (
  station_id STRING,
  name STRING,
  short_name STRING,
  lat DOUBLE,
  lon DOUBLE,
  address STRING,
  cross_street STRING,
  region_id STRING,
  post_code STRING,
  rental_methods ARRAY<STRING>,
  is_virtual_station BOOLEAN,
  station_area STRUCT<
    type STRING,
    coordinates ARRAY<ARRAY<ARRAY<ARRAY<DOUBLE>>>>
  >,
  parking_type STRING,
  parking_hoop BOOLEAN,
  contact_phone STRING,
  capacity INT,
  vehicle_capacity MAP<STRING, DOUBLE>,
  is_valet_station BOOLEAN,
  is_charging_station BOOLEAN,
  rental_uris STRUCT<
    android STRING,
    ios STRING,
    web STRING
  >,
  vehicle_type_capacity MAP<STRING, DOUBLE>
) WITH (kafka_topic='stations.info', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE STREAM "system_regions" (
  region_id STRING,
  name STRING
) WITH (kafka_topic='system.regions', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE TABLE "stations_info_latest"
  WITH (kafka_topic='stations.info.latest', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT 
    station_id AS station_id,
    latest_by_offset(info.name) AS name,
    latest_by_offset(info.short_name) AS short_name,
    latest_by_offset(info.lat) AS lat,
    latest_by_offset(info.lon) AS lon,
    latest_by_offset(info.address) AS address,
    latest_by_offset(info.cross_street) AS cross_street,
    latest_by_offset(info.region_id) AS region_id,
    latest_by_offset(reg.name) AS region_name,
    latest_by_offset(info.post_code) AS post_code,
    latest_by_offset(info.rental_methods) AS rental_methods,
    latest_by_offset(info.is_virtual_station) AS is_virtual_station,
    latest_by_offset(info.station_area) AS station_area,
    latest_by_offset(info.parking_type) AS parking_type,
    latest_by_offset(info.parking_hoop) AS parking_hoop,
    latest_by_offset(info.contact_phone) AS contact_phone,
    latest_by_offset(info.capacity) AS capacity,
    latest_by_offset(info.vehicle_capacity) AS vehicle_capacity,
    latest_by_offset(info.is_valet_station) AS is_valet_station,
    latest_by_offset(info.is_charging_station) AS is_charging_station,
    latest_by_offset(info.rental_uris) AS rental_uris,
    latest_by_offset(info.vehicle_type_capacity) AS vehicle_type_capacity
  FROM "stations_info" info
  INNER JOIN "system_regions" reg
  WITHIN 5 MINUTES GRACE PERIOD 1 MINUTE
  ON info.region_id = reg.region_id
  GROUP BY station_id;

CREATE OR REPLACE STREAM "stations_status" (
  station_id STRING,
  num_bikes_available INT,
  vehicle_types_available ARRAY<STRUCT<
    vehicle_type_id STRING,
    count INT
  >>,
  num_bikes_disabled INT,
  num_docks_available INT,
  num_docks_disabled INT,
  is_installed BOOLEAN,
  is_renting BOOLEAN,
  is_returning BOOLEAN,
  last_reported BIGINT,
  vehicle_docks_available ARRAY<STRUCT<
    vehicle_type_ids ARRAY<STRING>,
    count INT
  >>
) WITH (kafka_topic='stations.status', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE STREAM "stations_enriched"
  WITH (kafka_topic='stations.enriched', partitions=6, key_format='KAFKA', value_format='JSON_SR')
  AS SELECT 
    info.station_id,
    as_value(info.station_id) `station_id`,
    info.name `name`,
    info.short_name `short_name`,
    info.lat `lat`,
    info.lon `lon`,
    info.address `address`,
    info.cross_street `cross_street`,
    info.region_id `region_id`,
    info.region_name `region_name`,
    info.post_code `post_code`,
    info.rental_methods `rental_methods`,
    info.is_virtual_station `is_virtual_station`,
    info.station_area `station_area`,
    info.parking_type `parking_type`,
    info.parking_hoop `parking_hoop`,
    info.contact_phone `contact_phone`,
    info.capacity `capacity`,
    info.vehicle_capacity `vehicle_capacity`,
    info.is_valet_station `is_valet_station`,
    info.is_charging_station `is_charging_station`,
    info.rental_uris `rental_uris`,
    info.vehicle_type_capacity `vehicle_type_capacity`,
    status.num_bikes_available `num_bikes_available`,
    status.vehicle_types_available `vehicle_types_available`,
    status.num_bikes_disabled `num_bikes_disabled`,
    status.num_docks_available `num_docks_available`,
    status.num_docks_disabled `num_docks_disabled`,
    status.is_installed `is_installed`,
    status.is_renting `is_renting`,
    status.is_returning `is_returning`,
    status.last_reported `last_reported`,
    status.vehicle_docks_available `vehicle_docks_available`
  FROM "stations_status" status 
  INNER JOIN "stations_info_latest" info
  ON status.station_id = info.station_id;

-- -- Drop Everything
-- DROP STREAM IF EXISTS "stations_enriched";
-- DROP STREAM IF EXISTS "stations_status";
-- DROP TABLE IF EXISTS "stations_info_latest";
-- DROP STREAM IF EXISTS "stations_info";
-- DROP STREAM IF EXISTS "system_regions";