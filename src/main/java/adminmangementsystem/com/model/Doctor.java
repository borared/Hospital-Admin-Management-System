package adminmangementsystem.com.model;

/**
 * ENCAPSULATION: Doctor model class with private fields and public methods
 * Represents a doctor in the hospital system
 */
public class Doctor {

    // ENCAPSULATION: Private fields - data is hidden from outside access
    private final String id;  // 'final' = immutable, cannot change after construction
    private String name;
    private String dob;
    private String address;
    private String email;
    private String position;
    private double salary;
    private String doe;   

    // Constructor - creates a new Doctor object
    public Doctor(String id, String name, String dob, String address,
                  String email, String position, double salary, String doe) {
        // Validate ID in constructor (only place to set it)
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor ID cannot be null or empty");
        }
        this.id = id.trim();  // Set once, cannot change later
        
        // Other fields use setters (can be changed later)
        setName(name);
        setDob(dob);
        setAddress(address);
        setEmail(email);
        setPosition(position);
        setSalary(salary);
        setDoe(doe);
    }

    // ENCAPSULATION: Setters with validation - controlled write access
    // NOTE: No setId() method - ID is read-only after creation

    public boolean setName(String name) {
        // Validation: Only letters and spaces, max 50 characters
        if (name != null && name.matches("[a-zA-Z ]{1,50}")) {
            this.name = name;
            return true;
        }
        return false;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean setEmail(String email) {
        // Validation: Must be valid email format
        if (email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
            return true;
        }
        return false;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean setSalary(double salary) {
        // Validation: Salary must be positive
        if (salary > 0) {
            this.salary = salary;
            return true;
        }
        return false;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    // ENCAPSULATION: Getters - controlled read access

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String getDoe() {
        return doe;
    }

    // Display method - instance-based, shows THIS doctor's data in table format
    public void displayDoctor() {
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                id, name, dob, address, email, position, salary, doe);
    }
}
