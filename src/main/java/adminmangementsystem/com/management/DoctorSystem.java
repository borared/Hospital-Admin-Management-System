package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.model.Doctor;

/**
 * Implementation of IDoctorSystem interface.
 * Manages doctor records with CRUD operations and search functionality.
 */
public class DoctorSystem implements IDoctorSystem {
    
    private List<Doctor> doctors = new ArrayList<>();

    // Core interface methods (business logic)
    
    @Override
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null.");
        }
        
        if (!isIdUnique(doctor.getId())) {
            throw new IllegalArgumentException("Doctor ID already exists.");
        }
        
        doctors.add(doctor);
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
            doctors.remove(doctor);
            return true;
        }
        return false;
    }
    
    @Override
    public Doctor searchDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equalsIgnoreCase(id)) {
                return doctor;
            }
        }
        return null;
    }
    
    @Override
    public List<Doctor> searchDoctorsByName(String name) {
        List<Doctor> results = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                results.add(doctor);
            }
        }
        return results;
    }
    
    @Override
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
    
    @Override
    public boolean isIdUnique(String id) {
        return searchDoctorById(id) == null;
    }
    
    @Override
    public void viewDoctors() {
        viewDoctorList();
    }

    // UI interaction methods (Scanner-based for controller)
    
    public void addDoctor(Scanner sc) {
        Doctor doctor = adminmangementsystem.com.view.DoctorView.getDoctorInput(sc);
        
        if (!isIdUnique(doctor.getId())) {
            System.out.println("Error: Doctor ID already exists. Please use a unique ID.");
            return;
        }
        
        doctors.add(doctor);
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
            List<Doctor> results = searchDoctorsByName(searchName);
            
            if (results.isEmpty()) {
                System.out.println("|                                             No doctors found with that name                                              |");
            } else {
                for (Doctor d : results) {
                    System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s |\n",
                            d.getId(), d.getName(), d.getDob(), d.getAddress(), d.getEmail(),
                            d.getPosition(), d.getSalary(), d.getDoe());
                }
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
                    d.getId(), d.getName(), d.getDob(), d.getAddress(),
                    d.getEmail(), d.getPosition(), d.getSalary(), d.getDoe());
        }
        
        System.out.println(line);
    }
    
    public void viewDoctorList1() {
        viewDoctorList();
    }
}
