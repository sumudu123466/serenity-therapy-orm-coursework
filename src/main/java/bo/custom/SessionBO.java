package bo.custom;

import bo.superbo.SuperBO;
import dto.TherapySessionDTO;

import java.util.List;

public interface SessionBO
        extends SuperBO {

    boolean saveSession(TherapySessionDTO dto);

    boolean updateSession(TherapySessionDTO dto);

    boolean deleteSession(String id);

    TherapySessionDTO searchSession(String id);

    List<TherapySessionDTO> getAllSessions();
}

