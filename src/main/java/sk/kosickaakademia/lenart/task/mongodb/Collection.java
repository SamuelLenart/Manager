package sk.kosickaakademia.lenart.task.mongodb;


import com.mongodb.MongoClient;
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
    // name: task
    //collection: tasks
    private MongoClient client = new MongoClient();
    private MongoDatabase database = client.getDatabase("task");
    private MongoCollection<Document> collection = database.getCollection("tasks");

    @Override
    public void insertTask(Task task) {
        Document doc = new Document();
        doc.put("name", task.getName());
        doc.put("date", task.getDate());
        doc.put("priority", task.getPriority());
        doc.put("done", task.isDone());
        collection.insertOne(doc);
    }

    @Override
    public void setTaskDone(int id) {

    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();
        for (Document doc : collection.find()){
            try {
                JSONObject object = (JSONObject) new JSONParser().parse(doc.toJson());
                String name = (String) object.get("name");
                Date date = (Date) object.get("date");
                int priority = Integer.parseInt(String.valueOf(object.get("priority")));
                boolean done = (boolean) object.get("done");
                Task task = new Task(name, date, priority, done);
            } catch (Exception e) { e.printStackTrace(); }
        }
        return list;
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
