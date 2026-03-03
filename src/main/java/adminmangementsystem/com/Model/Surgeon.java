package adminmangementsystem.com.Model;

public class Surgeon extends Doctor {

    private int surgeriesPerformed;
    private String surgerySpecialty;

    public Surgeon(String id, String name, String dob, String address,
                   String email, String position, double salary, String doe) {
        super(id, name, dob, address, email, position, salary, doe);
        this.surgeriesPerformed = 0;
        this.surgerySpecialty = "General Surgery";
    }

    // POLYMORPHISM - Override methods from Doctor
    @Override
    public String getResponsibilities() {
        return "Perform surgical operations, pre-op and post-op care, emergency surgeries";
    }

    @Override
    public String getDepartment() {
        return "Surgical Department - " + surgerySpecialty;
    }

    @Override
    public void performDuty() {
        System.out.println("Surgeon " + name + " is performing " + surgerySpecialty + " operations.");
    }

    @Override
    public void conductSurgery(String surgeryType) {
        surgeriesPerformed++;
        System.out.println("Surgeon " + name + " successfully completed " + surgeryType + 
                         " surgery. Total surgeries: " + surgeriesPerformed);
    }

    // Surgeon-specific methods
    public int getSurgeriesPerformed() {
        return surgeriesPerformed;
    }

    public String getSurgerySpecialty() {
        return surgerySpecialty;
    }

    public void setSurgerySpecialty(String surgerySpecialty) {
        this.surgerySpecialty = surgerySpecialty;
    }

    public void performEmergencySurgery(String patientName, String condition) {
        surgeriesPerformed++;
        System.out.println("EMERGENCY: Surgeon " + name + " performing emergency surgery on " + 
                         patientName + " for " + condition);
    }
}
