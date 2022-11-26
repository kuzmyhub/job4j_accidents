package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentHibernate {

    private final SessionFactory sessionFactory;

    public Accident save(Accident accident) {
        try (Session session = sessionFactory.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public List<Accident> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Accident a JOIN FETCH a.accidentType JOIN FETCH a.rules", Accident.class)
                    .list();
        }
    }
}
