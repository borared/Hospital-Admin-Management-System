package adminmangementsystem.com.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class showing polymorphism with Staff hierarchy
 */
public class StaffDemo {
    
    public static void main(String[] args) {
        // Create different staff types
        List<Staff> hospitalStaff = new ArrayList<>();
        
        // Add different staff members
        hospitalStaff.add(new Doctor("D001", "John Smith", "1980-05-15", 
                                    "123 Main St", "john@hospital.com", 
                                    "General Practitioner", 85000, "2015-01-10"));
        
        hospitalStaff.add(new Surgeon("S001", "Sarah Johnson", "1975-08-20", 
                                     "456 Oak Ave", "sarah@hospital.com", 
                                     "Surgeon", 120000, "2010-03-15"));
        
        hospitalStaff.add(new Cardiologist("C001", "Michael Chen", "1982-11-30", 
                                          "789 Pine Rd", "michael@hospital.com", 
                                          "Cardiologist", 150000, "2012-06-01"));
        
        hospitalStaff.add(new Nurse("N001", "Emily Davis", "1990-02-14", 
                                   "321 Elm St", "emily@hospital.com", 
                                   "Registered Nurse", 55000, "2018-09-01"));
        
        System.out.println("=== HOSPITAL STAFF ROSTER ===\n");
        
        // POLYMORPHISM IN ACTION - Same method call, different behavior
        for (Staff staff : hospitalStaff) {
            System.out.println("ID: " + staff.getId());
            System.out.println("Name: " + staff.getName());
            System.out.println("Position: " + staff.getPosition());
            System.out.println("Department: " + staff.getDepartment());
            System.out.println("Responsibilities: " + staff.getResponsibilities());
            staff.performDuty();
            System.out.println("Salary: $" + staff.getSalary());
            System.out.println("-".repeat(60) + "\n");
        }
        
        // Demonstrate specific abilities
        System.out.println("\n=== DEMONSTRATING SPECIFIC ABILITIES ===\n");
        
        // Doctor specific
        Doctor doctor = (Doctor) hospitalStaff.get(0);
        doctor.prescribeMedication("Patient A", "Antibiotics");
        
        // Surgeon specific
        Surgeon surgeon = (Surgeon) hospitalStaff.get(1);
        surgeon.setSurgerySpecialty("Orthopedic Surgery");
        surgeon.conductSurgery("Knee Replacement");
        surgeon.performEmergencySurgery("Patient B", "Appendicitis");
        
        // Cardiologist specific
        Cardiologist cardiologist = (Cardiologist) hospitalStaff.get(2);
        cardiologist.performECG("Patient C");
        cardiologist.performAngioplasty("Patient D");
        cardiologist.diagnoseHeartCondition("Patient E", "Arrhythmia");
        
        // Nurse specific
        Nurse nurse = (Nurse) hospitalStaff.get(3);
        nurse.setWard("ICU");
        nurse.administerMedication("Patient F", "Pain Relief");
        nurse.checkVitals("Patient G");
    }
}
