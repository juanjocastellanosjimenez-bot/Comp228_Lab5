package com.jc.lab3.wk05;

/**
 * Parent class for every hospital employee.
 * Demonstrates: classes/objects, constructors, encapsulation.
 */
public class Person {
    private int personId;
    private String name;
    private int age;

    public Person(int personId, String name, int age) {
        this.personId = personId;
        this.name = name;
        this.age = age;
    }

    public int getPersonId() { return personId; }
    public String getName()  { return name; }
    public int getAge()      { return age; }

    public void performDuties() {
        System.out.println(name + " is performing general hospital duties.");
    }

    public void displayDetails() {
        System.out.println("ID: " + personId + " | Name: " + name + " | Age: " + age);
    }
}
