package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentMem accidentMem;

    public void create(Accident accident) {
        accidentMem.create(accident);
    }

    public void save(Accident accident) {
        accidentMem.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }
}
