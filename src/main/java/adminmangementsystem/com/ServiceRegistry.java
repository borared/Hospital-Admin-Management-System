package adminmangementsystem.com;

import adminmangementsystem.com.Management.AppointmentService;
import adminmangementsystem.com.Management.DoctorSystem;
import adminmangementsystem.com.Management.PatientSystem;

public class ServiceRegistry {
    private static final ServiceRegistry INSTANCE = new ServiceRegistry();
    
    private final DoctorSystem doctorSystem;
    private final PatientSystem patientSystem;
    private final AppointmentService appointmentService;
    
    private ServiceRegistry() {
        this.doctorSystem = new DoctorSystem();
        this.patientSystem = new PatientSystem();
        this.appointmentService = new AppointmentService();
    }
    
    public static ServiceRegistry getInstance() {
        return INSTANCE;
    }
    
    public DoctorSystem getDoctorSystem() {
        return doctorSystem;
    }
    
    public PatientSystem getPatientSystem() {
        return patientSystem;
    }
    
    public AppointmentService getAppointmentService() {
        return appointmentService;
    }
}
