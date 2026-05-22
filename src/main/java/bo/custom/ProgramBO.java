package bo.custom;

import bo.superbo.SuperBO;
import dto.TherapyProgramDTO;

import java.util.List;

public interface ProgramBO
        extends SuperBO {

    boolean saveProgram(TherapyProgramDTO dto);

    boolean updateProgram(TherapyProgramDTO dto);

    boolean deleteProgram(String id);

    TherapyProgramDTO searchProgram(String id);

    List<TherapyProgramDTO> getAllPrograms();
}

