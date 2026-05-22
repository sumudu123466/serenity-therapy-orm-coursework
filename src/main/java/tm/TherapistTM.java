package tm;

public class TherapistTM {

    private String therapistId;
    private String name;
    private String specialization;
    private String program;
    private String availability;

    public TherapistTM() {
    }

    public TherapistTM(String therapistId,
                       String name,
                       String specialization,
                       String program,
                       String availability) {

        this.therapistId = therapistId;
        this.name = name;
        this.specialization = specialization;
        this.program = program;
        this.availability = availability;
    }

    public String getTherapistId() {
        return therapistId;
    }

    public void setTherapistId(String therapistId) {
        this.therapistId = therapistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}