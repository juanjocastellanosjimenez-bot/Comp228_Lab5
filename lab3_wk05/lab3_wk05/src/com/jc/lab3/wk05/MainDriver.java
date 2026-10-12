package com.jc.lab3.wk05;

public class MainDriver {
    public static void main(String[] args) {
        System.out.println("===== HOSPITAL MANAGEMENT SYSTEM =====\n");

        // ---- Create one object of each major class ----
        Doctor doctor = new Doctor(101, "Alice Smith", 45, "Cardiology", 200.00);
        Nurse nurse = new Nurse(102, "Bob Johnson", 32, "Pediatrics", "Night");
        Surgeon surgeon = new Surgeon(103, "Carla Reyes", 50, "Orthopedics", 350.00,
                "Knee Replacement", "OR-3");
        EmergencyDoctor erDoctor = new EmergencyDoctor(104, "David Lee", 38,
                "Emergency Medicine", 250.00, 12);

        // ---- Display details ----
        System.out.println("--- Object Details ---");
        System.out.println("[Doctor]");          doctor.displayDetails();   System.out.println();
        System.out.println("[Nurse]");           nurse.displayDetails();    System.out.println();
        System.out.println("[Surgeon]");         surgeon.displayDetails();  System.out.println();
        System.out.println("[EmergencyDoctor]"); erDoctor.displayDetails(); System.out.println();

        // ---- Specialized methods ----
        System.out.println("--- Specialized Methods ---");
        doctor.diagnosePatient();
        nurse.assistPatient();
        surgeon.diagnosePatient();   // inherited from Doctor (multilevel)
        surgeon.performSurgery();
        erDoctor.diagnosePatient();  // inherited from Doctor
        erDoctor.handleEmergency();
        System.out.println();

        // ---- Method overriding ----
        System.out.println("--- Method Overriding: performDuties() ---");
        doctor.performDuties();
        nurse.performDuties();
        surgeon.performDuties();
        erDoctor.performDuties();
        System.out.println();

        // ---- Polymorphism via a Person reference ----
        System.out.println("--- Polymorphism (Person reference) ---");
        Person person;

        person = new Doctor(201, "Emma Brown", 41, "Neurology", 220.00);
        person.performDuties();

        person = new Nurse(202, "Frank Miller", 29, "Emergency", "Day");
        person.performDuties();

        person = new Surgeon(203, "Grace Kim", 47, "Cardiac Surgery", 400.00,
                "Bypass", "OR-1");
        person.performDuties();

        person = new EmergencyDoctor(204, "Henry Garcia", 36, "Trauma", 260.00, 5);
        person.performDuties();
        System.out.println();

        // ---- Interface methods via EmergencyDoctor ----
        System.out.println("--- Interface Methods (MedicalProfessional & Billable) ---");
        erDoctor.prescribeMedication();
        erDoctor.generateBill();

        MedicalProfessional mp = erDoctor;  // interface reference
        Billable billable = erDoctor;       // interface reference
        mp.prescribeMedication();
        billable.generateBill();
    }
}
