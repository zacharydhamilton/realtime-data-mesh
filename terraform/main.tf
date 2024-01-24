# PROVIDERS
# --------------------
terraform {
    required_providers {
        confluent = {
            source = "confluentinc/confluent"
            version = "1.51.0"
        }
    }
}
# RANDOM IDS
# --------------------
resource "random_id" "confluent" {
    byte_length = 4
}
# VARS
# --------------------
variable "example_name" {
    type = string
}
variable "aws_region" {
    type = string
    default = "us-east-2"
}
# ENV
# --------------------
resource "confluent_environment" "main" {
    display_name = var.example_name
}
# TF MANAGER
# --------------------
resource "confluent_service_account" "app_manager" {
    display_name = "app-manager-${random_id.confluent.hex}"
    description = "app-manager for 'schema-registry-examples'"
}
resource "confluent_role_binding" "app_manager_env_admin" {
    principal = "User:${confluent_service_account.app_manager.id}"
    role_name = "EnvironmentAdmin"
    crn_pattern = confluent_environment.main.resource_name
}
resource "confluent_api_key" "app_manager_sr" {
    display_name = "app-manager-sr-${random_id.confluent.hex}"
    description = "app-manager-sr-${random_id.confluent.hex}"
    owner {
        id = confluent_service_account.app_manager.id
        api_version = confluent_service_account.app_manager.api_version
        kind = confluent_service_account.app_manager.kind
    }
    managed_resource {
        id = confluent_schema_registry_cluster.main.id
        api_version = confluent_schema_registry_cluster.main.api_version
        kind = confluent_schema_registry_cluster.main.kind
        environment {
            id = confluent_environment.main.id
        }
    }
    depends_on = [
        confluent_service_account.app_manager,
        confluent_role_binding.app_manager_env_admin
    ]
}
resource "confluent_api_key" "app_manager_kafka" {
    display_name = "app-manager-kafka-${random_id.confluent.hex}"
    description = "app-manager-kafka-${random_id.confluent.hex}"
    owner {
        id = confluent_service_account.app_manager.id
        api_version = confluent_service_account.app_manager.api_version
        kind = confluent_service_account.app_manager.kind
    }
    managed_resource {
        id = confluent_kafka_cluster.main.id
        api_version = confluent_kafka_cluster.main.api_version
        kind = confluent_kafka_cluster.main.kind
        environment {
            id = confluent_environment.main.id
        }
    }
    depends_on = [
        confluent_service_account.app_manager,
        confluent_role_binding.app_manager_env_admin
    ]
}
resource "confluent_api_key" "app_manager_ksql" {
    display_name = "app-manager-ksql-${random_id.confluent.hex}"
    description = "app-manager-ksql-${random_id.confluent.hex}"
    owner {
        id = confluent_service_account.app_manager.id
        api_version = confluent_service_account.app_manager.api_version
        kind = confluent_service_account.app_manager.kind
    }
    managed_resource {
        id = confluent_ksql_cluster.main.id
        api_version = confluent_ksql_cluster.main.api_version
        kind = confluent_ksql_cluster.main.kind
        environment {
            id = confluent_environment.main.id
        }
    }
    depends_on = [
        confluent_service_account.app_manager,
        confluent_role_binding.app_manager_env_admin
    ]
}
# SCHEMA REGISTRY
# --------------------
data "confluent_schema_registry_region" "main" {
    cloud = "AWS"
    region = var.aws_region
    package = "ADVANCED"
}
resource "confluent_schema_registry_cluster" "main" {
    package = data.confluent_schema_registry_region.main.package
    environment {
        id = confluent_environment.main.id
    }
    region {
        id = data.confluent_schema_registry_region.main.id
    }
}
resource "confluent_schema_registry_cluster_config" "main" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id 
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    // None by default. Examples can set this for their own subjects.
    compatibility_level = "NONE"
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
}
resource "confluent_schema_registry_cluster_mode" "main" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    mode = "READWRITE"
    credentials {
        key = confluent_api_key.app_manager_sr.id 
        secret = confluent_api_key.app_manager_sr.secret
    }
}
# KAFKA
# --------------------
resource "confluent_kafka_cluster" "main" {
    display_name = "kafka-ride4ever"
    availability = "SINGLE_ZONE"
    cloud = "AWS"
    region = var.aws_region
    basic {}
    environment {
        id = confluent_environment.main.id 
    }
}
# KSQL
# --------------------
resource "confluent_ksql_cluster" "main" {
    display_name = "ksql-ride4ever"
    csu = 2
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
    credential_identity {
        id = confluent_service_account.app_manager.id
    }
    environment {
        id = confluent_environment.main.id
    }
    depends_on = [
        confluent_role_binding.app_manager_env_admin,
        confluent_schema_registry_cluster.main
    ]
}
# KAFKA CLIENTS
# --------------------
resource "confluent_service_account" "clients" {
    display_name = "clients-${random_id.confluent.hex}"
    description = "Service account for clients"
}
resource "confluent_role_binding" "clients_sr" {
    principal = "User:${confluent_service_account.clients.id}"
    role_name = "ResourceOwner"
    crn_pattern = format("%s/%s", confluent_schema_registry_cluster.main.resource_name, "subject=*")
}
resource "confluent_role_binding" "clients_kafka" {
    principal = "User:${confluent_service_account.clients.id}"
    role_name = "CloudClusterAdmin"
    crn_pattern = confluent_kafka_cluster.main.rbac_crn
}
resource "confluent_api_key" "clients_sr" {
    display_name = "clients-sr-${random_id.confluent.hex}"
    description = "clients-sr-${random_id.confluent.hex}"
    owner {
        id = confluent_service_account.clients.id
        api_version = confluent_service_account.clients.api_version
        kind = confluent_service_account.clients.kind
    }
    managed_resource {
        id = confluent_schema_registry_cluster.main.id 
        api_version = confluent_schema_registry_cluster.main.api_version
        kind = confluent_schema_registry_cluster.main.kind
        environment {
            id = confluent_environment.main.id 
        }
    }
    depends_on = [
        confluent_role_binding.clients_sr
    ]
}
resource "confluent_api_key" "clients_kafka" {
    display_name = "clients-kafka-${random_id.confluent.hex}"
    description = "clients-kafka-${random_id.confluent.hex}"
    owner {
        id = confluent_service_account.clients.id
        api_version = confluent_service_account.clients.api_version
        kind = confluent_service_account.clients.kind
    }
    managed_resource {
        id = confluent_kafka_cluster.main.id
        api_version = confluent_kafka_cluster.main.api_version
        kind = confluent_kafka_cluster.main.kind
        environment {
            id = confluent_environment.main.id 
        }
    }
    depends_on = [
        confluent_role_binding.clients_kafka
    ]
}
resource "confluent_api_key" "clients_ksql" {
    display_name = "clients-ksql-${random_id.confluent.hex}"
    description = "clients-ksql-${random_id.confluent.hex}"
    owner {
        id = confluent_service_account.clients.id
        api_version = confluent_service_account.clients.api_version
        kind = confluent_service_account.clients.kind
    }
    managed_resource {
        id = confluent_ksql_cluster.main.id 
        api_version = confluent_ksql_cluster.main.api_version
        kind = confluent_ksql_cluster.main.kind
        environment {
            id = confluent_environment.main.id
        }
    }
}
# TOPICS
# --------------------
resource "confluent_kafka_topic" "stations_status" {
    topic_name = "stations.status"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "stations_info" {
    topic_name = "stations.info"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "stations_info_neighborhoods"  {
    topic_name = "stations.info.neighborhoods"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "stations_encirched" {
    topic_name = "stations.enriched"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "system_regions" {
    topic_name = "system.regions"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "customer_info" {
    topic_name = "customers.info"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "customer_demographics" {
    topic_name = "customers.demographics"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
resource "confluent_kafka_topic" "customers_enriched" {
    topic_name = "customers.enriched"
    rest_endpoint = confluent_kafka_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_kafka.id
        secret = confluent_api_key.app_manager_kafka.secret
    }
    kafka_cluster {
        id = confluent_kafka_cluster.main.id
    }
}
# SCHEMAS
# --------------------
resource "confluent_schema" "customer_info" {
    subject_name = "customers.info-value"
    format = "JSON"
    schema = file("../schemas/customer_info.json")
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
}
resource "confluent_schema" "customer_demographics" {
    subject_name = "customers.demographics-value"
    format = "JSON"
    schema = file("../schemas/customer_demographics.json")
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
}
resource "confluent_schema" "customer_enriched" {
    subject_name = "customers.enriched-value"
    format = "JSON"
    schema = file("../schemas/customer_enriched.json")
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
}
# BUSINESS METADATA
# --------------------
resource "confluent_business_metadata" "domain_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id 
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    name = "Domain Details"
    description = "Additional metadata and details regarding a specific business domain."
    attribute_definition {
        name = "name"
    }
    attribute_definition {
        name = "executive_owner"
    }
    attribute_definition {
        name = "executive_contact"
    }
}
resource "confluent_business_metadata" "data_product_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id 
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    name = "Data Product Details"
    description = "Addition information and details regarding a Data Product."
    attribute_definition {
        name = "name"
    }
    attribute_definition {
        name = "primary_owner"
    }
    attribute_definition {
        name = "primary_contact"
    }
    attribute_definition {
        name = "responsible_team"
    }
    attribute_definition {
        name = "domain"
    }
}
resource "confluent_business_metadata" "team_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    name = "Team Details"
    description = "Team details for contacting the owner's of a dataset."
    attribute_definition {
        name = "team"
    }
    attribute_definition {
        name = "channel"
    }
    attribute_definition {
        name = "slack_alias"
    }
}
# BUSINESS METADATA BINDINGS
# --------------------
resource "confluent_business_metadata_binding" "stations_domain_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    business_metadata_name = confluent_business_metadata.domain_details.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.stations_encirched.topic_name}"
    entity_type = "kafka_topic"
    attributes = {
        "name" = "Stations"
        "executive_owner" = "Will LaForest"
        "executive_contact" = "will@ride4ever.com"
    }
}
resource "confluent_business_metadata_binding" "stations_data_product_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    business_metadata_name = confluent_business_metadata.data_product_details.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.stations_encirched.topic_name}"
    entity_type = "kafka_topic"
    attributes = {
        "name" = "stations-enriched"
        "primary_owner" = "Zach Hamilton"
        "primary_contact" = "zach@ride4ever.com"
        "responsible_team" = "Station Ops Team"
        "domain" = "Stations"
    }
}
resource "confluent_business_metadata_binding" "stations_team_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    business_metadata_name = confluent_business_metadata.team_details.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.stations_encirched.topic_name}"
    entity_type = "kafka_topic"
    attributes = {
        "team" = "Station Opertions Team"
        "channel" = "#station-ops-oncall"
        "slack_alias" = "@station-ops-oncall"
    }
}
resource "confluent_business_metadata_binding" "customers_domain_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    business_metadata_name = confluent_business_metadata.domain_details.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.customers_enriched.topic_name}"
    entity_type = "kafka_topic"
    attributes = {
        "name" = "Customers"
        "executive_owner" = "Will LaForest"
        "executive_contact" = "will@ride4ever.com"
    }
}
resource "confluent_business_metadata_binding" "customers_data_product_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    business_metadata_name = confluent_business_metadata.data_product_details.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.customers_enriched.topic_name}"
    entity_type = "kafka_topic"
    attributes = {
        "name" = "customers-enriched"
        "primary_owner" = "Zach Hamilton"
        "primary_contact" = "zach@ride4ever.com"
        "responsible_team" = "Customer Ops Team"
        "domain" = "Customers"
    }
}
resource "confluent_business_metadata_binding" "customer_team_details" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    business_metadata_name = confluent_business_metadata.team_details.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.customers_enriched.topic_name}"
    entity_type = "kafka_topic"
    attributes = {
        "team" = "Customers Team"
        "channel" = "#customers-oncall"
        "slack_alias" = "@customers-oncall"
    }
}
# TAGS
# --------------------
resource "confluent_tag" "data_product" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    name = "DataProduct"
    description = "DataProduct identifies a formatted, cleaned, data set for re-use."
}
resource "confluent_tag_binding" "stations_data_product" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    tag_name = confluent_tag.data_product.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.stations_encirched.topic_name}"
    entity_type = "kafka_topic"

    depends_on = [ confluent_tag.data_product ]
}
resource "confluent_tag_binding" "customers_data_product" {
    schema_registry_cluster {
        id = confluent_schema_registry_cluster.main.id
    }
    rest_endpoint = confluent_schema_registry_cluster.main.rest_endpoint
    credentials {
        key = confluent_api_key.app_manager_sr.id
        secret = confluent_api_key.app_manager_sr.secret
    }
    tag_name = confluent_tag.data_product.name
    entity_name = "${confluent_schema_registry_cluster.main.id}:${confluent_kafka_cluster.main.id}:${confluent_kafka_topic.customers_enriched.topic_name}"
    entity_type = "kafka_topic"

    depends_on = [ confluent_tag.data_product ]
}

