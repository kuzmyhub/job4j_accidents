package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
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
        model.addAttribute("user", "Гость");
        Accident firstAccident = new Accident();
        Accident secondAccident = new Accident();
        Accident thirdAccident = new Accident();
        firstAccident.setText("Accident 1");
        secondAccident.setText("Accident 2");
        thirdAccident.setText("Accident 3");
        accidentService.add(1, firstAccident);
        accidentService.add(2, secondAccident);
        accidentService.add(3, thirdAccident);
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
