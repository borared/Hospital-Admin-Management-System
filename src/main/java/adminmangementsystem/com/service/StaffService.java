package adminmangementsystem.com.service;

import adminmangementsystem.com.entity.Staff;
import adminmangementsystem.com.repository.HospitalRepository;
import adminmangementsystem.com.service.interfaces.IStaffService;

import java.util.List;

public class StaffService implements IStaffService {
    
    private HospitalRepository repository;
    
    public StaffService() {
        this.repository = HospitalRepository.getInstance();
    }
    
    @Override
    public int addStaff(Staff staff) {
        return repository.addStaff(staff);
    }
    
    @Override
    public Staff getStaffById(int staffId) {
        return repository.getStaff(staffId);
    }
    
    @Override
    public List<Staff> getAllStaff() {
        return repository.getAllStaff();
    }
    
    @Override
    public boolean updateStaff(Staff staff) {
        return repository.updateStaff(staff);
    }
    
    @Override
    public boolean deleteStaff(int staffId) {
        return repository.deleteStaff(staffId);
    }
    
    public void displayAllStaff() {
        List<Staff> staffList = getAllStaff();
        if (staffList.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        
        System.out.println("\n=== All Staff ===");
        for (Staff staff : staffList) {
            staff.display();
            System.out.println("-------------------");
        }
    }
}
