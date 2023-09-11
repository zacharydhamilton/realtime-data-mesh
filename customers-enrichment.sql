-----------------------------------------------------------------------------------
CREATE OR REPLACE STREAM "customers_info" (
    customer_id STRING,
    first_name STRING,
    last_name STRING,
    email STRING,
    phone_number STRING
) WITH (kafka_topic='customers.info', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE TABLE "customers_info_latest" 
    WITH (kafka_topic='customers.info.latest', partitions=6, key_format='KAFKA', value_format='JSON_SR')
    AS SELECT 
        customer_id pk,
        as_value(customer_id) customer_id,
        latest_by_offset(first_name) first_name,
        latest_by_offset(last_name) last_name,
        latest_by_offset(email) email,
        latest_by_offset(phone_number) phone_number
    FROM "customers_info"
    GROUP BY customer_id;

CREATE OR REPLACE STREAM "customers_demographics" (
    customer_id STRING,
    age INT,
    gender STRING,
    income INT,
    education_level STRING,
    marital_status STRING
) WITH (kafka_topic='customers.demographics', partitions=6, key_format='KAFKA', value_format='JSON_SR');

CREATE OR REPLACE TABLE "customers_demographics_latest" 
    WITH (kafka_topic='customers.demographics.latest', partitions=6, key_format='KAFKA', value_format='JSON_SR')
    AS SELECT 
        customer_id pk,
        as_value(customer_id) customer_id,
        latest_by_offset(age) age,
        latest_by_offset(gender) gender,
        latest_by_offset(income) income,
        latest_by_offset(education_level) education_level,
        latest_by_offset(marital_status) marital_status
    FROM "customers_demographics"
    GROUP BY customer_id;

CREATE OR REPLACE TABLE "customers_enriched" 
    WITH (kafka_topic='customers.enriched', partitions=6, key_format='KAFKA', value_format='JSON_SR')
    AS SELECT 
        info.pk pk,
        info.customer_id `customer_id`,
        info.first_name `first_name`,
        info.last_name `last_name`,
        info.email `email`,
        info.phone_number `phone_number`,
        demo.age `age`,
        demo.gender `gender`,
        demo.income `income`,
        demo.education_level `education_level`,
        demo.marital_status `marital_status`
    FROM "customers_info_latest" info
    JOIN "customers_demographics_latest" demo 
    ON info.pk = demo.pk;