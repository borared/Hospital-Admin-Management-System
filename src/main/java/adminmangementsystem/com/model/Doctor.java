package adminmangementsystem.com.model;

/**
 * ENCAPSULATION: Doctor model class with private fields and public methods
 * Represents a doctor in the hospital system
 * INHERITANCE: Extends Staff to inherit common staff properties
 */
public class Doctor extends Staff {

    // Constructor - creates a new Doctor object
    public Doctor(String id, String name, String dob, String address,
                  String email, String position, double salary, String doe) {
        super(id, name, dob, address, email, position, salary, doe);
    }

    // Display method - instance-based, shows THIS doctor's data in table format
    public void displayDoctor() {
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                id, name, dob, address, email, position, salary, doe);
    }

    @Override
    public String getResponsibilities() {
        return "Diagnose and treat patients, prescribe medications, refer to specialists.";
    }

    @Override
    public String getDepartment() {
        return "General Medicine";
    }

    @Override
    public void performDuty() {
        System.out.println(name + " is consulting with a patient.");
    }
}
