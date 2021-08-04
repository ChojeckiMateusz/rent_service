package pl.chojecki.rent_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
}
