package adminmangementsystem.com.model;

/**
 * COMPOSITION: Appointment HAS-A Patient (not IS-A Patient)
 * This demonstrates the COMPOSITION OOP concept - objects contain other objects
 */
public class Appointment {

    // COMPOSITION: Appointment contains a Patient object
    private Patient patient;
    private String appointmentDate;
    private String appointmentTime;

    // Constructor using setters (no 'this' keyword as requested)
    public Appointment(Patient patient, String date, String time) {
        if (!setPatient(patient) ||
            !setAppointmentDate(date) ||
            !setAppointmentTime(time)) {
            throw new IllegalArgumentException("Invalid appointment data.");
        }
    }

    // SETTERS WITH VALIDATION
    public boolean setPatient(Patient patient) {
        if (patient != null) {
            this.patient = patient;
            return true;
        }
        return false;
    }

    public boolean setAppointmentDate(String date) {
        if (date != null && !date.isEmpty()) {
            appointmentDate = date;
            return true;
        }
        return false;
    }

    public boolean setAppointmentTime(String time) {
        if (time != null && !time.isEmpty()) {
            appointmentTime = time;
            return true;
        }
        return false;
    }

    // GETTERS - Access patient info through composition
    public Patient getPatient() {
        return patient;
    }
    
    public String getPatientId() {
        return patient.getId();
    }
    
    public String getPatientName() {
        return patient.getName();
    }
    
    public String getPatientDOB() {
        return patient.getDob();
    }
    
    public String getPatientAddress() {
        return patient.getAddress();
    }
    
    public String getPatientDisease() {
        return patient.getDisease();
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }
    
    // Keep old method names for compatibility
    public String getDOA() {
        return appointmentDate;
    }
    
    public String getPatientPhoneNum() {
        return patient.getAddress(); // Address field was used as phone in Patient
    }

    // Display method
    public void displayAppointment() {
        System.out.print("\n");
        System.out.println("\t\t\t\t------Appointment Details------");
        String line = "------------------------------------------------------------------------------------------------------------";

        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-20s | %-15s | %-12s | %-8s |\n", 
                          "ID", "Patient Name", "DOB", "Address", "Disease", "Date", "Time");
        System.out.println(line);
        
        System.out.printf("| %-5s | %-18s | %-12s | %-20s | %-15s | %-12s | %-8s |\n", 
                          getPatientId(), getPatientName(), getPatientDOB(), 
                          getPatientAddress(), getPatientDisease(), 
                          appointmentDate, appointmentTime);
        System.out.println(line);
    }
}
