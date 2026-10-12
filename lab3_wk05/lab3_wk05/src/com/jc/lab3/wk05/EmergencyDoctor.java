package com.jc.lab3.wk05;

/**
 * MULTIPLE INHERITANCE USING INTERFACES:
 * extends Doctor, implements MedicalProfessional and Billable.
 */
public class EmergencyDoctor extends Doctor implements MedicalProfessional, Billable {
    private int emergenciesHandled;

    public EmergencyDoctor(int personId, String name, int age,
                           String specialization, double consultationFee,
                           int emergenciesHandled) {
        super(personId, name, age, specialization, consultationFee);
        this.emergenciesHandled = emergenciesHandled;
    }

    public int getEmergenciesHandled() { return emergenciesHandled; }

    public void handleEmergency() {
        emergenciesHandled++;
        System.out.println("Dr. " + getName() + " is handling a critical emergency case (total handled: "
                + emergenciesHandled + ").");
    }

    @Override
    public void prescribeMedication() {
        System.out.println("Dr. " + getName() + " is prescribing emergency medication to stabilize the patient.");
    }

    @Override
    public void generateBill() {
        double emergencySurcharge = 150.00;
        double total = getConsultationFee() + emergencySurcharge;
        System.out.println("Bill generated for emergency care by Dr. " + getName()
                + ": consultation $" + String.format("%.2f", getConsultationFee())
                + " + emergency surcharge $" + String.format("%.2f", emergencySurcharge)
                + " = $" + String.format("%.2f", total));
    }

    @Override
    public void performDuties() {
        System.out.println("Dr. " + getName() + " is triaging and treating urgent cases in the emergency room.");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Emergencies Handled: " + emergenciesHandled);
    }
}
