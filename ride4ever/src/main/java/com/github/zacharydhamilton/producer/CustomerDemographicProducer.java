package com.github.zacharydhamilton.producer;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import com.github.zacharydhamilton.objects.CustomerDemographics;
import com.github.zacharydhamilton.objects.CustomerInfo;
import com.github.zacharydhamilton.objects.CustomerDemographics.EducationLevel;
import com.github.zacharydhamilton.objects.CustomerDemographics.Gender;
import com.github.zacharydhamilton.objects.CustomerDemographics.MaritalStatus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;

public class CustomerDemographicProducer {
    static String configType = (System.getenv("CONFIG_TYPE") != null) ? System.getenv("CONFIG_TYPE") : "FILE"; 
    static String configFile = (System.getenv("CONFIG_FILE") != null) ? System.getenv("CONFIG_FILE") : "../client.properties";
    static String topic = "customers.demographics";
    static String clientId = "customer-demographic-producer";

    public static void main(String[] args) throws IOException, NullPointerException {
        KafkaProducer<String, CustomerDemographics> producer = createProducer();
        ArrayList<CustomerDemographics> demographics = new ArrayList<>();
        try (Reader reader = new InputStreamReader(CustomerDemographicProducer.class.getClassLoader().getResourceAsStream("demographics.json"))) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            Gson gson = new Gson();
            for (JsonElement jsonElement : jsonArray) {
                CustomerDemographics demographic = gson.fromJson(jsonElement.getAsJsonObject(), CustomerDemographics.class);
                demographics.add(demographic);
                ProducerRecord<String, CustomerDemographics> record = new ProducerRecord<String, CustomerDemographics>(topic, demographic.getCustomerId(), demographic);
                producer.send(record);
            }
            producer.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        while (true) {
            try {
                Random random = new Random();
                int randomIndex = random.nextInt(demographics.size());
                CustomerDemographics demographic = demographics.get(randomIndex);
                demographic.setAge(getRandomAge());
                demographic.setGender(getRandomGender());
                demographic.setIncome(getRandomIncome());
                demographic.setEducationLevel(getRandomEducationLevel());
                demographic.setMaritalStatus(getRandomMaritalStatus());
                demographics.set(randomIndex, demographic);
                ProducerRecord<String, CustomerDemographics> record = new ProducerRecord<String, CustomerDemographics>(topic, demographic.getCustomerId(), demographic);
                producer.send(record);
                producer.flush();
                Thread.sleep(5*1000);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static KafkaProducer<String, CustomerDemographics> createProducer() throws IOException {
        Properties props = new Properties();
        if (configType.equals("FILE")) {
            addPropsFromFile(props, configFile);
        } else {
            preInitChecks();
            props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, System.getenv("BOOTSTRAP_SERVERS"));
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
            props.put(SaslConfigs.SASL_JAAS_CONFIG, System.getenv("SASL_JAAS_CONFIG"));
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        }
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSchemaSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5); 
        return new KafkaProducer<String, CustomerDemographics>(props);
    }

    public static ArrayList<CustomerDemographics> createRandomDemographics() {
        Gson gson = new Gson();
        ArrayList<CustomerDemographics> demographics = new ArrayList<>();
        try (Reader reader = new InputStreamReader(CustomerDemographicProducer.class.getClassLoader().getResourceAsStream("customers.json"))) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                CustomerInfo customer = gson.fromJson(jsonElement.getAsJsonObject(), CustomerInfo.class);
                CustomerDemographics demographic = new CustomerDemographics();
                demographic.setCustomerId(customer.getCustomerId());
                demographic.setAge(getRandomAge());
                demographic.setGender(getRandomGender());
                demographic.setIncome(getRandomIncome());
                demographic.setEducationLevel(getRandomEducationLevel());
                demographic.setMaritalStatus(getRandomMaritalStatus());
                demographics.add(demographic);
            }
            try (FileWriter writer = new FileWriter("src/main/resources/demographics.json")) {
                gson.toJson(demographics, writer);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return demographics;
    }

    public static int getRandomAge() {
        Random random = new Random();
        return random.nextInt(18, 65+1);
    }

    public static Gender getRandomGender() {
        Gender[] genders = Gender.values();
        Random random = new Random();
        int randomIndex = random.nextInt(genders.length);
        Gender randomGender = genders[randomIndex];
        return randomGender;
    }

    public static int getRandomIncome() {
        Random random = new Random();
        return random.nextInt(60000, 300000);
    }

    public static EducationLevel getRandomEducationLevel() {
        EducationLevel[] educationLevels = EducationLevel.values();
        Random random = new Random();
        int randomIndex = random.nextInt(educationLevels.length);
        EducationLevel randomEducation = educationLevels[randomIndex];
        return randomEducation;
    }

    public static MaritalStatus getRandomMaritalStatus() {
        MaritalStatus[] maritalStatuses = MaritalStatus.values();
        Random random = new Random();
        int randomIndex = random.nextInt(maritalStatuses.length);
        MaritalStatus randomMaritalStatus = maritalStatuses[randomIndex];
        return randomMaritalStatus;
    }

    /**
     * Check for the necessary configurations to initialize the client. If any are missing, fails. 
     * 
     * @throws ConfigException
     */
    private static void preInitChecks() throws ConfigException {
        ArrayList<String> requiredProps = new ArrayList<String>(Arrays.asList("BOOTSTRAP_SERVERS", "SASL_JAAS_CONFIG", "METADATA_FILE"));
        ArrayList<String> missingProps = new ArrayList<String>();
        for (String prop : requiredProps) {
            if (System.getenv(prop).equals(null)) {
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
