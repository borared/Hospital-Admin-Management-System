package adminmangementsystem.com.Model;

public class Patient {

    private String id;
    private String name;
    private String dob;
    private String address;
    private String disease;
    private String entryDate;

    // Constructor
    public Patient(String patId, String patName, String patDOB,
                   String patDisease, String patPhoneNumber, String patDOE) {
        if(!setId(patId) ||
           !setName(patName) ||
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
    public boolean setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
            return true;
        }
        return false;
    }

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
        if (disease != null && !disease.isEmpty()) {
            this.disease = disease;
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

    // Display method
    public void displayPatientInfo() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Address: " + address);
        System.out.println("Disease: " + disease);
        System.out.println("Entry Date: " + entryDate);
    }


    
}


