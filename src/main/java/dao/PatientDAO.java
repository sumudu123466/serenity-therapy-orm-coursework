package dao;

import entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class PatientDAO {

    // SAVE
    public void savePatient(Patient p) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(p);

        tx.commit();
        session.close();
    }

    // UPDATE
    public void updatePatient(Patient p) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.update(p);

        tx.commit();
        session.close();
    }

    // DELETE
    public void deletePatient(String id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Patient p = session.get(Patient.class, id);

        if (p != null) {
            session.delete(p);
        }

        tx.commit();
        session.close();
    }

    // GET ALL
    public List<Patient> getAllPatients() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Patient> list = session.createQuery("FROM Patient", Patient.class).list();

        session.close();

        return list;
    }

    // GET BY ID
    public Patient getById(String id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Patient p = session.get(Patient.class, id);

        session.close();

        return p;
    }
}