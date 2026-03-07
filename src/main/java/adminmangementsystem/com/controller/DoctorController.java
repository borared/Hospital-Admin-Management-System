package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.management.DoctorSystem;
import adminmangementsystem.com.Menu;

public class DoctorController {
    private final DoctorSystem doctorSystem;

    public DoctorController(DoctorSystem doctorSystem) {
        this.doctorSystem = doctorSystem;
    }

    // expose for cases where caller needs more than menu actions
    public DoctorSystem getDoctorSystem() {
        return doctorSystem;
    }

    public void run(Scanner sc) {
        boolean doctorMenu = true;
        while (doctorMenu) {
            Menu.doctorMenu();
            System.out.print("Choose an option: ");
            int doctorChoice;
            try {
                doctorChoice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice!\n");
                continue;
            }

            switch (doctorChoice) {
                case 1:
                    doctorSystem.addDoctor(sc);
                    break;
                case 2:
                    doctorSystem.updateDoctor(sc);
                    break;
                case 3:
                    doctorSystem.deleteDoctor(sc);
                    break;
                case 4:
                    doctorSystem.searchDoctor(sc);
                    break;
                case 5:
                    doctorSystem.viewDoctorList();
                    break;
                case 6:
                    doctorMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice!\n");
            }
        }
    }
}
