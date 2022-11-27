package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    private static final String INSERT_ACCIDENT = """
            INSERT INTO accidents(name, text, address, accident_types_id)
            VALUES (?, ?, ?, ?)
            """;

    private static final String SELECT_ACCIDENT_WITH_JOIN = """
            SELECT a.id, a.name, a.text, a.address,
            a.accident_types_id, at.name
            FROM accidents a
            INNER JOIN accident_types at ON a.accident_types_id = at.id
            """;

    private static final String SELECT_ACCIDENT_WITH_JOIN_BY_ACCIDENT_ID = """
            WHERE a.id = (?)
            """;

    private static final String UPDATE_ACCIDENT = """
            UPDATE accidents
            SET name = (?), text = (?), address = (?), accident_types_id = (?)
            WHERE id = (?)
            """;

    private static final String INSERT_ACCIDENT_ID_RULE_ID = """
            INSERT INTO accidents_rules(accidents_id, rules_id) VALUES (?, ?)
            """;

    private static final String SELECT_ACCIDENTS_ID_RULES = """
            SELECT ar.accidents_id, r.id, r.name
            FROM accidents_rules ar INNER JOIN rules r ON ar.rules_id = r.id 
            """;

    private static final String DELETE_ACCIDENT_ID_RULE_ID_BY_ACCIDENT_ID = """
            DELETE FROM accidents_rules WHERE accidents_id = (?)
            """;

    private static final String SELECT_RULES_BY_ACCIDENTS_ID = """
            SELECT r.id, r.name
            FROM accidents_rules ar INNER JOIN rules r ON ar.rules_id = r.id
            WHERE ar.accidents_id = (?)
            """;

    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            INSERT_ACCIDENT,
                            Statement.RETURN_GENERATED_KEYS
                    );
            preparedStatement.setString(1, accident.getName());
            preparedStatement.setString(2, accident.getText());
            preparedStatement.setString(3, accident.getAddress());
            preparedStatement.setInt(4, accident.getAccidentType().getId());
            return preparedStatement;
                }, keyHolder);
        int accidentId = (Integer) keyHolder.getKeys().get("id");
        for (Rule r : accident.getRules()) {
            jdbc.update(
                    INSERT_ACCIDENT_ID_RULE_ID,
                    accidentId, r.getId()
            );
        }
        return accident;
    }

    public void save(Accident accident) {
        jdbc.update(
                UPDATE_ACCIDENT,
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getAccidentType().getId(), accident.getId()
        );
        jdbc.update(DELETE_ACCIDENT_ID_RULE_ID_BY_ACCIDENT_ID, accident.getId());
        Set<Rule> rules = accident.getRules();
        for (Rule r : rules) {
            jdbc.update(INSERT_ACCIDENT_ID_RULE_ID, accident.getId(), r.getId());
        }
    }

    public Optional<Accident> findById(int id) {
        List<Accident> accidents = jdbc.query(
                String.format(
                        "%s %s",
                        SELECT_ACCIDENT_WITH_JOIN,
                        SELECT_ACCIDENT_WITH_JOIN_BY_ACCIDENT_ID
                ),
                new AccidentMapper(), id);
        Accident accident = accidents.get(0);
        accident.setRules(findRulesByAccidentId(accident.getId()));
        return Optional.ofNullable(accident);
    }

    public List<Accident> findAll() {
        List<Accident> accidents = jdbc.query(SELECT_ACCIDENT_WITH_JOIN,
                new AccidentMapper());
        HashMap<Integer, Set<Rule>> accidentsAndRules = findAllRules();
        for (Accident a : accidents) {
            a.setRules(accidentsAndRules.get(a.getId()));
        }
       return accidents;
    }

    public HashMap<Integer, Set<Rule>> findAllRules() {
        HashMap<Integer, Set<Rule>> accidentsAndRules = new HashMap<>();
        jdbc.query(SELECT_ACCIDENTS_ID_RULES,
                (resultSet, rowNum) -> {
                    Integer accidentId = resultSet.getInt("accidents_id");
                    Rule rule = new Rule();
                    rule.setId(resultSet.getInt("id"));
                    rule.setName(resultSet.getString("name"));
                    if (!accidentsAndRules.containsKey(accidentId)) {
                        Set<Rule> rules = new HashSet<>();
                        rules.add(rule);
                        accidentsAndRules.put(accidentId, rules);
                    } else {
                        accidentsAndRules.get(accidentId).add(rule);
                    }
                    return accidentId;
                });
        return accidentsAndRules;
    }

    public HashSet<Rule> findRulesByAccidentId(int id) {
        List<Rule> rulesList = jdbc.query(SELECT_RULES_BY_ACCIDENTS_ID,
                (resultSet, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setId(resultSet.getInt(1));
                    rule.setName(resultSet.getString(2));
                    return rule;
                }, id);
        return new HashSet<>(rulesList);
    }
}
