package com.fprojects.blog.controllers;

import com.fprojects.blog.entitys.UserEntity;
import com.fprojects.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());
        return "registration";
    }

    /*@PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm")
                          @Valid User userForm,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) { return "registration"; }

        if (userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }*/

    /**
     *Создать нового пользователя
     */
    @PostMapping("/registration")
    public String blogAdd(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String passwordConfirm,
                          Model model) {
        UserEntity user = new UserEntity(username, password, passwordConfirm);
        userService.saveUser(user);
        return "redirect:/";
    }
}
