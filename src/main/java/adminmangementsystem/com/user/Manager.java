package adminmangementsystem.com.user;

/**
 * Manager class represents an administrator with full system access.
 * Demonstrates OOP Inheritance and Polymorphism.
 */
public class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    /**
     * Polymorphism: Override the can() method
     * Manager has full control of the system - can perform any action
     */
    @Override
    public boolean can(String action) {
        // Manager/Admin has full control of the system
        return true;
    }
}
