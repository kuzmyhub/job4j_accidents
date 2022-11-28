package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @EntityGraph(attributePaths = { "accidentType", "rules" })
    List<Accident> findAll();

    @EntityGraph(attributePaths = { "accidentType", "rules" })
    Optional<Accident> findById(int id);
}
