package code.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vijay on 6/8/2016.
 */
public class AccessJsonFile {
    public JSONObject jsonObject;

    private JSONParser parser = new JSONParser();
    private static String fileSeparatot = System.getProperty("file.separator");
    private static String projectNameDirectory = System.getProperty("user.dir");
    public static String testDataFilePath = projectNameDirectory + "/src/main/resources/kafkaChecks.json".replace("/", fileSeparatot);

    public AccessJsonFile() throws IOException, ParseException {
        this(testDataFilePath);
    }

    public AccessJsonFile(String filePath) throws IOException, ParseException {
        jsonObject = (JSONObject) parser.parse(new BufferedReader(new FileReader(filePath)));
    }

    public JSONObject getInnerJson(String keyValue) {
        return (JSONObject) jsonObject.get(keyValue);
    }

    public int getInnerJsonInt(JSONObject InnerJson, String keyInInnerJson) {
        return Integer.parseInt(InnerJson.get(keyInInnerJson).toString());
    }

    public String getInnerJsonString(JSONObject InnerJson, String keyInInnerJson) {
        return InnerJson.get(keyInInnerJson).toString();
    }

    public JSONObject getInnerJsonObject(JSONObject InnerJson, String keyInInnerJson) {
        return (JSONObject) InnerJson.get(keyInInnerJson);
    }

    public ArrayList getInnerJsonArray(JSONObject InnerJson, String keyInInnerJson) {
        return (JSONArray) InnerJson.get(keyInInnerJson);
    }

    public ArrayList getTraitsForEvent(String eventNameInJsonFile){
        return getInnerJsonArray(getInnerJson(eventNameInJsonFile),"traits");
    }

    public String getOperatorForEvent(String eventNameInJsonFile){
        return getInnerJsonString(getInnerJson(eventNameInJsonFile),"operator");
    }

    public int getCountForEvent(String eventNameInJsonFile){
        return getInnerJsonInt(getInnerJson(eventNameInJsonFile),"count");
    }

    public JSONObject getChecksAsMap(String eventNameInJsonFile){
        return getInnerJsonObject(getInnerJson(eventNameInJsonFile),"checks");
    }

    public ArrayList getCountArrayForChecks(String eventNameInJsonFile){
        return getInnerJsonArray(getInnerJson(eventNameInJsonFile),"count");
    }

    public ArrayList getOperatorArrayForChecks(String eventNameInJsonFile){
        return getInnerJsonArray(getInnerJson(eventNameInJsonFile),"operator");
    }

}
