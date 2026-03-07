package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.management.PatientSystem;
import adminmangementsystem.com.Menu;

public class PatientController {
    private final PatientSystem patientSystem;

    public PatientController(PatientSystem patientSystem) {
        this.patientSystem = patientSystem;
    }

    public PatientSystem getPatientSystem() {
        return patientSystem;
    }

    public void run(Scanner sc) {
        boolean patientMenu = true;
        while (patientMenu) {
            Menu.patientMenu();
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
                    patientSystem.addPatient(sc);
                    break;
                case 2:
                    patientSystem.updatePatient(sc);
                    break;
                case 3:
                    patientSystem.deletePatient(sc);
                    break;
                case 4:
                    patientSystem.searchPatient(sc);
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
