package adminmangementsystem.com.model;

/**
 * ABSTRACTION: Interface for Staff model classes
 * Defines contract for staff data access and responsibilities
 */
public interface IStaff {
    
    // Getters for staff data
    String getId();
    String getName();
    String getDob();
    String getAddress();
    String getEmail();
    String getPosition();
    double getSalary();
    String getDoe();
    
    // Setters for staff data
    boolean setId(String id);
    boolean setName(String name);
    void setDob(String dob);
    void setAddress(String address);
    boolean setEmail(String email);
    void setPosition(String position);
    boolean setSalary(double salary);
    void setDoe(String doe);
    
    // Abstract methods that each staff type must implement
    String getResponsibilities();
    String getDepartment();
    void performDuty();
    
    // Display method
    void display();
}