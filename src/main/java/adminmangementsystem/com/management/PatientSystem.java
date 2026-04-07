package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;
import adminmangementsystem.com.model.Patient;

/**
 * ENCAPSULATION: Manages patient records privately
 */
public class PatientSystem implements IPatientSystem {
    
    // ENCAPSULATION: Private list of patients
    private List<Patient> records = new ArrayList<>();

    @Override
    public void addPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }

        if (!isIdUnique(patient.getId())) {
            throw new IllegalArgumentException("Patient ID already exists.");
        }

        records.add(patient);
    }

    @Override
    public boolean deletePatient(String id) {
        Patient patient = searchPatient(id);
        if (patient != null) {
            records.remove(patient);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePatient(Patient updatedPatient) {
        if (updatedPatient == null) {
            return false;
        }

        Patient existingPatient = searchPatient(updatedPatient.getId());

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

  

    @Override
    public Patient searchPatient(String id) {
        for (Patient patient : records) {
            if (patient.getId().equalsIgnoreCase(id)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public List<Patient> searchPatient(String name, String dob) {
        List<Patient> results = new ArrayList<>();
        for (Patient p : records) {
            if (p.getName().equalsIgnoreCase(name)) {
                results.add(p);
            }
        }
        return results;
    }                        

    @Override
    public List<Patient> getAllPatients() {
        return new ArrayList<>(records);
    }
    
    private boolean isEmpty() {
        return records.isEmpty();
    }

    private boolean isIdUnique(String id) {
        return searchPatient(id) == null;
    }
    
    public void displayAll() {
        viewPatientList(null);
    }

    public void addPatient(java.util.Scanner sc) {
        Patient patient = adminmangementsystem.com.view.PatientView.getPatientInput(sc);
        
        if (!isIdUnique(patient.getId())) {
            System.out.println("Error: Patient ID already exists. Please use a unique ID.");
            return;
        }
        
        records.add(patient);
        System.out.println("Patient data captured successfully.\n");
    }
    
    public void updatePatient(java.util.Scanner sc) {
        String updateId = adminmangementsystem.com.view.PatientView.getSearchId(sc);
        Patient pToUpdate = searchPatient(updateId);
        
        if (pToUpdate == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        adminmangementsystem.com.view.PatientView.getPatientUpdateInput(sc, pToUpdate);
        System.out.println("Patient updated successfully.");
    }
    
    public void deletePatient(java.util.Scanner sc) {
        String deleteId = adminmangementsystem.com.view.PatientView.getPatientIdForDelete(sc);
        Patient pToDelete = searchPatient(deleteId);
        
        if (pToDelete == null) {
            System.out.println("Patient not found!");
        } else {
            records.remove(pToDelete);
            System.out.println("Patient deleted successfully.");
        }
    }
    
    public void searchPatient(java.util.Scanner sc) {
        int searchChoice = adminmangementsystem.com.view.PatientView.getSearchChoice(sc);
        
        if (searchChoice == -1) {
            return;
        }
        
        String line = "----------------------------------------------------------------------------------------------------------------------------";
        
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
        System.out.println(line);
        
        if (searchChoice == 1) {
            String searchName = adminmangementsystem.com.view.PatientView.getSearchName(sc);
            List<Patient> results = new ArrayList<>();
            
            for (Patient p : records) {
                if (p.getName().equalsIgnoreCase(searchName)) {
                    results.add(p);
                }
            }
            
            if (results.isEmpty()) {
                System.out.println("|                                             No patients found with that name                                              |");
            } else {
                for (Patient p : results) {
                    p.displayPatientInfo();  // Instance method
                }
            }
            
        } else if (searchChoice == 2) {
            String searchId = adminmangementsystem.com.view.PatientView.getSearchId(sc);
            Patient found = searchPatient(searchId);
            
            if (found != null) {
                found.displayPatientInfo();  // Instance method
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

        if (isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        String line = "----------------------------------------------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
        System.out.println(line);

        for (Patient p : records) {
            p.displayPatientInfo();  // Instance method - each patient displays itself
        }

        System.out.println(line);
    }
}
