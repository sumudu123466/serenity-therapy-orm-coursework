package dao;

import entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class TherapySessionDAO {

    public void save(TherapySession ts) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.save(ts);

        tx.commit();
        s.close();
    }

    public List<TherapySession> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<TherapySession> list =
                s.createQuery("FROM TherapySession", TherapySession.class).list();

        s.close();
        return list;
    }
}