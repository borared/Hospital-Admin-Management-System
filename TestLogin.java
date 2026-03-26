import adminmangementsystem.com.user.*;

public class TestLogin {
    public static void main(String[] args) {
        User manager = new Manager("admin", "admin$$");
        User receptionist = new Receptionist("receptionist", "rec123");
        User doctor = new Doctor("doctor", "doc123");
        
        System.out.println("Testing Manager login:");
        System.out.println("  admin/admin$$: " + manager.login("admin", "admin$$"));
        System.out.println("  Manager instance check: " + (manager instanceof Manager));
        System.out.println("  Manager class: " + manager.getClass().getName());
        
        System.out.println("\nTesting Receptionist login:");
        System.out.println("  receptionist/rec123: " + receptionist.login("receptionist", "rec123"));
        System.out.println("  Receptionist instance check: " + (receptionist instanceof Receptionist));
        
        System.out.println("\nTesting Doctor login:");
        System.out.println("  doctor/doc123: " + doctor.login("doctor", "doc123"));
        System.out.println("  Doctor instance check: " + (doctor instanceof Doctor));
        
        System.out.println("\nTesting permissions:");
        System.out.println("  Manager can manage all: " + manager.can("manage all users"));
        System.out.println("  Receptionist can register patients: " + receptionist.can("register new patients"));
        System.out.println("  Doctor can view appointments: " + doctor.can("view their appointments"));
    }
}
