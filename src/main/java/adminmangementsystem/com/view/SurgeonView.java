package adminmangementsystem.com.view;

import java.util.Scanner;

import adminmangementsystem.com.model.Surgeon;
import adminmangementsystem.com.Validator;

public class SurgeonView {

    public static Surgeon getSurgeonInput(Scanner sc) {
        System.out.println("\t\t------ Add Surgeon ------");

        String id = Validator.getValidPatient(sc, "Enter Surgeon ID: ");
        String name = Validator.getOnlyLetter(sc, "Enter Surgeon name: ");
        String dob = Validator.getValidDateFomart(sc, "Enter Surgeon Date of Birth (dd/mm/yyyy): ");
        String address = Validator.getNonEmpty(sc, "Enter Surgeon address: ");
        String email = Validator.getValidEmail(sc, "Enter Surgeon email: ");
        String position = Validator.getOnlyLetter(sc, "Enter Surgeon position (e.g., Orthopedic): ");
        double salary = Validator.getPositiveDouble(sc, "Enter Surgeon salary: ");
        String doe = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
        String specialization = Validator.getOnlyLetter(sc, "Enter Surgeon specialization: ");

        return new Surgeon(id, name, dob, address, email, position, salary, doe, specialization);
    }

    public static Surgeon getSurgeonUpdateInput(Scanner sc, Surgeon existingSurgeon) {
        System.out.println("Leave blank to keep current value.");

        // Update ID? Usually ID should not be changed; we assume it's fixed.
        // If needed, you can add ID update with caution.

        // Update Name
        System.out.print("Enter new Name (" + existingSurgeon.getName() + "): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty() && newName.matches("[a-zA-Z ]{1,50}")) {
            existingSurgeon.setName(newName);
        } else if (!newName.isEmpty()) {
            System.out.println("Invalid Name! Keeping old value.");
        }

        // Update DOB
        System.out.print("Enter new DOB (" + existingSurgeon.getDob() + ") [dd/MM/yyyy]: ");
        String newDob = sc.nextLine().trim();
        if (!newDob.isEmpty() && newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            existingSurgeon.setDob(newDob);
        } else if (!newDob.isEmpty()) {
            System.out.println("Invalid DOB format! Keeping old value.");
        }

        // Update Address
        System.out.print("Enter new Address (" + existingSurgeon.getAddress() + "): ");
        String newAddress = sc.nextLine().trim();
        if (!newAddress.isEmpty()) {
            existingSurgeon.setAddress(newAddress);
        }

        // Update Email
        System.out.print("Enter new Email (" + existingSurgeon.getEmail() + "): ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty() && newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            existingSurgeon.setEmail(newEmail);
        } else if (!newEmail.isEmpty()) {
            System.out.println("Invalid Email format! Keeping old value.");
        }

        // Update Position
        System.out.print("Enter new Position (" + existingSurgeon.getPosition() + "): ");
        String newPosition = sc.nextLine().trim();
        if (!newPosition.isEmpty()) {
            existingSurgeon.setPosition(newPosition);
        }

        // Update Salary
        System.out.print("Enter new Salary (" + existingSurgeon.getSalary() + "): ");
        String newSalary = sc.nextLine().trim();
        if (!newSalary.isEmpty()) {
            try {
                double salaryValue = Double.parseDouble(newSalary);
                if (salaryValue > 0) {
                    existingSurgeon.setSalary(salaryValue);
                } else {
                    System.out.println("Salary must be positive! Keeping old value.");
                }
            } catch (Exception e) {
                System.out.println("Invalid salary! Keeping old value.");
            }
        }
        

        // Update Date of Entry
        System.out.print("Enter new Entry Date (" + existingSurgeon.getDoe() + ") [dd/MM/yyyy]: ");
        String newDoe = sc.nextLine().trim();
        if (!newDoe.isEmpty() && newDoe.matches("\\d{2}/\\d{2}/\\d{4}")) {
            existingSurgeon.setDoe(newDoe);
        } else if (!newDoe.isEmpty()) {
            System.out.println("Invalid Entry Date format! Keeping old value.");
        }

        // Update Specialization
        System.out.print("Enter new Specialization (" + existingSurgeon.getSpecialization() + "): ");
        String newSpecialization = sc.nextLine().trim();
        if (!newSpecialization.isEmpty()) {
            existingSurgeon.setSpecialization(newSpecialization);
        }

        return existingSurgeon;
    }

    // Helper methods for search (similar to DoctorView)
    public static int getSearchChoice(Scanner sc) {
        System.out.print("Search by (1) Name or (2) ID: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid choice!");
            return -1;
        }
    }

    public static String getSearchName(Scanner sc) {
        System.out.print("Enter Surgeon name: ");
        return sc.nextLine().trim();
    }

    public static String getSearchId(Scanner sc) {
        System.out.print("Enter Surgeon ID: ");
        return sc.nextLine().trim();
    }
}

