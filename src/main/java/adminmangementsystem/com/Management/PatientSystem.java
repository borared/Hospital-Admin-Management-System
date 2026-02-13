package adminmangementsystem.com.Management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adminmangementsystem.com.Model.Patient;
import adminmangementsystem.com.Validator;

public class PatientSystem {

    static List<Patient> patients = new ArrayList<>();

    //---ADD Patient---
    public void addPatient(Scanner sc){
        System.out.println("\t\t------Add Patient------");

            String patId = Validator.getValidPatient(sc, "Enter Patient ID: ");
            String patName = Validator.getOnlyLetter(sc, "Enter patient name: ");
            String patDOB = Validator.getValidDateFomart(sc, "Enter patient Date of Birth (dd/mm/yyyy): ");
            String patAddress = Validator.getNonEmpty(sc, "Enter patient address: ");
            String patDisease = Validator.getNonEmpty(sc, "Enter patient disease: ");
            String patDOE = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
            Patient patient = new Patient(patId, patName, patDOB, patDisease, patAddress, patDOE);
            patients.add(patient);

            System.out.println("Patient data captured successfully.\n");
    }
      //Search Patient by ID
    public static Patient searchPatientById(String id) {
    for (Patient p : patients) {
        if (p.getId().equalsIgnoreCase(id)) {
            return p;
        }
    }
    return null;
    }

    //Check ID
    public static boolean isIdUnique(String id) {
    for (Patient p : patients) {
        if (p.getId().equalsIgnoreCase(id)) {
            return false; // ID already exists
        }
    }
    return true; // ID is unique
    }

    public void updatePatient(Scanner sc){
        String updateId = Validator.getValidUpdatePatientId(sc, "Enter patient ID to update: ");
        Patient pToUpdate = searchPatientById(updateId);

        if (pToUpdate == null) {
           System.out.println("Patient not found!");
        } else {

        System.out.println("Leave blank to keep current value.");

        // --- Update Name ---
        System.out.print("Enter new Name (" + pToUpdate.getName() + "): ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            pToUpdate.setName(newName);
        }

        // --- Update DOB ---
        System.out.print("Enter new DOB (" + pToUpdate.getDob() + ") [dd/MM/yyyy]: ");
        String newDob = sc.nextLine().trim();
        if (!newDob.isEmpty()) {
            if (newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
                pToUpdate.setDob(newDob);
            } else {
                System.out.println("Invalid DOB format! Keeping old value.");
            }
        }

        // --- Update Address ---
        System.out.print("Enter new Address (" + pToUpdate.getAddress() + "): ");
        String newAddress = sc.nextLine().trim();
        if (!newAddress.isEmpty()) {
            pToUpdate.setAddress(newAddress);
        }
    
        // --- Update Disease ---
        System.out.print("Enter new Disease (" + pToUpdate.getDisease() + "): ");
        String newDisease = sc.nextLine().trim();
        if (!newDisease.isEmpty()) {
            pToUpdate.setDisease(newDisease);
        }

        // --- Update Entry Date ---
        System.out.print("Enter new Entry Date (" + pToUpdate.getEntryDate() + ") [dd/MM/yyyy]: ");
        String newDOE = sc.nextLine().trim();
        if (!newDOE.isEmpty()) {
            if (newDOE.matches("\\d{2}/\\d{2}/\\d{4}")) {
                pToUpdate.setEntryDate(newDOE);
            } else {
                System.out.println("Invalid Entry Date format! Keeping old value.");
            }
        }

        System.out.println("Patient updated successfully.");
    }

    }

    public void deletePatient(Scanner sc){
        System.out.print("Enter patient ID to delete: ");
        String deleteId = sc.nextLine();
        Patient pToDelete = searchPatientById(deleteId);
        if (pToDelete == null) {
            System.out.println("Patient not found!");
        } else {
            patients.remove(pToDelete);
            System.out.println("Patient deleted successfully.");
        }
    }

    public void searchPatient(Scanner sc){
        System.out.print("Search by (1) Name or (2) ID: ");
            int searchChoice = Integer.parseInt(sc.nextLine());
            String line = "------------------------------------------------------------------------------------------------------------";

            if (searchChoice == 1) { // Search by Name
                System.out.print("Enter patient name: ");
                String searchName = sc.nextLine();
                boolean foundAny = false;

                // Table header
                System.out.println(line);
                System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n", 
                                "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
                System.out.println(line);

                for (Patient p : patients) {
                    if (p.getName().equalsIgnoreCase(searchName)) {
                        System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n",
                                        p.getId(), p.getName(), p.getDob(), p.getAddress(), p.getDisease(), p.getEntryDate());
                        foundAny = true;
                    }
                }

                if (!foundAny) {
                    System.out.println("|                        No patients found with that name                        |");
                }

                System.out.println(line);

                } else if (searchChoice == 2) { // Search by ID
                    System.out.print("Enter patient ID: ");
                    String searchId = sc.nextLine();
                    Patient found = searchPatientById(searchId);

                    // Table header
                    System.out.println(line);
                    System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n", 
                                    "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
                    System.out.println(line);

                    if (found != null) {
                        System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n",
                                        found.getId(), found.getName(), found.getDob(), found.getAddress(), found.getDisease(), found.getEntryDate());
                    } else {
                        System.out.println("|                               Patient not found                               |");
                    }

                    System.out.println(line);

                    } else {
                        System.out.println("Invalid choice!");
                    }
    }

    public void viewPatientList(Scanner sc){
        System.out.println("\t\t\t\t------Patient List------");

                    if (patients.isEmpty()) {
                        System.out.println("No patients found.");
                    } else {
                        String line = "------------------------------------------------------------------------------------------------------------";

                        System.out.println(line);
                        System.out.printf("| %-5s | %-20s | %-12s | %-15s | %-15s | %-12s |\n", 
                                          "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
                        System.out.println(line);

                        for (Patient p : patients) {
                            System.out.printf("| %-5s | %-20s | %-12s | %-15s | %-15s | %-12s |\n", 
                                              p.getId(), p.getName(), p.getDob(), p.getAddress(), p.getDisease(), p.getEntryDate());
                        }
                        System.out.println(line);
                    }
    }
}
