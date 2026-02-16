package adminmangementsystem.com.Management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import adminmangementsystem.com.Validator;
import adminmangementsystem.com.Model.Doctor;


public class DoctorSystem {
    private List<Doctor> doctors = new ArrayList<>();

    //---ADD Doctor---
    public void addDoctor(Scanner sc){
        System.out.println("\t\t------Add Doctor------");

            String doctorId = Validator.getValidPatient(sc, "Enter Doctor ID: ");
            String doctorName = Validator.getOnlyLetter(sc, "Enter Doctor name: ");
            String doctorDOB = Validator.getValidDateFomart(sc, "Enter Doctor Date of Birth (dd/mm/yyyy): ");
            String doctorAddress = Validator.getNonEmpty(sc, "Enter Doctor address: ");
            String doctorEmail = Validator.getValidEmail(sc, "Enter Doctor email: ");
            String doctorPosition = Validator.getOnlyLetter(sc, "Enter Doctor position: ");
            Double doctorSalary = Validator.getPositiveDouble(sc, "Enter Doctor salary: ");
            String doctorDOE = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
            Doctor doctor = new Doctor(doctorId, doctorName, doctorDOB, doctorAddress, doctorEmail, doctorPosition, doctorSalary, doctorDOE);
            doctors.add(doctor);

            System.out.println("Doctor data captured successfully.\n");
    }

    public void updateDoctor(Scanner sc) {

    String updateId = Validator.getNonEmpty(sc, "Enter Doctor ID to update: ");
    Doctor dToUpdate = searchDoctorById(updateId);

    if (dToUpdate == null) {
        System.out.println("Doctor not found!");
        return;
    }

    System.out.println("Leave blank to keep current value.");

    // --- Update Name ---
    System.out.print("Enter new Name (" + dToUpdate.getName() + "): ");
    String newName = sc.nextLine().trim();
    if (!newName.isEmpty()) {
        if (newName.matches("[a-zA-Z ]{1,50}")) {
            dToUpdate.setName(newName);
        } else {
            System.out.println("Invalid Name! Keeping old value.");
        }
    }

    // --- Update DOB ---
    System.out.print("Enter new DOB (" + dToUpdate.getDob() + ") [dd/MM/yyyy]: ");
    String newDob = sc.nextLine().trim();
    if (!newDob.isEmpty()) {
        if (newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            dToUpdate.setDob(newDob);
        } else {
            System.out.println("Invalid DOB format! Keeping old value.");
        }
    }

    // --- Update Address ---
    System.out.print("Enter new Address (" + dToUpdate.getAddress() + "): ");
    String newAddress = sc.nextLine().trim();
    if (!newAddress.isEmpty()) {
        dToUpdate.setAddress(newAddress);
    }

    // --- Update Email ---
    System.out.print("Enter new Email (" + dToUpdate.getEmail() + "): ");
    String newEmail = sc.nextLine().trim();
    if (!newEmail.isEmpty()) {
        if (newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            dToUpdate.setEmail(newEmail);
        } else {
            System.out.println("Invalid Email format! Keeping old value.");
        }
    }

    // --- Update Position ---
    System.out.print("Enter new Position (" + dToUpdate.getPosition() + "): ");
    String newPosition = sc.nextLine().trim();
    if (!newPosition.isEmpty()) {
        dToUpdate.setPosition(newPosition);
    }

    // --- Update Salary ---
    System.out.print("Enter new Salary (" + dToUpdate.getSalary() + "): ");
    String newSalary = sc.nextLine().trim();
    if (!newSalary.isEmpty()) {
        try {
            double salaryValue = Double.parseDouble(newSalary);
            if (salaryValue > 0) {
                dToUpdate.setSalary(salaryValue);
            } else {
                System.out.println("Salary must be positive! Keeping old value.");
            }
        } catch (Exception e) {
            System.out.println("Invalid salary! Keeping old value.");
        }
    }

    // --- Update Date of Entry ---
    System.out.print("Enter new Entry Date (" + dToUpdate.getDoe() + ") [dd/MM/yyyy]: ");
    String newDoe = sc.nextLine().trim();
    if (!newDoe.isEmpty()) {
        if (newDoe.matches("\\d{2}/\\d{2}/\\d{4}")) {
            dToUpdate.setDoe(newDoe);
        } else {
            System.out.println("Invalid Entry Date format! Keeping old value.");
        }
    }

    System.out.println("Doctor updated successfully.");
    }


      //Search Doctor by ID
    public Doctor searchDoctorById(String id) {
    for (Doctor p : doctors) {
        if (p.getId().equalsIgnoreCase(id)) {
            return p;
        }
    }
    return null;
    }

    //Check ID
    public boolean isIdUnique(String id) {
    for (Doctor p : doctors) {
        if (p.getId().equalsIgnoreCase(id)) {
            return false; // ID already exists
        }
    }
    return true; // ID is unique
    }

    public void deleteDoctor(Scanner sc){
        System.out.print("Enter doctor ID to delete: ");
        String deleteId = sc.nextLine();
        Doctor pToDelete = searchDoctorById(deleteId);
        if (pToDelete == null) {
            System.out.println("Doctor not found!");
        } else {
            doctors.remove(pToDelete);
            System.out.println("Patient deleted successfully.");
        }
    }

    public void searchDoctor(Scanner sc) {

    System.out.print("Search by (1) Name or (2) ID: ");
    int searchChoice;

    try {
        searchChoice = Integer.parseInt(sc.nextLine());
    } catch (Exception e) {
        System.out.println("Invalid choice!");
        return;
    }

    String line = "----------------------------------------------------------------------------------------------------------------------------";

    // ---- TABLE HEADER ----
    System.out.println(line);
    System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s |\n",
            "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
    System.out.println(line);

    if (searchChoice == 1) { // Search by Name

        System.out.print("Enter Doctor name: ");
        String searchName = sc.nextLine().trim();
        boolean foundAny = false;

        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(searchName)) {
                System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                        d.getId(),
                        d.getName(),
                        d.getDob(),
                        d.getAddress(),
                        d.getEmail(),
                        d.getPosition(),
                        d.getSalary(),
                        d.getDoe());
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("|                                             No doctors found with that name                                              |");
        }

    } else if (searchChoice == 2) { // Search by ID

        System.out.print("Enter Doctor ID: ");
        String searchId = sc.nextLine().trim();
        Doctor found = searchDoctorById(searchId);

        if (found != null) {
            System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                    found.getId(),
                    found.getName(),
                    found.getDob(),
                    found.getAddress(),
                    found.getEmail(),
                    found.getPosition(),
                    found.getSalary(),
                    found.getDoe());
        } else {
            System.out.println("|                                                     Doctor not found                                                      |");
        }

    } else {
        System.out.println("Invalid choice!");
    }

    System.out.println(line);
    }

    //View List
    public void viewDoctorList() {
    System.out.println("\t\t\t\t------Doctor List------");

    if (doctors.isEmpty()) {
        System.out.println("No doctors found.");
        return;
    }

    String line = "-----------------------------------------------------------------------------------------------------------------------------------";

    System.out.println(line);
    System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s |\n",
            "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
    System.out.println(line);

    for (Doctor d : doctors) {
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | $%-9.2f | %-12s |\n",
                d.getId(),
                d.getName(),
                d.getDob(),
                d.getAddress(),
                d.getEmail(),
                d.getPosition(),
                d.getSalary(),
                d.getDoe());
    }

    System.out.println(line);

    // Full-width total row (matches table width exactly)
    System.out.printf("| %-128s |\n", "Total Doctors: " + doctors.size());

    System.out.println(line);
}






}
