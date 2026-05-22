package dao.custom.impl;

import dao.custom.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class UserDAOImpl
        implements UserDAO {

    @Override
    public User findByUsername(String username) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Query<User> query =
                session.createQuery(

                        "FROM User WHERE username=:u",

                        User.class
                );

        query.setParameter("u",username);

        User user =
                query.uniqueResult();

        session.close();

        return user;
    }

    @Override
    public boolean save(User user) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        session.persist(user);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(User user) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        session.merge(user);

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

        User user =
                session.get(User.class,id);

        if(user != null){
            session.remove(user);
        }

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public User search(String id) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        User user =
                session.get(User.class,id);

        session.close();

        return user;
    }

    @Override
    public List<User> getAll() {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        List<User> list =
                session.createQuery(
                        "FROM User",
                        User.class
                ).list();

        session.close();

        return list;
    }
}