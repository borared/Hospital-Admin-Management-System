package adminmangementsystem.com.Model;

public class Cardiologist extends Doctor {

    private int heartProcedures;

    public Cardiologist(String id, String name, String dob, String address,
                        String email, String position, double salary, String doe) {
        super(id, name, dob, address, email, "Cardiologist", salary, doe);
        this.heartProcedures = 0;
    }

    // POLYMORPHISM - Override methods from Doctor
    @Override
    public String getResponsibilities() {
        return "Diagnose and treat heart conditions, perform cardiac procedures, manage cardiovascular diseases";
    }

    @Override
    public String getDepartment() {
        return "Cardiology Department";
    }

    @Override
    public void performDuty() {
        System.out.println("Cardiologist " + name + " is treating patients with heart conditions.");
    }

    // Cardiologist-specific methods
    public void performECG(String patientName) {
        System.out.println("Dr. " + name + " is performing ECG test on " + patientName);
    }

    public void performAngioplasty(String patientName) {
        heartProcedures++;
        System.out.println("Dr. " + name + " successfully performed angioplasty on " + patientName + 
                         ". Total procedures: " + heartProcedures);
    }

    public void diagnoseHeartCondition(String patientName, String condition) {
        System.out.println("Dr. " + name + " diagnosed " + patientName + " with " + condition);
    }

    public int getHeartProcedures() {
        return heartProcedures;
    }
}
