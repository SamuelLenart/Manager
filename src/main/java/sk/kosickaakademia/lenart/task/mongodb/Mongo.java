package sk.kosickaakademia.lenart.task.mongodb;

import sk.kosickaakademia.lenart.task.collection.Task;

import java.util.List;

public interface Mongo {
    public boolean insertTask(String title, String task, int priority, double price);

    public void setTaskDone(int id);

    public List<Task> getAllTasks();

    public List<Task> getAllTasks(boolean done);

    public List<Task> getAllTasksByPriority(int priority);

    public List<Task> getAllTasksByName(String name);

    public void DeleteDoneTasks();
}
