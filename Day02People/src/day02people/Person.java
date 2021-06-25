package day02people;

import java.security.InvalidParameterException;



public class Person {
    
    public Person(String name, int age) throws InvalidDataException {
        setName(name);
        setAge(age);
    }
    
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidDataException {
        if (name.length() < 2 || name.length() > 20 || name.contains(";")) {
            throw new InvalidDataException("Name must be 2-20 characters long and not contain semicolons");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidDataException { // 1-150
        if (age < 1 || age > 150) {
            throw new InvalidDataException("Age must be 1-150");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        //return "Person{" + "name=" + name + ", age=" + age + '}';
        return String.format("%s is %d y/o", name, age);
    }
    
}
