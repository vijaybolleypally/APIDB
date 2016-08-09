package code;


/**
 * Created by Vijay on 6/3/2016.
 */
public class ConfigMe {

    //KAFKA Configurations
    //"172.25.5.141:2181"; //"54.175.27.6:2181";
    public static String zookeeperConnect = "54.175.27.6:2181";
    public static String aGroupId = "QA-topic_test_12";
    //"Phenom_Track_YODLA0048_TOPIC"//"Phenom_Track_ORTHA0029_TOPIC";//"Phenom_Track_ORTHA003I_TOPIC";//"Phenom_Track_SUSQA004Y_TOPIC";//"Phenom_Track_HEALA0026_TOPIC";//"Phenom_Track_HERSA005H_TOPIC";
//    public static String topicName = "Phenom_Track_ORTHA0029_TOPIC";

    //Files address
    public static String logFilePath = getFilePathByName("kafkaLogFile");
    public static String kafkaTestFeed = getFilePathByName("templates/T11/Viliving/Vi_Desktop.json");

    //Variables
    public static String Refnum = "VILIA005J";
    public static String topicName = "Phenom_Track_"+Refnum+"_TOPIC";
    public static String clientToken = Refnum;
    public static String phenomRefnum = Refnum;
    public static String UID_Value = "15654eea0592d1-100200-0934-15654eea05a60f";
    public static String deviceName = "desktop";
    public static String ipAddress = "182.76.40.162";
    public static String browser = "Chrome";
    public static String pt_browser = browser;
    public static int browser_version = 52;
    public static String os = "Windows";
    public static String pt_platform = os;
    public static String pt_lib = "web";
    public static int screen_height = 768;
    public static int screen_width = 1366;
    public static String uas = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36";
    public static String pt_session = "";
    public static String initialReferrer = "\"initialReferral\":\"direct\"";


    //Variable Strings
    public static String globalFilterUID = "\"uid\":\"" + UID_Value + "\"";
    public static String globalFilterRefnum = "\"trait2\":\"" + Refnum + "\"";
    public static String globalFilterDevice = "\"trait65\":\""+deviceName+"\"";
    public static String globalFilterclientToken = "\"clientToken\":\"" + clientToken + "\"";
    public static String globalFilteripAddress = "\"ipAddress\":\"" + ipAddress + "\"";
//    public static String globalFilteripAddress = "\"ipAddress\":\"";
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
//    public static String globalFilterLang = "\"trait79\":\"us_en_US_\"";

    public static String[] globalFilters ={globalFilterUID,
            globalFilterRefnum,
//            globalFilterDevice,
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
            globalFiltersUAS,
//            globalFilterLang,
            initialReferrer
    };

    //MongoDB Configurations
    public static String serverAddress = "54.174.245.244";
    public static int port = 27017;
    public static String CollectionName = "YODLA0048";
    public static String UID_FiledName = "uid";

    //non-flat
    //    public static String dbName = "usertables_qa";
    //    public static String dbUserName = "phegloqa";
    //    public static String dbPassword = "goodDevelopers@1";

    //flat
    public static String dbName = "usertables_qa_flat";
    public static String dbUserName = "phegloqaflat";
    public static String dbPassword = "goodDevelopers@1";

    public static String mongoTestFeed = getFilePathByName("mongoChecks.json");

    private static String getFilePathByName(String fileName) {
        return (System.getProperty("user.dir") + "/src/test/resources/" + fileName).replace("/", System.getProperty("file.separator"));
    }
}
