package adminmangementsystem.com;

public class Appointment {

    private String patientId;
    private String patientName;
    private String patientDOB;
    private String disease;
    private String phoneNumber;
    private String DOA;

    // Constructor
    public Appointment(String patientId, String patientName, String patientDOB,
                       String disease, String phoneNumber, String DOA) {

        if (!setPatientId(patientId) ||
            !setPatientName(patientName) ||
            !setPatientDOB(patientDOB) ||
            !setPatientDisease(disease) ||
            !setPatientPhoneNum(phoneNumber) ||
            !setDOA(DOA)) {

            throw new IllegalArgumentException("Invalid appointment data.");
        }
    }

    // SETTERS WITH VALIDATION
    public boolean setPatientId(String patientId) {
        if (patientId != null && !patientId.isEmpty()) {
            this.patientId = patientId;
            return true;
        }
        return false;
    }

    public boolean setPatientName(String patientName) {
        if (patientName.matches("[a-zA-Z ]{1,50}")) {
            this.patientName = patientName;
            return true;
        }
        return false;
    }

    public boolean setPatientDOB(String patientDOB) {
        if (!patientDOB.isEmpty()) {
            this.patientDOB = patientDOB;
            return true;
        }
        return false;
    }

    public boolean setPatientDisease(String disease) {
        if (!disease.isEmpty()) {
            this.disease = disease;
            return true;
        }
        return false;
    }

    public boolean setPatientPhoneNum(String phoneNumber) {
        if (phoneNumber.matches("\\d{8,15}")) {
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public boolean setDOA(String DOA) {
        if (!DOA.isEmpty()) {
            this.DOA = DOA;
            return true;
        }
        return false;
    }

    // GETTERS
    public String getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getPatientDOB() { return patientDOB; }
    public String getPatientDisease() { return disease; }
    public String getPatientPhoneNum() { return phoneNumber; }
    public String getDOA() { return DOA; }

    // Display method
    public void displayAppointment() {
        System.out.print("\n");
        System.out.println("\t\t\t\t------Appointment List------");
        String line = "------------------------------------------------------------------------------------------------";

        System.out.println(line);
        
        System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n", 
                          "ID", "Patient Name", "DOB", "Phone Number", "Disease", "DOA");
        System.out.println(line);
        
        System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n", 
                          patientId, patientName, patientDOB, phoneNumber, disease, DOA);
        System.out.println(line);
    }

}
