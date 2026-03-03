package adminmangementsystem.com.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adminmangementsystem.com.Management.StaffSystem;
import adminmangementsystem.com.Model.Staff;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffSystem staffSystem;

    public StaffController() {
        this.staffSystem = new StaffSystem();
    }

    // Check authentication
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn");
    }

    // List all staff
    @GetMapping
    public String listAllStaff(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        
        model.addAttribute("allStaff", staffSystem.getAllStaff());
        model.addAttribute("doctors", staffSystem.getDoctors());
        model.addAttribute("nurses", staffSystem.getNurses());
        model.addAttribute("surgeons", staffSystem.getSurgeons());
        model.addAttribute("cardiologists", staffSystem.getCardiologists());
        
        // Add counts
        model.addAttribute("doctorCount", staffSystem.getDoctorCount());
        model.addAttribute("nurseCount", staffSystem.getNurseCount());
        model.addAttribute("surgeonCount", staffSystem.getSurgeonCount());
        model.addAttribute("cardiologistCount", staffSystem.getCardiologistCount());
        model.addAttribute("totalCount", staffSystem.getTotalStaffCount());
        
        return "staff-list";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        return "staff-add";
    }

    // Add staff - automatically creates correct type based on position
    @PostMapping("/add")
    public String addStaff(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String dob,
                          @RequestParam String address,
                          @RequestParam String email,
                          @RequestParam String position,
                          @RequestParam Double salary,
                          @RequestParam String doe,
                          HttpSession session,
                          Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        // Factory pattern - creates correct staff type based on position
        Staff newStaff = staffSystem.addStaff(id, name, dob, address, email, position, salary, doe);
        
        model.addAttribute("success", true);
        model.addAttribute("staffType", newStaff.getClass().getSimpleName());
        
        return "redirect:/staff";
    }

    // Delete staff
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable String id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        staffSystem.deleteStaff(id);
        return "redirect:/staff";
    }

    // View by type
    @GetMapping("/doctors")
    public String listDoctors(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute("staff", staffSystem.getDoctors());
        model.addAttribute("type", "Doctors");
        return "staff-by-type";
    }

    @GetMapping("/nurses")
    public String listNurses(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute("staff", staffSystem.getNurses());
        model.addAttribute("type", "Nurses");
        return "staff-by-type";
    }

    @GetMapping("/surgeons")
    public String listSurgeons(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute("staff", staffSystem.getSurgeons());
        model.addAttribute("type", "Surgeons");
        return "staff-by-type";
    }

    @GetMapping("/cardiologists")
    public String listCardiologists(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        model.addAttribute("staff", staffSystem.getCardiologists());
        model.addAttribute("type", "Cardiologists");
        return "staff-by-type";
    }
}
