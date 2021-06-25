/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day10students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Teacher
 */
public class Database {

    private static final String dbURL = "jdbc:mysql://localhost:3306/day10students";
    private static final String username = "root";
    private static final String password = "root";

    private Connection conn;

    public Database() throws SQLException {
        conn = DriverManager.getConnection(dbURL, username, password);
    }

    public ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> list = new ArrayList<>();
        String sql = "SELECT id,name FROM students";
        PreparedStatement statement = conn.prepareStatement(sql);
        // it is a good practice to use try-with-resources for ResultSet so it is freed up as soon as possible
        try (ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) { // has next row to read
                int id = result.getInt("id");
                String name = result.getString("name");
                // we do not fetch the image here, on purpose
                // System.out.printf("%d: %s is %d y/o\n", id, name, age);
                list.add(new Student(id, name, null));
            }
        }
        return list;
    }

    // fetch one record, including the BLOB
    Student getStudentById(int id) throws SQLException {
        PreparedStatement stmtSelect = conn.prepareStatement("SELECT * FROM students WHERE id=?");
        stmtSelect.setInt(1, id);
        try (ResultSet resultSet = stmtSelect.executeQuery()) {
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                byte[] image = resultSet.getBytes("image");
                return new Student(id, name, image);
            } else {
                throw new SQLException("Record not found");
            }
        }
    }
    
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students VALUES (NULL, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, student.name);
        statement.setBytes(2, student.image);
        // TODO: handle image
        statement.executeUpdate(); // for insert, update, delete
        System.out.println("Record inserted");
    }
    
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, image=? WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, student.name);
        statement.setBytes(2, student.image);
        statement.setInt(3, student.id);
        statement.executeUpdate();
        System.out.println("Record updated id=" + student.id);
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println("Record deleted id=" + id);
    }

}
