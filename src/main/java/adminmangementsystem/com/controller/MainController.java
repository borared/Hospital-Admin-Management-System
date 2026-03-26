package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.Menu;
import adminmangementsystem.com.user.Manager;
import adminmangementsystem.com.user.Receptionist;

/**
 * ENCAPSULATION: MainController encapsulates menu handling logic
 * POLYMORPHISM: Handles different user types with different menus
 */
public class MainController {
    
    // ENCAPSULATION: Private fields - controllers are hidden from outside
    private final DoctorController doctorController;
    private final PatientController patientController;
    private final AppointmentController appointmentController;

    // Constructor - receives dependencies
    public MainController(DoctorController doctorController,
                          PatientController patientController,
                          AppointmentController appointmentController) {
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.appointmentController = appointmentController;
    }

    /**
     * POLYMORPHISM: Method behaves differently based on user type
     * Uses instanceof to check actual type at runtime
     */
    public void start(Scanner sc, adminmangementsystem.com.user.User currentUser) {
        // POLYMORPHISM: Runtime type checking
        if (currentUser instanceof Manager) {
            handleManagerMenu(sc, currentUser);  // Show full menu
        } else if (currentUser instanceof Receptionist) {
            handleReceptionistMenu(sc, currentUser);  // Show limited menu
        }
    }

    /**
     * ENCAPSULATION: Private method - only used internally
     * Handles Manager menu with full access
     */
    private void handleManagerMenu(Scanner sc, adminmangementsystem.com.user.User currentUser) {
        boolean running = true;
        while (running) {
            Menu.printDashboardMenu();
            System.out.print("Enter your choice: ");
            
            // EXCEPTION HANDLING: Try-catch to handle invalid input
            int choiceIn;
            try {
                choiceIn = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number.\n");
                continue;
            }

            switch (choiceIn) {
                case 1:  // Doctor Management
                    doctorController.run(sc);
                    break;
                case 2:  // Patient Management
                    patientController.run(sc);
                    break;
                case 3:  // View Cardiologists
                    doctorController.getDoctorSystem().viewDoctorsByPosition("Cardiologist", "Cardiologist List");
                    break;
                case 4:  // View Surgeons
                    doctorController.getDoctorSystem().viewDoctorsByPosition("Surgeon", "Surgeon List");
                    break;
                case 5:  // View Nurses
                    doctorController.getDoctorSystem().viewDoctorsByPosition("Nurse", "Nurse List");
                    break;
                case 6:  // Schedule Appointment
                    appointmentController.getAppointmentService().addAppointment(sc);
                    break;
                case 7:  // View Appointments
                    appointmentController.getAppointmentService().viewAppointments();
                    break;
                case 8:  // View All Doctors
                    doctorController.getDoctorSystem().viewDoctorList1();
                    break;
                case 9:  // View All Patients
                    patientController.getPatientSystem().viewPatientList(sc);
                    break;
                case 10:  // Exit
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * ENCAPSULATION: Private method - only used internally
     * Handles Receptionist menu with limited access
     */
    private void handleReceptionistMenu(Scanner sc, adminmangementsystem.com.user.User currentUser) {
        boolean running = true;
        while (running) {
            Menu.printReceptionistMenu();
            System.out.print("Enter your choice: ");
            
            // EXCEPTION HANDLING: Try-catch to handle invalid input
            int choiceIn;
            try {
                choiceIn = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number.\n");
                continue;
            }

            switch (choiceIn) {
                case 1:  // Patient Management
                    patientController.run(sc);
                    break;
                case 2:  // Schedule Appointment
                    appointmentController.getAppointmentService().addAppointment(sc);
                    break;
                case 3:  // View Appointments
                    appointmentController.getAppointmentService().viewAppointments();
                    break;
                case 4:  // View Patients
                    patientController.getPatientSystem().viewPatientList(sc);
                    break;
                case 5:  // Exit
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
