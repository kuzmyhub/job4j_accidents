package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate {

    private final SessionFactory sessionFactory;

    private static final String SELECT_ACCIDENTS = """
            SELECT DISTINCT a FROM Accident a
            JOIN FETCH a.accidentType
            JOIN FETCH a.rules
            """;

    public Accident create(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        }
        return accident;
    }

    public void save(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(accident);
            session.getTransaction().commit();
        }
    }

    public Optional<Accident> findById(int id) {
        Optional<Accident> optionalAccident;
        try (Session session = sessionFactory.openSession()) {
            optionalAccident = Optional.of(session.get(Accident.class, id));
        } catch (Exception e) {
            optionalAccident = Optional.empty();
        }
        return optionalAccident;
    }

    public List<Accident> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery(SELECT_ACCIDENTS, Accident.class)
                    .list();
        }
    }
}
