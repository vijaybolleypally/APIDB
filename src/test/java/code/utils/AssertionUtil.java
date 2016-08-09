package code.utils;


import org.testng.Assert;

import java.util.ArrayList;

/**
 * Created by Vijay on 6/8/2016.
 */
public class AssertionUtil {


    public static String getErrorMessage(int expected, String operation, int actual) {
        return String.format("Expected :%d to be %s the %d", expected, operation, actual);
    }

    public static boolean myAssertion(int expected, String operator, int actual) throws MyException {

        boolean isTrue =true;
        if (operator.equals(">=")) {
            throw new MyException("operator >= is not supported");
        } else if (operator.equals("<=")) {
            if (!(expected <= actual)) {
                isTrue=false;
            }
        } else if (operator.equals(">")) {
            throw new MyException("operator > is not supported");
        } else if (operator.equals("<")) {
            if (!(expected < actual)) {
                isTrue=false;
            }
        } else if (operator.equals("==")) {
            if (!(expected == actual)) {
                isTrue=false;
            }
        }
        return isTrue;
    }
}
