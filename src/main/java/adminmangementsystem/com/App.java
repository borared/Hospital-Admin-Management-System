package adminmangementsystem.com;

import java.util.Scanner;

import adminmangementsystem.com.management.AppointmentService;
import adminmangementsystem.com.management.PatientSystem;
import adminmangementsystem.com.management.DoctorSystem;

public class App {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin$$$");
        AppointmentService appointmentSystem = new AppointmentService();
        PatientSystem patientSystem = new PatientSystem();
        DoctorSystem doctorSystem = new DoctorSystem();

        DoctorController doctorController = new DoctorController(doctorSystem);
        PatientController patientController = new PatientController(patientSystem);
        AppointmentController appointmentController = new AppointmentController(appointmentSystem);
        MainController mainController = new MainController(doctorController, patientController, appointmentController);

        boolean loggedIn = false;

        while (!loggedIn) {
            Menu.printAdminLoginMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1: // Login
                    System.out.print("Enter admin username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter admin password: ");
                    String password = sc.nextLine();

                    if (admin.login(username, password)) {
                        loggedIn = true;
                        System.out.println("\nLogin successful.\n");
                    } else {
                        System.out.println("Invalid login. Try again.\n");
                    }
                    break;

                case 2: // Exit
                    System.out.println("System exited.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.\n");
            }
        }

        // hand over control to main controller
        mainController.start(sc);

        sc.close();
    }
}
