package day09peopledb;

public class Person {

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return String.format("%d: %s is %d y/o", id, name, age);
    }

    // null if valid, otherwise error string is returned
    public static String isNameValid(String name) {
        if (name.length() < 2 || name.length() > 45) {
            return "Name must be 2-45 characters long";
        } else {
            return null;
        }
    }

    // null if valid, otherwise error string is returned
    public static String isAgeValid(int age) {
        if (age < 1 || age > 150) {
            return "Age must be 1-150";
        } else {
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String msg;
        if ((msg = isNameValid(name)) != null) {
            throw new IllegalArgumentException(msg);
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        String msg;
        if ((msg = isAgeValid(age)) != null) {
            throw new IllegalArgumentException(msg);
        }
        this.age = age;
    }

}
