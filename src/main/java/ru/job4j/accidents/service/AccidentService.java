package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentHibernate accidentRepository;

    /*public Accident create(Accident accident) {
        return accidentRepository.create(accident);
    }*/

    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    /*public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }*/

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }
}
