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

    public Task(String title , int priority, boolean done, Date date) {
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.done = done;
    }
    public Task(String title, Date date, double price, int priority, boolean done) {
        this(title,priority, done,date);
        this.price = price;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
