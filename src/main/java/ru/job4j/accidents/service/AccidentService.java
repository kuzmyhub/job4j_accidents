package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public void add(Integer number, Accident accident) {
        accidentMem.add(number, accident);
    }

    public HashMap<Integer, Accident> findAll() {
        return accidentMem.findAll();
    }
}
