<div align="center" padding=25px>
    <img src="images/confluent.png" width=50% height=50%>
</div>

# <div align="center">Real-time Data Mesh</div>

Introduction

## Prerequisites

**Confluent**
* Confluent Cloud Account
* Terraform (in order to build everything in Confluent Cloud)
* Docker (in order to run the Kafka Producers)

## Getting started

Since you'll need some secrets through this walkthrough, the first thing you should do is create a file for your secrets. This repo will ignore the file `env.sh`, so using that is a safe bet. Start by cloning the repo, then creating the file with the following command. 

```bash 
git clone https://github.com/zacharydhamilton/realtime-data-mesh && cd realtime-data-mesh
```

```bash
echo "# Confluent Cloud\nexport CONFLUENT_CLOUD_API_KEY="key"\nexport CONFLUENT_CLOUD_API_SECRET="secret"\n" > env.sh
```

With the secrets file created, go to Confluent Cloud and create Cloud API Keys (guide [here](https://docs.confluent.io/cloud/current/access-management/authenticate/api-keys/api-keys.html#cloud-cloud-api-keys)) and paste the values into the secrets file for the key and secret respectively.

```bash
source env.sh
```

With all the secrets available to the console, you can switch to the Terraform directory where you'll build the necessary Confluent Cloud resources. Follow these next few steps to create everything, and then wait until it's done before moving on. 

```bash
cd terraform && terraform init
```

```bash
terraform plan
```

*When prompted, approve the plan by entering "yes", or provide the "-auto-approve" flag to the apply command.*

```bash
terraform apply 
```

Wait for Terraform to finish creating all the resources, then navigate back to the base directory. 

```bash
cd ..
```

## Station and Customer data products

#### Create the Ksql topologies

The `stations-enriched` and `customers-enriched` data products have topics but don't have any way of having data generated at the moment. In order for this data to be generated, you'll need to create the Ksql statements that will process the raw data and output the enriched data. To create the statements, you'll need to copy-paste the statements from the provided SQL files into the KsqlDB editor in Confluent Cloud. 

1. Starting with the Customers data, open the file `customers-enrichment.sql` and copy the entire contents of the file (you'll be pasting it in the editor like this). 
1. Navigate to the KsqlDB editor in the Confluent Cloud console and paste the contents of the file into it. 
1. There is no data yet in the topics, so latest/earliest offset isn't much of an issue. Leave it as latest and execute the statments. 

After a few seconds, everything should be created if everything worked correctly. Now, let's redo the process for the Stations data.

1. Open the file `stations-enrichment.sql` and copy the entire contents.
1. Navigate back to the KsqlDB editor in Confluent Cloud and paste the contents. 
1. Like before, leave the `auto.offset.reset` to "latest" and execute the statements. 

Like before, give it a second to execute all the statements before moving on.

#### Produce some data

With the topologies set up to create your first two data products, it's now time to produce the data. Use the following steps to launch the build and launch the services with Docker. 

1. Before launching the services defined in `docker-compose.yaml`, build them.
    ```bash
    docker compose build
    ```
    *This might take a few minutes depending on your internet speed and computer.*

1. With the images built, you can now launch the services.
    ```bash
    docker compose up -d
    ```

1. (Optional) Data should now be being produced to Confluent Cloud in the background. To monitor the services for any issues, tail their logs. 
    ```bash
    docker compose logs -f
    ```
    *You can Crtl+C at any time to interrupt and stop tailing the logs.*

With the data being produced, you can open the Confluent Cloud console and begin to look at the data. With these two data products available, you have now completed the "setup" part of this example. The concept of this exercise is to imagine creating a new data product from existing onces, and these are the existing data products. 

## Inventory Redistribution data product

Now, imagine that all the previous steps you went through to set things up to this point were already existing and that these following steps your starting point. You will use the existing data products in order to create a new one based on a request from the business. 

##### Create the Ksql topology

In order to create the new data product, you'll need to use Ksql in order to create it. Thankfully, doing so will be fairly simple as the statements are prepared for you. As you did previously, follow these steps to create the Ksql statements.

1. Open the final SQL file `inventory-redistribution.sql` and copy its contents. 
1. Navigate to the KsqlDB editor in Confluent Cloud and paste the contents. 
1. This time set the `auto.offset.reset` to "earliest" and execute the statements. 

Wait for everything to execute before moving on. 

#### Adding Business Metadata and Tags

Creating the Ksql topology and having data that meets the business expectations for your new data product doesn't mean you have finished creating your data product. As defined by the business' Central Governance team, a data product needs Domain details, Team details, and Data Product details. These three pieces of Business Metadata capture information about the ownership, responsibility, contact information, and more about the data product. 

1. Find your new topic in the topics menu. It should be named `inventory.redistribution`.
1. On the right-hand side of the UI, you should see Business Metadata and Tags. Click "Add business metadata", select "Team details", and fill in the values with the following.
    | **Metadata** | **Value**              |
    |--------------|------------------------|
    | team         | Bicycle Inventory Team |
    | channel      | #bike-inv-oncall       |
    | slack_alias  | @bike-inv-oncall       |
1. Add more Business Metadata, this time selecting "Domain details" and adding the following information.
    | **Metadata**      | **Value**          |
    |-------------------|--------------------|
    | name              | Inventory          |
    | executive_owner   | Will LaForest      |
    | executive_contact | will@ride4ever.com |
1. Add the final Business Metadata, choosing "Data Product details" and adding the following information.
    | **Metadata**     | **Value**                |
    |------------------|--------------------------|
    | name             | inventory-redistribution |
    | primary_owner    | David the Data Engineer  |
    | primary_contact  | david@ride4ever.com      |
    | responsible_team | Bicycle Inventory Team   |
    | domain           | Inventory                |
1. As the final step, click in "Add tags to this topic" in the "Tags" section above the Business Metadata, and select "DataProduct". 

With all your Business Metadata and Tags added to your new topic, you've successfully created your new data product. 

#### (Optional) Explore the new data product

With your new data product created, you can look into what was accomplished. The two ways you'll do this are with Stream Lineage and the message viewer. 

1. Navigate back to the topic `inventory.redistribution` if not there. 
1. Select the "Messages" tab and select the -1 offset for any partition of your choice by typing -1 in the "Jump to offset" search bar.
1. Expand the message, and you should see the new data product which contains a match between two Bike stations where the surplus inventory of one is great than or equal to the shortage inventory of the other. 
1. Select "Explore Stream Lineage" in the top right hand corner of the screen when you're done viewing messages. 
1. In this new view, you can see the consumption of the `stations.enriched` topic, the creation of new topics for high and low inventory, and the joining of them back together to perform the matching. 
