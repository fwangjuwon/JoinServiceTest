package site.metacoding.logintest.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.logintest.handler.ex.CustomException;
import site.metacoding.logintest.service.UserService;
import site.metacoding.logintest.web.dto.JoinReqDto;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    
    
    @GetMapping("/join-form")
    public String joinForm() {
        return "/joinForm";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/loginForm";
    }

    @GetMapping("/user/{id}")
    public String updateForm(@PathVariable Integer id) {
        return "/updateForm";
    }

     @PostMapping("/join")
     public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

         if (bindingResult.hasErrors()) {
             Map<String, String> errorMap = new HashMap<>();
             for (FieldError fe : bindingResult.getFieldErrors()) {
                 errorMap.put(fe.getField(), fe.getDefaultMessage());
             }
             throw new CustomException(errorMap.toString());
         }

         userService.회원가입(joinReqDto.toEntity());

         return "redirect:/login-form";
     }

     @GetMapping("/juso-popup")
     public String jusoPopup() {
         return "/jusoPopup";
     }

     @PostMapping("/juso-popup")
     public String juso(String inputYn, String roadFullAddr, Model model) {
         model.addAttribute("inputYn", inputYn);
         model.addAttribute("roadFullAddr", roadFullAddr);
               return "/jusoPopup";
     }
    
}
