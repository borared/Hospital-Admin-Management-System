package adminmangementsystem.com.entity;

public class Doctor extends Staff {

    private String specialization;  // e.g., "Cardiology", "Orthopedic Surgery", "Ophthalmology"
    private String licenseNumber;
    private String medicalSchool;

    // CONSTRUCTOR
    public Doctor(String id, String name, String dob, String address,
                  String email, String position, double salary, String doe,
                  String specialization, String licenseNumber) {
        super(id, name, dob, address, email, position, salary, doe);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    // GETTERS AND SETTERS
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getMedicalSchool() {
        return medicalSchool;
    }

    public void setMedicalSchool(String medicalSchool) {
        this.medicalSchool = medicalSchool;
    }

    // ABSTRACT METHOD IMPLEMENTATIONS
    @Override
    public String getResponsibilities() {
        return "Diagnose and treat patients, prescribe medications, perform procedures related to " + specialization;
    }

    @Override
    public String getDepartment() {
        return specialization != null ? specialization : "General Medicine";
    }

    @Override
    public void performDuty() {
        System.out.println(name + " (Doctor - " + specialization + ") is consulting patients.");
    }

    // DISPLAY
    @Override
    public void display() {
        super.display();
        System.out.println("Specialization: " + specialization + " | License: " + licenseNumber);
    }
}