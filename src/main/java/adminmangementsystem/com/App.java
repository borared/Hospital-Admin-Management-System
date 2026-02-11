package adminmangementsystem.com;

import java.util.Scanner;

import adminmangementsystem.com.Management.AppointmentService;
import adminmangementsystem.com.Model.Appointment;
import adminmangementsystem.com.Model.Patient;

import java.util.ArrayList;
import java.util.List;

public class App {
    static List<Patient> patients = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin$$$");
        AppointmentService appointmentService = new AppointmentService();

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
                        System.out.println("\n\t\t------Patient Management System------");
                        System.out.println("\t1. Add Patient");
                        System.out.println("\t2. Update Patient Info");
                        System.out.println("\t3. Delete Patient");
                        System.out.println("\t4. Search Patient by Name or ID");
                        System.out.println("\t5. Exit Patient Management");
                        System.out.print("Choose an option: ");
                        int patientChoice = Integer.parseInt(sc.nextLine());

                        switch (patientChoice) {

                            // --------- ADD PATIENT ----------
                            case 1:
                                System.out.println("\t\t------Add Patient------");

                                // --- Validate ID ---
                                String patId;
                                while (true) {
                                    System.out.print("Enter patient ID: ");
                                    patId = sc.nextLine();
                                    if (patId == null || patId.isEmpty()) {
                                        System.out.println("ID cannot be empty. Please try again.");
                                    } else if (!isIdUnique(patId)) {
                                        System.out.println("Error: This ID already exists! Please enter a different ID.");
                                    } else {
                                        break;
                                    }
                                }

                                // --- Validate Name ---
                                String patName;
                                while (true) {
                                    System.out.print("Enter patient Name: ");
                                    patName = sc.nextLine();
                                    if (patName == null || patName.isEmpty() || !patName.matches("[a-zA-Z ]{1,50}")) {
                                        System.out.println("Invalid Name! Only letters and spaces allowed (max 50 characters).");
                                    } else {
                                        break;
                                    }
                                }

                                // --- Validate DOB ---
                                String patDOB;
                                while (true) {
                                    System.out.print("Enter patient Date of Birth (dd/mm/yyyy): ");
                                    patDOB = sc.nextLine();
                                    if (patDOB == null || patDOB.isEmpty() || !patDOB.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                        System.out.println("Invalid DOB! Format must be dd/mm/yyyy.");
                                    } else {
                                        break;
                                    }
                                }

                                // --- Validate Address ---
                                String patAddress;
                                while (true) {
                                    System.out.print("Enter patient address: ");
                                    patAddress = sc.nextLine();
                                    if (patAddress == null || patAddress.isEmpty()) {
                                        System.out.println("Address cannot be empty!");
                                    } else {
                                        break;
                                    }
                                }

                                // --- Validate Disease ---
                                String patDisease;
                                while (true) {
                                    System.out.print("Enter patient disease: ");
                                    patDisease = sc.nextLine();
                                    if (patDisease == null || patDisease.isEmpty()) {
                                        System.out.println("Disease cannot be empty!");
                                    } else {
                                        break;
                                    }
                                }

                                // --- Validate Entry Date ---
                                String patDOE;
                                while (true) {
                                    System.out.print("Enter date of entry (dd/mm/yyyy): ");
                                    patDOE = sc.nextLine();
                                    if (patDOE == null || patDOE.isEmpty() || !patDOE.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                        System.out.println("Invalid Entry Date! Format must be dd/mm/yyyy.");
                                    } else {
                                        break;
                                    }
                                }

                                // --- Create and store patient ---
                                Patient patient = new Patient(patId, patName, patDOB, patDisease, patAddress, patDOE);
                                patients.add(patient);

                                System.out.println("Patient data captured successfully.\n");
                                break;

                            // --------- UPDATE PATIENT ----------
                           case 2: // Update Patient
                                System.out.print("Enter patient ID to update: ");
                                String updateId = sc.nextLine();
                                Patient pToUpdate = searchPatientById(updateId);
                                if (pToUpdate == null) {
                                    System.out.println("Patient not found!");
                                } else {
                                    System.out.println("Leave blank to keep current value.");

                                    // --- Update Name ---
                                    while (true) {
                                        System.out.print("Enter new Name (" + pToUpdate.getName() + "): ");
                                        String newName = sc.nextLine();
                                        if (newName.isEmpty()) {
                                            break; // keep current
                                        } else if (!newName.matches("[a-zA-Z ]{1,50}")) {
                                            System.out.println("Invalid Name! Only letters and spaces allowed (max 50 characters).");
                                        } else {
                                            pToUpdate.setName(newName);
                                            break;
                                        }
                                    }

                                    // --- Update DOB ---
                                    while (true) {
                                        System.out.print("Enter new DOB (" + pToUpdate.getDob() + "): ");
                                        String newDob = sc.nextLine();
                                        if (newDob.isEmpty()) {
                                            break;
                                        } else if (!newDob.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                            System.out.println("Invalid DOB! Format must be dd/mm/yyyy.");
                                        } else {
                                            pToUpdate.setDob(newDob);
                                            break;
                                        }
                                    }

                                    // --- Update Address ---
                                    while (true) {
                                        System.out.print("Enter new Address (" + pToUpdate.getAddress() + "): ");
                                        String newAddress = sc.nextLine();
                                        if (newAddress.isEmpty()) {
                                            break;
                                        } else {
                                            pToUpdate.setAddress(newAddress);
                                            break;
                                        }
                                    }

                                    // --- Update Disease ---
                                    while (true) {
                                        System.out.print("Enter new Disease (" + pToUpdate.getDisease() + "): ");
                                        String newDisease = sc.nextLine();
                                        if (newDisease.isEmpty()) {
                                            break;
                                        } else {
                                            pToUpdate.setDisease(newDisease);
                                            break;
                                        }
                                    }

                                    // --- Update Entry Date ---
                                    while (true) {
                                        System.out.print("Enter new Entry Date (" + pToUpdate.getEntryDate() + "): ");
                                        String newDOE = sc.nextLine();
                                        if (newDOE.isEmpty()) {
                                            break;
                                        } else if (!newDOE.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                            System.out.println("Invalid Entry Date! Format must be dd/mm/yyyy.");
                                        } else {
                                            pToUpdate.setEntryDate(newDOE);
                                            break;
                                        }
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

                        int appointmentChoice;
                        try {
                            appointmentChoice = Integer.parseInt(sc.nextLine());
                        } catch (Exception e) {
                        System.out.println("Invalid choice!");
                        continue;
                        }
                        switch (appointmentChoice){
                            
                            case 1:
                                appointmentService.addAppointment(sc);
                                break;
                            case 2:
                                appointmentService.viewAppointments();
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
