import adminmangementsystem.com.user.*;
import java.util.Scanner;

public class DebugLogin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        User[] users = {
            new Manager("admin", "admin$$"),
            new Receptionist("receptionist", "rec123"),
            new Doctor("doctor", "doc123")
        };
        
        System.out.println("=== Debug Login Test ===");
        System.out.println("Available users:");
        for (User u : users) {
            System.out.println("  - " + u.getClass().getSimpleName() + 
                             ": username='" + u.getUsername() + 
                             "', password='" + u.getPassword() + "'");
        }
        
        System.out.println("\n--- Testing Manager Authentication ---");
        User result = authenticateUser(sc, users, Manager.class);
        if (result != null) {
            System.out.println("SUCCESS: Logged in as " + result.getClass().getSimpleName());
        } else {
            System.out.println("FAILED: Authentication failed");
        }
        
        sc.close();
    }
    
    private static User authenticateUser(
            Scanner sc, 
            User[] users, 
            Class<? extends User> userType) {
        
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.println("DEBUG: Entered username = '" + username + "' (length: " + username.length() + ")");

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.println("DEBUG: Entered password = '" + password + "' (length: " + password.length() + ")");
        
        System.out.println("\nDEBUG: Looking for user type: " + userType.getSimpleName());

        for (User user : users) {
            System.out.println("  Checking user: " + user.getClass().getSimpleName());
            System.out.println("    - Is instance of " + userType.getSimpleName() + "? " + userType.isInstance(user));
            System.out.println("    - Username matches? " + user.getUsername().equals(username));
            System.out.println("    - Password matches? " + user.getPassword().equals(password));
            System.out.println("    - Login result: " + user.login(username, password));
            
            if (userType.isInstance(user) && user.login(username, password)) {
                System.out.println("  MATCH FOUND!");
                return user;
            }
        }
        System.out.println("  NO MATCH FOUND");
        return null;
    }
}