# PROPS FILE OUTPUT
# --------------------
resource "local_file" "client_properties" {
    filename = "../client.properties"
    content = <<-EOT
    # Kafka
    bootstrap.servers=${substr(confluent_kafka_cluster.main.bootstrap_endpoint,11,-1)}
    security.protocol=SASL_SSL
    sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="${confluent_api_key.clients_kafka.id}" password="${confluent_api_key.clients_kafka.secret}";
    ssl.endpoint.identification.algorithm=https
    sasl.mechanism=PLAIN

    # Schema Registry
    schema.registry.url=${confluent_schema_registry_cluster.main.rest_endpoint}
    schema.registry.basic.auth.user.info=${confluent_api_key.app_manager_sr.id}:${confluent_api_key.app_manager_sr.secret}
    basic.auth.credentials.source=USER_INFO
    EOT
}
resource "local_file" "telegraf_conf" {
    filename = "../influxdb/telegraf/telegraf.conf"
    content = templatefile("../influxdb/telegraf/telegraf.tmpl", {
        BOOTSTRAP_SERVERS = substr(confluent_kafka_cluster.main.bootstrap_endpoint,11,-1),
        KAFKA_API_KEY = confluent_api_key.clients_kafka.id,
        KAFKA_API_SECRET = confluent_api_key.clients_kafka.secret
    })
}