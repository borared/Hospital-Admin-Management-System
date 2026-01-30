package adminmangementsystem.com;

public class Patient extends Person {
    private String disease;
    private String entryDate;

    public Patient(String id, String name, String dob, String address,
                   String disease, String entryDate) {
        super(id, name, dob, address);
        this.disease = disease;
        this.entryDate = entryDate;
    }

    public void setName(String name) { this.name = name; }
    public void setDob(String dob) { this.dob = dob; }
    public void setAddress(String address) { this.address = address; }
    public void setDisease(String disease) { this.disease = disease; }

    @Override
    public void display() {
        System.out.println(id + " " + name + " " + dob + " " +
                address + " " + disease + " " + entryDate);
    }
}

