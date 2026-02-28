package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import adminmangementsystem.com.Validator;
import adminmangementsystem.com.model.Doctor;


public class DoctorSystem implements IDoctorSystem {
    public void viewDoctorList1() {
        System.out.println("\t\t\t\t------Doctor List------");

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        String line = "------------------------------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s | %-10s | %-12s |\n",
            "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
        System.out.println(line);

        for (Doctor d : doctors) {
            System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s | %-10.2f | %-12s |\n",
                d.getId(), d.getName(), d.getDob(), d.getAddress(), d.getEmail(), d.getPosition(), d.getSalary(), d.getDoe());
        }
        System.out.println(line);
    }
    private List<Doctor> doctors = new ArrayList<>();

    //---ADD Doctor---
    public void addDoctor(Scanner sc){
            Doctor doctor = adminmangementsystem.com.view.DoctorView.getDoctorInput(sc);

            if (!isIdUnique(doctor.getId())) {
                System.out.println("Error: Doctor ID already exists. Please use a unique ID.");
                return;
            }

            doctors.add(doctor);
            System.out.println("Doctor data captured successfully.\n");
        }


    @Override
    public void viewDoctors() {
        viewDoctorList1();
    }

    public void updateDoctor(Scanner sc) {
            String updateId = adminmangementsystem.com.view.DoctorView.getUpdateDoctorId(sc);
            Doctor dToUpdate = searchDoctorById(updateId);

            if (dToUpdate == null) {
                System.out.println("Doctor not found!");
                return;
            }

            adminmangementsystem.com.view.DoctorView.getDoctorUpdateInput(sc, dToUpdate);
            System.out.println("Doctor updated successfully.");
        }



      //Search Doctor by ID
    @Override
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
            String deleteId = adminmangementsystem.com.view.DoctorView.getDoctorIdForDelete(sc);
            Doctor pToDelete = searchDoctorById(deleteId);
            if (pToDelete == null) {
                System.out.println("Doctor not found!");
            } else {
                doctors.remove(pToDelete);
                System.out.println("Doctor deleted successfully.");
            }
        }


    public void searchDoctor(Scanner sc) {
        int searchChoice = adminmangementsystem.com.view.DoctorView.getSearchChoice(sc);
        
        if (searchChoice == -1) {
            return;
        }

        String line = "----------------------------------------------------------------------------------------------------------------------------";

        // TABLE HEADER
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
        System.out.println(line);

        if (searchChoice == 1) { // Search by Name
            String searchName = adminmangementsystem.com.view.DoctorView.getSearchName(sc);
            boolean foundAny = false;

            for (Doctor d : doctors) {
                if (d.getName().equalsIgnoreCase(searchName)) {
                    System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                            d.getId(), d.getName(), d.getDob(), d.getAddress(), d.getEmail(),
                            d.getPosition(), d.getSalary(), d.getDoe());
                    foundAny = true;
                }
            }

            if (!foundAny) {
                System.out.println("|                                             No doctors found with that name                                              |");
            }

        } else if (searchChoice == 2) { // Search by ID
            String searchId = adminmangementsystem.com.view.DoctorView.getSearchId(sc);
            Doctor found = searchDoctorById(searchId);

            if (found != null) {
                System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                        found.getId(), found.getName(), found.getDob(), found.getAddress(),
                        found.getEmail(), found.getPosition(), found.getSalary(), found.getDoe());
            } else {
                System.out.println("|                                                     Doctor not found                                                      |");
            }

        } else {
            System.out.println("Invalid choice!");
        }

        System.out.println(line);
    }

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
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
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
    }




}
