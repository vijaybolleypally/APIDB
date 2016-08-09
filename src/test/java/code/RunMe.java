package code;

import code.kafkaevents.ConsumerGroup;

/**
 * Created by Vijay on 6/1/2016.
 */

public class RunMe {

    public static void main(String[] args) {

        //Config Properties
        String zooKeeper = ConfigMe.zookeeperConnect;
        String topic = ConfigMe.topicName;

        String groupId = ConfigMe.aGroupId;//Keep it unchanged to track only latest events new groupId results in getting all events
        int numberOfThreads = Integer.parseInt("10");

        ConsumerGroup executors = new ConsumerGroup(zooKeeper, groupId, topic);
        executors.run(numberOfThreads);

//        Runtime.getRuntime().addShutdownHook(new code.kafkaevents.WriteToFileThread());
    }
}
