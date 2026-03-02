package adminmangementsystem.com.Management;

import java.util.List;
import adminmangementsystem.com.Model.Doctor;


public interface IDoctorSystem {
    
    void addDoctor(Doctor doctor);
    
    boolean updateDoctor(Doctor doctor);
    
    boolean deleteDoctor(String id);
    
    Doctor searchDoctorById(String id);
    
    List<Doctor> searchDoctorsByName(String name);
    
    List<Doctor> getAllDoctors();
    
    boolean isIdUnique(String id);
    
    void viewDoctors();
}
