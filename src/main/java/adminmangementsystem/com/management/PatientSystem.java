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

    // Scanner-based methods for controller interaction
    
    public void addPatient(java.util.Scanner sc) {
        Patient patient = adminmangementsystem.com.view.PatientView.getPatientInput(sc);
        
        if (!isIdUnique(patient.getId())) {
            System.out.println("Error: Patient ID already exists. Please use a unique ID.");
            return;
        }
        
        patients.add(patient);
        System.out.println("Patient data captured successfully.\n");
    }
    
    public void updatePatient(java.util.Scanner sc) {
        String updateId = adminmangementsystem.com.view.PatientView.getSearchId(sc);
        Patient pToUpdate = searchPatientById(updateId);
        
        if (pToUpdate == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        adminmangementsystem.com.view.PatientView.getPatientUpdateInput(sc, pToUpdate);
        System.out.println("Patient updated successfully.");
    }
    
    public void deletePatient(java.util.Scanner sc) {
        String deleteId = adminmangementsystem.com.view.PatientView.getPatientIdForDelete(sc);
        Patient pToDelete = searchPatientById(deleteId);
        
        if (pToDelete == null) {
            System.out.println("Patient not found!");
        } else {
            patients.remove(pToDelete);
            System.out.println("Patient deleted successfully.");
        }
    }
    
    public void searchPatient(java.util.Scanner sc) {
        int searchChoice = adminmangementsystem.com.view.PatientView.getSearchChoice(sc);
        
        if (searchChoice == -1) {
            return;
        }
        
        String line = "----------------------------------------------------------------------------------------------------------------------------";
        
        // TABLE HEADER
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
        System.out.println(line);
        
        if (searchChoice == 1) { // Search by Name
            String searchName = adminmangementsystem.com.view.PatientView.getSearchName(sc);
            boolean foundAny = false;
            
            for (Patient p : patients) {
                if (p.getName().equalsIgnoreCase(searchName)) {
                    System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                            p.getId(), p.getName(), p.getDob(), p.getAddress(),
                            p.getDisease(), p.getEntryDate());
                    foundAny = true;
                }
            }
            
            if (!foundAny) {
                System.out.println("|                                             No patients found with that name                                              |");
            }
            
        } else if (searchChoice == 2) { // Search by ID
            String searchId = adminmangementsystem.com.view.PatientView.getSearchId(sc);
            Patient found = searchPatientById(searchId);
            
            if (found != null) {
                System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                        found.getId(), found.getName(), found.getDob(), found.getAddress(),
                        found.getDisease(), found.getEntryDate());
            } else {
                System.out.println("|                                                     Patient not found                                                      |");
            }
            
        } else {
            System.out.println("Invalid choice!");
        }
        
        System.out.println(line);
    }

    public void viewPatientList(java.util.Scanner sc) {
        System.out.println("\t\t\t\t------Patient List------");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        String line = "----------------------------------------------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
        System.out.println(line);

        for (Patient p : patients) {
            System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                    p.getId(), p.getName(), p.getDob(), p.getAddress(),
                    p.getDisease(), p.getEntryDate());
        }

        System.out.println(line);
    }
}
