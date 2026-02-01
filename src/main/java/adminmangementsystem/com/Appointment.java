package adminmangementsystem.com;

import java.util.Scanner;

public class Appointment {
    private String patientId;
    private String patientName;
    private String patientDOB;
    private String disease;
    private String phoneNumber;
    private String DOA;

    // Constructor
    public Appointment(String patientId, String patientName, String patientDOB, String disease, String phoneNumber, String DOA) {
        setPatientId(patientId);
        setPatientName(patientName);
        setPatientDOB(patientDOB);
        setPatientDisease(disease);
        setPatientPhoneNum(phoneNumber);
        setDOA(DOA);
    }

    // SETTERS 
    public Appointment setPatientId(String patientId) { this.patientId = patientId; return this; }
    public Appointment setPatientName(String patientName) { this.patientName = patientName; return this; }
    public Appointment setPatientDOB(String patientDOB) { this.patientDOB = patientDOB; return this; }
    public Appointment setPatientDisease(String disease) { this.disease = disease; return this; }
    public Appointment setPatientPhoneNum(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
    public Appointment setDOA(String DOA) { this.DOA = DOA; return this; }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\t------Appointment Management System------");

        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Enter patient Name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter patient Date of Birth (dd/mm/yyyy): ");
        String patientDOB = scanner.nextLine();

        System.out.print("Enter patient Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter patient disease: ");
        String disease = scanner.nextLine();

        System.out.print("Enter date of appointment: ");
        String DOA = scanner.nextLine();

        // OUTPUT FORMATTING
        System.out.print("\n");
        System.out.println("\t\t\t\t------Appointment List------");
        // Extended the line to accommodate the new column
        String line = "------------------------------------------------------------------------------------------------";
        
        // Header
        System.out.println(line);
        // Column widths: ID(5), Name(18), DOB(12), Phone(15), Disease(15), DOA(12)
        System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n", 
                          "ID", "Patient Name", "DOB", "Phone Number", "Disease", "DOA");
        System.out.println(line);
        
        // Data Row
        System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n", 
                          patientId, patientName, patientDOB, phoneNumber, disease, DOA);
        System.out.println(line);

        scanner.close();
    }
}