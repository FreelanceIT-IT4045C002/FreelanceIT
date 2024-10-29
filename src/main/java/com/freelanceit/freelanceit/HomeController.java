package com.freelanceit.freelanceit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index.html")
    public String index() {
        return "index";  // This will return the index.html
    }

    @GetMapping("/login")
    public String login() {
        // This will return the login.html template
        return "login";
    }
}
