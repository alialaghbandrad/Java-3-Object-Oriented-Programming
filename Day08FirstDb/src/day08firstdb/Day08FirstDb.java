package day08firstdb;

import java.sql.*;
import java.util.Random;

public class Day08FirstDb {

    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/day08people";
        String username = "root";
        String password = "root";

        Random random = new Random();

        Connection conn = null;
        // open database connection
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1); // fatal error
        }
        // Insert
        try {
            String sql = "INSERT INTO people VALUES (NULL, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Bill " + random.nextInt(30));
            statement.setString(2, "" + random.nextInt(100));
            statement.executeUpdate(); // for insert, update, delete
            System.out.println("Record inserted");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Select
        try {
            String sql = "SELECT * FROM people";
            PreparedStatement statement = conn.prepareStatement(sql);
            // it is a good practice to use try-with-resources for ResultSet so it is freed up as soon as possible
            try (ResultSet result = statement.executeQuery(sql)) {
                while (result.next()) { // has next row to read
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    int age = result.getInt("age");
                    System.out.printf("%d: %s is %d y/o\n", id, name, age);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Update
        try {
            int id = random.nextInt(10);
            int newAge = random.nextInt(100) + 100;
            String sql = "UPDATE people SET age=? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, newAge);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Record updated id=" + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Delete
        try {
            int id = random.nextInt(10);
            String sql = "DELETE FROM people WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Record deleted id=" + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}
