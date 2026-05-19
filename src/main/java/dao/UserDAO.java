package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UserDAO {

    // SAVE USER
    public void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();
        session.close();
    }

    // LOGIN CHECK
    public boolean login(String username, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = session.createQuery(
                        "FROM User WHERE username = :u AND password = :p",
                        User.class)
                .setParameter("u", username)
                .setParameter("p", password)
                .uniqueResult();

        session.close();

        return user != null;
    }

    // UPDATE
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.update(user);

        tx.commit();
        session.close();
    }

    // DELETE
    public void deleteUser(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }

        tx.commit();
        session.close();
    }
}