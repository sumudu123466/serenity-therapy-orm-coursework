package dao.custom;

import dao.crud.CrudDAO;
import entity.TherapySession;

public interface SessionDAO
        extends CrudDAO<TherapySession,String> {

    boolean hasTherapistConflict(String therapistId,
                                 java.sql.Date sessionDate,
                                 java.sql.Time sessionTime,
                                 String ignoreSessionId);
}