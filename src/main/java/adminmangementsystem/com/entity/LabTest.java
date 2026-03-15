package adminmangementsystem.com.entity;

import java.time.LocalDate;

public class LabTest {

    private int testId;
    private int patientId;
    private String testName;
    private String result;
    private LocalDate testDate;

    public LabTest() {}

    public LabTest(int testId, int patientId, String testName, 
                   String result, LocalDate testDate) {
        this.testId = testId;
        this.patientId = patientId;
        this.testName = testName;
        this.result = result;
        this.testDate = testDate;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }
}
