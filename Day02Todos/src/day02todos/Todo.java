package day02todos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

class Todo {

    public Todo(String task, Date dueDate, int hoursOfWork, TaskStatus status) throws DataInvalidException {
        setTask(task);
        setDueDate(dueDate);
        setHoursOfWork(hoursOfWork);
        setStatus(status);
        instanceCount++;
    }

    public Todo(String dataLine) throws DataInvalidException { // throws ParseException, NumberFormatException, IllegalArgumentException {
        String[] data = dataLine.split(";");
        if (data.length != 4) {
            throw new DataInvalidException("Invalid number of items in line");
        }
        try {
            // continue parsing, use setters to set values
            String task = data[0];
            Date dueDate = dateFormatFile.parse(data[1]); // ex ParseException
            int hoursOfWork = Integer.parseInt(data[2]); // ex NumberFormatException
            TaskStatus status = TaskStatus.valueOf(data[3]); // ex IllegalArgumentException
            setTask(task); // ex DataInvalidException
            setDueDate(dueDate); // ex DataInvalidException
            setHoursOfWork(hoursOfWork); // ex DataInvalidException
            setStatus(status);
            //
            instanceCount++;
        } catch (ParseException ex) {
            throw new DataInvalidException("Date format invalid", ex);
        } catch (NumberFormatException ex) {
            throw new DataInvalidException("Integer value expected", ex);
        }
    }

    private static int instanceCount; // static, read-only property

    public static int getInstanceCount() {
        return instanceCount;
    }

    private String task; // 2-50 characters long, must NOT contain a semicolon or | or ` (reverse single quote) characters
    private Date dueDate; // Date between year 1900 and 2100
    private int hoursOfWork; // 0 or greater number
    private TaskStatus status;

    enum TaskStatus {
        Pending, Done
    };

    // format all fields of this Todo item for display exactly as specified below in the example interactions
    @Override
    public String toString() {
        String dueDateStr = Globals.dateFormatScreen.format(dueDate);
        return String.format("%s, %s, will take %d hour(s) of work, %s", task, dueDateStr, hoursOfWork, status);
    }

    private static final SimpleDateFormat dateFormatFile;

    static { // static initializer
        dateFormatFile = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatFile.setLenient(false);
    }

    public String toDataString() {
        // returns a string similar to "Buy milk 2%;2019-11-22;2"
        String dueDateStr = dateFormatFile.format(dueDate);
        return String.format("%s;%s;%d;%s", task, dueDateStr, hoursOfWork, status);
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) throws DataInvalidException {
        //if (task.length() < 2 || task.length() > 50) {
        if (!task.matches("[^;|`]{2,50}")) {
            throw new DataInvalidException("Task must be 2-50 character long and not contain ;|` characters");
        }
        this.task = task;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) throws DataInvalidException {
        // int year = dueDate.getYear() + 1900; // getYear() gives us year - 1900
        Calendar cal = Calendar.getInstance();
        cal.setTime(dueDate);
        int year = cal.get(Calendar.YEAR);
        if (year < 1900 || year > 2100) { // error
            throw new DataInvalidException("Year must be 1900 to 2100");
        }
        this.dueDate = dueDate;
    }

    public int getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(int hoursOfWork) throws DataInvalidException {
        if (hoursOfWork < 0) {
            throw new DataInvalidException("Hours of work can't be negative");
        }
        this.hoursOfWork = hoursOfWork;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

}
