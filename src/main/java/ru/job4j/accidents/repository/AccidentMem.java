package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;

@Repository
@AllArgsConstructor
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    private int ids = 1;

    public AccidentMem() {
        Accident firstAccident = new Accident();
        Accident secondAccident = new Accident();
        Accident thirdAccident = new Accident();
        firstAccident.setName("Accident 1");
        secondAccident.setName("Accident 2");
        thirdAccident.setName("Accident 3");
        create(firstAccident);
        create(secondAccident);
        create(thirdAccident);
    }

    public void create(Accident accident) {
        accidents.put(ids++, accident);
    }

    public HashMap<Integer, Accident> findAll() {
        return new HashMap<>(accidents);
    }
}
