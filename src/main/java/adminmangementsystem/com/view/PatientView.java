package adminmangementsystem.com.view;

import java.util.Scanner;
import adminmangementsystem.com.Validator;
import adminmangementsystem.com.model.Patient;

public class PatientView {

    public static Patient getPatientInput(Scanner sc) {
        System.out.println("\t\t------Add Patient------");
        
        String patId = Validator.getValidPatient(sc, "Enter Patient ID: ");
        String patName = Validator.getOnlyLetter(sc, "Enter patient name: ");
        String patDOB = Validator.getValidDateFomart(sc, "Enter patient Date of Birth (dd/mm/yyyy): ");
        String patAddress = Validator.getNonEmpty(sc, "Enter patient address: ");
        String patDisease = Validator.getNonEmpty(sc, "Enter patient disease: ");
        String patDOE = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
        
        return new Patient(patId, patName, patDOB, patDisease, patAddress, patDOE);
    }

    public static Patient getPatientUpdateInput(Scanner sc, Patient existingPatient) {
        System.out.println("Leave blank to keep current value.");

        // Update Name
        System.out.print("Enter new Name (" + existingPatient.getName() + "): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty() && newName.matches("[a-zA-Z ]{1,50}")) {
            existingPatient.setName(newName);
        } else if (!newName.isEmpty()) {
            System.out.println("Invalid Name! Keeping old value.");
        }

        // Update DOB
        System.out.print("Enter new DOB (" + existingPatient.getDob() + ") [dd/MM/yyyy]: ");
        String newDob = sc.nextLine().trim();
        if (!newDob.isEmpty() && newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            existingPatient.setDob(newDob);
        } else if (!newDob.isEmpty()) {
            System.out.println("Invalid DOB format! Keeping old value.");
        }

        // Update Address
        System.out.print("Enter new Address (" + existingPatient.getAddress() + "): ");
        String newAddress = sc.nextLine().trim();
        if (!newAddress.isEmpty()) {
            existingPatient.setAddress(newAddress);
        }

        // Update Disease
        System.out.print("Enter new Disease (" + existingPatient.getDisease() + "): ");
        String newDisease = sc.nextLine().trim();
        if (!newDisease.isEmpty()) {
            existingPatient.setDisease(newDisease);
        }

        // Update Entry Date
        System.out.print("Enter new Entry Date (" + existingPatient.getEntryDate() + ") [dd/MM/yyyy]: ");
        String newDoe = sc.nextLine().trim();
        if (!newDoe.isEmpty() && newDoe.matches("\\d{2}/\\d{2}/\\d{4}")) {
            existingPatient.setEntryDate(newDoe);
        } else if (!newDoe.isEmpty()) {
            System.out.println("Invalid Entry Date format! Keeping old value.");
        }

        return existingPatient;
    }

    public static String getPatientIdForDelete(Scanner sc) {
        System.out.print("Enter patient ID to delete: ");
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
        System.out.print("Enter Patient name: ");
        return sc.nextLine().trim();
    }

    public static String getSearchId(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        return sc.nextLine().trim();
    }
}
