package adminmangementsystem.com.user;

/**
 * Abstract base class for all users in the system.
 * Demonstrates OOP principles:
 * - Encapsulation: Private fields with getters/setters
 * - Abstraction: Abstract class with common behavior
 * - Inheritance: Base class for Manager, Receptionist, Doctor
 * - Polymorphism: Subclasses override can() method
 */
public abstract class User implements IStaff {
    
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Encapsulation: standard getters and setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Common behavior for all users
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    // Polymorphism: Subclasses must implement the 'can' method from IStaff interface
    // This allows each user type to have different permissions
}
