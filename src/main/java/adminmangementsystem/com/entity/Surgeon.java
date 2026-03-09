package adminmangementsystem.com;

public class Surgeon extends Staff {

    private String specialization;  // e.g., "Orthopedic", "Cardiothoracic"

    public Surgeon(String id, String name, String dob, String address,
                   String email, String position, double salary, String doe,
                   String specialization) {
        super(id, name, dob, address, email, position, salary, doe);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String getResponsibilities() {
        return "Perform surgical operations, pre‑ and post‑operative care.";
    }

    @Override
    public String getDepartment() {
        return "Surgery";
    }

    @Override
    public void performDuty() {
        System.out.println(name + " is performing a " + specialization + " surgery.");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Specialization: " + specialization);
    }
}