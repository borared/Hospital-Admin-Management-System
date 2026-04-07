package adminmangementsystem.com.management;

import java.util.List;
import adminmangementsystem.com.model.Patient;

public interface IPatientSystem {

    void addPatient(Patient patient);

    boolean deletePatient(String id);

    boolean updatePatient(Patient patient);


    // Overloaded search methods for patient lookup
    Patient searchPatient(String id);

    List<Patient> searchPatient(String name, String dob);

    List<Patient> getAllPatients();
}