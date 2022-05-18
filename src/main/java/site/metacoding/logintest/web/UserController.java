package site.metacoding.logintest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
    
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/update-form")
    public String updateForm() {
        return "updateForm";
    }
}
