package adminmangementsystem.com.Model;

public class Doctor extends Staff {

    private String specialization;

    // CONSTRUCTOR
    public Doctor(String id, String name, String dob, String address,
                  String email, String position, double salary, String doe) {
        super(id, name, dob, address, email, position, salary, doe);
        this.specialization = position; // Position acts as specialization
    }

    // POLYMORPHISM - Override abstract methods
    @Override
    public String getResponsibilities() {
        return "Diagnose and treat patients, prescribe medications, perform medical procedures";
    }

    @Override
    public String getDepartment() {
        return "Medical Department - " + specialization;
    }

    @Override
    public void performDuty() {
        System.out.println("Dr. " + name + " is examining patients and providing medical care.");
    }

    // Doctor-specific methods
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void prescribeMedication(String patientName, String medication) {
        System.out.println("Dr. " + name + " prescribed " + medication + " to " + patientName);
    }

    public void conductSurgery(String surgeryType) {
        System.out.println("Dr. " + name + " is performing " + surgeryType + " surgery.");
    }
}
