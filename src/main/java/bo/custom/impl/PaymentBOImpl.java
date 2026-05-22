package bo.custom.impl;
import bo.custom.PaymentBO;
import dao.DAOFactory;
import dao.custom.PaymentDAO;
import dto.PaymentDTO;
import entity.Payment;
import entity.Patient;
import exception.PaymentProcessingException;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
public class PaymentBOImpl
        implements PaymentBO {
    PaymentDAO paymentDAO =
            (PaymentDAO) DAOFactory
                    .getInstance()
                    .getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public boolean savePayment(PaymentDTO dto) {
        validatePayment(dto);

        return paymentDAO.save(
                new Payment(
                        dto.getPaymentId(),
                        dto.getAmount(),
                        dto.getPaymentDate(),
                        dto.getPaymentMethod(),
                        new Patient(dto.getPatientId(), null, null, null, null, null)
                )
        );
    }
    @Override
    public boolean updatePayment(PaymentDTO dto) {
        validatePayment(dto);

        return paymentDAO.update(
                new Payment(
                        dto.getPaymentId(),
                        dto.getAmount(),
                        dto.getPaymentDate(),
                        dto.getPaymentMethod(),
                        new Patient(dto.getPatientId(), null, null, null, null, null)
                )
        );
    }
    @Override
    public boolean deletePayment(String id) {
        ValidationUtil.requireNonBlank(id, "Payment ID");
        return paymentDAO.delete(id);
    }
    @Override
    public PaymentDTO searchPayment(String id) {
        Payment payment =
                paymentDAO.search(id);
        if(payment == null) return null;
        return new PaymentDTO(
                payment.getPaymentId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getPatient().getPatientId()
        );
    }
    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments =
                paymentDAO.getAll();
        List<PaymentDTO> dtoList =
                new ArrayList<>();
        for (Payment payment : payments){
            dtoList.add(
                    new PaymentDTO(
                            payment.getPaymentId(),
                            payment.getAmount(),
                            payment.getPaymentDate(),
                            payment.getPaymentMethod(),
                            payment.getPatient().getPatientId()
                    )
            );
        }
        return dtoList;
    }

    private void validatePayment(PaymentDTO dto) {
        if (dto == null) {
            throw new PaymentProcessingException("Payment data is required");
        }

        ValidationUtil.requireNonBlank(dto.getPaymentId(), "Payment ID");
        ValidationUtil.requireNonBlank(dto.getPatientId(), "Patient ID");
        if (dto.getPaymentDate() == null) {
            throw new PaymentProcessingException("Payment date is required");
        }
        ValidationUtil.requireNonBlank(dto.getPaymentMethod(), "Payment method");

        if (dto.getAmount() <= 0) {
            throw new PaymentProcessingException("Payment amount must be greater than zero");
        }
    }
}
