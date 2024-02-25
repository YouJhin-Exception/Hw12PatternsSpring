package org.youjhin.hw12patternsspring.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController {
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
