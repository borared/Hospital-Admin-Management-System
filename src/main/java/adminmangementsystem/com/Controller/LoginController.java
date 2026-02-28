package adminmangementsystem.com.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adminmangementsystem.com.Admin;

@Controller
public class LoginController {

    private final Admin admin = new Admin("admin", "admin$$");

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        // If already logged in, redirect to dashboard
        if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, 
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        
        if (admin.login(username, password)) {
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", username);
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
