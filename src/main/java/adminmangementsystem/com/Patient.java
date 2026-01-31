package adminmangementsystem.com;

public class Patient {

    private String id;
    private String name;
    private String dob;
    private String address;
    private String disease;
    private String entryDate;

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

    public void setDob(String dob) { this.dob = dob; }
    public void setAddress(String address) { this.address = address; }
    public void setDisease(String disease) { this.disease = disease; }
    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }

    public String getId() { return id; }

    public void display() {
        System.out.println(id + " " + name + " " + dob + " "
                + address + " " + disease + " " + entryDate);
    }
}



