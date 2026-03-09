package adminmangementsystem.com.controller;

import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.entity.Pharmacist;
import adminmangementsystem.com.service.StaffService;
import adminmangementsystem.com.view.PharmacistView;

public class PharmacistController {

    private StaffService<Pharmacist> pharmacistService;

    public PharmacistController(StaffService<Pharmacist> pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    public void run(Scanner sc) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Pharmacist Management ===");
            System.out.println("1. Add Pharmacist");
            System.out.println("2. Update Pharmacist");
            System.out.println("3. Delete Pharmacist");
            System.out.println("4. Search Pharmacist");
            System.out.println("5. View All Pharmacists");
            System.out.println("6. Check In Pharmacist");
            System.out.println("7. Check Out Pharmacist");
            System.out.println("8. View Attendance");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addPharmacist(sc);
                        break;
                    case 2:
                        updatePharmacist(sc);
                        break;
                    case 3:
                        deletePharmacist(sc);
                        break;
                    case 4:
                        searchPharmacist(sc);
                        break;
                    case 5:
                        viewAllPharmacists();
                        break;
                    case 6:
                        checkInPharmacist(sc);
                        break;
                    case 7:
                        checkOutPharmacist(sc);
                        break;
                    case 8:
                        pharmacistService.displayAttendance();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private void addPharmacist(Scanner sc) {
        try {
            Pharmacist pharmacist = PharmacistView.getPharmacistInput(sc);
            pharmacistService.addStaff(pharmacist);
            System.out.println("Pharmacist added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updatePharmacist(Scanner sc) {
        String id = PharmacistView.getUpdatePharmacistId(sc);
        Pharmacist pharmacist = pharmacistService.searchStaffById(id);

        if (pharmacist == null) {
            System.out.println("Pharmacist not found!");
            return;
        }

        PharmacistView.getPharmacistUpdateInput(sc, pharmacist);
        if (pharmacistService.updateStaff(pharmacist)) {
            System.out.println("Pharmacist updated successfully!");
        } else {
            System.out.println("Failed to update pharmacist.");
        }
    }

    private void deletePharmacist(Scanner sc) {
        String id = PharmacistView.getPharmacistIdForDelete(sc);
        if (pharmacistService.deleteStaff(id)) {
            System.out.println("Pharmacist deleted successfully!");
        } else {
            System.out.println("Pharmacist not found!");
        }
    }

    private void searchPharmacist(Scanner sc) {
        int searchChoice = PharmacistView.getSearchChoice(sc);

        if (searchChoice == 0) {
            return;
        }

        String line = "-----------------------------------------------------------------------------------------------------------------------------------";

        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10s | %-12s | %-15s |\n",
                "ID", "Name", "DOB", "Address", "Email", "Position", "Salary", "Entry Date", "License Number");
        System.out.println(line);

        if (searchChoice == 1) {
            String searchName = PharmacistView.getSearchName(sc);
            List<Pharmacist> results = pharmacistService.searchStaffByName(searchName);

            if (results.isEmpty()) {
                System.out.println("|                                             No pharmacists found with that name                                              |");
            } else {
                for (Pharmacist p : results) {
                    System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s | %-15s |\n",
                            p.getId(), p.getName(), p.getDob(), p.getAddress(), p.getEmail(),
                            p.getPosition(), p.getSalary(), p.getDoe(), p.getLicenseNumber());
                }
            }

        } else if (searchChoice == 2) {
            String searchId = PharmacistView.getSearchId(sc);
            Pharmacist found = pharmacistService.searchStaffById(searchId);

            if (found != null) {
                System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-20s | %-12s | %-10.2f | %-12s | %-15s |\n",
                        found.getId(), found.getName(), found.getDob(), found.getAddress(),
                        found.getEmail(), found.getPosition(), found.getSalary(), found.getDoe(), found.getLicenseNumber());
            } else {
                System.out.println("|                                                     Pharmacist not found                                                      |");
            }

        } else {
            System.out.println("Invalid choice!");
        }

        System.out.println(line);
    }

    private void viewAllPharmacists() {
        System.out.println("\n\t\t\t\t------Pharmacist List------");
        pharmacistService.displayAll();
    }

    private void checkInPharmacist(Scanner sc) {
        String id = PharmacistView.getSearchId(sc);
        pharmacistService.checkInStaff(id);
    }

    private void checkOutPharmacist(Scanner sc) {
        String id = PharmacistView.getSearchId(sc);
        pharmacistService.checkOutStaff(id);
    }
}
