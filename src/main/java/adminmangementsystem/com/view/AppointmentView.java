package adminmangementsystem.com.view;

import java.util.Scanner;
import adminmangementsystem.com.Validator;
import adminmangementsystem.com.model.Appointment;
import adminmangementsystem.com.model.Patient;
import adminmangementsystem.com.management.PatientSystem;

/**
 * COMPOSITION: AppointmentView creates appointments using existing Patient objects
 */
public class AppointmentView {

    /**
     * COMPOSITION: Creates appointment by linking to existing Patient
     */
    public static Appointment getAppointmentInput(Scanner sc, PatientSystem patientSystem) {
        System.out.println("\t\t------Appointment Management System------");
        
        // Get patient ID and search for existing patient
        String patientId = Validator.getNonEmpty(sc, "Enter Patient ID: ");
        Patient patient = patientSystem.searchPatientById(patientId);
        
        if (patient == null) {
            System.out.println("Error: Patient not found. Please register the patient first.");
            return null;
        }
        
        // Get appointment details
        String appointmentDate = Validator.getValidDateFomart(sc, "Enter appointment date (dd/mm/yyyy): ");
        String appointmentTime = Validator.getNonEmpty(sc, "Enter appointment time (e.g., 10:00 AM): ");
        
        return new Appointment(patient, appointmentDate, appointmentTime);
    }
}
