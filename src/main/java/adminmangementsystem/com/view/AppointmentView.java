package adminmangementsystem.com.view;

import java.util.Scanner;
import adminmangementsystem.com.Validator;
import adminmangementsystem.com.model.Appointment;

public class AppointmentView {

    public static Appointment getAppointmentInput(Scanner sc) {
        System.out.println("\t\t------Appointment Management System------");
        
        String patientId = Validator.getNonEmpty(sc, "Enter Patient ID: ");
        String patientName = Validator.getOnlyLetter(sc, "Enter Patient Name: ");
        String patientDOB = Validator.getValidDateFomart(sc, "Enter patient Date of Birth (dd/mm/yyyy): ");
        String phoneNumber = Validator.getPhoneNumberLength(sc, "Enter patient Phone Number: ");
        String disease = Validator.getOnlyLetter(sc, "Enter patient disease: ");
        String DOA = Validator.getValidDateFomart(sc, "Enter date of appointment (dd/mm/yyyy): ");
        
        return new Appointment(patientId, patientName, patientDOB, disease, phoneNumber, DOA);
    }
}
