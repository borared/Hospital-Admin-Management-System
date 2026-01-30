package adminmangementsystem.com;

import java.util.ArrayList;

public class PatientService {
    private ArrayList<Patient> patients = new ArrayList<>();

    public void addPatient(Patient p) {
        for (Patient pt : patients) {
            if (pt.getId().equals(p.getId())) {
                System.out.println("ID already exists.");
                return;
            }
        }
        patients.add(p);
        System.out.println("Patient added successfully.");
    }

    public void viewPatients() {
        for (Patient p : patients) p.display();
    }

    public Patient findById(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public void deletePatient(String id) {
        Patient p = findById(id);
        if (p != null) {
            patients.remove(p);
            System.out.println("Patient deleted.");
        } else {
            System.out.println("Patient not found.");
        }
    }
}
