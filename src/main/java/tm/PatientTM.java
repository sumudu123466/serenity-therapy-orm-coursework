package tm;

public class PatientTM {

    private String patientId;
    private String name;
    private String email;
    private String phone;
    private String program;
    private String payment;

    public PatientTM() {
    }

    public PatientTM(String patientId,
                     String name,
                     String email,
                     String phone,
                     String program,
                     String payment) {

        this.patientId = patientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.program = program;
        this.payment = payment;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getProgram() {
        return program;
    }

    public String getPayment() {
        return payment;
    }
}