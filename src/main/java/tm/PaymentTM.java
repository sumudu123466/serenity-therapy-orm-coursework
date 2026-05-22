package tm;

public class PaymentTM {

    private String paymentId;
    private String patientId;
    private double amount;
    private String date;
    private String method;

    public PaymentTM() {
    }

    public PaymentTM(String paymentId,
                     String patientId,
                     double amount,
                     String date,
                     String method) {

        this.paymentId = paymentId;
        this.patientId = patientId;
        this.amount = amount;
        this.date = date;
        this.method = method;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}