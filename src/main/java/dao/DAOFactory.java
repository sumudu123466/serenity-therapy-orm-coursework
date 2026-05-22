package dao;

import dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory factory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){

        if(factory == null){
            factory = new DAOFactory();
        }

        return factory;
    }

    public enum DAOTypes{

        PATIENT,
        USER,
        THERAPIST,
        PAYMENT,
        PROGRAM,
        SESSION
    }

    public Object getDAO(DAOTypes type){

        switch(type){

            case PATIENT:
                return new PatientDAOImpl();

            case USER:
                return new UserDAOImpl();

            case THERAPIST:
                return new TherapistDAOImpl();

            case PAYMENT:
                return new PaymentDAOImpl();

            case PROGRAM:
                return new ProgramDAOImpl();

            case SESSION:
                return new SessionDAOImpl();

            default:
                return null;
        }
    }
}