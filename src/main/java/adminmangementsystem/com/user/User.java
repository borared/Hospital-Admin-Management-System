package adminmangementsystem.com.user;

/**
 * ABSTRACTION: Abstract base class - cannot create User objects directly
 * INHERITANCE: Parent class for Manager and Receptionist
 * ENCAPSULATION: Private fields with controlled access
 */
public abstract class User implements IStaff {
    
    // ENCAPSULATION: Private fields - hidden from outside access
    private String username;
    private String password;
    
    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // ENCAPSULATION: Public getter - controlled read access
    public String getUsername() {
        return username;
    }
    
    // ENCAPSULATION: Public setter - controlled write access
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Common login method for all users
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    // ABSTRACTION: Abstract method - subclasses must implement
    // POLYMORPHISM: Each subclass provides its own implementation
    // Declared in IStaff interface: public abstract boolean can(String action);
}
