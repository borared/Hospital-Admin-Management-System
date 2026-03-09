package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.service.PatientService;
import adminmangementsystem.com.view.MenuView;

public class PatientController {
    private final PatientService PatientService;

    public PatientController(PatientService PatientService) {
        this.PatientService = PatientService;
    }

    public PatientService getPatientSystem() {
        return PatientService;
    }

    public void run(Scanner sc) {
        boolean patientMenu = true;
        while (patientMenu) {
            MenuView.patientMenu();
            System.out.print("Choose an option: ");
            int patientChoice;
            try {
                patientChoice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice!\n");
                continue;
            }
            switch (patientChoice) {
                case 1:
                    PatientService.addPatient(sc);
                    break;
                case 2:
                    PatientService.updatePatient(sc);
                    break;
                case 3:
                    PatientService.deletePatient(sc);
                    break;
                case 4:
                    PatientService.searchPatient(sc);
                    break;
                case 5:
                    patientMenu = false;
                    System.out.println("Exiting Patient Management System...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
