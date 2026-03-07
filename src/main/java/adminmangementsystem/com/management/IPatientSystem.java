package adminmangementsystem.com.management;

import java.util.List;
import adminmangementsystem.com.model.Patient;

public interface IPatientSystem {

    void addPatient(Patient patient);

    boolean deletePatient(String id);

    boolean updatePatient(Patient patient);

    Patient searchPatientById(String id);

    List<Patient> getAllPatients();
}