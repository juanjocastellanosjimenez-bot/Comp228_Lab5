package com.jc.lab3.wk05;

/**
 * HIERARCHICAL INHERITANCE: Doctor and Nurse both extend Person.
 */
public class Nurse extends Person {
    private String department;
    private String shift;

    public Nurse(int personId, String name, int age, String department, String shift) {
        super(personId, name, age);
        this.department = department;
        this.shift = shift;
    }

    public String getDepartment() { return department; }
    public String getShift()      { return shift; }

    public void assistPatient() {
        System.out.println("Nurse " + getName() + " is assisting a patient in the "
                + department + " department.");
    }

    @Override
    public void performDuties() {
        System.out.println("Nurse " + getName() + " is monitoring patients and administering care on the "
                + shift + " shift.");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department + " | Shift: " + shift);
    }
}
