package adminmangementsystem.com.entity;

public class Nurse extends Staff {

    private String shift;  // e.g., "Day", "Night"

    public Nurse(String id, String name, String dob, String address,
                 String email, String position, double salary, String doe,
                 String shift) {
        super(id, name, dob, address, email, position, salary, doe);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String getResponsibilities() {
        return "Patient care, administer medication, assist doctors.";
    }

    @Override
    public String getDepartment() {
        return "Nursing";
    }

    @Override
    public void performDuty() {
        System.out.println(name + " is checking vital signs of patients.");
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Shift: " + shift);
    }
}
