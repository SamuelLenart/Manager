package sk.kosickaakademia.lenart.task.mongodb;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sk.kosickaakademia.lenart.task.collection.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Collection implements Mongo {
    private static final MongoClient mongoClient = new MongoClient();
    private static MongoDatabase database;
    private static Document docs;
    private static MongoCollection<Document> collection;
    private static Date date = new Date();

    @Override
    public boolean insertTask(String title, String task, int priority, double price) {
        if(title==null || title.equals("") || task==null || task.equals("") || priority<1 || priority>3 )
            return false;
        if(price==0){
            database = mongoClient.getDatabase("TaskDB");
            collection = database.getCollection("tasks");
            docs = new Document("task", task);
            docs.append("date", date);
            docs.append("title", title);
            docs.append("task", task);
            docs.append("priority", priority);
            docs.append("done", false);
            collection.insertOne(docs);
            return true;
        }else {
            database = mongoClient.getDatabase("TaskDB");
            collection = database.getCollection("tasks");
            docs = new Document("task", task);
            docs.append("date", date);
            docs.append("title", title);
            docs.append("task", task);
            docs.append("priority", priority);
            docs.append("price", price);
            docs.append("done", false);
            collection.insertOne(docs);
            return true;
        }
    }

    @Override
    public void setTaskDone(int id) {

    }

    @Override
    public List<Task> getAllTasks() {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("tasks");
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            System.out.println(document);
        }
        return null;
    }

    public List<Task> getAllTasks(boolean done){
        List<Task> list = new ArrayList<>();


        return list;
    }

    @Override
    public List<Task> getAllTasksByPriority(int priority) {
        return null;
    }

    @Override
    public List<Task> getAllTasksByName(String name) {
        return null;
    }

    @Override
    public void DeleteDoneTasks() {

    }
}
