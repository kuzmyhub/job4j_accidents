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
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AccidentControl {

    private AccidentService accidentService;

    private AccidentTypeService accidentTypeService;

    private RuleService ruleService;

    @GetMapping("/formCreateAccident")
    public String viewCreateAccident(Model model) {
        List<AccidentType> accidentTypes = accidentTypeService.findAll();
        Set<Rule> rules = ruleService.findAll();
        model.addAttribute("accidentTypes", accidentTypes);
        model.addAttribute("rules", rules);
        return "formCreateAccident";
    }

    @PostMapping("/createAccident")
    public String create(@ModelAttribute Accident accident,
                         HttpServletRequest httpServletRequest) {
        Optional<AccidentType> accidentType = accidentTypeService
                .findById(
                        accident.getAccidentType()
                                .getId()
                );
        if (accidentType.isEmpty()) {
            return "404";
        }
        accident.setAccidentType(accidentType.get());
        String[] rIds = httpServletRequest.getParameterValues("rIds");
        Set<Rule> rules = new HashSet<>();
        for (String r : rIds) {
            Optional<Rule> optionalRule = ruleService
                    .findById(
                    Integer.parseInt(r)
            );
            if (optionalRule.isEmpty()) {
                return "404";
            }
            rules.add(optionalRule.get());
        }
        accident.setRules(rules);
        accidentService.create(accident);
        return "redirect:/index";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident,
                       HttpServletRequest httpServletRequest) {
        Optional<AccidentType> accidentType = accidentTypeService
                .findById(
                        accident.getAccidentType()
                                .getId()
                );
        if (accidentType.isEmpty()) {
            return "404";
        }
        accident.setAccidentType(accidentType.get());
        String[] rIds = httpServletRequest.getParameterValues("rIds");
        Set<Rule> rules = new HashSet<>();
        for (String r : rIds) {
            Optional<Rule> optionalRule = ruleService
                    .findById(
                            Integer.parseInt(r)
                    );
            if (optionalRule.isEmpty()) {
                return "404";
            }
            rules.add(optionalRule.get());
        }
        accident.setRules(rules);
        accidentService.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            return "404";
        }
        List<AccidentType> accidentTypes = accidentTypeService.findAll();
        Set<Rule> rules = ruleService.findAll();
        model.addAttribute("accident", optionalAccident.get());
        model.addAttribute("accidentTypes", accidentTypes);
        model.addAttribute("rules", rules);
        return "formUpdateAccident";
    }
}
