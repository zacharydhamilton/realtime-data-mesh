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
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import com.github.zacharydhamilton.objects.CustomerInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer;

public class CustomerInfoProducer {
    static String configType = (System.getenv("CONFIG_TYPE") != null) ? System.getenv("CONFIG_TYPE") : "FILE"; 
    static String configFile = (System.getenv("CONFIG_FILE") != null) ? System.getenv("CONFIG_FILE") : "../client.properties";
    static String topic = "customers.info";
    static String clientId = "customer-info-producer";

    public static void main(String[] args) throws IOException {
        KafkaProducer<String, CustomerInfo> producer = createProducer();
        ArrayList<CustomerInfo> customers = new ArrayList<>();
        try (Reader reader = new InputStreamReader(CustomerInfoProducer.class.getClassLoader().getResourceAsStream("customers.json"))) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            Gson gson = new Gson();
            for (JsonElement jsonElement : jsonArray) {
                CustomerInfo customer = gson.fromJson(jsonElement.getAsJsonObject(), CustomerInfo.class);
                customers.add(customer);
                ProducerRecord<String, CustomerInfo> record = new ProducerRecord<String, CustomerInfo>(topic, customer.getCustomerId(), customer);
                producer.send(record);
            }
            producer.flush();
        }
        while (true) {
            try {
                Random random = new Random();
                int randomIndex = random.nextInt(customers.size());
                CustomerInfo customer = customers.get(randomIndex);
                String firstName = getRandomFirstName();
                String lastName = getRandomLastName();
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(getRandomEmail(firstName, lastName));
                customer.setPhoneNumber(getRandomPhoneNumber());
                customers.set(randomIndex, customer);
                ProducerRecord<String, CustomerInfo> record = new ProducerRecord<String, CustomerInfo>(topic, customer.getCustomerId(), customer);
                producer.send(record);
                producer.flush();
                Thread.sleep(5*1000);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static KafkaProducer<String, CustomerInfo> createProducer() throws IOException {
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
        return new KafkaProducer<String, CustomerInfo>(props);
    }

    public static String getRandomCustomerId() {
        UUID uuid = UUID.randomUUID();
        String longUUID = uuid.toString().replaceAll("-", "");
        String shortUUID = longUUID.substring(0, 8);
        return shortUUID;
    }

    public static String getRandomFirstName() {
        Random random = new Random();
        String firstName = getRandomFemaleName();
        if (random.nextDouble() <= 0.50) {
            firstName = getRandomMaleName();
        } else {
            firstName = getRandomFemaleName();
        }
        return firstName;
    }

    public static String getRandomMaleName() {
        List<String> maleNames = Arrays.asList(
            "James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Charles", "Thomas",
            "Christopher", "Daniel", "Matthew", "Donald", "Anthony", "Paul", "Mark", "George", "Steven", "Kenneth",
            "Andrew", "Brian", "Edward", "Ronald", "Joshua", "Kevin", "Eric", "Stephen", "Gary", "Frank",
            "Scott", "Larry", "Justin", "Brandon", "Raymond", "Gregory", "Samuel", "Patrick", "Jack", "Dennis",
            "Jerry", "Alexander", "Henry", "Walter", "Peter", "Tyler", "Aaron", "Jose", "Adam", "Harold",
            "Douglas", "Nathan", "Carl", "Arthur", "Albert", "Roger", "Ralph", "Joe", "Louis", "Billy",
            "Bruce", "Eugene", "Christian", "Gerald", "Sean", "Austin", "Lawrence", "Jesse", "Willie", "Ethan",
            "Roy", "Terry", "Jeremy", "Ryan", "Bryan", "Randall", "Howard", "Fred", "Wayne", "Harry",
            "Johnny", "Russell", "Phillip", "Victor", "Bobby", "Martin", "Keith", "Allen", "Joel", "Antonio",
            "Marvin", "Glenn", "Vincent", "Chris", "Stanley"
        );
        Random random = new Random();
        int randomIndex = random.nextInt(maleNames.size());
        String randomName = maleNames.get(randomIndex);
        return randomName;
    }

    public static String getRandomFemaleName() {
        List<String> femaleNames = Arrays.asList(
            "Mary", "Linda", "Patricia", "Barbara", "Elizabeth", "Jennifer", "Maria", "Nancy", "Margaret", "Karen",
            "Betty", "Helen", "Sandra", "Donna", "Carol", "Ruth", "Sharon", "Michelle", "Laura", "Sarah",
            "Kimberly", "Deborah", "Jessica", "Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Anna",
            "Rebecca", "Virginia", "Kathleen", "Pamela", "Dorothy", "Martha", "Carolyn", "Janet", "Catherine", "Frances",
            "Ann", "Joyce", "Diane", "Alice", "Jean", "Julie", "Heather", "Teresa", "Doris", "Gloria",
            "Evelyn", "Joan", "Cheryl", "Mildred", "Katherine", "Ashley", "Tammy", "Jane", "Sara", "Kelly",
            "Denise", "Nicole", "Judy", "Christine", "Kathy", "Theresa", "Beverly", "Denise", "Tammy", "Irene",
            "Jane", "Lori", "Rachel", "Marilyn", "Andrea", "Kathryn", "Louise", "Anne", "Jacqueline", "Wanda",
            "Bonnie", "Julia", "Ruby", "Tina", "Lois", "Phyllis", "Janice", "Nina", "Paula", "Diana",
            "Annie", "Lillian", "Emily", "Robin", "Peggy"
        );
        Random random = new Random();
        int randomIndex = random.nextInt(femaleNames.size());
        String randomName = femaleNames.get(randomIndex);
        return randomName;
    }

    public static String getRandomLastName() {
        List<String> lastNames = Arrays.asList(
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin",
            "Lee", "Perez", "Thompson", "White", "Harris",

            "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson",
            "Walker", "Young", "Allen", "King", "Wright",
            "Scott", "Torres", "Nguyen", "Hill", "Flores",
            "Green", "Adams", "Nelson", "Baker", "Hall",
            "Rivera", "Campbell", "Mitchell", "Carter", "Roberts",

            "Gomez", "Phillips", "Evans", "Turner", "Diaz",
            "Parker", "Cruz", "Edwards", "Collins", "Reyes",
            "Stewart", "Morris", "Morales", "Murphy", "Cook",
            "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper",
            "Peterson", "Bailey", "Reed", "Kelly", "Howard",

            "Ramos", "Kim", "Cox", "Ward", "Richardson",
            "Watson", "Brooks", "Chavez", "Wood", "James",
            "Bennett", "Gray", "Mendoza", "Ruiz", "Hughes",
            "Price", "Alvarez", "Castillo", "Sanders", "Patel",
            "Myers", "Long", "Ross", "Foster", "Jimenez",

            "Powell", "Jenkins", "Perry", "Russell", "Sullivan",
            "Bell", "Coleman", "Butler", "Henderson", "Barnes",
            "Gonzales", "Fisher", "Vasquez", "Simmons", "Romero",
            "Jordan", "Patterson", "Alexander", "Hamilton", "Graham",
            "Reynolds", "Griffin", "Wallace", "Moreno", "West",

            "Cole", "Hayes", "Bryant", "Herrera", "Gibson",
            "Ellis", "Tran", "Medina", "Aguilar", "Stevens",
            "Murray", "Ford", "Castro", "Marsh", "O'Connor",
            "Wells", "Todd", "Chen", "Kumar", "Webster",
            "Malone", "Hammond", "Flowers", "Cobb", "Moody",

            "Quinn", "Blake", "Maxwell", "Pope", "Floyd",
            "Osborne", "Paul", "Mccarthy", "Guerrero", "Lindsey",
            "Estrada", "Sandoval", "Gibbs", "Tyler", "Gross",
            "Fitzgerald", "Stokes", "Doyle", "Sherman", "Saunders",
            "Wise", "Colon", "Gill", "Alvarado", "Greer",

            "Padilla", "Simon", "Waters", "Nunez", "Ballard",
            "Schwartz", "Mcbride", "Houston", "Christensen", "Klein",
            "Pratt", "Briggs", "Parsons", "Mclaughlin", "Zimmerman",
            "French", "Buchanan", "Moran", "Copeland", "Roy",
            "Pittman", "Brady", "Mccormick", "Holloway", "Brock",

            "Poole", "Frank", "Logan", "Owen", "Bass",
            "Marsh", "Drake", "Wong", "Jefferson", "Park",
            "Morton", "Abbott", "Sparks", "Patrick", "Norton",
            "Huff", "Clayton", "Massey", "Lloyd", "Figueroa",
            "Carson", "Bowers", "Roberson", "Barton", "Tran"
        );
        Random random = new Random();
        int randomIndex = random.nextInt(lastNames.size());
        String randomName = lastNames.get(randomIndex);
        return randomName;
    }

    public static String getRandomEmail(String firstName, String lastName) {
        List<String> popularEmailDomains = Arrays.asList(
            "@gmail.com", "@yahoo.com", "@hotmail.com",
            "@outlook.com", "@aol.com", "@icloud.com",
            "@msn.com", "@comcast.net", "@sbcglobal.net", "@verizon.net"
        );
        Random random = new Random();
        int randomIndex = random.nextInt(popularEmailDomains.size());
        String randomDomain = popularEmailDomains.get(randomIndex);
        return firstName+"."+lastName+randomDomain;
    }

    public static String getRandomPhoneNumber() {
        // List of area codes for Hoboken, Jersey City, and NYC
        List<Integer> areaCodes = Arrays.asList(201, 551, 212, 718, 347, 646);
        Random rand = new Random();
        int randomAreaCode = areaCodes.get(rand.nextInt(areaCodes.size()));
        int threeDigits = rand.nextInt(900) + 100;
        int fourDigits = rand.nextInt(9000) + 1000;
        String phoneNumber = randomAreaCode + "-" + threeDigits + "-" + fourDigits;
        return phoneNumber;
    }

    public static ArrayList<CustomerInfo> createRandomCustomers(int numCustomers) {
        Gson gson = new Gson();
        ArrayList<CustomerInfo> customers = new ArrayList<>();
        for (int i=0; i<numCustomers; i++) {
            CustomerInfo customer = new CustomerInfo();
            customer.setCustomerId(getRandomCustomerId());
            String firstName = getRandomFirstName();
            String lastName = getRandomLastName();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(getRandomEmail(firstName, lastName));
            customer.setPhoneNumber(getRandomPhoneNumber());
            customers.add(customer);
        }
        try (FileWriter writer = new FileWriter("src/main/resources/customers.json")) {
            gson.toJson(customers, writer);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return customers;
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
