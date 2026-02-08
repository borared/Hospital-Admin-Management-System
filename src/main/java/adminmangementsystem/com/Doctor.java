package adminmangementsystem.com;

public class Doctor {

    private String id;
    private String name;
    private String dob;
    private String address;
    private String position;
    private double salary;

    // Constructor with validation
    public Doctor(String docId, String docName, String docDOB,
            String docAddress, String docPosition, double docSalary) {
        if (!setId(docId) ||
                !setName(docName) ||
                !setDob(docDOB) ||
                !setAddress(docAddress) ||
                !setPosition(docPosition) ||
                !setSalary(docSalary)) {

            throw new IllegalArgumentException("Invalid doctor data.");
        }
    }

    // getters
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

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
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

    public boolean setPosition(String position) {
        if (position != null && !position.isEmpty()) {
            this.position = position;
            return true;
        }
        return false;
    }

    public boolean setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
            return true;
        }
        return false;
    }

    // Display method
    public void displayDoctorInfo() {
        System.out.println("Doctor ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Address: " + address);
        System.out.println("Position: " + position);
        System.out.println("Salary: $" + salary);
    }
}