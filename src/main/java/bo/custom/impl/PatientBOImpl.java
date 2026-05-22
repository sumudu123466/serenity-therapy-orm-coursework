package bo.custom.impl;

import bo.custom.PatientBO;
import dao.DAOFactory;
import dao.custom.PatientDAO;
import dto.PatientDTO;
import dto.PatientProgramDTO;
import entity.Patient;
import entity.TherapyProgram;
import exception.RegistrationException;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl
        implements PatientBO {

    PatientDAO patientDAO =
            (PatientDAO) DAOFactory
                    .getInstance()
                    .getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean savePatient(PatientDTO dto) {

        validatePatient(dto);

        if (patientDAO.search(dto.getPatientId()) != null) {
            throw new RegistrationException("Patient already exists: " + dto.getPatientId());
        }

        return patientDAO.save(

                new Patient(
                        dto.getPatientId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getGender(),
                        dto.getMedicalHistory()
                )
        );
    }

    @Override
    public boolean updatePatient(PatientDTO dto) {

        validatePatient(dto);

        return patientDAO.update(

                new Patient(
                        dto.getPatientId(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getPhone(),
                        dto.getGender(),
                        dto.getMedicalHistory()
                )
        );
    }

    @Override
    public boolean deletePatient(String id) {

        ValidationUtil.requireNonBlank(id, "Patient ID");
        return patientDAO.delete(id.trim());
    }

    @Override
    public PatientDTO searchPatient(String id) {

        Patient patient =
                patientDAO.search(id);

        return new PatientDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getGender(),
                patient.getMedicalHistory()
        );
    }

    @Override
    public List<PatientDTO> getAllPatients() {

        List<Patient> patients =
                patientDAO.getAll();

        List<PatientDTO> dtoList =
                new ArrayList<>();

        for (Patient patient : patients){

            dtoList.add(

                    new PatientDTO(
                            patient.getPatientId(),
                            patient.getName(),
                            patient.getEmail(),
                            patient.getPhone(),
                            patient.getGender(),
                            patient.getMedicalHistory()
                    )
            );
        }

        return dtoList;
    }

    @Override
    public List<PatientProgramDTO> getPatientsWithPrograms() {

        return mapPatientProgramDetails(patientDAO.getPatientsWithPrograms());
    }

    @Override
    public List<PatientProgramDTO> getPatientsEnrolledInAllPrograms() {

        return mapPatientProgramDetails(patientDAO.getPatientsEnrolledInAllPrograms());
    }

    private void validatePatient(PatientDTO dto) {
        if (dto == null) {
            throw new RegistrationException("Patient data is required");
        }

        ValidationUtil.requireNonBlank(dto.getPatientId(), "Patient ID");
        ValidationUtil.requireNonBlank(dto.getName(), "Patient name");
        ValidationUtil.validateEmail(dto.getEmail(), "Patient email");
        ValidationUtil.validateSriLankanPhone(dto.getPhone(), "Patient phone");
    }

    private List<PatientProgramDTO> mapPatientProgramDetails(List<Patient> patients) {
        List<PatientProgramDTO> dtoList = new ArrayList<>();

        for (Patient patient : patients) {
            List<String> programNames = new ArrayList<>();
            if (patient.getPrograms() != null) {
                for (TherapyProgram program : patient.getPrograms()) {
                    programNames.add(program.getProgramName());
                }
            }

            dtoList.add(new PatientProgramDTO(
                    patient.getPatientId(),
                    patient.getName(),
                    programNames
            ));
        }

        return dtoList;
    }
}