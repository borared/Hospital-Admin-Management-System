package adminmangementsystem.com.entity;

public class Staff {

    private int staffId;
    private String firstName;
    private String lastName;
    private String role;
    private String phone;
    private int departmentId;

    public Staff() {}

    public Staff(int staffId, String firstName, String lastName, 
                 String role, String phone, int departmentId) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void display() {
        System.out.println("Staff ID: " + staffId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Role: " + role);
        System.out.println("Phone: " + phone);
        System.out.println("Department ID: " + departmentId);
    }
}
