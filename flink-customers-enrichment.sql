-----------------------------------------------------------------------------------
USE kafka-ride4ever;

USE CATALOG realtime-data-mesh;
USE kafka-ride4ever;

CREATE TABLE `customers.info.latest` (
    customer_id STRING PRIMARY KEY NOT ENFORCED,
    first_name STRING,
    last_name STRING,
    email STRING,
    phone_number STRING
) WITH (
  'key.format' = 'String',
  'value.format' = 'avro-registry',
  'kafka.cleanup-policy' = 'compact',
  'changelog.mode' = 'upsert'
);

insert into `customers.info.latest` (customer_id, first_name, last_name, email, phone_number)
select customer_id, first_name, last_name, email, phone_number from `customers.info`;

CREATE TABLE `customers.demographics.latest` (
    customer_id STRING,
    age INT,
    gender STRING,
    income INT,
    education_level STRING,
    marital_status STRING
) WITH (
    'value.format' = 'avro-registry',
    'kafka.cleanup-policy' = 'compact'
);