package adminmangementsystem.com.model;

public class Patient {

    private final String id;  // 'final' = immutable, cannot change after construction
    private String name;
    private String dob;
    private String address;
    private String disease;
    private String entryDate;

    // Constructor
    public Patient(String patId, String patName, String patDOB,
                   String patDisease, String patPhoneNumber, String patDOE) {
        // Validate ID in constructor (only place to set it)
        if (patId == null || patId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be null or empty");
        }
        this.id = patId.trim();  // Set once, cannot change later
        
        // Other fields use setters with validation
        if(!setName(patName) ||
           !setDob(patDOB) ||
           !setDisease(patDisease) ||
           !setAddress(patPhoneNumber) ||
           !setEntryDate(patDOE)) {

            throw new IllegalArgumentException("Invalid patient data.");
        }
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getDisease() {
        return disease;
    }

    public String getEntryDate() {
        return entryDate;
    }

    // Setters with validation
    // NOTE: No setId() method - ID is read-only after creation
    
    public boolean setName(String name) {
        if (name != null && !name.isEmpty() && name.matches("[a-zA-Z ]{1,50}")) {
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean setDob(String dob) {
        if (dob != null && !dob.isEmpty() && dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            this.dob = dob;
            return true;
        }
        return false;
    }

    public boolean setAddress(String address) {
        if (address != null && !address.isEmpty()) {
            this.address = address;
            return true;
        }
        return false;
    }

    public boolean setDisease(String disease) {
        if (disease != null && !disease.trim().isEmpty()) {
            this.disease = disease.trim();
            return true;
        }
        return false;
    }

    public boolean setEntryDate(String entryDate) {
        if (entryDate != null && !entryDate.isEmpty() && entryDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            this.entryDate = entryDate;
            return true;
        }
        return false;
    }

    // Display method - instance-based, shows THIS patient's data in table format
    public void displayPatientInfo() {
        System.out.printf("| %-5s | %-18s | %-12s | %-18s | %-15s | %-12s |\n",
                id, name, dob, address, disease, entryDate);
    }


    
}


