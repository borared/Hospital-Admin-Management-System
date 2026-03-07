package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.Menu;

public class MainController {
    private final DoctorController doctorController;
    private final PatientController patientController;
    private final AppointmentController appointmentController;

    public MainController(DoctorController doctorController,
                          PatientController patientController,
                          AppointmentController appointmentController) {
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.appointmentController = appointmentController;
    }

    public void start(Scanner sc) {
        boolean running = true;
        while (running) {
            Menu.printDashboardMenu();
            System.out.print("Enter your choice: ");
            int choiceIn;
            try {
                choiceIn = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice.\n");
                continue;
            }

            switch (choiceIn) {
                case 1:
                    doctorController.run(sc);
                    break;
                case 2:
                    patientController.run(sc);
                    break;
                case 3:
                    doctorController.getDoctorSystem().viewDoctorsByPosition("Cardiologist", "Cardiologist List");
                    break;
                case 4:
                    doctorController.getDoctorSystem().viewDoctorsByPosition("Surgeon", "Surgeon List");
                    break;
                case 5:
                    doctorController.getDoctorSystem().viewDoctorsByPosition("Nurse", "Nurse List");
                    break;
                case 6:
                    appointmentController.getAppointmentService().addAppointment(sc);
                    break;
                case 7:
                    appointmentController.getAppointmentService().viewAppointments();
                    break;
                case 8:
                    doctorController.getDoctorSystem().viewDoctorList1();
                    break;
                case 9:
                    patientController.getPatientSystem().viewPatientList(sc);
                    break;
                case 10:
                    running = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
