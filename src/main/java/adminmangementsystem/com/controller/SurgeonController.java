package adminmangementsystem.com.controller;

import java.util.Scanner;
import java.util.List;
import adminmangementsystem.com.management.StaffSystem;
import adminmangementsystem.com.model.Surgeon;
import adminmangementsystem.com.view.SurgeonView;
import adminmangementsystem.com.Menu;

public class SurgeonController {

    private final StaffSystem<Surgeon> surgeonSystem;

    public SurgeonController(StaffSystem<Surgeon> surgeonSystem) {
        this.surgeonSystem = surgeonSystem;
    }

    public StaffSystem<Surgeon> getSurgeonSystem() {
        return surgeonSystem;
    }

    public void run(Scanner sc) {
        boolean menuActive = true;
        while (menuActive) {
            Menu.surgeonMenu();
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice!\n");
                continue;
            }

            switch (choice) {
                case 1: // Add Surgeon
                    Surgeon newSurgeon = SurgeonView.getSurgeonInput(sc);
                    try {
                        surgeonSystem.addStaff(newSurgeon);
                        System.out.println("Surgeon added successfully.\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;

                case 2: // Update Surgeon
                    System.out.print("Enter Surgeon ID to update: ");
                    String updateId = sc.nextLine();
                    Surgeon existing = surgeonSystem.searchStaffById(updateId);
                    if (existing == null) {
                        System.out.println("Surgeon not found.\n");
                    } else {
                        Surgeon updated = SurgeonView.getSurgeonUpdateInput(sc, existing);
                        if (surgeonSystem.updateStaff(updated)) {
                            System.out.println("Surgeon updated successfully.\n");
                        } else {
                            System.out.println("Update failed.\n");
                        }
                    }
                    break;

                case 3: // Delete Surgeon
                    System.out.print("Enter Surgeon ID to delete: ");
                    String deleteId = sc.nextLine();
                    if (surgeonSystem.deleteStaff(deleteId)) {
                        System.out.println("Surgeon deleted successfully.\n");
                    } else {
                        System.out.println("Surgeon not found.\n");
                    }
                    break;

                case 4: // Search Surgeon
                    int searchChoice = SurgeonView.getSearchChoice(sc);
                    if (searchChoice == -1) break;

                    switch (searchChoice) {
                        case 1: // by name
                            String searchName = SurgeonView.getSearchName(sc);
                            List<Surgeon> results = surgeonSystem.searchStaffByName(searchName);
                            if (results.isEmpty()) {
                                System.out.println("No surgeons found with that name.\n");
                            } else {
                                System.out.println("\n--- Search Results ---");
                                for (Surgeon s : results) {
                                    s.display();
                                    System.out.println("-----");
                                }
                            }
                            break;

                        case 2: // by ID
                            String searchId = SurgeonView.getSearchId(sc);
                            Surgeon found = surgeonSystem.searchStaffById(searchId);
                            if (found == null) {
                                System.out.println("Surgeon not found.\n");
                            } else {
                                found.display();
                            }
                            break;

                        default:
                            System.out.println("Invalid option.\n");
                    }
                    break;

                case 5: // View All Surgeons
                    surgeonSystem.displayAll();
                    break;

                case 6: // Check In
                    System.out.print("Enter Surgeon ID to check in: ");
                    String checkInId = sc.nextLine();
                    Surgeon checkInSurgeon = surgeonSystem.searchStaffById(checkInId);
                    if (checkInSurgeon == null) {
                        System.out.println("Surgeon not found.\n");
                    } else {
                        surgeonSystem.checkInStaff(checkInId);
                        System.out.println("Surgeon checked in successfully.\n");
                    }
                    break;

                case 7: // Check Out
                    System.out.print("Enter Surgeon ID to check out: ");
                    String checkOutId = sc.nextLine();
                    Surgeon checkOutSurgeon = surgeonSystem.searchStaffById(checkOutId);
                    if (checkOutSurgeon == null) {
                        System.out.println("Surgeon not found.\n");
                    } else {
                        surgeonSystem.checkOutStaff(checkOutId);
                        System.out.println("Surgeon checked out successfully.\n");
                    }
                    break;

                case 8: // View Attendance
                    surgeonSystem.displayAttendance();
                    break;

                case 9: // Exit
                    menuActive = false;
                    System.out.println("Exiting Surgeon Management.\n");
                    break;

                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }
}
