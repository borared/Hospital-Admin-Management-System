package adminmangementsystem.com.service;

import adminmangementsystem.com.entity.Patient;
import adminmangementsystem.com.repository.HospitalRepository;
import adminmangementsystem.com.service.interfaces.IPatientService;

import java.util.List;

public class PatientService implements IPatientService {
    
    private HospitalRepository repository;
    
    public PatientService() {
        this.repository = HospitalRepository.getInstance();
    }
    
    @Override
    public int addPatient(Patient patient) {
        return repository.addPatient(patient);
    }
    
    @Override
    public Patient getPatientById(int patientId) {
        return repository.getPatient(patientId);
    }
    
    @Override
    public List<Patient> getAllPatients() {
        return repository.getAllPatients();
    }
    
    @Override
    public boolean updatePatient(Patient patient) {
        return repository.updatePatient(patient);
    }
    
    @Override
    public boolean deletePatient(int patientId) {
        return repository.deletePatient(patientId);
    }
    
    public void displayAllPatients() {
        List<Patient> patients = getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        
        System.out.println("\n=== All Patients ===");
        for (Patient patient : patients) {
            patient.displayPatientInfo();
            System.out.println("-------------------");
        }
    }
}
