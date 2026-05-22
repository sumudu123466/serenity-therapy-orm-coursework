package dto;

public class TherapySessionDTO {

    private String sessionId;
    private java.sql.Date sessionDate;
    private java.sql.Time sessionTime;
    private String patientId;
    private String therapistId;
    private String programId;

    public TherapySessionDTO() {
    }

    public TherapySessionDTO(String sessionId,
                            java.sql.Date sessionDate,
                            java.sql.Time sessionTime,
                            String patientId,
                            String therapistId,
                            String programId) {

        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.sessionTime = sessionTime;
        this.patientId = patientId;
        this.therapistId = therapistId;
        this.programId = programId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public java.sql.Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(java.sql.Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public java.sql.Time getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(java.sql.Time sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTherapistId() {
        return therapistId;
    }

    public void setTherapistId(String therapistId) {
        this.therapistId = therapistId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }
}

