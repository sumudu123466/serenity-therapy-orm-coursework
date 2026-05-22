package dao.custom.impl;

import dao.custom.PaymentDAO;
import entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PaymentDAOImpl
        implements PaymentDAO {

    @Override
    public boolean save(Payment payment) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                session.beginTransaction();

        session.persist(payment);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Payment payment) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                session.beginTransaction();

        session.merge(payment);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                session.beginTransaction();

        Payment payment =
                session.get(Payment.class,id);

        if(payment != null){
            session.remove(payment);
        }

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Payment search(String id) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Payment payment =
                session.get(Payment.class,id);

        session.close();

        return payment;
    }

    @Override
    public List<Payment> getAll() {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        List<Payment> list =
                session.createQuery(
                        "FROM Payment",
                        Payment.class
                ).list();

        session.close();

        return list;
    }
}