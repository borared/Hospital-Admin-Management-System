package adminmangementsystem.com.service;

import adminmangementsystem.com.entity.Department;
import adminmangementsystem.com.repository.HospitalRepository;

import java.util.List;

public class DepartmentService {
    
    private HospitalRepository repository;
    
    public DepartmentService() {
        this.repository = HospitalRepository.getInstance();
    }
    
    public int addDepartment(Department department) {
        return repository.addDepartment(department);
    }
    
    public Department getDepartmentById(int departmentId) {
        return repository.getDepartment(departmentId);
    }
    
    public List<Department> getAllDepartments() {
        return repository.getAllDepartments();
    }
    
    public void displayAllDepartments() {
        List<Department> departments = getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }
        
        System.out.println("\n=== All Departments ===");
        for (Department dept : departments) {
            System.out.println("ID: " + dept.getDepartmentId() + 
                             " | Name: " + dept.getDepartmentName() + 
                             " | Location: " + dept.getLocation());
        }
    }
}
