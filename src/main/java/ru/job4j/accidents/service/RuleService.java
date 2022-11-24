package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.HashSet;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleService {

    private RuleMem ruleMem;

    public Optional<Rule> findById(int id) {
        return ruleMem.findById(id);
    }

    public HashSet<Rule> findAll() {
        return ruleMem.findAll();
    }

}
