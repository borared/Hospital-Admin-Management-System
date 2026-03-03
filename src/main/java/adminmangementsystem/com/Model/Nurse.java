package adminmangementsystem.com.Model;

public class Nurse extends Staff {

    private String ward;

    public Nurse(String id, String name, String dob, String address,
                 String email, String position, double salary, String doe) {
        super(id, name, dob, address, email, position, salary, doe);
        this.ward = "General Ward";
    }

    // POLYMORPHISM - Override abstract methods
    @Override
    public String getResponsibilities() {
        return "Assist doctors, administer medications, monitor patient vitals, provide patient care";
    }

    @Override
    public String getDepartment() {
        return "Nursing Department - " + ward;
    }

    @Override
    public void performDuty() {
        System.out.println("Nurse " + name + " is caring for patients in " + ward);
    }

    // Nurse-specific methods
    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void administerMedication(String patientName, String medication) {
        System.out.println("Nurse " + name + " administered " + medication + " to " + patientName);
    }

    public void checkVitals(String patientName) {
        System.out.println("Nurse " + name + " is checking vitals for " + patientName);
    }
}
