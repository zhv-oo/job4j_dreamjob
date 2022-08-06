package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.services.UserService;

import java.util.Optional;

@ThreadSafe
@Controller
public class UsersRegController {
    private final UserService userService;

    public UsersRegController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formRegistrationUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User(0, "Введите почту", "Введите имя"));
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
            Optional<User> regUser = userService.add(user);
            if (regUser.isEmpty()) {
                model.addAttribute("message", "Пользователь с такой почтой уже существует");
                return "redirect:/fail";
        }

        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }
}
