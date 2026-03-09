package adminmangementsystem.com.service.interfaces;

import java.time.LocalDateTime;

public interface IStaffService {

    String getId();
    String getName();
    String getDOB();
    String getAddress();
    String getEmail();
    String getPosition();
    double getSalary();
    String getDoe();
    String getQrCode();
    boolean isActive();
    LocalDateTime getLastCheckIn();
    LocalDateTime getLastCheckOut();
    
}