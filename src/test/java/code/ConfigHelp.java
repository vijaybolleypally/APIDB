package code;


/**
 * Created by Vijay on 6/3/2016.
 */
public class ConfigHelp {
    //###################################################################################################
    //KAFKA Configurations
    //1-> Kafka Consumer Configuration

    public static String zookeeperConnect = "54.175.27.6:2181";//Prod : "172.25.5.141:2181", QA :"54.175.27.6:2181"
    public static String aGroupId = "QA-topic_consumer";//Any random text
    public static String topicName = "Phenom_Track_YODLA0048_TOPIC"; //Phenom_Track_%clientToken/phenomRefnum%_TOPIC general standards

    //2->Run RunMe
    //3-> Do User Actions [Capture UID of your browser]
    //4->Create a file with logs
    public static String logFilePath = getFilePathByName("kafkaLogs.txt");

    //5-> Fill kafkaChecks.json with your custom values
    public static String kafkaTestFeed = getFilePathByName("kafkaLogs.txt");
    //6 -> Run code.tests.KafkaTests if any fail look if all pass celebrate it :-)

    //Global Checks for Kafka and DB all checks are written to support single UID for test
    //Variables
    public static String Refnum = "AMNCA0089";
    public static String clientToken = Refnum;
    public static String phenomRefnum = Refnum;
    public static String UID_Value = "155d904858e12e-100200-27c1-155d904858f19e";
    public static String deviceName = "mobile";//mobile
    public static String ipAddress = "182.72.240.254";
    public static String browser = "Chrome";
    public static String pt_browser = browser;
    public static int browser_version = 48;
    public static String os = "Android";
    public static String pt_platform = os;
    public static String pt_lib = "web";
    public static int screen_height = 640;
    public static int screen_width = 360;
    public static String uas = "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36";

    //Variable Strings
    public static String globalFilterUID = "\"uid\":\"" + UID_Value + "\"";
    public static String globalFilterRefnum = "\"trait2\":\"" + Refnum + "\"";
    public static String globalFilterDevice = "\"trait65\":\""+deviceName+"\"";
    public static String globalFilterclientToken = "\"clientToken\":\"" + clientToken + "\"";
    public static String globalFilteripAddress = "\"ipAddress\":\"" + ipAddress + "\"";
    public static String globalFilterphenomRefnum = "\"phenomRefnum\":\"" + phenomRefnum + "\"";
    public static String globalFilterbrowser = "\"browser\":\"" + browser + "\"";
    public static String globalFilterbrowser_version = "\"browser_version\":"+browser_version;
    public static String globalFilterOs= "\"os\":\"" + os + "\"";
    public static String globalFilterpt_browser = "\"pt_browser\":\"" + pt_browser + "\"";
    public static String globalFilterpt_lib = "\"pt_lib\":\"" + pt_lib + "\"";
    public static String globalFilterpt_platform = "\"pt_platform\":\"" + pt_platform + "\"";
    public static String globalFilterscreen_height = "\"screen_height\":" +screen_height;
    public static String globalFilterscreen_width = "\"screen_width\":" +screen_width;
    public static String globalFiltersUAS = "\"uas\":\"" + uas + "\"";
    public static String globalFilterpt_session = "\"pt_session\":\"";
    public static String currentUrl = "\"current_url\":\"";
    public static String properties = "\"properties\":{\"";
    public static String globalFilterpt_timestamp = "\"pt_timestamp\":\"";
    public static String pt_page = "\"pt_page\":\"";
    public static String globalFilterLang = "\"trait79\":\"us_en_US_\"";
    public static String[] globalFilters ={globalFilterUID,
            globalFilterRefnum,
            globalFilterDevice,
            globalFilterclientToken,
            globalFilteripAddress,
            globalFilterphenomRefnum,
            globalFilterbrowser,
            globalFilterbrowser_version,
            globalFilterOs,
            globalFilterpt_browser,
            globalFilterpt_lib,
            globalFilterpt_platform,
            globalFilterscreen_height,
            globalFilterscreen_width,
            currentUrl,
            properties,
            globalFilterpt_timestamp,
            pt_page,
            globalFilterpt_session,
            globalFiltersUAS
    };

    //####################################################################################################
    //1-> MongoDB Configurations
    public static String serverAddress = "54.174.245.244";
    public static int port = 27017;
    public static String CollectionName = "YODLA0048";
    public static String UID_FiledName = "uid";

    //2->Select flat or non-flat DB Type
    //non-flat
//    public static String dbName = "usertables_qa";
//    public static String dbUserName = "phegloqa";
//    public static String dbPassword = "goodDevelopers@1";

    //flat
    public static String dbName = "usertables_qa_flat";
    public static String dbUserName = "phegloqaflat";
    public static String dbPassword = "goodDevelopers@1";

    //3->Fill mongoChecks.json with your custom values
    public static String mongoTestFeed = getFilePathByName("mongoChecks.json");

    //4 -> Run code.tests.MongoTests if any fail look if all pass celebrate it again :-)

    private static String getFilePathByName(String fileName) {
        return System.getProperty("user.dir") + "/src/main/resources/" + fileName.replace("/", System.getProperty("file.separator"));
    }
}
