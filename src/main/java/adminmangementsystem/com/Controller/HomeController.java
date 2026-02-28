package adminmangementsystem.com.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session) {
        // Check if user is logged in
        if (session.getAttribute("loggedIn") == null || !(Boolean) session.getAttribute("loggedIn")) {
            return "redirect:/login";
        }
        return "index";
    }
}
