package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.*;

@Repository
public class AccidentTypeMem {

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public AccidentTypeMem() {
        accidentTypes.put(1, new AccidentType(1, "Две машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(accidentTypes.values());
    }
}
