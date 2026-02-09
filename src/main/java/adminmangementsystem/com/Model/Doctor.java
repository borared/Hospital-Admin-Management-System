package adminmangementsystem.com.Model;

public class Doctor {

    private String id;
    private String name;
    private String dob;
    private String address;
    private String position;
    private double salary;

    // SETTERS
    public boolean setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
            return true;
        }
        return false;
    }

    public boolean setName(String name) {
        if (name.matches("[a-zA-Z ]{1,50}")) {
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

    // GETTERS
    public String getId() { return id; }

    public void display() {
        System.out.println(id + " " + name + " " + dob + " "
                + address + " " + position + " " + salary + "$");
    }
}

