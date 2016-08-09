package code.kafkaevents; /**
 * Created by Vijay on 6/1/2016.
 */

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import scala.util.parsing.combinator.testing.Str;

import java.util.ArrayList;

public class ConsumerThread implements Runnable {
    private KafkaStream m_stream;
    private int m_threadNumber;
    static ArrayList<JSONObject> jsonResponses = new ArrayList<JSONObject>();
    Logger log = Logger.getLogger("ConsumerThread");

    public ConsumerThread(KafkaStream a_stream, int a_threadNumber) {
        m_threadNumber = a_threadNumber;
        m_stream = a_stream;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()) {
//            System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()));
            String kafkaEachMessage = new String(it.next().message());
            log.info(kafkaEachMessage);
            System.out.println(kafkaEachMessage);
            jsonResponses.add(new JSONObject(kafkaEachMessage));
        }
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}