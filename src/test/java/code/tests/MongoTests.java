package code.tests;

import code.ConfigMe;
import code.mongodb.MongoCommunications;
import code.utils.AccessJsonFile;
import code.utils.AssertionUtil;
import code.utils.MyException;
import code.utils.MyStringUtil;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Vijay on 6/6/2016.
 */
@Listeners({ org.uncommons.reportng.HTMLReporter.class, org.uncommons.reportng.JUnitXMLReporter.class })
public class MongoTests {

    public static DBCollection resultCollection;
    public static String documnetsOfGivenUID;
    public static ArrayList<DBObject> dbRecordsWithGivenUID;
    public static MongoCommunications mongoCommunications;
    private static AccessJsonFile dataFeedsFromJsonFile;

    @BeforeClass
    public static void connectToDB() throws IOException, ParseException {
        mongoCommunications = new MongoCommunications(ConfigMe.serverAddress, ConfigMe.port, ConfigMe.dbName, ConfigMe.dbUserName, ConfigMe.dbPassword, ConfigMe.CollectionName);
        resultCollection = mongoCommunications.collection;
        documnetsOfGivenUID = mongoCommunications.getMatchedRecordsAsString(resultCollection, ConfigMe.UID_FiledName, ConfigMe.UID_Value);
        dbRecordsWithGivenUID = mongoCommunications.getMatchedRecords(resultCollection, ConfigMe.UID_FiledName, ConfigMe.UID_Value);
        dataFeedsFromJsonFile = new AccessJsonFile(ConfigMe.mongoTestFeed);
    }

    @DataProvider(name = "feedDBTest")
    public Object[][] getEventsList() {
        Set<Object> keys = dataFeedsFromJsonFile.jsonObject.keySet();
        String[] eventsInArray = keys.toArray(new String[keys.size()]);
        String[][] listOfEvents = new String[keys.size()][1];
        for (int i = 0; i < keys.size(); i++) {
            for (int y = 0; y < 1; y++) {
                listOfEvents[i][y] = eventsInArray[i];
            }
        }
        return listOfEvents;
    }

    @Test(dataProvider = "feedDBTest")
    public void dbTests001(String eventName) throws MyException {
        System.out.println("Running Tests For Event:" + eventName);
        JSONObject checksFromJon = dataFeedsFromJsonFile.getChecksAsMap(eventName);
        ArrayList<Integer> countsFromJson = dataFeedsFromJsonFile.getCountArrayForChecks(eventName);
        ArrayList<String> operatorsFromJson = dataFeedsFromJsonFile.getOperatorArrayForChecks(eventName);
        ArrayList<DBObject> dbRecordsWithGivenEvent = mongoCommunications.getRecordsWithGivenKey(dbRecordsWithGivenUID, eventName);
        ArrayList<String> eventValuesFromDB = mongoCommunications.getValueOfGivenKey(dbRecordsWithGivenEvent, eventName);
        for (int i = 1; i <= checksFromJon.size(); i++) {
            String checkName = "check" + i;
            System.out.println("Checking " + checkName);
            ArrayList<String> listOfFiltersInEachCheck = dataFeedsFromJsonFile.getInnerJsonArray(checksFromJon, checkName);
            int expectedCountFromJson = Integer.parseInt(String.valueOf(countsFromJson.get(i - 1)));
            String operatorFromJson = operatorsFromJson.get(i - 1);
            int actualCountFromDB = MyStringUtil.getMatchedResponses(eventValuesFromDB, listOfFiltersInEachCheck).size();
            if (!(AssertionUtil.myAssertion(expectedCountFromJson, operatorFromJson, actualCountFromDB))) {
                throw new MyException(AssertionUtil.getErrorMessage(expectedCountFromJson, operatorFromJson, actualCountFromDB));
            }
        }
    }
}
