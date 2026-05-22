package dao.custom.impl;

import dao.custom.TherapistDAO;
import entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class TherapistDAOImpl
        implements TherapistDAO {

    @Override
    public boolean save(Therapist therapist) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        session.persist(therapist);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Therapist therapist) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        session.merge(therapist);

        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        Therapist therapist =
                session.get(Therapist.class,id);

        if(therapist != null){
            session.remove(therapist);
        }

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public Therapist search(String id) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Therapist therapist =
                session.get(Therapist.class,id);

        session.close();

        return therapist;
    }

    @Override
    public List<Therapist> getAll() {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        List<Therapist> list =
                session.createQuery(
                        "FROM Therapist",
                        Therapist.class
                ).list();

        session.close();

        return list;
    }
}