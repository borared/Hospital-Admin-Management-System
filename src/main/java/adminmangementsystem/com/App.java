package adminmangementsystem.com;

import java.util.Scanner;

import adminmangementsystem.com.Management.AppointmentSystem;
import adminmangementsystem.com.Management.PatientSystem;
import adminmangementsystem.com.Model.Appointment;
import adminmangementsystem.com.Model.Patient;

import java.util.ArrayList;
import java.util.List;

public class App {
    static List<Patient> patients = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin$$$");
        AppointmentSystem appointmentSystem = new AppointmentSystem();
        PatientSystem patientSystem = new PatientSystem();

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
                                String updateId = Validator.getValidUpdatePatientId(sc, "Enter patient ID to update: ");
                                Patient pToUpdate = searchPatientById(updateId);
                                if (pToUpdate == null) {
                                    System.out.println("Patient not found!");
                                } else {
                                    System.out.println("Leave blank to keep current value.");

                                    // --- Update Name ---
                                        //Update Section not yet complate need to do it w4
                                   
                                        String newName = Validator.getOnlyLetter(sc, "Enter new Name (" + pToUpdate.getName() + "): ");
                                        if (newName.isEmpty()) {
                                            break; // keep current
                                       
                                        } else {
                                            pToUpdate.setName(newName);
                                        }
                                    

                                    // --- Update DOB ---
                                   
                                        String newDob = Validator.getValidDateFomart(sc, "Enter new DOB (" + pToUpdate.getDob() + "): ");
                                        if (newDob.isEmpty()) {
                                            break;
                                        } else if (!newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                            System.out.println("Invalid DOB! Format must be dd/mm/yyyy.");
                                        } else {
                                            pToUpdate.setDob(newDob);
                                            
                                        }
                                    

                                    // --- Update Address ---
                                    
                                        String newAddress = Validator.getNonEmpty(sc, "Enter new Address (" + pToUpdate.getAddress() + "): ");
                                        if (newAddress.isEmpty()) {
                                            break;
                                        } else {
                                            pToUpdate.setAddress(newAddress);
                                            
                                        }
                                    

                                    // --- Update Disease ---
                                   
                                        String newDisease = Validator.getNonEmpty(sc, "Enter new Disease (" + pToUpdate.getDisease() + "): ");
                                        if (newDisease.isEmpty()) {
                                            break;
                                        } else {
                                            pToUpdate.setDisease(newDisease);
                                            
                                        }
                                    

                                    // --- Update Entry Date ---
                                   
                                        String newDOE = Validator.getValidDateFomart(sc, "Enter new Entry Date (" + pToUpdate.getEntryDate() + "): ");
                                        if (newDOE.isEmpty()) {
                                            break;
                                        } else if (!newDOE.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                            System.out.println("Invalid Entry Date! Format must be dd/mm/yyyy.");
                                        } else {
                                            pToUpdate.setEntryDate(newDOE);
                                            break;
                                        }
                                    

                                    System.out.println("Patient updated successfully.");
                                }
                                break;

                            // --------- DELETE PATIENT ----------
                            case 3:
                                System.out.print("Enter patient ID to delete: ");
                                String deleteId = sc.nextLine();
                                Patient pToDelete = searchPatientById(deleteId);
                                if (pToDelete == null) {
                                    System.out.println("Patient not found!");
                                } else {
                                    patients.remove(pToDelete);
                                    System.out.println("Patient deleted successfully.");
                                }
                                break;


                            // --------- SEARCH PATIENT ----------
                            case 4:
                                System.out.print("Search by (1) Name or (2) ID: ");
                                int searchChoice = Integer.parseInt(sc.nextLine());
                                String line = "------------------------------------------------------------------------------------------------------------";

                                if (searchChoice == 1) { // Search by Name
                                    System.out.print("Enter patient name: ");
                                    String searchName = sc.nextLine();
                                    boolean foundAny = false;

                                    // Table header
                                    System.out.println(line);
                                    System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n", 
                                                    "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
                                    System.out.println(line);

                                    for (Patient p : patients) {
                                        if (p.getName().equalsIgnoreCase(searchName)) {
                                            System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n",
                                                            p.getId(), p.getName(), p.getDob(), p.getAddress(), p.getDisease(), p.getEntryDate());
                                            foundAny = true;
                                        }
                                    }

                                    if (!foundAny) {
                                        System.out.println("|                        No patients found with that name                        |");
                                    }

                                    System.out.println(line);

                                } else if (searchChoice == 2) { // Search by ID
                                    System.out.print("Enter patient ID: ");
                                    String searchId = sc.nextLine();
                                    Patient found = searchPatientById(searchId);

                                    // Table header
                                    System.out.println(line);
                                    System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n", 
                                                    "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
                                    System.out.println(line);

                                    if (found != null) {
                                        System.out.printf("| %-5s | %-20s | %-12s | %-20s | %-15s | %-12s |\n",
                                                        found.getId(), found.getName(), found.getDob(), found.getAddress(), found.getDisease(), found.getEntryDate());
                                    } else {
                                        System.out.println("|                               Patient not found                               |");
                                    }

                                    System.out.println(line);

                                } else {
                                    System.out.println("Invalid choice!");
                                }

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
                  break;

                case 5: // View Doctor List
                  break;

                case 6: // View Patient List
                    System.out.println("\t\t\t\t------Patient List------");

                    if (patients.isEmpty()) {
                        System.out.println("No patients found.");
                    } else {
                        String line = "------------------------------------------------------------------------------------------------------------";

                        System.out.println(line);
                        System.out.printf("| %-5s | %-20s | %-12s | %-15s | %-15s | %-12s |\n", 
                                          "ID", "Name", "DOB", "Address", "Disease", "Entry Date");
                        System.out.println(line);

                        for (Patient p : patients) {
                            System.out.printf("| %-5s | %-20s | %-12s | %-15s | %-15s | %-12s |\n", 
                                              p.getId(), p.getName(), p.getDob(), p.getAddress(), p.getDisease(), p.getEntryDate());
                        }
                        System.out.println(line);
                    }

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
    //Check ID
    public static boolean isIdUnique(String id) {
    for (Patient p : patients) {
        if (p.getId().equalsIgnoreCase(id)) {
            return false; // ID already exists
        }
    }
    return true; // ID is unique
    }
    //Search Patient by ID
    public static Patient searchPatientById(String id) {
    for (Patient p : patients) {
        if (p.getId().equalsIgnoreCase(id)) {
            return p;
        }
    }
    return null;
}


}
