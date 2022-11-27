package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccidentMapper implements RowMapper<Accident> {

    @Override
    public Accident mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt(1));
        accident.setName(resultSet.getString(2));
        accident.setText(resultSet.getString(3));
        accident.setAddress(resultSet.getString(4));
        accident.setAccidentType(
                new AccidentType(
                        resultSet.getInt(5),
                        resultSet.getString(6)
                )
        );
        return accident;
    }
}
