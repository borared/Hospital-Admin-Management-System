package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;
import adminmangementsystem.com.model.Patient;

public class PatientSystem implements IPatientSystem {

    private List<Patient> patients = new ArrayList<>();

    // Add Patient
    @Override
    public void addPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }

        if (!isIdUnique(patient.getId())) {
            throw new IllegalArgumentException("Patient ID already exists.");
        }

        patients.add(patient);
    }

    // Delete Patient
    @Override
    public boolean deletePatient(String id) {
        Patient patient = searchPatientById(id);
        if (patient != null) {
            patients.remove(patient);
            return true;
        }
        return false;
    }

    // Update Patient
    @Override
    public boolean updatePatient(Patient updatedPatient) {
        if (updatedPatient == null) {
            return false;
        }

        Patient existingPatient = searchPatientById(updatedPatient.getId());

        if (existingPatient == null) {
            return false;
        }

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setDob(updatedPatient.getDob());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setDisease(updatedPatient.getDisease());
        existingPatient.setEntryDate(updatedPatient.getEntryDate());

        return true;
    }

    // Search Patient by ID
    @Override
    public Patient searchPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    // Get All Patients
    @Override
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients); // return copy for safety
    }

    // Check ID uniqueness
    private boolean isIdUnique(String id) {
        return searchPatientById(id) == null;
    }
}