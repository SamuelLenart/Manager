package sk.kosickaakademia.lenart.task.mongodb;

import org.json.simple.JSONObject;
import sk.kosickaakademia.lenart.task.collection.Task;



public interface MongoJSON {
    public void insertTaskJSON(JSONObject task);

    public JSONObject getAllTasksJSON();
}
