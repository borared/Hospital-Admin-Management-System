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
import adminmangementsystem.com.Management.DoctorSystem;
import adminmangementsystem.com.Model.Doctor;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorSystem doctorSystem;

    public DoctorController() {
        this.doctorSystem = ServiceRegistry.getInstance().getDoctorSystem();
    }

    // Check authentication helper
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn");
    }

    @GetMapping
    public String listDoctors(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("doctors", doctorSystem.getAllDoctors());
        return "doctors";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "doctor-add";
    }

    @PostMapping("/add")
    public String addDoctor(@RequestParam String id,
                           @RequestParam String name,
                           @RequestParam String dob,
                           @RequestParam String address,
                           @RequestParam String email,
                           @RequestParam String position,
                           @RequestParam Double salary,
                           @RequestParam String doe,
                           HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Doctor doctor = new Doctor(id, name, dob, address, email, position, salary, doe);
        doctorSystem.getAllDoctors().add(doctor);
        
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable String id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Doctor doctor = doctorSystem.searchDoctorById(id);
        if (doctor != null) {
            doctorSystem.getAllDoctors().remove(doctor);
        }
        
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Doctor doctor = doctorSystem.searchDoctorById(id);
        if (doctor == null) {
            return "redirect:/doctors";
        }
        
        model.addAttribute("doctor", doctor);
        return "doctor-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDoctor(@PathVariable String id,
                              @RequestParam String name,
                              @RequestParam String dob,
                              @RequestParam String address,
                              @RequestParam String email,
                              @RequestParam String position,
                              @RequestParam Double salary,
                              @RequestParam String doe,
                              HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        Doctor doctor = doctorSystem.searchDoctorById(id);
        if (doctor != null) {
            doctor.setName(name);
            doctor.setDob(dob);
            doctor.setAddress(address);
            doctor.setEmail(email);
            doctor.setPosition(position);
            doctor.setSalary(salary);
            doctor.setDoe(doe);
        }
        
        return "redirect:/doctors";
    }

    @GetMapping("/search")
    public String showSearchForm(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "doctor-search";
    }

    @PostMapping("/search")
    public String searchDoctor(@RequestParam String searchType,
                              @RequestParam String searchValue,
                              Model model,
                              HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        java.util.List<Doctor> results = new java.util.ArrayList<>();
        
        if ("id".equals(searchType)) {
            Doctor doctor = doctorSystem.searchDoctorById(searchValue);
            if (doctor != null) {
                results.add(doctor);
            }
        } else if ("name".equals(searchType)) {
            for (Doctor d : doctorSystem.getAllDoctors()) {
                if (d.getName().equalsIgnoreCase(searchValue)) {
                    results.add(d);
                }
            }
        }
        
        model.addAttribute("doctors", results);
        model.addAttribute("searched", true);
        return "doctor-search";
    }
}
