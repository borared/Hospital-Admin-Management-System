package adminmangementsystem.com;

public class Doctor extends Person {
    private String position;
    private double salary;

    public Doctor(String id, String name, String dob, String address,
                  String position, double salary) {
        super(id, name, dob, address);
        this.position = position;
        this.salary = salary;
    }

    public void setName(String name) { this.name = name; }
    public void setDob(String dob) { this.dob = dob; }
    public void setAddress(String address) { this.address = address; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public void display() {
        System.out.println(id + " " + name + " " + dob + " " +
                address + " " + position + " $" + salary);
    }
}
