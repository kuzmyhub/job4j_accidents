package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    String SELECT_ACCIDENTS = """
            SELECT DISTINCT a FROM Accident a
            JOIN FETCH a.accidentType
            JOIN FETCH a.rules
            """;

    String SELECT_ACCIDENT = """
            SELECT a FROM Accident a WHERE id = :fId
            """;

    @Query(SELECT_ACCIDENTS)
    List<Accident> findAll();

    @Query(SELECT_ACCIDENT)
    Optional<Accident> findById(@Param("fId") int id);
}
