package adminmangementsystem.com;

import java.util.Scanner;
import adminmangementsystem.com.controller.AppointmentController;
import adminmangementsystem.com.controller.DoctorController;
import adminmangementsystem.com.controller.MainController;
import adminmangementsystem.com.controller.PatientController;
import adminmangementsystem.com.management.AppointmentService;
import adminmangementsystem.com.management.DoctorSystem;
import adminmangementsystem.com.management.PatientSystem;
import adminmangementsystem.com.user.User;
import adminmangementsystem.com.user.Manager;
import adminmangementsystem.com.user.Receptionist;

public class App {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        User[] users = {
            new Manager("admin", "admin$$"),
            new Receptionist("receptionist", "rec123")
        };
        PatientSystem patientSystem = new PatientSystem();
        DoctorSystem doctorSystem = new DoctorSystem();
        AppointmentService appointmentSystem = new AppointmentService(patientSystem);

        DoctorController doctorController = new DoctorController(doctorSystem);
        PatientController patientController = new PatientController(patientSystem);
        AppointmentController appointmentController = new AppointmentController(appointmentSystem);
        MainController mainController = new MainController(doctorController, patientController, appointmentController);
        
        User currentUser = null;
        boolean loggedIn = false;

        while (!loggedIn) {
            Menu.printAdminLoginMenu();
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.\n");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    currentUser = authenticateUser(sc, users, Manager.class);
                    if (currentUser != null) {
                        loggedIn = true;
                        System.out.println("\nLogin successful as Manager.\n");
                    } else {
                        System.out.println("Invalid login. Try again.\n");
                    }
                    break;

                case 2:
                    currentUser = authenticateUser(sc, users, Receptionist.class);
                    if (currentUser != null) {
                        loggedIn = true;
                        System.out.println("\nLogin successful as Receptionist.\n");
                    } else {
                        System.out.println("Invalid login. Try again.\n");
                    }
                    break;

                case 3:
                    System.out.println("System exited.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.\n");
            }
        }

        mainController.start(sc, currentUser);
        sc.close();
    }
    
    private static <T extends User> T authenticateUser(Scanner sc, User[] users, Class<T> userType) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        for (User user : users) {
            boolean isCorrectType = userType.isInstance(user);
            boolean isValidLogin = user.login(username, password);

            if (isCorrectType && isValidLogin) {
                return userType.cast(user);
            }
        }
        return null;
    }
}
