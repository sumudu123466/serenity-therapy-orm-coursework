package dao.custom.impl;

import dao.custom.PatientDAO;
import entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PatientDAOImpl
        implements PatientDAO {

    @Override
    public boolean save(Patient patient) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        session.persist(patient);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Patient patient) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Transaction transaction =
                session.beginTransaction();

        session.merge(patient);

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

        Patient patient =
                session.get(Patient.class,id);

        if(patient != null){
            session.remove(patient);
        }

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public Patient search(String id) {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        Patient patient =
                session.get(Patient.class,id);

        session.close();

        return patient;
    }

    @Override
    public List<Patient> getAll() {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        List<Patient> list =
                session.createQuery(
                        "FROM Patient",
                        Patient.class
                ).list();

        session.close();

        return list;
    }

    @Override
    public List<Patient> getPatientsWithPrograms() {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        List<Patient> list =
                session.createQuery(
                        "SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.programs",
                        Patient.class
                ).list();

        session.close();

        return list;
    }

    @Override
    public List<Patient> getPatientsEnrolledInAllPrograms() {

        Session session =
                HibernateUtil
                        .getSessionFactory()
                        .openSession();

        List<Patient> list =
                session.createQuery(
                        "SELECT p FROM Patient p JOIN p.programs pr GROUP BY p.patientId HAVING COUNT(DISTINCT pr.programId) = (SELECT COUNT(tp.programId) FROM TherapyProgram tp)",
                        Patient.class
                ).list();

        session.close();

        return list;
    }
}