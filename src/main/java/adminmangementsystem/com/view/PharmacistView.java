package adminmangementsystem.com.view;

import java.util.Scanner;
import adminmangementsystem.com.entity.Pharmacist;
import adminmangementsystem.com.util.Validator;

public class PharmacistView {

    public static Pharmacist getPharmacistInput(Scanner sc) {
        System.out.println("\n--- Add New Pharmacist ---");

        String id = Validator.getNonEmpty(sc, "Enter Pharmacist ID: ");
        String name = Validator.getOnlyLetter(sc, "Enter Name: ");
        String dob = Validator.getValidDateFomart(sc, "Enter DOB (dd/mm/yyyy): ");
        String address = Validator.getValidAddress(sc, "Enter Address: ");
        String email = Validator.getValidEmail(sc, "Enter Email: ");
        String position = "Pharmacist";
        double salary = Validator.getValidSalary(sc, "Enter Salary: ");
        String doe = Validator.getValidDateFomart(sc, "Enter Date of Entry (dd/mm/yyyy): ");
        String licenseNumber = Validator.getNonEmpty(sc, "Enter License Number: ");

        return new Pharmacist(id, name, dob, address, email, position, salary, doe, licenseNumber);
    }

    public static String getUpdatePharmacistId(Scanner sc) {
        return Validator.getNonEmpty(sc, "Enter Pharmacist ID to update: ");
    }

    public static void getPharmacistUpdateInput(Scanner sc, Pharmacist pharmacist) {
        System.out.println("\n--- Update Pharmacist (Press Enter to keep current value) ---");

        String name = Validator.getValidUpdateDoc(sc, "Enter Name", pharmacist.getName());
        pharmacist.setName(name);

        String dob = Validator.getValidUpdateDoc(sc, "Enter DOB", pharmacist.getDob());
        pharmacist.setDob(dob);

        String address = Validator.getValidUpdateDoc(sc, "Enter Address", pharmacist.getAddress());
        pharmacist.setAddress(address);

        String email = Validator.getValidUpdateDoc(sc, "Enter Email", pharmacist.getEmail());
        pharmacist.setEmail(email);

        double salary = Validator.getValidUpdateSalary(sc, "Enter Salary", pharmacist.getSalary());
        pharmacist.setSalary(salary);

        String doe = Validator.getValidUpdateDoc(sc, "Enter Date of Entry", pharmacist.getDoe());
        pharmacist.setDoe(doe);

        String licenseNumber = Validator.getValidUpdateDoc(sc, "Enter License Number", pharmacist.getLicenseNumber());
        pharmacist.setLicenseNumber(licenseNumber);
    }

    public static String getPharmacistIdForDelete(Scanner sc) {
        return Validator.getNonEmpty(sc, "Enter Pharmacist ID to delete: ");
    }

    public static int getSearchChoice(Scanner sc) {
        System.out.println("\n--- Search Pharmacist ---");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by ID");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (choice >= 0 && choice <= 2) {
                return choice;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        return -1;
    }

    public static String getSearchName(Scanner sc) {
        return Validator.getOnlyLetter(sc, "Enter Pharmacist Name to search: ");
    }

    public static String getSearchId(Scanner sc) {
        return Validator.getNonEmpty(sc, "Enter Pharmacist ID to search: ");
    }
}
