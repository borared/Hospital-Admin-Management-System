package adminmangementsystem.com.user;

/**
 * Interface defining the contract for staff permission checking.
 * Demonstrates OOP Interface and Polymorphism principles.
 * Each user type implements this interface with their own permission logic.
 */
public interface IStaff {
    /**
     * Check if the user has permission to perform a specific action.
     * @param action The action to check permission for
     * @return true if the user can perform the action, false otherwise
     */
    public abstract boolean can(String action);
}
