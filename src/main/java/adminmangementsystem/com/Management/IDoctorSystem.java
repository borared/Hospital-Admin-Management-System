// ...existing code...
package adminmangementsystem.com.management;

import adminmangementsystem.com.model.Doctor;

public interface IDoctorSystem {
    void addDoctor(java.util.Scanner sc);
    void updateDoctor(java.util.Scanner sc);
    void deleteDoctor(java.util.Scanner sc);
    void viewDoctors();
    Doctor searchDoctorById(String id);
}