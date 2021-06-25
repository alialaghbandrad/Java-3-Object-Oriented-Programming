package day09todosdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.StampedLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static final SimpleDateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");
    
    private Connection dbConn;

    private final String CONN_STR = "jdbc:mysql://localhost/day09todos?user=root&password=root";

    Database() throws SQLException {
        // constructor will connect to database, initialize dbConn
        dbConn = DriverManager.getConnection(CONN_STR);
    }

    ArrayList<Todo> getAllTodos() throws SQLException {
        try {
            ArrayList<Todo> result = new ArrayList<>();
            Statement stmtSelect = dbConn.createStatement();
            // Result set get the result of the SQL query
            try (ResultSet resultSet = stmtSelect.executeQuery("SELECT * FROM todos")) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String task = resultSet.getString("task");
                    int diff = resultSet.getInt("difficulty");
                    Date dueDate = resultSet.getDate("dueDate");
                    String statusStr = resultSet.getString("status");
                    Todo.Status status = Todo.Status.valueOf(statusStr); // ex IllegalArgumentException
                    Todo todo = new Todo(id, task, diff, dueDate, status); // ex InvalidDataException
                    result.add(todo);
                }
                return result;
            }
        } catch (InvalidDataException | IllegalArgumentException ex) { 
            throw new SQLException("Error creating todo from record", ex); // exception chaining
        }
    }

    Todo getTodoById(int id) throws SQLException {
        try {
            // Result set get the result of the SQL query
            PreparedStatement stmtSelect = dbConn.prepareStatement("SELECT * FROM todos WHERE id=?");
            stmtSelect.setInt(1, id);
            ResultSet resultSet = stmtSelect.executeQuery();
            if (resultSet.next()) {
                String task = resultSet.getString("task");
                int diff = resultSet.getInt("difficulty");
                Date dueDate = resultSet.getDate("dueDate");
                String statusStr = resultSet.getString("status");
                Todo.Status status = Todo.Status.valueOf(statusStr); // ex IllegalArgumentException
                Todo todo = new Todo(id, task, diff, dueDate, status); // ex InvalidDataException
                return todo;
            } else {
                throw new SQLException("Record not found");
            }
        } catch (InvalidDataException | IllegalArgumentException ex) {
            throw new SQLException("Error creating todo from record", ex); // exception chaining
        }
    }

    int addTodo(Todo todo) throws SQLException {
        PreparedStatement stmtInsert = dbConn
                .prepareStatement("INSERT INTO todos VALUES (NULL, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
        // Parameters start with 1
        stmtInsert.setString(1, todo.getTask());
        stmtInsert.setInt(2, todo.getDifficulty());
        stmtInsert.setDate(3, new java.sql.Date(todo.getDueDate().getTime()));
        // Another solution: use setString for date formatted as YYYY-MM-DD
        stmtInsert.setString(4, todo.getStatus() + "");
        stmtInsert.executeUpdate();
        // fetch the generated id
        ResultSet rs = stmtInsert.getGeneratedKeys();
        if (rs.next()) {
            int lastInsertedId = rs.getInt(1);
            return lastInsertedId;
        } else {
            throw new SQLException("Error getting last insert id");
        }
    }

    void updateTodo(Todo todo) throws SQLException {

    }

    void deleteTodo(int id) throws SQLException {

    }
}
