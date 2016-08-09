package code.tests;

import code.ConfigMe;
import code.utils.AccessJsonFile;
import code.utils.AssertionUtil;
import code.utils.MyException;
import code.utils.MyStringUtil;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Vijay on 6/6/2016.
 */
@Listeners({ org.uncommons.reportng.HTMLReporter.class, org.uncommons.reportng.JUnitXMLReporter.class })
public class KafkaTests {

    private static ArrayList<String> actualResponsesFromFileToArrayList;
    private static AccessJsonFile dataFeedsFromJsonFile;

    @BeforeClass
    public static void loadkafkaResponses() throws IOException, ParseException {
        File logFile = new File(ConfigMe.logFilePath);
        String allResponseInString = FileUtils.readFileToString(logFile);
        actualResponsesFromFileToArrayList = new ArrayList<String>(Arrays.asList(allResponseInString.split(System.getProperty("line.separator"))));
        dataFeedsFromJsonFile = new AccessJsonFile(ConfigMe.kafkaTestFeed);
    }

    @DataProvider(name = "feedKafkaTest")
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

    @Test(dataProvider = "feedKafkaTest")
    public void kafkaTests001(String eventName) throws MyException {
        System.out.println("Running Tests For Event :" + eventName);
        ArrayList<Integer> countsFromJson = dataFeedsFromJsonFile.getCountArrayForChecks(eventName);
        ArrayList<String> operatorsFromJson = dataFeedsFromJsonFile.getOperatorArrayForChecks(eventName);
        JSONObject checksFromJon = dataFeedsFromJsonFile.getChecksAsMap(eventName);

        for (int i = 1; i <= checksFromJon.size(); i++) {
            String checkName = "check" + i;
            System.out.println("Checking :" + checkName);
            //Json Filters
            ArrayList<String> listOfFiltersFromJson = dataFeedsFromJsonFile.getInnerJsonArray(checksFromJon, checkName);
            listOfFiltersFromJson.add("\"event\":\"" + eventName + "\"");
            //Global ConfigMe Filters
            ArrayList<String> globalFiltersInEachCheck = new ArrayList<String>(Arrays.asList(ConfigMe.globalFilters));

            //Final list of filters
            ArrayList<String> finalFilters = new ArrayList<String>();
            finalFilters.addAll(listOfFiltersFromJson);
            finalFilters.addAll(globalFiltersInEachCheck);

            int expectedCountFromJson = Integer.parseInt(String.valueOf(countsFromJson.get(i - 1)));
            String operatorFromJson = operatorsFromJson.get(i - 1);
            System.out.println("finalFilters"+ finalFilters);
            int actualCountFromFile = MyStringUtil.getMatchedResponses(actualResponsesFromFileToArrayList, finalFilters).size();
            if (!(AssertionUtil.myAssertion(expectedCountFromJson, operatorFromJson, actualCountFromFile))) {
                throw new MyException(AssertionUtil.getErrorMessage(expectedCountFromJson, operatorFromJson, actualCountFromFile));
            }
        }
    }
}
