package adminmangementsystem.com;

public class Cardiologist extends Staff {

    private String fellowship;  // e.g., "Interventional Cardiology"

    public Cardiologist(String id, String name, String dob, String address,
                        String email, String position, double salary, String doe,
                        String fellowship) {
        super(id, name, dob, address, email, position, salary, doe);
        this.fellowship = fellowship;
    }

    // Getter and setter for fellowship
    public String getFellowship() {
        return fellowship;
    }

    public void setFellowship(String fellowship) {
        this.fellowship = fellowship;
    }

    // Implementation of abstract methods
    @Override
    public String getResponsibilities() {
        return "Diagnose and treat heart conditions, perform catheterizations, prescribe medications.";
    }

    @Override
    public String getDepartment() {
        return "Cardiology";
    }

    @Override
    public void performDuty() {
        System.out.println(name + " is consulting a patient with heart issues.");
    }

    // Optionally override display to include fellowship
    @Override
    public void display() {
        super.display();  // prints common staff info
        System.out.println("Fellowship: " + fellowship);
    }
}