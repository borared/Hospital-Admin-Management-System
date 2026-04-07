package adminmangementsystem.com.management;

import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.model.Appointment;

public interface IAppointmentService {
    
    void addAppointment(Appointment appointment);
    
    void addAppointment(Scanner sc);
    
    boolean updateAppointment(Appointment appointment);
    
    boolean cancelAppointment(String patientId);
    
    Appointment searchAppointmentById(String patientId);
    
    List<Appointment> searchAppointmentsByName(String patientName);
    
    List<Appointment> searchAppointmentsByDate(String date);
    
    List<Appointment> getAllAppointments();
    
    boolean isPatientIdUnique(String patientId);
    
    void viewAppointments();
}
