package adminmangementsystem.com.user;

/**
 * INHERITANCE: Manager extends User (IS-A relationship)
 * POLYMORPHISM: Overrides can() method with Manager-specific behavior
 */
public class Manager extends User {

    // INHERITANCE: Constructor calls parent constructor using super()
    public Manager(String username, String password) {
        super(username, password);
    }

    /**
     * POLYMORPHISM: Override the can() method from IStaff interface
     * Manager has full system access - can perform any action
     */
    @Override
    public boolean can(String action) {
        return true;  // Simple: Manager can do everything
    }
}
