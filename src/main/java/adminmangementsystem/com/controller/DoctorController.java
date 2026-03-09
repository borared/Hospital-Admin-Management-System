package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.service.DoctorService;
import adminmangementsystem.com.view.MenuView;

public class DoctorController {
    private final DoctorService DoctorService;

    public DoctorController(DoctorService DoctorService) {
        this.DoctorService = DoctorService;
    }

    // expose for cases where caller needs more than menu actions
    public DoctorService getDoctorSystem() {
        return DoctorService;
    }

    public void run(Scanner sc) {
        boolean doctorMenu = true;
        while (doctorMenu) {
            MenuView.doctorMenu();
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
                    DoctorService.addDoctor(sc);
                    break;
                case 2:
                    DoctorService.updateDoctor(sc);
                    break;
                case 3:
                    DoctorService.deleteDoctor(sc);
                    break;
                case 4:
                    DoctorService.searchDoctor(sc);
                    break;
                case 5:
                    DoctorService.viewDoctorList();
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
