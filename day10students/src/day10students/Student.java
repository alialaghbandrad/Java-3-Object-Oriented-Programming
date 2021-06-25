/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day10students;

import java.util.Comparator;

/**
 *
 * @author Teacher
 */
public class Student {

    public Student(int id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    int id;
    String name;
    byte[] image; // other possible types ByteArray, Blob, a data stream

    @Override
    public String toString() {
        return String.format("%d: %s", id, name);
    }

    public static final Comparator<Student> compareById = (Student o1, Student o2) -> o1.id - o2.id;
    
    public static final Comparator<Student> compareByName = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareToIgnoreCase(o2.name);
        }
    };
    
}

