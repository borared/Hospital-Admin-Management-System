package adminmangementsystem.com;

import java.util.Scanner;

import adminmangementsystem.com.controller.AppointmentController;
import adminmangementsystem.com.controller.DoctorController;
import adminmangementsystem.com.controller.MainController;
import adminmangementsystem.com.controller.PatientController;
import adminmangementsystem.com.service.AppointmentService;
import adminmangementsystem.com.service.DoctorService;
import adminmangementsystem.com.service.PatientService;

public class App {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin$$$");
        AppointmentService appointmentSystem = new AppointmentService();
        PatientService PatientService = new PatientService();
        DoctorService DoctorService = new DoctorService();

        DoctorController doctorController = new DoctorController(DoctorService);
        PatientController patientController = new PatientController(PatientService);
        AppointmentController appointmentController = new AppointmentController(appointmentSystem);
        MainController mainController = new MainController(doctorController, patientController, appointmentController);
        
        

        boolean loggedIn = false;

        while (!loggedIn) {
            MenuView.printAdminLoginMenu();
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