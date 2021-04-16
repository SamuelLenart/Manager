package sk.kosickaakademia.lenart.task.mongodb;


import sk.kosickaakademia.lenart.task.collection.Task;

import java.util.List;

public class Collection implements Mongo {
    @Override
    public void insertTask(Task task) {

    }

    @Override
    public void setTaskDone(int id) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Task> getAllTasks(boolean done) {
        return null;
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
