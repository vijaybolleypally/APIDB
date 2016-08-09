package code.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vijay on 6/1/2016.
 */
public class MyStringUtil {

    public static int getSubStringCount(String actS, String subS) {
        int count = actS.split(subS).length - 1;
        System.out.println(count);
        return count;
    }

    public static ArrayList getMatchedResponses(String[] allResponses, String filter) {
        ArrayList<String> matchedResp = new ArrayList<String>();
        for (String s : allResponses) {
            if (s.contains(filter)) {
                matchedResp.add(s);
            }
        }
        return matchedResp;
    }

    public static ArrayList getMatchedResponses(String[] allResponses, String[] filters) {
        ArrayList<String> matchedResp = new ArrayList<String>();
        for (String eachResponse : allResponses) {
            for (int i = 0; i < filters.length; i++) {
                String currentFilter = filters[i];
                if (eachResponse.contains(currentFilter)) {
                    if (i == filters.length - 1) {
                        matchedResp.add(eachResponse);
                    }
                } else {
                    break;
                }
            }

        }
        return matchedResp;
    }

    public static ArrayList getMatchedResponses(ArrayList<String> allResponses, ArrayList<String> filters) {
        ArrayList<String> matchedResp = new ArrayList<String>();
        for (String eachResponse : allResponses) {
            for (int i = 0; i < filters.size(); i++) {
                String currentFilter = filters.get(i);
                if (eachResponse.contains(currentFilter)) {
                    if (i == filters.size() - 1) {
                        matchedResp.add(eachResponse);
                    }
                } else {
                    break;
                }
            }
        }
        //System.out.println("matchedResp" + matchedResp);
        return matchedResp;
    }
}
