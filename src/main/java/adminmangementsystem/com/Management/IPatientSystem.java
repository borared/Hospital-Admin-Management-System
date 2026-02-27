package adminmangementsystem.com.management;
import adminmangementsystem.com.model.Patient;


public interface IPatientSystem {
    void addPatient(java.util.Scanner sc);
    void viewPatients();
    void deletePatient(java.util.Scanner sc);
    void updatePatient(java.util.Scanner sc);
    Patient searchPatientById(String id);
}