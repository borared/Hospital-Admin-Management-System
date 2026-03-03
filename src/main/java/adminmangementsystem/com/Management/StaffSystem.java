package adminmangementsystem.com.Management;

import adminmangementsystem.com.Model.*;
import java.util.ArrayList;
import java.util.List;

public class StaffSystem {
    
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private List<Surgeon> surgeons;
    private List<Cardiologist> cardiologists;

    public StaffSystem() {
        this.doctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.surgeons = new ArrayList<>();
        this.cardiologists = new ArrayList<>();
    }

    // Add staff based on position - POLYMORPHISM IN ACTION
    public Staff addStaff(String id, String name, String dob, String address,
                         String email, String position, double salary, String doe) {
        
        Staff newStaff = null;
        String positionLower = position.toLowerCase();
        
        // Create appropriate staff type based on position
        if (positionLower.contains("surgeon")) {
            Surgeon surgeon = new Surgeon(id, name, dob, address, email, position, salary, doe);
            surgeon.setSurgerySpecialty(position);
            surgeons.add(surgeon);
            newStaff = surgeon;
            
        } else if (positionLower.contains("cardiologist") || positionLower.contains("cardiology")) {
            Cardiologist cardiologist = new Cardiologist(id, name, dob, address, email, position, salary, doe);
            cardiologists.add(cardiologist);
            newStaff = cardiologist;
            
        } else if (positionLower.contains("nurse")) {
            Nurse nurse = new Nurse(id, name, dob, address, email, position, salary, doe);
            nurses.add(nurse);
            newStaff = nurse;
            
        } else {
            // Default to Doctor for other medical positions
            Doctor doctor = new Doctor(id, name, dob, address, email, position, salary, doe);
            doctors.add(doctor);
            newStaff = doctor;
        }
        
        return newStaff;
    }

    // Get all staff combined
    public List<Staff> getAllStaff() {
        List<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(doctors);
        allStaff.addAll(surgeons);
        allStaff.addAll(cardiologists);
        allStaff.addAll(nurses);
        return allStaff;
    }

    // Get staff by category
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public List<Surgeon> getSurgeons() {
        return surgeons;
    }

    public List<Cardiologist> getCardiologists() {
        return cardiologists;
    }

    // Search staff by ID across all types
    public Staff searchStaffById(String id) {
        for (Staff staff : getAllStaff()) {
            if (staff.getId().equals(id)) {
                return staff;
            }
        }
        return null;
    }

    // Delete staff by ID
    public boolean deleteStaff(String id) {
        Staff staff = searchStaffById(id);
        if (staff == null) return false;
        
        if (staff instanceof Surgeon) {
            return surgeons.remove(staff);
        } else if (staff instanceof Cardiologist) {
            return cardiologists.remove(staff);
        } else if (staff instanceof Nurse) {
            return nurses.remove(staff);
        } else if (staff instanceof Doctor) {
            return doctors.remove(staff);
        }
        return false;
    }

    // Get staff type name
    public String getStaffType(Staff staff) {
        if (staff instanceof Surgeon) return "Surgeon";
        if (staff instanceof Cardiologist) return "Cardiologist";
        if (staff instanceof Nurse) return "Nurse";
        if (staff instanceof Doctor) return "Doctor";
        return "Unknown";
    }

    // Get count by type
    public int getDoctorCount() {
        return doctors.size();
    }

    public int getNurseCount() {
        return nurses.size();
    }

    public int getSurgeonCount() {
        return surgeons.size();
    }

    public int getCardiologistCount() {
        return cardiologists.size();
    }

    public int getTotalStaffCount() {
        return doctors.size() + nurses.size() + surgeons.size() + cardiologists.size();
    }
}
