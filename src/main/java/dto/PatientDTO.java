package dto;

public class PatientDTO {

    private String patientId;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String medicalHistory;

    public PatientDTO() {
    }

    public PatientDTO(String patientId,
                      String name,
                      String email,
                      String phone,
                      String gender,
                      String medicalHistory) {

        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.medicalHistory = medicalHistory;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}