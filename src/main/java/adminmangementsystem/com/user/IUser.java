package adminmangementsystem.com.user;

/**
 * ABSTRACTION: Interface - defines contract without implementation
 * All user members must be able to check permissions
 */
public interface IUser {
    /**
     * POLYMORPHISM: Each class implements this differently
     * Check if user has permission to perform an action
     */
    public abstract boolean can(String action);
}
