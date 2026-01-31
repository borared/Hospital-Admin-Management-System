package adminmangementsystem.com;

import java.util.Scanner;

/**
 * Hello world!
 */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DoctorService doctorService = new DoctorService();

        while (true) {
            System.out.println("\n--- Admin Management System ---");
            System.out.println("1. Login as Admin");
            System.out.println("2. Sign up admin account");
            System.out.println("3. Exit");

            int start = Validator.validChoice(sc, 1, 3);
            if (start == 3) break;

            while (true) {
                System.out.println("\n--- Admin Management System ---");
                System.out.println("1. Doctor Management");
                System.out.println("2. Patient Management");
                System.out.println("3. Schedule Appointment");
                System.out.println("4. View Appointment");
                System.out.println("5. View Doctor List");
                System.out.println("6. View Patient List");
                System.out.println("7. Exit");

                int mainChoice = Validator.validChoice(sc, 1, 7);
                if (mainChoice == 7) break;

                if (mainChoice == 1) {
                    System.out.println("\n--- Doctor Management System ---");
                    System.out.println("1. Add Doctor");
                    System.out.println("2. Update Doctor");
                    System.out.println("3. Delete Doctor");
                    System.out.println("4. Search Doctor");
                    System.out.println("5. Exit");

                    int dChoice = Validator.validChoice(sc, 1, 5);
                    sc.nextLine();

                    if (dChoice == 1) {
                        String id;
                        while (true) {
                            id = Validator.validString(sc, "Enter Doctor ID: ");
                            if (!doctorService.idExists(id)) break;
                            System.out.println("ID already exists.");
                        }

                        String name = Validator.validName(sc, "Enter Doctor Name: ");
                        String dob = Validator.validString(sc, "Enter DOB: ");
                        String address = Validator.validString(sc, "Enter Address: ");
                        String pos = Validator.validString(sc, "Enter Position: ");
                        double sal = Validator.validSalary(sc);

                        doctorService.add(
                                new Doctor(id, name, dob, address, pos, sal));
                    }

                    if (dChoice == 5) continue;
                }
            }
        }
        System.out.println("System exited.");
    }
}
