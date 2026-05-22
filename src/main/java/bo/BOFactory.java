package bo;

import bo.custom.impl.*;

public class BOFactory {

    private static BOFactory factory;

    private BOFactory(){}

    public static BOFactory getInstance(){

        if(factory == null){
            factory = new BOFactory();
        }

        return factory;
    }

    public enum BOTypes{

        PATIENT,
        USER,
        THERAPIST,
        PAYMENT,
        PROGRAM,
        SESSION
    }

    public Object getBO(BOTypes type){

        switch(type){

            case PATIENT:
                return new PatientBOImpl();

            case USER:
                return new UserBOImpl();

            case THERAPIST:
                return new TherapistBOImpl();

            case PAYMENT:
                return new PaymentBOImpl();

            case PROGRAM:
                return new ProgramBOImpl();

            case SESSION:
                return new SessionBOImpl();

            default:
                return null;
        }
    }
}