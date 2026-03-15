package adminmangementsystem.com.repository;

import adminmangementsystem.com.entity.*;
import java.util.*;

/**
 * In-memory repository simulating database tables with relationships
 * This is a Singleton pattern to ensure single source of truth
 */
public class HospitalRepository {
    
    private static HospitalRepository instance;
    
    // "Tables" - Collections that store entities
    private Map<Integer, Department> departments;
    private Map<Integer, Patient> patients;
    private Map<Integer, Doctor> doctors;
    private Map<Integer, Staff> staff;
    private Map<Integer, Appointment> appointments;
    private Map<Integer, MedicalRecord> medicalRecords;
    private Map<Integer, LabTest> labTests;
    private Map<Integer, Billing> billings;
    
    // Auto-increment IDs (simulating database AUTO_INCREMENT)
    private int nextPatientId = 1;
    private int nextDoctorId = 1;
    private int nextStaffId = 1;
    private int nextAppointmentId = 1;
    private int nextDepartmentId = 1;
    private int nextRecordId = 1;
    private int nextTestId = 1;
    private int nextBillId = 1;
    
    private HospitalRepository() {
        departments = new HashMap<>();
        patients = new HashMap<>();
        doctors = new HashMap<>();
        staff = new HashMap<>();
        appointments = new HashMap<>();
        medicalRecords = new HashMap<>();
        labTests = new HashMap<>();
        billings = new HashMap<>();
        
        initializeSampleData();
    }
    
    public static HospitalRepository getInstance() {
        if (instance == null) {
            instance = new HospitalRepository();
        }
        return instance;
    }
    
    // Department operations
    public int addDepartment(Department dept) {
        dept.setDepartmentId(nextDepartmentId);
        departments.put(nextDepartmentId, dept);
        return nextDepartmentId++;
    }
    
    public Department getDepartment(int id) {
        return departments.get(id);
    }
    
    public List<Department> getAllDepartments() {
        return new ArrayList<>(departments.values());
    }
    
    // Patient operations
    public int addPatient(Patient patient) {
        patient.setPatientId(nextPatientId);
        patients.put(nextPatientId, patient);
        return nextPatientId++;
    }
    
    public Patient getPatient(int id) {
        return patients.get(id);
    }
    
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }
    
    public boolean updatePatient(Patient patient) {
        if (patients.containsKey(patient.getPatientId())) {
            patients.put(patient.getPatientId(), patient);
            return true;
        }
        return false;
    }
    
    public boolean deletePatient(int id) {
        return patients.remove(id) != null;
    }
    
    // Doctor operations
    public int addDoctor(Doctor doctor) {
        doctor.setDoctorId(nextDoctorId);
        doctors.put(nextDoctorId, doctor);
        return nextDoctorId++;
    }
    
    public Doctor getDoctor(int id) {
        return doctors.get(id);
    }
    
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors.values());
    }
    
    public boolean updateDoctor(Doctor doctor) {
        if (doctors.containsKey(doctor.getDoctorId())) {
            doctors.put(doctor.getDoctorId(), doctor);
            return true;
        }
        return false;
    }
    
    public boolean deleteDoctor(int id) {
        return doctors.remove(id) != null;
    }
    
    // Staff operations
    public int addStaff(Staff staffMember) {
        staffMember.setStaffId(nextStaffId);
        staff.put(nextStaffId, staffMember);
        return nextStaffId++;
    }
    
    public Staff getStaff(int id) {
        return staff.get(id);
    }
    
    public List<Staff> getAllStaff() {
        return new ArrayList<>(staff.values());
    }
    
    public boolean updateStaff(Staff staffMember) {
        if (staff.containsKey(staffMember.getStaffId())) {
            staff.put(staffMember.getStaffId(), staffMember);
            return true;
        }
        return false;
    }
    
    public boolean deleteStaff(int id) {
        return staff.remove(id) != null;
    }
    
    // Appointment operations
    public int addAppointment(Appointment appointment) {
        appointment.setAppointmentId(nextAppointmentId);
        appointments.put(nextAppointmentId, appointment);
        return nextAppointmentId++;
    }
    
    public Appointment getAppointment(int id) {
        return appointments.get(id);
    }
    
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments.values());
    }
    
    public boolean updateAppointment(Appointment appointment) {
        if (appointments.containsKey(appointment.getAppointmentId())) {
            appointments.put(appointment.getAppointmentId(), appointment);
            return true;
        }
        return false;
    }
    
    public boolean deleteAppointment(int id) {
        return appointments.remove(id) != null;
    }
    
    // Medical Record operations
    public int addMedicalRecord(MedicalRecord record) {
        record.setRecordId(nextRecordId);
        medicalRecords.put(nextRecordId, record);
        return nextRecordId++;
    }
    
    public List<MedicalRecord> getMedicalRecordsByPatient(int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        for (MedicalRecord record : medicalRecords.values()) {
            if (record.getPatientId() == patientId) {
                records.add(record);
            }
        }
        return records;
    }
    
    // Lab Test operations
    public int addLabTest(LabTest test) {
        test.setTestId(nextTestId);
        labTests.put(nextTestId, test);
        return nextTestId++;
    }
    
    public List<LabTest> getLabTestsByPatient(int patientId) {
        List<LabTest> tests = new ArrayList<>();
        for (LabTest test : labTests.values()) {
            if (test.getPatientId() == patientId) {
                tests.add(test);
            }
        }
        return tests;
    }
    
    // Billing operations
    public int addBilling(Billing bill) {
        bill.setBillId(nextBillId);
        billings.put(nextBillId, bill);
        return nextBillId++;
    }
    
    public List<Billing> getBillingsByPatient(int patientId) {
        List<Billing> bills = new ArrayList<>();
        for (Billing bill : billings.values()) {
            if (bill.getPatientId() == patientId) {
                bills.add(bill);
            }
        }
        return bills;
    }
    
    private void initializeSampleData() {
        // Initialize departments (from SQL)
        addDepartment(new Department(0, "Cardiology", "Building A"));
        addDepartment(new Department(0, "Neurology", "Building B"));
        addDepartment(new Department(0, "Pediatrics", "Building C"));
        addDepartment(new Department(0, "Orthopedics", "Building D"));
        addDepartment(new Department(0, "Dermatology", "Building E"));
    }
}
