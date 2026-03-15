package adminmangementsystem.com;

import adminmangementsystem.com.entity.*;
import adminmangementsystem.com.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Demo showing OOP-based hospital management system
 * Using in-memory repository pattern (no SQL database)
 */
public class HospitalDemo {
    
    public static void main(String[] args) {
        
        // Initialize services
        DepartmentService deptService = new DepartmentService();
        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();
        StaffService staffService = new StaffService();
        AppointmentService appointmentService = new AppointmentService();
        MedicalRecordService recordService = new MedicalRecordService();
        
        System.out.println("=== Hospital Management System - OOP Demo ===\n");
        
        // 1. Display departments
        System.out.println("1. Departments:");
        deptService.displayAllDepartments();
        
        // 2. Add patients
        System.out.println("\n2. Adding Patients:");
        Patient p1 = new Patient();
        p1.setFirstName("John");
        p1.setLastName("Doe");
        p1.setGender("Male");
        p1.setDateOfBirth(LocalDate.of(1990, 5, 12));
        p1.setPhone("100000001");
        p1.setAddress("Phnom Penh");
        int patientId1 = patientService.addPatient(p1);
        System.out.println("Added patient with ID: " + patientId1);
        
        Patient p2 = new Patient();
        p2.setFirstName("Sarah");
        p2.setLastName("Smith");
        p2.setGender("Female");
        p2.setDateOfBirth(LocalDate.of(1985, 8, 20));
        p2.setPhone("100000002");
        p2.setAddress("Siem Reap");
        int patientId2 = patientService.addPatient(p2);
        System.out.println("Added patient with ID: " + patientId2);
        
        // 3. Add doctors
        System.out.println("\n3. Adding Doctors:");
        Doctor d1 = new Doctor();
        d1.setFirstName("Michael");
        d1.setLastName("Lee");
        d1.setSpecialization("Cardiologist");
        d1.setPhone("200000001");
        d1.setDepartmentId(1); // Cardiology
        int doctorId1 = doctorService.addDoctor(d1);
        System.out.println("Added doctor with ID: " + doctorId1);
        
        Doctor d2 = new Doctor();
        d2.setFirstName("Anna");
        d2.setLastName("Kim");
        d2.setSpecialization("Neurologist");
        d2.setPhone("200000002");
        d2.setDepartmentId(2); // Neurology
        int doctorId2 = doctorService.addDoctor(d2);
        System.out.println("Added doctor with ID: " + doctorId2);
        
        // 4. Add staff
        System.out.println("\n4. Adding Staff:");
        Staff s1 = new Staff();
        s1.setFirstName("James");
        s1.setLastName("Clark");
        s1.setRole("Nurse");
        s1.setPhone("300000001");
        s1.setDepartmentId(1);
        int staffId1 = staffService.addStaff(s1);
        System.out.println("Added staff with ID: " + staffId1);
        
        // 5. Schedule appointments
        System.out.println("\n5. Scheduling Appointments:");
        Appointment apt1 = new Appointment();
        apt1.setPatientId(patientId1);
        apt1.setDoctorId(doctorId1);
        apt1.setAppointmentDate(LocalDateTime.of(2026, 3, 15, 9, 0));
        apt1.setStatus("Scheduled");
        int aptId1 = appointmentService.addAppointment(apt1);
        System.out.println("Scheduled appointment with ID: " + aptId1);
        
        // 6. Add medical records
        System.out.println("\n6. Adding Medical Records:");
        MedicalRecord record1 = new MedicalRecord();
        record1.setPatientId(patientId1);
        record1.setDoctorId(doctorId1);
        record1.setDiagnosis("High Blood Pressure");
        record1.setTreatment("Medication prescribed");
        record1.setRecordDate(LocalDate.now());
        int recordId1 = recordService.addMedicalRecord(record1);
        System.out.println("Added medical record with ID: " + recordId1);
        
        // 7. Display all data
        System.out.println("\n7. Displaying All Data:");
        patientService.displayAllPatients();
        doctorService.displayAllDoctors();
        staffService.displayAllStaff();
        appointmentService.displayAllAppointments();
        recordService.displayPatientMedicalHistory(patientId1);
        
        System.out.println("\n=== Demo Complete ===");
        System.out.println("All data stored in-memory using OOP principles!");
    }
}
