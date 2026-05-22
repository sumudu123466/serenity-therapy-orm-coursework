package dao.custom.impl;

import dao.custom.SessionDAO;
import entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class SessionDAOImpl
        implements SessionDAO {

    @Override
    public boolean save(TherapySession session) {

        Session hibernateSession =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                hibernateSession.beginTransaction();

        hibernateSession.persist(session);

        transaction.commit();
        hibernateSession.close();
        return true;
    }

    @Override
    public boolean update(TherapySession session) {

        Session hibernateSession =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                hibernateSession.beginTransaction();

        hibernateSession.merge(session);

        transaction.commit();
        hibernateSession.close();
        return true;
    }

    @Override
    public boolean delete(String id) {

        Session hibernateSession =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction =
                hibernateSession.beginTransaction();

        TherapySession session =
                hibernateSession.get(TherapySession.class,id);

        if(session != null){
            hibernateSession.remove(session);
        }

        transaction.commit();
        hibernateSession.close();
        return true;
    }

    @Override
    public TherapySession search(String id) {

        Session hibernateSession =
                HibernateUtil.getSessionFactory().openSession();

        TherapySession session =
                hibernateSession.get(TherapySession.class,id);

        hibernateSession.close();

        return session;
    }

    @Override
    public List<TherapySession> getAll() {

        Session hibernateSession =
                HibernateUtil.getSessionFactory().openSession();

        List<TherapySession> list =
                hibernateSession.createQuery(
                        "FROM TherapySession",
                        TherapySession.class
                ).list();

        hibernateSession.close();

        return list;
    }

    @Override
    public boolean hasTherapistConflict(String therapistId,
                                        java.sql.Date sessionDate,
                                        java.sql.Time sessionTime,
                                        String ignoreSessionId) {

        Session hibernateSession =
                HibernateUtil.getSessionFactory().openSession();

        String hql = "SELECT COUNT(ts.sessionId) FROM TherapySession ts " +
                "WHERE ts.therapist.therapistId = :therapistId " +
                "AND ts.sessionDate = :sessionDate " +
                "AND ts.sessionTime = :sessionTime";

        if (ignoreSessionId != null && !ignoreSessionId.trim().isEmpty()) {
            hql += " AND ts.sessionId <> :ignoreSessionId";
        }

        Query<Long> query = hibernateSession.createQuery(hql, Long.class);
        query.setParameter("therapistId", therapistId);
        query.setParameter("sessionDate", sessionDate);
        query.setParameter("sessionTime", sessionTime);

        if (ignoreSessionId != null && !ignoreSessionId.trim().isEmpty()) {
            query.setParameter("ignoreSessionId", ignoreSessionId);
        }

        Long count = query.uniqueResult();
        hibernateSession.close();

        return count != null && count > 0;
    }
}