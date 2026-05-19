package dao;

import entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PaymentDAO {

    public void save(Payment p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.save(p);

        tx.commit();
        s.close();
    }

    public List<Payment> getAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Payment> list = s.createQuery("FROM Payment", Payment.class).list();

        s.close();
        return list;
    }
}