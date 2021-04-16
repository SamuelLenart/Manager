package sk.kosickaakademia.lenart.task.collection;

import org.bson.types.ObjectId;
import java.util.Date;

public class Task {
    private ObjectId id;
    private String name;
    private int priority;
    private boolean done;
    private Date date;
    private double price;

    public Task(String name, Date date, int priority, boolean done) {
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.done = done;
    }
    public Task(String name, Date date, int priority, boolean done, double price) {
        this(name, date, priority, done);
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isDone() {
        return done;
    }

    public Date getDate() {
        return date;
    }
}
