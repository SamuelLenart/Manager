package sk.kosickaakademia.lenart.task.mongodb;

import org.json.simple.JSONObject;
import sk.kosickaakademia.lenart.task.collection.Task;

import java.util.List;

public interface Mongo {
    public boolean insertTask(String title, String task, int priority, double price);

    public void setTaskDone();

    public List<Task> getAllTasks();

    public List<Task> getAllTasks(boolean done);

    public List<Task> getAllTasksByPriority(int priority);

    public List<Task> getAllTasksByName(String name);

    public void DeleteDoneTasks();

    public void insertTaskJSON(JSONObject task);

    public JSONObject getAllTasksJSON();
}
