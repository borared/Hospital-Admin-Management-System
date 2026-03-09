package adminmangementsystem.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.entity.Staff;

public class StaffService<T extends Staff> {

    private final Class<T> type;
    private List<T> records = new ArrayList<>();

    public StaffService(Class<T> type) {
        this.type = type;
    }

    public void addStaff(T staff) {
        if (staff == null) {
            throw new IllegalArgumentException("Staff cannot be null.");
        }
        if (!isIdUnique(staff.getId())) {
            throw new IllegalArgumentException("Staff ID already exists.");
        }
        records.add(staff);
    }

    public boolean updateStaff(T updatedStaff) {
        if (updatedStaff == null) return false;
        T existing = searchStaffById(updatedStaff.getId());
        if (existing == null) return false;

        // Replace the old record with the updated one
        records.remove(existing);
        records.add(updatedStaff);
        return true;
    }

    public boolean deleteStaff(String id) {
        T staff = searchStaffById(id);
        if (staff != null) {
            records.remove(staff);
            return true;
        }
        return false;
    }

    public T searchStaffById(String id) {
        for (T s : records) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public List<T> searchStaffByName(String name) {
        List<T> results = new ArrayList<>();
        for (T s : records) {
            if (s.getName().equalsIgnoreCase(name)) {
                results.add(s);
            }
        }
        return results;
    }

    public boolean isIdUnique(String id) {
        return searchStaffById(id) == null;
    }

    // Attendance methods
    public void checkInStaff(String id) {
        T staff = searchStaffById(id);
        if (staff != null) {
            staff.checkIn();
            System.out.println(staff.getName() + " checked in at " + staff.getLastCheckIn());
        } else {
            System.out.println("Staff not found.");
        }
    }

    public void checkOutStaff(String id) {
        T staff = searchStaffById(id);
        if (staff != null) {
            staff.checkOut();
            System.out.println(staff.getName() + " checked out at " + staff.getLastCheckOut());
        } else {
            System.out.println("Staff not found.");
        }
    }

    public void displayAttendance() {
        for (T s : records) {
            System.out.println(s.getId() + " | " + s.getName() + " | Active: " + s.isActive() +
                               " | Last In: " + s.getLastCheckIn() + " | Last Out: " + s.getLastCheckOut());
        }
    }

    public void displayAll() {
        if (records.isEmpty()) {
            System.out.println("No " + type.getSimpleName() + " records found.");
            return;
        }
        for (T s : records) {
            s.display();  // uses the overridden display in each subclass
            System.out.println("QR Code: " + s.getQrCode());
            System.out.println("Department: " + s.getDepartment());
            System.out.println("Responsibilities: " + s.getResponsibilities());
            System.out.println("---");
        }
    }
}