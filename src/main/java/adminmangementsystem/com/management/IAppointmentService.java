package adminmangementsystem.com.management;

import java.util.List;
import adminmangementsystem.com.model.Appointment;

public interface IAppointmentService {
    
    void addAppointment(Appointment appointment);
    
    boolean updateAppointment(Appointment appointment);
    
    boolean cancelAppointment(String patientId);
    
    Appointment searchAppointmentById(String patientId);
    
    List<Appointment> searchAppointmentsByName(String patientName);
    
    List<Appointment> searchAppointmentsByDate(String date);
    
    List<Appointment> getAllAppointments();
    
    boolean isPatientIdUnique(String patientId);
    
    void viewAppointments();
}
