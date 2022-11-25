package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentJdbcTmplate {

    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name) values (?)",
        accident.getName());
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query("select id, name from accidents",
                (resultSet, rowNum) -> {
           Accident accident = new Accident();
           accident.setId(resultSet.getInt("id"));
           accident.setName(resultSet.getString("name"));
           return accident;
        });
    }
}
