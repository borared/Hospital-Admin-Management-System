package adminmangementsystem.com.entity;

public class Pharmacist extends Staff {

    private String licenseNumber;  // e.g., "PH-12345"

    public Pharmacist(String id, String name, String dob, String address,
                      String email, String position, double salary, String doe,
                      String licenseNumber) {
        super(id, name, dob, address, email, position, salary, doe);
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String getResponsibilities() {
        return "Dispense medications, provide drug information, manage pharmacy inventory.";
    }

    @Override
    public String getDepartment() {
        return "Pharmacy";
    }

    @Override
    public void performDuty() {
        System.out.println(getName() + " is dispensing medications and counseling patients.");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("License Number: " + licenseNumber);
    }
}
