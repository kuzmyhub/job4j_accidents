package ru.job4j.accidents.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.*;

@Data
@Repository
public class RuleMem {

    private final Map<Integer, Rule> rules = new HashMap<>();

    public RuleMem() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    public HashSet<Rule> findAll() {
        return new HashSet<>(rules.values());
    }
}
