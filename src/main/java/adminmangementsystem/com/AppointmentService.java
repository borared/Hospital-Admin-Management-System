package adminmangementsystem.com;

import java.util.ArrayList;

public class AppointmentService {
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment a) {
        appointments.add(a);
        System.out.println("Appointment scheduled.");
    }

    public void viewAppointments() {
        for (Appointment a : appointments) a.display();
    }
}
