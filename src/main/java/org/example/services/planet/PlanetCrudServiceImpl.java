package org.example.services.planet;

import org.example.entities.Planet;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudServiceImpl implements PlanetCrudService {
    private static final String GET_ALL_PLANETS_QUERY = "from Planet";
    @Override
    public Planet createPlanet(Planet planet) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(planet);
                transaction.commit();

            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return planet;
    }

    @Override
    public Planet updatePlanet(Planet planet) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(planet);
                transaction.commit();
            } catch(Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
        }
        return planet;
    }

    @Override
    public List<Planet> getAllPlanets() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.createQuery(GET_ALL_PLANETS_QUERY, Planet.class).list();
        }
    }

    @Override
    public Planet getPlanetById(String planetId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            return session.get(Planet.class, planetId);
        }
    }

    @Override
    public void deletePlanetById(String planetId) {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet existing = session.get(Planet.class, planetId);
            session.remove(existing);
            transaction.commit();
        }
    }
}
