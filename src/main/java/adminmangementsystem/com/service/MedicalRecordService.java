package adminmangementsystem.com.service;

import adminmangementsystem.com.entity.MedicalRecord;
import adminmangementsystem.com.repository.HospitalRepository;

import java.util.List;

public class MedicalRecordService {
    
    private HospitalRepository repository;
    
    public MedicalRecordService() {
        this.repository = HospitalRepository.getInstance();
    }
    
    public int addMedicalRecord(MedicalRecord record) {
        return repository.addMedicalRecord(record);
    }
    
    public List<MedicalRecord> getPatientMedicalHistory(int patientId) {
        return repository.getMedicalRecordsByPatient(patientId);
    }
    
    public void displayPatientMedicalHistory(int patientId) {
        List<MedicalRecord> records = getPatientMedicalHistory(patientId);
        if (records.isEmpty()) {
            System.out.println("No medical records found for patient ID: " + patientId);
            return;
        }
        
        System.out.println("\n=== Medical History for Patient " + patientId + " ===");
        for (MedicalRecord record : records) {
            System.out.println("Record ID: " + record.getRecordId());
            System.out.println("Doctor ID: " + record.getDoctorId());
            System.out.println("Diagnosis: " + record.getDiagnosis());
            System.out.println("Treatment: " + record.getTreatment());
            System.out.println("Date: " + record.getRecordDate());
            System.out.println("-------------------");
        }
    }
}
