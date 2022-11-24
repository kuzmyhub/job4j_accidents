package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentMem accidentMem;

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public HashMap<Integer, Accident> findAll() {
        return accidentMem.findAll();
    }
}
