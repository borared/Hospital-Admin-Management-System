package adminmangementsystem.com.service;

import adminmangementsystem.com.entity.Appointment;
import adminmangementsystem.com.repository.HospitalRepository;
import adminmangementsystem.com.service.interfaces.IAppointmentService;

import java.util.List;

public class AppointmentService implements IAppointmentService {
    
    private HospitalRepository repository;
    
    public AppointmentService() {
        this.repository = HospitalRepository.getInstance();
    }
    
    @Override
    public int addAppointment(Appointment appointment) {
        return repository.addAppointment(appointment);
    }
    
    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return repository.getAppointment(appointmentId);
    }
    
    @Override
    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }
    
    @Override
    public boolean updateAppointment(Appointment appointment) {
        return repository.updateAppointment(appointment);
    }
    
    @Override
    public boolean deleteAppointment(int appointmentId) {
        return repository.deleteAppointment(appointmentId);
    }
    
    public void displayAllAppointments() {
        List<Appointment> appointments = getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        
        System.out.println("\n=== All Appointments ===");
        for (Appointment appointment : appointments) {
            appointment.display();
            System.out.println("-------------------");
        }
    }
}
