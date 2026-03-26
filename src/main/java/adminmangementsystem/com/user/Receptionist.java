package adminmangementsystem.com.user;

/**
 * Receptionist class with limited permissions for patient and appointment management.
 * Demonstrates OOP Inheritance and Polymorphism.
 */
public class Receptionist extends User {

    public Receptionist(String username, String password) {
        super(username, password);
    }

    /**
     * Polymorphism: Override the can() method
     * Receptionist has specific permissions:
     * - Register and update patients
     * - Schedule, cancel, and reschedule appointments
     * - Check doctor availability
     * - Handle basic billing
     */
    @Override
    public boolean can(String action) {
        if (action == null) return false;
        
        String lowerAction = action.toLowerCase();
        
        // Receptionist permissions
        return lowerAction.contains("register new patients") ||
               lowerAction.contains("update patient information") ||
               lowerAction.contains("schedule appointments") ||
               lowerAction.contains("cancel") ||
               lowerAction.contains("reschedule") ||
               lowerAction.contains("check doctor availability") ||
               lowerAction.contains("view patient details") ||
               lowerAction.contains("view all patient records") ||
               lowerAction.contains("handle basic billing") ||
               lowerAction.contains("create invoices");
    }
}
