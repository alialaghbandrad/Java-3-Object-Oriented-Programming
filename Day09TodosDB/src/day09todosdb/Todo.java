package day09todosdb;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Todo {

    public Todo(int id, String task, int difficulty, Date dueDate, Status status) throws InvalidDataException {
        setId(id);
        setTask(task);
        setDifficulty(difficulty);
        setDueDate(dueDate);
        setStatus(status);
    }
    
    private int id;
    private String task; // 1-100 characters, any characters
    private int difficulty; // 1-5
    private Date dueDate; // any valid date where year is 1970 to 2100 both inclusive
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) throws InvalidDataException {
        if (task.length() < 1 || task.length() > 100) {
            throw new InvalidDataException("Task length must be 1-100 characters");
        }
        this.task = task;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        // TODO: verify year 1900-2100
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%d: %s by %s diff. %d is %s", id, task,
                dateFormatDisplay.format(dueDate), difficulty, status);
    }

    static SimpleDateFormat dateFormatDisplay = new SimpleDateFormat("yyyy-MM-dd");
    
    enum Status {
        Pending, Done, Delegated
    }
}
