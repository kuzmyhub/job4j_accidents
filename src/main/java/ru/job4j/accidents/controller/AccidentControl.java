package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentControl {

    private AccidentService accidentService;

    private AccidentTypeMem accidentTypeService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        List<AccidentType> accidentTypes = accidentTypeService.findAll();
        model.addAttribute("accidentTypes", accidentTypes);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        if (accident.getId() == 0) {
            Optional<AccidentType> accidentType = accidentTypeService
                    .findById(
                    accident.getAccidentType().getId()
            );
            if (accidentType.isEmpty()) {
                return "404";
            }
            accident.setAccidentType(accidentType.get());
            accidentService.create(accident);
        } else {
            Optional<AccidentType> accidentType = accidentTypeService
                    .findById(
                            accident.getAccidentType().getId()
                    );
            if (accidentType.isEmpty()) {
                return "404";
            }
            accident.setAccidentType(accidentType.get());
            accidentService.save(accident);
        }
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            return "404";
        }
        List<AccidentType> accidentTypes = accidentTypeService.findAll();
        model.addAttribute("accidentTypes", accidentTypes);
        model.addAttribute("accident", optionalAccident.get());
        return "formUpdateAccident";
    }
}
