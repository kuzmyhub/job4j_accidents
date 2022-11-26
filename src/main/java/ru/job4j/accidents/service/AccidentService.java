package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentMem accidentJdbcTemplate;

    public void create(Accident accident) {
        accidentJdbcTemplate.create(accident);
    }

    public void save(Accident accident) {
        accidentJdbcTemplate.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public List<Accident> findAll() {
        return accidentJdbcTemplate.findAll();
    }
}
