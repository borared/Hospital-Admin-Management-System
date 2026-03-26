package adminmangementsystem.com.management;

import java.util.ArrayList;
import java.util.List;

/**
 * ABSTRACTION: Abstract class - provides common functionality for all management systems
 * INHERITANCE: Parent class for DoctorSystem, PatientSystem, etc.
 * POLYMORPHISM: Subclasses override displayAll() method
 */
public abstract class AbstractManagementSystem<T> {
    
    // ENCAPSULATION: Protected field - accessible by subclasses only
    protected List<T> records = new ArrayList<>();
    
    // Common method - check if list is empty
    public boolean isEmpty() {
        return records.isEmpty();
    }
    
    // Common method - get count of records
    public int getCount() {
        return records.size();
    }
    
    // Common method - get all records (returns copy for safety)
    public List<T> getAll() {
        return new ArrayList<>(records);
    }
    
    // ABSTRACTION: Abstract method - each subclass implements differently
    // POLYMORPHISM: DoctorSystem displays doctors, PatientSystem displays patients
    public abstract void displayAll();
}
