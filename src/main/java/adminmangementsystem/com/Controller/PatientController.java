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
import adminmangementsystem.com.Management.PatientSystem;
import adminmangementsystem.com.Model.Patient;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientSystem patientSystem;

    public PatientController() {
        this.patientSystem = ServiceRegistry.getInstance().getPatientSystem();
    }

    // Check authentication helper
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn");
    }

    @GetMapping
    public String listPatients(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("patients", patientSystem.getAllPatients());
        return "patients";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "patient-add";
    }

    @PostMapping("/add")
    public String addPatient(@RequestParam String id,
                            @RequestParam String name,
                            @RequestParam String dob,
                            @RequestParam String address,
                            @RequestParam String disease,
                            @RequestParam String entryDate,
                            HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Patient patient = new Patient(id, name, dob, disease, address, entryDate);
        patientSystem.getAllPatients().add(patient);
        
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable String id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Patient patient = PatientSystem.searchPatientById(id);
        if (patient != null) {
            patientSystem.getAllPatients().remove(patient);
        }
        
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Patient patient = PatientSystem.searchPatientById(id);
        if (patient == null) {
            return "redirect:/patients";
        }
        
        model.addAttribute("patient", patient);
        return "patient-edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable String id,
                               @RequestParam String name,
                               @RequestParam String dob,
                               @RequestParam String address,
                               @RequestParam String disease,
                               @RequestParam String entryDate,
                               HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Patient patient = PatientSystem.searchPatientById(id);
        if (patient != null) {
            patient.setName(name);
            patient.setDob(dob);
            patient.setAddress(address);
            patient.setDisease(disease);
            patient.setEntryDate(entryDate);
        }
        
        return "redirect:/patients";
    }

    @GetMapping("/search")
    public String showSearchForm(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "patient-search";
    }

    @PostMapping("/search")
    public String searchPatient(@RequestParam String searchType,
                               @RequestParam String searchValue,
                               Model model,
                               HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        java.util.List<Patient> results = new java.util.ArrayList<>();
        
        if ("id".equals(searchType)) {
            Patient patient = PatientSystem.searchPatientById(searchValue);
            if (patient != null) {
                results.add(patient);
            }
        } else if ("name".equals(searchType)) {
            for (Patient p : patientSystem.getAllPatients()) {
                if (p.getName().equalsIgnoreCase(searchValue)) {
                    results.add(p);
                }
            }
        }
        
        model.addAttribute("patients", results);
        model.addAttribute("searched", true);
        return "patient-search";
    }
}
