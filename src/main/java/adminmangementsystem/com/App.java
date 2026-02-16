package adminmangementsystem.com;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import com.google.gson.reflect.TypeToken;

import adminmangementsystem.com.Management.AppointmentService;
import adminmangementsystem.com.Management.PatientSystem;
import adminmangementsystem.com.Management.DoctorSystem;
import adminmangementsystem.com.Management.Persistence;
import adminmangementsystem.com.Model.Appointment;
import adminmangementsystem.com.Model.Doctor;
import adminmangementsystem.com.Model.Patient;

public class App {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin$$$");
        AppointmentService appointmentSystem = new AppointmentService();
        PatientSystem patientSystem = new PatientSystem();
        DoctorSystem doctorSystem = new DoctorSystem();

        // Load persisted data (if any)
        try {
            Type doctorListType = new TypeToken<List<Doctor>>(){}.getType();
            List<Doctor> loadedDoctors = Persistence.loadList("data/doctors.json", doctorListType);
            doctorSystem.setDoctors(loadedDoctors);

            Type patientListType = new TypeToken<List<Patient>>(){}.getType();
            List<Patient> loadedPatients = Persistence.loadList("data/patients.json", patientListType);
            PatientSystem.setPatients(loadedPatients);

            Type appointmentListType = new TypeToken<List<Appointment>>(){}.getType();
            List<Appointment> loadedAppointments = Persistence.loadList("data/appointments.json", appointmentListType);
            appointmentSystem.setAppointments(loadedAppointments);
        } catch (Exception e) {
            System.out.println("Warning: could not load persisted data: " + e.getMessage());
        }

        // Save on exit using shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Persistence.saveList(doctorSystem.getDoctors(), "data/doctors.json");
                Persistence.saveList(PatientSystem.getPatients(), "data/patients.json");
                Persistence.saveList(appointmentSystem.getAppointments(), "data/appointments.json");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        boolean loggedIn = false;

        while (!loggedIn) {
            Menu.printAdminLoginMenu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1: // Login
                    System.out.print("Enter admin username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter admin password: ");
                    String password = sc.nextLine();

                    if (admin.login(username, password)) {
                        loggedIn = true;
                        System.out.println("\nLogin successful.\n");
                    } else {
                        System.out.println("Invalid login. Try again.\n");
                    }
                    break;

                case 2: // Exit
                    System.out.println("System exited.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.\n");
            }
        }

        boolean running = true;

        while (running) {
            Menu.printDashboardMenu();
            System.out.print("Enter your choice: ");
            int choiceIn = sc.nextInt();
            sc.nextLine(); 

            switch (choiceIn) {
                case 1: //Doctor System
                    boolean doctorMenu = true;
                    while(doctorMenu){
                        Menu.doctorMenu();

                        System.out.print("Choose an option: ");
                        int doctorChoice = Integer.parseInt(sc.nextLine());

                        switch (doctorChoice) {
                            //Add Doctor
                            case 1:
                                doctorSystem.addDoctor(sc);
                                break;
                            //Update Doctor
                            case 2:
                                doctorSystem.updateDoctor(sc);
                                break;
                            //Delete Doctor    
                            case 3:
                                doctorSystem.deleteDoctor(sc);
                                break;

                            //Search Doctor
                            case 4:
                                doctorSystem.searchDoctor(sc);
                                break;
                            
                            //View Doctor List
                            case 5:
                                doctorSystem.viewDoctorList();
                                break;

                            //Exit 
                            case 6:
                                doctorMenu = false;
                                break;
                        
                            default:
                                System.out.println("Invalid choice!");
                                break;
                        }
                    }
                    break;

                case 2: // Patient Management System
                    boolean patientMenu = true;
                    while (patientMenu) {
                        Menu.patientMenu();
                        
                        System.out.print("Choose an option: ");
                        int patientChoice = Integer.parseInt(sc.nextLine());

                        switch (patientChoice) {
                            
                            // --------- ADD PATIENT ----------
                            case 1:
                                patientSystem.addPatient(sc);
                                break;

                            // --------- UPDATE PATIENT ----------
                           case 2: // Update Patient ID
                                patientSystem.updatePatient(sc);
                                break;

                            // --------- DELETE PATIENT ----------
                            case 3:
                                patientSystem.deletePatient(sc);
                                break;


                            // --------- SEARCH PATIENT ----------
                            case 4:
                                patientSystem.searchPatient(sc);
                                break;

                            // --------- EXIT PATIENT MENU ----------
                            case 5:
                                patientMenu = false;
                                System.out.println("Exiting Patient Management System...");
                                break;

                            default:
                                System.out.println("Invalid choice! Please try again.");
                                break;
                        }
                    }
                break;


                case 3: //Make an Appointment
                    boolean appointmentDisplayMenu = true;
                    
                    while(appointmentDisplayMenu){

                        Menu.appointmentMenu();

                        System.out.print("Choose an option: ");
                        int appointmentChoice;
                        try {
                            appointmentChoice = Integer.parseInt(sc.nextLine());
                        } catch (Exception e) {
                        System.out.println("Invalid choice!");
                        continue;
                        }
                        switch (appointmentChoice){
                            
                            case 1:
                                appointmentSystem.addAppointment(sc);
                                break;
                            case 2:
                                appointmentSystem.viewAppointments();
                                break;
                            case 3:
                                appointmentDisplayMenu = false;
                            break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                case 4: // View Appointment List
                    appointmentSystem.viewAppointments();
                  break;

                case 5: // View Doctor List
                    doctorSystem.viewDoctorList();
                  break;

                case 6: // View Patient List
                    patientSystem.viewPatientList(sc);
                  break;

                case 7: // Exit dashboard
                    running = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
