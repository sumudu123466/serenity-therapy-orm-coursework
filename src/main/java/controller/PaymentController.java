package controller;

import bo.BOFactory;
import bo.custom.PaymentBO;
import dto.PaymentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.PaymentTM;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtPatientId;

    @FXML
    private TextField txtAmount;

    @FXML
    private DatePicker dpPaymentDate;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colPatientId;

    @FXML
    private TableColumn<PaymentTM, Double> colAmount;

    @FXML
    private TableColumn<PaymentTM, String> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colMethod;

    private final PaymentBO paymentBO =
            (PaymentBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.PAYMENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));

        cmbPaymentMethod.getItems().addAll(
                "CASH",
                "CARD",
                "BANK_TRANSFER",
                "CHEQUE"
        );

        tblPayment.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, selected) -> {
            if (selected != null) {
                txtPaymentId.setText(selected.getPaymentId());
                txtPatientId.setText(selected.getPatientId());
                txtAmount.setText(String.valueOf(selected.getAmount()));
                try {
                    dpPaymentDate.setValue(LocalDate.parse(selected.getDate()));
                } catch (Exception e) {
                    System.err.println("Error parsing payment date: " + e.getMessage());
                }
                cmbPaymentMethod.setValue(selected.getMethod());
            }
        });

        try {
            loadAllPayments();
        } catch (Exception e) {
            System.err.println("Error loading payments: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void savePayment(ActionEvent event) {
        try {
            PaymentDTO dto = buildDTO();
            boolean isSaved = paymentBO.savePayment(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Saved").show();
                loadAllPayments();
                clearForm(event);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void deletePayment(ActionEvent event) {
        try {
            boolean isDeleted = paymentBO.deletePayment(txtPaymentId.getText());

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Deleted").show();
                loadAllPayments();
                clearForm(event);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void clearForm(ActionEvent event) {
        txtPaymentId.clear();
        txtPatientId.clear();
        txtAmount.clear();
        dpPaymentDate.setValue(null);
        cmbPaymentMethod.setValue(null);
        tblPayment.getSelectionModel().clearSelection();
    }

    private PaymentDTO buildDTO() {
        double amount;

        try {
            amount = Double.parseDouble(txtAmount.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be a valid number");
        }

        if (dpPaymentDate.getValue() == null) {
            throw new IllegalArgumentException("Payment date is required");
        }

        if (cmbPaymentMethod.getValue() == null) {
            throw new IllegalArgumentException("Payment method is required");
        }

        return new PaymentDTO(
                txtPaymentId.getText(),
                amount,
                Date.valueOf(dpPaymentDate.getValue()),
                cmbPaymentMethod.getValue(),
                txtPatientId.getText()
        );
    }

    private void loadAllPayments() {
        ObservableList<PaymentTM> obList = FXCollections.observableArrayList();
        List<PaymentDTO> dtoList = paymentBO.getAllPayments();

        for (PaymentDTO dto : dtoList) {
            obList.add(new PaymentTM(
                    dto.getPaymentId(),
                    dto.getPatientId(),
                    dto.getAmount(),
                    dto.getPaymentDate().toString(),
                    dto.getPaymentMethod()
            ));
        }

        tblPayment.setItems(obList);
    }
}

