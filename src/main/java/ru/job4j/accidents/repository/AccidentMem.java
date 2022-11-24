package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    private int ids = 1;

    public AccidentMem() {
        accidents.put(ids++, new Accident(
                1, "Accident 1", "text", "address",
                new AccidentType(1, "Две машины"),
                Set.of(
                        new Rule(1, "Статья. 1"),
                        new Rule(2, "Статья. 2")
                )
        ));
        accidents.put(ids++, new Accident(
                2, "Accident 2", "text", "address",
                new AccidentType(1, "Машина и человек"),
                Set.of(
                        new Rule(1, "Статья. 2"),
                        new Rule(2, "Статья. 3")
                )
        ));
        accidents.put(ids++, new Accident(
                3, "Accident 3", "text", "address",
                new AccidentType(1, "Машина и велосипед"),
                Set.of(
                        new Rule(1, "Статья. 1"),
                        new Rule(2, "Статья. 3")
                )
        ));
    }

    public void create(Accident accident) {
        accident.setId(ids);
        accidents.put(ids++, accident);
    }

    public void save(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public HashMap<Integer, Accident> findAll() {
        return new HashMap<>(accidents);
    }
}
