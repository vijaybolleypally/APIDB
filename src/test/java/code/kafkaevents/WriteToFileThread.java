package code.kafkaevents; /**
 * Created by Vijay on 6/1/2016.
 */

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class WriteToFileThread extends Thread {

    String finalData;
    public void run() {
        System.out.println("####################################################################################");
        Iterator itr = ConsumerThread.jsonResponses.iterator();
        while (itr.hasNext())
        {
            System.out.println(itr.next().toString());
            finalData = finalData+ System.getProperty("line.separator") + itr.next().toString();
        }

        try {
            FileUtils.writeStringToFile(new File("test.txt"), finalData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}