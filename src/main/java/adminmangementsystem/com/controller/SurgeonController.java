package adminmangementsystem.com.controller;

import java.util.Scanner;
import adminmangementsystem.com.management.StaffSystem;
import adminmangementsystem.com.Surgeon;
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
                case 1:
                    Surgeon newSurgeon = SurgeonView.getSurgeonInput(sc);
                    try {
                        surgeonSystem.addStaff(newSurgeon);
                        System.out.println("Surgeon added successfully.\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;

                case 2:
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

                case 3:
                    System.out.print("Enter Surgeon ID to delete: ");
                    String deleteId = sc.nextLine();
                    if (surgeonSystem.deleteStaff(deleteId)) {
                        System.out.println("Surgeon deleted successfully.\n");
                    } else {
                        System.out.println("Surgeon not found.\n");
                    }
                    break;


                case 4:
                    System.out.print("Search by (1) Name or (2) ID: ");
                    String searchOpt = sc.nextLine();
                    if ("1".equals(searchOpt)) {
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        var results = surgeonSystem.searchStaffByName(name);
                        if (results.isEmpty()) {
                            System.out.println("No surgeons found with that name.\n");
                        } else {
                            System.out.println("\n--- Search Results ---");
                            for (Surgeon s : results) {
                                s.display();
                                System.out.println("-----");
                            }
                        }
                    } else if ("2".equals(searchOpt)) {
                        System.out.print("Enter ID: ");
                        String id = sc.nextLine();
                        Surgeon found = surgeonSystem.searchStaffById(id);
                        if (found == null) {
                            System.out.println("Surgeon not found.\n");
                        } else {
                            found.display();
                        }
                    } else {
                        System.out.println("Invalid option.\n");
                    }
                    break;

                case 5:
                    surgeonSystem.displayAll();
                    break;

                case 6:
                    System.out.print("Enter Surgeon ID to check in: ");
                    String checkInId = sc.nextLine();
                    surgeonSystem.checkInStaff(checkInId);
                    break;

                case 7:
                    System.out.print("Enter Surgeon ID to check out: ");
                    String checkOutId = sc.nextLine();
                    surgeonSystem.checkOutStaff(checkOutId);
                    break;

                case 8:
                    surgeonSystem.displayAttendance();
                    break;

                case 9:
                    menuActive = false;
                    System.out.println("Exiting Surgeon Management.\n");
                    break;

                default:
                    System.out.println("Invalid choice.\n");
            }
        }
    }
}
