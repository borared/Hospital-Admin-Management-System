package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adminmangementsystem.com.model.Appointment;
import adminmangementsystem.com.Validator;

public class AppointmentService implements IAppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    // --------- ADD APPOINTMENT ----------
    public void addAppointment(Scanner sc) {
            Appointment appointment = adminmangementsystem.com.view.AppointmentView.getAppointmentInput(sc);
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.\n");
        }


    // --------- VIEW APPOINTMENTS ----------
    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        String line = "------------------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n",
                "ID", "Patient Name", "DOB", "Phone", "Disease", "DOA");
        System.out.println(line);

        for (Appointment a : appointments) {
            System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n",
                    a.getPatientId(),
                    a.getPatientName(),
                    a.getPatientDOB(),
                    a.getPatientPhoneNum(),
                    a.getPatientDisease(),
                    a.getDOA());
        }

        System.out.println(line);
    }
}
