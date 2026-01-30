package adminmangementsystem.com;

import java.util.ArrayList;

public class DoctorService {
    private ArrayList<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor d) {
        for (Doctor doc : doctors) {
            if (doc.getId().equals(d.getId())) {
                System.out.println("ID already exists.");
                return;
            }
        }
        doctors.add(d);
        System.out.println("Doctor added successfully.");
    }

    public void viewDoctors() {
        for (Doctor d : doctors) d.display();
    }

    public Doctor findById(String id) {
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) return d;
        }
        return null;
    }

    public void deleteDoctor(String id) {
        Doctor d = findById(id);
        if (d != null) {
            doctors.remove(d);
            System.out.println("Doctor deleted.");
        } else {
            System.out.println("Doctor not found.");
        }
    }
}
