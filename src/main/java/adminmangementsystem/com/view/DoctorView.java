package adminmangementsystem.com.view;

import java.util.Scanner;
import adminmangementsystem.com.Validator;
import adminmangementsystem.com.model.Doctor;

public class DoctorView {

    public static Doctor getDoctorInput(Scanner sc) {
        System.out.println("\t\t------Add Doctor------");
        
        String doctorId = Validator.getValidPatient(sc, "Enter Doctor ID: ");
        String doctorName = Validator.getOnlyLetter(sc, "Enter Doctor name: ");
        String doctorDOB = Validator.getValidDateFomart(sc, "Enter Doctor Date of Birth (dd/mm/yyyy): ");
        String doctorAddress = Validator.getNonEmpty(sc, "Enter Doctor address: ");
        String doctorEmail = Validator.getValidEmail(sc, "Enter Doctor email: ");
        String doctorPosition = Validator.getOnlyLetter(sc, "Enter Doctor position: ");
        Double doctorSalary = Validator.getPositiveDouble(sc, "Enter Doctor salary: ");
        String doctorDOE = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
        
        return new Doctor(doctorId, doctorName, doctorDOB, doctorAddress, doctorEmail, doctorPosition, doctorSalary, doctorDOE);
    }

    public static Doctor getDoctorUpdateInput(Scanner sc, Doctor existingDoctor) {
        System.out.println("Leave blank to keep current value.");

        // Update Name
        System.out.print("Enter new Name (" + existingDoctor.getName() + "): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty() && newName.matches("[a-zA-Z ]{1,50}")) {
            existingDoctor.setName(newName);
        } else if (!newName.isEmpty()) {
            System.out.println("Invalid Name! Keeping old value.");
        }

        // Update DOB
        System.out.print("Enter new DOB (" + existingDoctor.getDob() + ") [dd/MM/yyyy]: ");
        String newDob = sc.nextLine().trim();
        if (!newDob.isEmpty() && newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            existingDoctor.setDob(newDob);
        } else if (!newDob.isEmpty()) {
            System.out.println("Invalid DOB format! Keeping old value.");
        }

        // Update Address
        System.out.print("Enter new Address (" + existingDoctor.getAddress() + "): ");
        String newAddress = sc.nextLine().trim();
        if (!newAddress.isEmpty()) {
            existingDoctor.setAddress(newAddress);
        }

        // Update Email
        System.out.print("Enter new Email (" + existingDoctor.getEmail() + "): ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty() && newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            existingDoctor.setEmail(newEmail);
        } else if (!newEmail.isEmpty()) {
            System.out.println("Invalid Email format! Keeping old value.");
        }

        // Update Position
        System.out.print("Enter new Position (" + existingDoctor.getPosition() + "): ");
        String newPosition = sc.nextLine().trim();
        if (!newPosition.isEmpty()) {
            existingDoctor.setPosition(newPosition);
        }

        // Update Salary
        System.out.print("Enter new Salary (" + existingDoctor.getSalary() + "): ");
        String newSalary = sc.nextLine().trim();
        if (!newSalary.isEmpty()) {
            try {
                double salaryValue = Double.parseDouble(newSalary);
                if (salaryValue > 0) {
                    existingDoctor.setSalary(salaryValue);
                } else {
                    System.out.println("Salary must be positive! Keeping old value.");
                }
            } catch (Exception e) {
                System.out.println("Invalid salary! Keeping old value.");
            }
        }

        // Update Date of Entry
        System.out.print("Enter new Entry Date (" + existingDoctor.getDoe() + ") [dd/MM/yyyy]: ");
        String newDoe = sc.nextLine().trim();
        if (!newDoe.isEmpty() && newDoe.matches("\\d{2}/\\d{2}/\\d{4}")) {
            existingDoctor.setDoe(newDoe);
        } else if (!newDoe.isEmpty()) {
            System.out.println("Invalid Entry Date format! Keeping old value.");
        }

        return existingDoctor;
    }

    public static String getDoctorIdForDelete(Scanner sc) {
        System.out.print("Enter doctor ID to delete: ");
        return sc.nextLine().trim();
    }

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
        System.out.print("Enter Doctor name: ");
        return sc.nextLine().trim();
    }

    public static String getSearchId(Scanner sc) {
        System.out.print("Enter Doctor ID: ");
        return sc.nextLine().trim();
    }

    public static String getUpdateDoctorId(Scanner sc) {
        return Validator.getNonEmpty(sc, "Enter Doctor ID to update: ");
    }
}
