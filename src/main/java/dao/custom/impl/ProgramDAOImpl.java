package dao.custom.impl;

import dao.custom.ProgramDAO;
import entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ProgramDAOImpl
        implements ProgramDAO {

    @Override
    public boolean save(TherapyProgram program) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                session.beginTransaction();

        session.persist(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TherapyProgram program) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                session.beginTransaction();

        session.merge(program);

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

        TherapyProgram program =
                session.get(TherapyProgram.class,id);

        if(program != null){
            session.remove(program);
        }

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public TherapyProgram search(String id) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        TherapyProgram program =
                session.get(TherapyProgram.class,id);

        session.close();

        return program;
    }

    @Override
    public List<TherapyProgram> getAll() {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        List<TherapyProgram> list =
                session.createQuery(
                        "FROM TherapyProgram",
                        TherapyProgram.class
                ).list();

        session.close();

        return list;
    }
}