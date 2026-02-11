package adminmangementsystem.com.Management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adminmangementsystem.com.Model.Appointment;
import adminmangementsystem.com.Validator;

public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    // --------- ADD APPOINTMENT ----------
    public void addAppointment(Scanner sc) {

        System.out.println("\t\t------Appointment Management System------");

        String patientId = Validator.getNonEmpty(sc, "Enter Patient ID: ");
        String patientName = Validator.getOnlyLetter(sc, "Enter Patient Name: ");
        String patientDOB = Validator.getValidDateFomart(sc, "Enter patient Date of Birth (dd/mm/yyyy): ");
        String phoneNumber = Validator.getPhoneNumberLength(sc, "Enter patient Phone Number: ");
        String disease = Validator.getOnlyLetter(sc, "Enter patient disease: ");
        String DOA = Validator.getValidDateFomart(sc, "Enter date of appointment (dd/mm/yyyy): ");

        Appointment appointment = new Appointment(
                patientId,
                patientName,
                patientDOB,
                disease,
                phoneNumber,
                DOA
        );

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
