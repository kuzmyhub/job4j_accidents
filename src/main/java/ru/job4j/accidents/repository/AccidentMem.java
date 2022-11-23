package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public void add(int number, Accident accident) {
        accidents.put(number, accident);
    }

    public HashMap<Integer, Accident> findAll() {
        return new HashMap<>(accidents);
    }
}
