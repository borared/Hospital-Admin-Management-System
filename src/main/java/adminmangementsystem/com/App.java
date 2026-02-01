package adminmangementsystem.com;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin$$$");
       

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

                case 2: //Patient System
                  
                    break;

                case 3: //Make an Appointment
                    System.out.println("\t\t------Appointment Management System------");

                    System.out.print("Enter patient ID: ");
                    String patientId = sc.nextLine();

                    System.out.print("Enter patient Name: ");
                    String patientName = sc.nextLine();

                    System.out.print("Enter patient Date of Birth (dd/mm/yyyy): ");
                    String patientDOB = sc.nextLine();

                    System.out.print("Enter patient Phone Number: ");
                    String phoneNumber = sc.nextLine();

                    System.out.print("Enter patient disease: ");
                    String disease = sc.nextLine();

                    System.out.print("Enter date of appointment: ");
                    String DOA = sc.nextLine();
                    Appointment appointment = new Appointment(patientId, patientName, patientDOB, disease, phoneNumber, DOA);
                    appointment.displayAppointment();
                    System.out.println("Appointment data captured.\n");
                    break;

                case 4: // View Appointment List
                  break;

                case 5: // View Doctor List
                  break;

                case 6: // View Patient List
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
