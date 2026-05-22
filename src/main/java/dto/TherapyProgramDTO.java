package dto;

public class TherapyProgramDTO {

    private String programId;
    private String programName;
    private String duration;
    private double fee;
    private String description;

    public TherapyProgramDTO() {
    }

    public TherapyProgramDTO(String programId,
                            String programName,
                            String duration,
                            double fee,
                            String description) {

        this.programId = programId;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
        this.description = description;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

