package dto;

import java.util.List;

public class PatientProgramDTO {

    private String patientId;
    private String patientName;
    private List<String> programNames;

    public PatientProgramDTO() {
    }

    public PatientProgramDTO(String patientId, String patientName, List<String> programNames) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.programNames = programNames;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<String> getProgramNames() {
        return programNames;
    }

    public void setProgramNames(List<String> programNames) {
        this.programNames = programNames;
    }
}

