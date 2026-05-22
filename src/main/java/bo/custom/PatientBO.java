package bo.custom;

import bo.superbo.SuperBO;
import dto.PatientDTO;
import dto.PatientProgramDTO;

import java.util.List;

public interface PatientBO
        extends SuperBO {

    boolean savePatient(PatientDTO dto);

    boolean updatePatient(PatientDTO dto);

    boolean deletePatient(String id);

    PatientDTO searchPatient(String id);

    List<PatientDTO> getAllPatients();

    List<PatientProgramDTO> getPatientsWithPrograms();

    List<PatientProgramDTO> getPatientsEnrolledInAllPrograms();
}