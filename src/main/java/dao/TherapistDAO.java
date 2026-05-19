package dao;

import entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class TherapistDAO {

    public void save(Therapist t) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.save(t);

        tx.commit();
        s.close();
    }

    public List<Therapist> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Therapist> list = s.createQuery("FROM Therapist", Therapist.class).list();

        s.close();
        return list;
    }
}