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
    
    public Staff addStaff(String id, String name, String dob, String address,
                         String email, String position, double salary, String doe) {
        
        Staff newStaff = null;
        String positionLower = position.toLowerCase();
        
        if (positionLower.contains("surgeon")) {
            newStaff = new Surgeon(id, name, dob, address, email, position, salary, doe);
            surgeons.add((Surgeon) newStaff);
        } else if (positionLower.contains("cardiologist") || positionLower.contains("cardiology")) {
            newStaff = new Cardiologist(id, name, dob, address, email, position, salary, doe);
            cardiologists.add((Cardiologist) newStaff);
        } else if (positionLower.contains("nurse")) {
            newStaff = new Nurse(id, name, dob, address, email, position, salary, doe);
            nurses.add((Nurse) newStaff);
        } else {
            newStaff = new Doctor(id, name, dob, address, email, position, salary, doe);
            doctors.add((Doctor) newStaff);
        }
        
        return newStaff;
    }
    
    public List<Staff> getAllStaff() {
        List<Staff> allStaff = new ArrayList<>();
        allStaff.addAll(doctors);
        allStaff.addAll(nurses);
        allStaff.addAll(surgeons);
        allStaff.addAll(cardiologists);
        return allStaff;
    }
    
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
    
    public Staff searchStaffById(String id) {
        for (Staff staff : getAllStaff()) {
            if (staff.getId().equals(id)) {
                return staff;
            }
        }
        return null;
    }
    
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
    
    public Staff findStaffByQRCode(String qrCode) {
        for (Staff staff : getAllStaff()) {
            if (staff.getQrCode().equals(qrCode)) {
                return staff;
            }
        }
        return null;
    }
    
    public int getActiveStaffCount() {
        int count = 0;
        for (Staff staff : getAllStaff()) {
            if (staff.isActive()) {
                count++;
            }
        }
        return count;
    }
    
    public int getInactiveStaffCount() {
        return getTotalStaffCount() - getActiveStaffCount();
    }
}
