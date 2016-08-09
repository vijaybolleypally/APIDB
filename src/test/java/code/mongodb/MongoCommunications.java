package code.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vijay on 6/3/2016.
 */
public class MongoCommunications {
    public MongoClient mongoClient;
    public DB db;
    public DBCollection collection;

    public static String getMatchedRecordsAsString(DBCollection collectionName, String key, String value) {
        String resultString = null;
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put(key, value);
        DBCursor cursor = collectionName.find(whereQuery);
        while (cursor.hasNext()) {
            String match = cursor.next().toString();
            resultString = resultString + match;
        }
//        System.out.println("resultString:" + resultString);
        return resultString;
    }

    public MongoCommunications(String serverAddress, int port, String dbName, String dbUserName, String dbPassword, String dbCollectionName) throws UnknownHostException {
        this.mongoClient = new MongoClient(new ServerAddress(serverAddress, port));
        this.db = mongoClient.getDB(dbName);
        this.db.authenticate(dbUserName, dbPassword.toCharArray());
        this.collection = db.getCollection(dbCollectionName);
    }

    public static ArrayList<DBObject> getMatchedRecords(DBCollection collectionName, String key, String value) {
        ArrayList<DBObject> resultDBObjs = new ArrayList<DBObject>();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put(key, value);
        DBCursor cursor = collectionName.find(whereQuery);
        while (cursor.hasNext()) {
            DBObject match = cursor.next();
            resultDBObjs.add(match);
        }
        return resultDBObjs;
    }

    public static ArrayList<DBObject> getEachRecordWith(ArrayList<DBObject> allRecords, String key, String value) {
        ArrayList<DBObject> matchedRecords = new ArrayList<DBObject>();

        Iterator<DBObject> crunchifyIterator = allRecords.iterator();
        while (crunchifyIterator.hasNext()) {
            DBObject match = crunchifyIterator.next();
            if (match.containsKey(key)&&match.get(key).toString().contains(value)) {
                matchedRecords.add(match);
            }
        }
        return matchedRecords;
    }

    public static ArrayList<DBObject> getRecordsWithGivenKey(ArrayList<DBObject> allRecords, String key) {
        ArrayList<DBObject> matchedRecords = new ArrayList<DBObject>();

        Iterator<DBObject> crunchifyIterator = allRecords.iterator();
        while (crunchifyIterator.hasNext()) {
            DBObject match = crunchifyIterator.next();
            if (match.containsKey(key)) {
                matchedRecords.add(match);
            }
        }
        return matchedRecords;
    }

    public static ArrayList<String> getValueOfGivenKey(ArrayList<DBObject> allRecords, String key) {
        ArrayList<String> valuesList = new ArrayList<String>();

        Iterator<DBObject> crunchifyIterator = allRecords.iterator();
        while (crunchifyIterator.hasNext()) {
            DBObject match = crunchifyIterator.next();
            valuesList.add(match.get(key).toString());
        }
        return valuesList;
    }
}
