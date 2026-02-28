package adminmangementsystem.com.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adminmangementsystem.com.ServiceRegistry;
import adminmangementsystem.com.Management.AppointmentService;
import adminmangementsystem.com.Model.Appointment;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController() {
        this.appointmentService = ServiceRegistry.getInstance().getAppointmentService();
    }

    // Check authentication helper
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn");
    }

    @GetMapping
    public String listAppointments(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("appointments", appointmentService.getAppointments());
        return "appointments";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "appointment-add";
    }

    @PostMapping("/add")
    public String addAppointment(@RequestParam String patientId,
                                @RequestParam String patientName,
                                @RequestParam String patientDOB,
                                @RequestParam String disease,
                                @RequestParam String phoneNumber,
                                @RequestParam String doa,
                                HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        try {
            Appointment appointment = new Appointment(patientId, patientName, patientDOB, disease, phoneNumber, doa);
            appointmentService.getAppointments().add(appointment);
        } catch (IllegalArgumentException e) {
            // Handle validation error - for now just redirect back
            return "redirect:/appointments/add";
        }
        
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable String id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        // Find and remove appointment by patient ID
        appointmentService.getAppointments().removeIf(a -> a.getPatientId().equals(id));
        
        return "redirect:/appointments";
    }
}
