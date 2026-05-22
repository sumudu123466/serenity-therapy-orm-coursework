package tm;

public class SessionTM {

    private String sessionId;
    private String patientId;
    private String therapistId;
    private String date;
    private String time;
    private String programId;

    public SessionTM(String sessionId,
                     String patientId,
                     String therapistId,
                     String date,
                     String time,
                     String programId) {
        this.sessionId = sessionId;
        this.patientId = patientId;
        this.therapistId = therapistId;
        this.date = date;
        this.time = time;
        this.programId = programId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }
}

