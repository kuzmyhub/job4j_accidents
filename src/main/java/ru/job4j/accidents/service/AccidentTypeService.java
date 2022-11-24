package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMem;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private AccidentTypeMem accidentTypeMem;

    public Optional<AccidentType> findById(int id) {
        return accidentTypeMem.findById(id);
    }

    public List<AccidentType> findAll() {
        return accidentTypeMem.findAll();
    }
}
