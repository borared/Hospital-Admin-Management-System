package adminmangementsystem.com.user;

/**
 * INHERITANCE: Receptionist extends User (IS-A relationship)
 * POLYMORPHISM: Overrides can() method with Receptionist-specific behavior
 */
public class Receptionist extends User {

    // INHERITANCE: Constructor calls parent constructor using super()
    public Receptionist(String username, String password) {
        super(username, password);
    }

    /**
     * POLYMORPHISM: Override the can() method from IStaff interface
     * Receptionist has limited permissions - only patient and appointment management
     */
    @Override
    public boolean can(String action) {
        if (action == null) return false;
        
        String lowerAction = action.toLowerCase();
        
        // Receptionist can only do these specific actions
        return lowerAction.contains("register new patients") ||
               lowerAction.contains("update patient information") ||
               lowerAction.contains("schedule appointments") ||
               lowerAction.contains("view patient details") ||
               lowerAction.contains("view all patient records");
    }
}
