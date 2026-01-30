package adminmangementsystem.com;

public class Appointment {
    private String patientId;
    private String patientName;
    private String disease;
    private String date;

    public Appointment(String patientId, String patientName,
                       String disease, String date) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.disease = disease;
        this.date = date;
    }

    public void display() {
        System.out.println(patientId + " " + patientName + " " +
                disease + " " + date);
    }
}

