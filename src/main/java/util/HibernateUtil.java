package util;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){

        if(sessionFactory == null){

            try {

            sessionFactory =
                    new Configuration()

                            .configure()

                            .addAnnotatedClass(Patient.class)

                            .addAnnotatedClass(User.class)

                            .addAnnotatedClass(Therapist.class)

                            .addAnnotatedClass(TherapyProgram.class)

                            .addAnnotatedClass(TherapySession.class)

                            .addAnnotatedClass(Payment.class)

                            .buildSessionFactory();
                    } catch (Exception e) {
                        System.err.println("FATAL: Hibernate SessionFactory creation failed!");
                        e.printStackTrace();
                        throw new RuntimeException("Hibernate initialization error: " + e.getMessage(), e);
                    }

        }

        return sessionFactory;
    }
}