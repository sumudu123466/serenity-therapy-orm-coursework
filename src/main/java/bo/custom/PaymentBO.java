package bo.custom;

import bo.superbo.SuperBO;
import dto.PaymentDTO;

import java.util.List;

public interface PaymentBO
        extends SuperBO {

    boolean savePayment(PaymentDTO dto);

    boolean updatePayment(PaymentDTO dto);

    boolean deletePayment(String id);

    PaymentDTO searchPayment(String id);

    List<PaymentDTO> getAllPayments();
}

