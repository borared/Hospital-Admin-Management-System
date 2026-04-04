package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.model.Doctor;

/**
 * ENCAPSULATION: Manages doctor records privately
 */
public class DoctorSystem implements IDoctorSystem {
    
    // ENCAPSULATION: Private list of doctors
    private List<Doctor> records = new ArrayList<>();
    
    @Override
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null.");
        }
        
        if (!isIdUnique(doctor.getId())) {
            throw new IllegalArgumentException("Doctor ID already exists.");
        }
        
        records.add(doctor);
    }
    
    @Override
    public boolean updateDoctor(Doctor updatedDoctor) {
        if (updatedDoctor == null) {
            return false;
        }
        
        Doctor existingDoctor = searchDoctorById(updatedDoctor.getId());
        
        if (existingDoctor == null) {
            return false;
        }
        
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setDob(updatedDoctor.getDob());
        existingDoctor.setAddress(updatedDoctor.getAddress());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setPosition(updatedDoctor.getPosition());
        existingDoctor.setSalary(updatedDoctor.getSalary());
        existingDoctor.setDoe(updatedDoctor.getDoe());
        
        return true;
    }
    
    @Override
    public boolean deleteDoctor(String id) {
        Doctor doctor = searchDoctorById(id);
        if (doctor != null) {
            records.remove(doctor);
            return true;
        }
        return false;
    }
    
    @Override
    public Doctor searchDoctorById(String id) {
        for (Doctor doctor : records) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }
        return null;
    }
    
    @Override
    public List<Doctor> searchDoctorsByName(String name) {
        List<Doctor> results = new ArrayList<>();
        for (Doctor doctor : records) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                results.add(doctor);
            }
        }
        return results;
    }
    
    @Override
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(records);
    }
    
    @Override
    public boolean isIdUnique(String id) {
        return searchDoctorById(id) == null;
    }
    
    @Override
    public void viewDoctors() {
        viewDoctorList();
    }
    
    public void displayAll() {
        viewDoctorList();
    }

    public void addDoctor(Scanner sc) {
        Doctor doctor = adminmangementsystem.com.view.DoctorView.getDoctorInput(sc);
        
        if (!isIdUnique(doctor.getId())) {
            System.out.println("Error: Doctor ID already exists. Please use a unique ID.");
            return;
        }
        
        records.add(doctor);
        System.out.println("Doctor data captured successfully.\n");
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
    
    public void deleteDoctor(Scanner sc) {
        String deleteId = adminmangementsystem.com.view.DoctorView.getDoctorIdForDelete(sc);
        Doctor pToDelete = searchDoctorById(deleteId);
        if (pToDelete == null) {
            System.out.println("Doctor not found!");
        } else {
            records.remove(pToDelete);
            System.out.println("Doctor deleted successfully.");
        }
    }
    
    public void searchDoctor(Scanner sc) {
        int searchChoice = adminmangementsystem.com.view.DoctorView.getSearchChoice(sc);
        
        if (searchChoice == -1) {
            return;
        }
        
        String line = "----------------------------------------------------------------------------------------------------------------------------";
        
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
        System.out.println(line);
        
        if (searchChoice == 1) {
            String searchName = adminmangementsystem.com.view.DoctorView.getSearchName(sc);
            List<Doctor> results = searchDoctorsByName(searchName);
            
            if (results.isEmpty()) {
                System.out.println("|                                             No doctors found with that name                                              |");
            } else {
                for (Doctor d : results) {
                    d.display();  // Instance method
                }
            }
            
        } else if (searchChoice == 2) {
            String searchId = adminmangementsystem.com.view.DoctorView.getSearchId(sc);
            Doctor found = searchDoctorById(searchId);
            
            if (found != null) {
                found.display();  // Instance method
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
        
        if (records.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        
        String line = "-----------------------------------------------------------------------------------------------------------------------------------";
        
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
        System.out.println(line);
        
        for (Doctor d : records) {
            d.displayDoctor();  // Instance method - each doctor displays itself
        }
        
        System.out.println(line);
    }
    
    public void viewDoctorList1() {
        viewDoctorList();
    }

    public void viewDoctorsByPosition(String position, String title) {
        System.out.println("\t\t\t\t------" + title + "------");

        String line = "-----------------------------------------------------------------------------------------------------------------------------------";
        boolean found = false;

        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date");
        System.out.println(line);

        for (Doctor d : records) {
            if (d.getPosition() != null && d.getPosition().equalsIgnoreCase(position)) {
                found = true;
                d.displayDoctor();  // Instance method
            }
        }

        if (!found) {
            System.out.printf("| %-129s |\n", "No " + title.toLowerCase() + " found.");
        }

        System.out.println(line);
    }
}
