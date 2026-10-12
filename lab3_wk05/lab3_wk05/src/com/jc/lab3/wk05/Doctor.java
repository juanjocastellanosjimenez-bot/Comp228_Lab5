package com.jc.lab3.wk05;

/**
 * SINGLE INHERITANCE: Person -> Doctor
 */
public class Doctor extends Person {
    private String specialization;
    private double consultationFee;

    public Doctor(int personId, String name, int age,
                  String specialization, double consultationFee) {
        super(personId, name, age);   // call the Person constructor
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public String getSpecialization() { return specialization; }
    public double getConsultationFee() { return consultationFee; }

    public void diagnosePatient() {
        System.out.println("Dr. " + getName() + " (" + specialization
                + ") is diagnosing a patient.");
    }

    @Override
    public void performDuties() {
        System.out.println("Dr. " + getName() + " is examining patients and prescribing treatment plans.");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Specialization: " + specialization
                + " | Consultation Fee: $" + String.format("%.2f", consultationFee));
    }
}
