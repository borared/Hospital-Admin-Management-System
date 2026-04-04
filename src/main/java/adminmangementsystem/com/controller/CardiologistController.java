package adminmangementsystem.com.controller;

import java.util.List;
import java.util.Scanner;

import adminmangementsystem.com.model.Cardiologist;
import adminmangementsystem.com.management.StaffSystem;
import adminmangementsystem.com.view.CardiologistView;

public class CardiologistController {

    private final StaffSystem<Cardiologist> cardiologistSystem;

    public CardiologistController(StaffSystem<Cardiologist> cardiologistSystem) {
        this.cardiologistSystem = cardiologistSystem;
    }

    public void run(Scanner sc) {
        boolean menu = true;
        while (menu) {
            System.out.println("\n\t\t------Cardiologist Management------");
            System.out.println("\t1. Add Cardiologist");
            System.out.println("\t2. Update Cardiologist");
            System.out.println("\t3. Delete Cardiologist");
            System.out.println("\t4. Search Cardiologist");
            System.out.println("\t5. View All Cardiologists");
            System.out.println("\t6. Check In Cardiologist");
            System.out.println("\t7. Check Out Cardiologist");
            System.out.println("\t8. View Attendance");
            System.out.println("\t9. Exit");
            System.out.print("Choose: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    Cardiologist c = CardiologistView.getCardiologistInput(sc);
                    cardiologistSystem.addStaff(c);
                    System.out.println("Cardiologist added.");
                    break;
                case 2:
                    // Implement update (similar to previous DoctorSystem update)
                    System.out.print("Enter ID to update: ");
                    String updateId = sc.nextLine();
                    Cardiologist existing = cardiologistSystem.searchStaffById(updateId);
                    if (existing == null) {
                        System.out.println("Not found.");
                    } else {
                        // You would call a view method to update fields
                        // For simplicity, we skip detailed implementation here
                        System.out.println("Update feature not fully implemented.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    String delId = sc.nextLine();
                    if (cardiologistSystem.deleteStaff(delId)) {
                        System.out.println("Deleted.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter name or ID to search: ");
                    String query = sc.nextLine();
                    // simple search by ID first
                    Cardiologist byId = cardiologistSystem.searchStaffById(query);
                    if (byId != null) {
                        byId.display();
                    } else {
                        List<Cardiologist> byName = cardiologistSystem.searchStaffByName(query);
                        if (byName.isEmpty()) {
                            System.out.println("No match.");
                        } else {
                            for (Cardiologist doc : byName) doc.display();
                        }
                    }
                    break;
                case 5:
                    cardiologistSystem.displayAll();
                    break;
                case 6:
                    System.out.print("Enter ID to check in: ");
                    cardiologistSystem.checkInStaff(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Enter ID to check out: ");
                    cardiologistSystem.checkOutStaff(sc.nextLine());
                    break;
                    
                case 8:
                    cardiologistSystem.displayAttendance();
                    break;
                case 9:
                    menu = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

