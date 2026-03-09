package adminmangementsystem.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.entity.Appointment;
import adminmangementsystem.com.service.interfaces.IAppointmentService;

public class AppointmentService implements IAppointmentService {
    
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }
        
        if (!isPatientIdUnique(appointment.getPatientId())) {
            throw new IllegalArgumentException("Appointment for this patient ID already exists.");
        }
        
        appointments.add(appointment);
    }
    
    @Override
    public boolean updateAppointment(Appointment updatedAppointment) {
        if (updatedAppointment == null) {
            return false;
        }
        
        Appointment existingAppointment = searchAppointmentById(updatedAppointment.getPatientId());
        
        if (existingAppointment == null) {
            return false;
        }
        
        existingAppointment.setPatientName(updatedAppointment.getPatientName());
        existingAppointment.setPatientDOB(updatedAppointment.getPatientDOB());
        existingAppointment.setPatientPhoneNum(updatedAppointment.getPatientPhoneNum());
        existingAppointment.setPatientDisease(updatedAppointment.getPatientDisease());
        existingAppointment.setDOA(updatedAppointment.getDOA());
        
        return true;
    }
    
    @Override
    public boolean cancelAppointment(String patientId) {
        Appointment appointment = searchAppointmentById(patientId);
        if (appointment != null) {
            appointments.remove(appointment);
            return true;
        }
        return false;
    }
    
    @Override
    public Appointment searchAppointmentById(String patientId) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equalsIgnoreCase(patientId)) {
                return appointment;
            }
        }
        return null;
    }
    
    @Override
    public List<Appointment> searchAppointmentsByName(String patientName) {
        List<Appointment> results = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientName().equalsIgnoreCase(patientName)) {
                results.add(appointment);
            }
        }
        return results;
    }
    
    @Override
    public List<Appointment> searchAppointmentsByDate(String date) {
        List<Appointment> results = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDOA().equals(date)) {
                results.add(appointment);
            }
        }
        return results;
    }
    
    @Override
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }
    
    @Override
    public boolean isPatientIdUnique(String patientId) {
        return searchAppointmentById(patientId) == null;
    }
    
    @Override
    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        String line = "------------------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n",
                "ID", "Patient Name", "DOB", "Phone", "Disease", "DOA");
        System.out.println(line);

        for (Appointment a : appointments) {
            System.out.printf("| %-5s | %-18s | %-12s | %-15s | %-15s | %-12s |\n",
                    a.getPatientId(),
                    a.getPatientName(),
                    a.getPatientDOB(),
                    a.getPatientPhoneNum(),
                    a.getPatientDisease(),
                    a.getDOA());
        }

        System.out.println(line);
    }

    public void addAppointment(Scanner sc) {
        Appointment appointment = adminmangementsystem.com.view.AppointmentView.getAppointmentInput(sc);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.\n");
    }
}
