package adminmangementsystem.com.service;

import adminmangementsystem.com.entity.Doctor;
import adminmangementsystem.com.repository.HospitalRepository;
import adminmangementsystem.com.service.interfaces.IDoctorService;

import java.util.List;

public class DoctorService implements IDoctorService {
    
    private HospitalRepository repository;
    
    public DoctorService() {
        this.repository = HospitalRepository.getInstance();
    }
    
    @Override
    public int addDoctor(Doctor doctor) {
        return repository.addDoctor(doctor);
    }
    
    @Override
    public Doctor getDoctorById(int doctorId) {
        return repository.getDoctor(doctorId);
    }
    
    @Override
    public List<Doctor> getAllDoctors() {
        return repository.getAllDoctors();
    }
    
    @Override
    public boolean updateDoctor(Doctor doctor) {
        return repository.updateDoctor(doctor);
    }
    
    @Override
    public boolean deleteDoctor(int doctorId) {
        return repository.deleteDoctor(doctorId);
    }
    
    public void displayAllDoctors() {
        List<Doctor> doctors = getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        
        System.out.println("\n=== All Doctors ===");
        for (Doctor doctor : doctors) {
            doctor.display();
            System.out.println("-------------------");
        }
    }
}
