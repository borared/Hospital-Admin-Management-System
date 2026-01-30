package adminmangementsystem.com;

import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Admin admin = new Admin("admin", "1234");
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        System.out.println("Login as Admin");
        System.out.print("Username: ");
        String u = sc.next();
        System.out.print("Password: ");
        String p = sc.next();

        if (!admin.login(u, p)) {
            System.out.println("Invalid login.");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. View Doctor List");
            System.out.println("6. View Patient List");
            System.out.println("7. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String did = sc.next();
                    System.out.print("Name: ");
                    String dname = sc.next();
                    System.out.print("DOB: ");
                    String ddob = sc.next();
                    System.out.print("Address: ");
                    String dadd = sc.next();
                    System.out.print("Position: ");
                    String pos = sc.next();
                    System.out.print("Salary: ");
                    double sal = sc.nextDouble();
                    doctorService.addDoctor(
                            new Doctor(did, dname, ddob, dadd, pos, sal));
                    break;

                case 2:
                    System.out.print("ID: ");
                    String pid = sc.next();
                    System.out.print("Name: ");
                    String pname = sc.next();
                    System.out.print("DOB: ");
                    String pdob = sc.next();
                    System.out.print("Address: ");
                    String padd = sc.next();
                    System.out.print("Disease: ");
                    String dis = sc.next();
                    System.out.print("Entry Date: ");
                    String ed = sc.next();
                    patientService.addPatient(
                            new Patient(pid, pname, pdob, padd, dis, ed));
                    break;

                case 3:
                    System.out.print("Patient ID: ");
                    String apid = sc.next();
                    System.out.print("Patient Name: ");
                    String apname = sc.next();
                    System.out.print("Disease: ");
                    String adis = sc.next();
                    System.out.print("Date: ");
                    String adate = sc.next();
                    appointmentService.addAppointment(
                            new Appointment(apid, apname, adis, adate));
                    break;

                case 4:
                    appointmentService.viewAppointments();
                    break;

                case 5:
                    doctorService.viewDoctors();
                    break;

                case 6:
                    patientService.viewPatients();
                    break;
            }
        } while (choice != 7);

        System.out.println("System exited.");
    }
}
