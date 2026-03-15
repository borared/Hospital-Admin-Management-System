package adminmangementsystem.com.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Billing {

    private int billId;
    private int patientId;
    private BigDecimal totalAmount;
    private String paymentStatus;
    private LocalDate billDate;

    public Billing() {}

    public Billing(int billId, int patientId, BigDecimal totalAmount,
                   String paymentStatus, LocalDate billDate) {
        this.billId = billId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.billDate = billDate;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}
