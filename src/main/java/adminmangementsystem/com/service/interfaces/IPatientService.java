package adminmangementsystem.com.service.interfaces;

import java.util.List;
import adminmangementsystem.com.entity.Patient;

public interface IPatientService {

    void addPatient(Patient patient);

    boolean deletePatient(String id);

    boolean updatePatient(Patient patient);

    Patient searchPatientById(String id);

    List<Patient> getAllPatients();
}