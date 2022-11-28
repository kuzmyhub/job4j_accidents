package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

@Controller
@AllArgsConstructor
public class IndexControl {

    private AccidentService accidentService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute(
                "user",
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        );
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
