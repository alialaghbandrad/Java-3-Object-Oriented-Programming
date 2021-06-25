package day09peopledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {

    private static final String dbURL = "jdbc:mysql://localhost:3306/day08people";
    private static final String username = "root";
    private static final String password = "root";

    private Connection conn;

    public Database() throws SQLException {
        conn = DriverManager.getConnection(dbURL, username, password);
    }

    public ArrayList<Person> getAllPeople() throws SQLException {
        ArrayList<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM people";
        PreparedStatement statement = conn.prepareStatement(sql);
        // it is a good practice to use try-with-resources for ResultSet so it is freed up as soon as possible
        try (ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) { // has next row to read
                int id = result.getInt("id");
                String name = result.getString("name");
                int age = result.getInt("age");
                // System.out.printf("%d: %s is %d y/o\n", id, name, age);
                list.add(new Person(id, name, age));
            }
        }
        return list;
    }

    public void addPerson(Person person) throws SQLException {
        String sql = "INSERT INTO people VALUES (NULL, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setInt(2, person.getAge());
        statement.executeUpdate(); // for insert, update, delete
        System.out.println("Record inserted");
    }

    public void updatePerson(Person person) throws SQLException {
        String sql = "UPDATE people SET name=?, age=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setInt(2, person.getAge());
        statement.setInt(3, person.getId());
        statement.executeUpdate();
        System.out.println("Record updated id=" + person.getId());
    }

    public void deletePerson(int id) throws SQLException {
        String sql = "DELETE FROM people WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println("Record deleted id=" + id);
    }

}
