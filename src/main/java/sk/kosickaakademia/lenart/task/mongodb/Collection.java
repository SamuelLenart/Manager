package sk.kosickaakademia.lenart.task.mongodb;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    public void setTaskDone() {
        BasicDBObject query = new BasicDBObject();
        query.put("done", false);
        BasicDBObject doc = new BasicDBObject();
        doc.put("done", true);
        BasicDBObject updateObj = new BasicDBObject();
        updateObj.put("$set", doc);
        database.getCollection("tasks").updateOne(query, updateObj);
    }

    @Override
    public List<Task> getAllTasks() {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("tasks");
        List<Task> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            boolean done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Task task;
            if( document.containsKey("price") ) {
                double price = document.getDouble("price");
                task= new Task(title,date,priority,(int) price,done );
            }else{
                task = new Task(title,priority,done,date);
            }
            task.setId(id);
            list.add(task);
        }
        return list;
    }

    @Override
    public List<Task> getAllTasks(boolean done) {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("tasks");
        List<Task> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Task task;
            if (done==true) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    task = new Task(title, date, priority, (int) price, done);
                } else {
                    task = new Task(title, priority, done, date);
                }
                task.setId(id);
                list.add(task);
            }else
            if (done==false) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    task = new Task(title, date, priority, (int) price, done);
                } else {
                    task = new Task(title, priority, done, date);
                }
                task.setId(id);
                list.add(task);
            }
        }
        return list;
    }

    @Override
    public List<Task> getAllTasksByPriority(int p) {
        if(p<=0 || p>3) return null;
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("tasks");
        List<Task> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            boolean done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Task tasks;
            if (p==priority) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    tasks = new Task(title, date, priority, (int) price, done);
                } else {
                    tasks = new Task(title, priority, done, date);
                }
                tasks.setId(id);
                list.add(tasks);
            }
        }
        return list;
    }

    @Override
    public List<Task> getAllTasksByName(String name) {
        database = mongoClient.getDatabase("TaskDB");
        collection = database.getCollection("tasks");
        List<Task> list = new ArrayList<>();
        FindIterable<Document> iterDoc = collection.find();
        for (Document document : iterDoc) {
            String title = document.getString("title");
            int priority = document.getInteger("priority");
            boolean done = document.getBoolean("done");
            Date date = document.getDate("date");
            ObjectId id = document.getObjectId("_id");
            Task tasks;
            if (name==title) {
                if (document.containsKey("price")) {
                    double price = document.getDouble("price");
                    tasks = new Task(title, date, priority, (int) price, done);
                } else {
                    tasks = new Task(title, priority, done, date);
                }
                tasks.setId(id);
                list.add(tasks);
            }else {
                return null;
            }
        }
        return list;
    }

    @Override
    public void DeleteDoneTasks() {

    }

    @Override
    public void insertTaskJSON(JSONObject task) {
        collection=database.getCollection("tasks");
        JSONObject object = new JSONObject();
        object.put("task",task);
        docs=Document.parse(object.toJSONString());
        collection.insertOne(docs);
    }

    @Override
    public JSONObject getAllTasksJSON() {
        database = mongoClient.getDatabase("TaskDB");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        for(Document docs : database.getCollection("tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(docs.toJson());
                array.put(object);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        json.put("tasks", array);
        return json;
    }
    @Override
    public JSONObject getAllTasksByPriorityJSON() {
        database = mongoClient.getDatabase("TaskDB");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        for(Document docs : database.getCollection("tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(docs.toJson());
                array.put(object);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        json.put("tasks", array);
        return json;
    }

    @Override
    public JSONObject getAllTasksByNameJSON() {
        database = mongoClient.getDatabase("TaskDB");
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();
        for(Document docs : database.getCollection("tasks").find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(docs.toJson());
                array.put(object);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        json.put("tasks", array);
        return json;
    }
}
