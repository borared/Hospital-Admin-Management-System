package adminmangementsystem.com.Management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adminmangementsystem.com.Model.Patient;
import adminmangementsystem.com.Validator;

public class PatientSystem {

    static List<Patient> patients = new ArrayList<>();

    //---ADD Patient---
    public void addPatient(Scanner sc){
        System.out.println("\t\t------Add Patient------");

            String patId = Validator.getValidPatient(sc, "Enter Patient ID: ");
            String patName = Validator.getOnlyLetter(sc, "Enter patient name: ");
            String patDOB = Validator.getValidDateFomart(sc, "Enter patient Date of Birth (dd/mm/yyyy): ");
            String patAddress = Validator.getNonEmpty(sc, "Enter patient address: ");
            String patDisease = Validator.getNonEmpty(sc, "Enter patient disease: ");
            String patDOE = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
            Patient patient = new Patient(patId, patName, patDOB, patDisease, patAddress, patDOE);
            patients.add(patient);

            System.out.println("Patient data captured successfully.\n");
    }
}
