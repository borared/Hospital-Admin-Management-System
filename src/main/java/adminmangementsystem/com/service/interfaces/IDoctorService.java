package adminmangementsystem.com.service.interfaces;

import java.util.List;
import adminmangementsystem.com.entity.Doctor;


public interface IDoctorService {
    
    void addDoctor(Doctor doctor);
    
    boolean updateDoctor(Doctor doctor);
    
    boolean deleteDoctor(String id);
    
    Doctor searchDoctorById(String id);
    
    List<Doctor> searchDoctorsByName(String name);
    
    List<Doctor> getAllDoctors();
    
    boolean isIdUnique(String id);
    
    void viewDoctors();
}
