package adminmangementsystem.com.model;

import java.time.LocalDateTime;

public abstract class Staff {

      protected String id;
    protected String name;
    protected String dob;
    protected String address;
    protected String email;
    protected String position;
    protected double salary;
    protected String doe;
    protected String qrCode;
    protected boolean isActive;
    protected LocalDateTime lastCheckIn;
    protected LocalDateTime lastCheckOut;

    // CONSTRUCTOR
    public Staff(String id, String name, String dob, String address,
                 String email, String position, double salary, String doe) {
        setId(id);
        setName(name);
        setDob(dob);
        setAddress(address);
        setEmail(email);
        setPosition(position);
        setSalary(salary);
        setDoe(doe);
        this.qrCode = generateQRCode(id);
        this.isActive = false;
        this.lastCheckIn = null;
        this.lastCheckOut = null;
    }
    
    // Generate unique QR code data for staff
    private String generateQRCode(String staffId) {
        return "STAFF:" + staffId + ":" + System.currentTimeMillis();
    }

    // SETTERS
    public boolean setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
            return true;
        }
        return false;
    }

    public boolean setName(String name) {
        if (name != null && name.matches("[a-zA-Z ]{1,50}")) {
            this.name = name;
            return true;
        }
        return false;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean setEmail(String email) {
        if (email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
            return true;
        }
        return false;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
            return true;
        }
        return false;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    // GETTERS
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

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public String getDoe() {
        return doe;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public LocalDateTime getLastCheckIn() {
        return lastCheckIn;
    }
    
    public void setLastCheckIn(LocalDateTime lastCheckIn) {
        this.lastCheckIn = lastCheckIn;
    }
    
    public LocalDateTime getLastCheckOut() {
        return lastCheckOut;
    }
    
    public void setLastCheckOut(LocalDateTime lastCheckOut) {
        this.lastCheckOut = lastCheckOut;
    }
    
    // Check in staff
    public void checkIn() {
        this.isActive = true;
        this.lastCheckIn = LocalDateTime.now();
    }
    
    // Check out staff
    public void checkOut() {
        this.isActive = false;
        this.lastCheckOut = LocalDateTime.now();
    }

    // ABSTRACT METHODS - Each staff type must implement these
    public abstract String getResponsibilities();
    public abstract String getDepartment();
    public abstract void performDuty();

    // DISPLAY
    public void display() {
        System.out.println(id + " | " + name + " | " + dob + " | "
                + address + " | " + email + " | "
                + position + " | $" + salary + " | " + doe);
    }
}
