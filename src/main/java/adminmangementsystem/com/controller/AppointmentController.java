package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.management.AppointmentService;
import adminmangementsystem.com.Menu;

public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public void run(Scanner sc) {
        boolean appointmentDisplayMenu = true;
        while (appointmentDisplayMenu) {
            Menu.appointmentMenu();
            System.out.print("Choose an option: ");
            int appointmentChoice;
            try {
                appointmentChoice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice!\n");
                continue;
            }
            switch (appointmentChoice) {
                case 1:
                    appointmentService.addAppointment(sc);
                    break;
                case 2:
                    appointmentService.viewAppointments();
                    break;
                case 3:
                    appointmentDisplayMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
