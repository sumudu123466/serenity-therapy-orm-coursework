package controller;

import bo.BOFactory;
import bo.custom.PatientBO;
import dto.PatientDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.PatientTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private TextField txtPatientId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private DatePicker dateRegister;

    @FXML
    private TextField txtPayment;

    @FXML
    private TableView<PatientTM> table;

    @FXML
    private TableColumn<PatientTM, String> colId;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colPhone;

    @FXML
    private TableColumn<PatientTM, String> colProgram;

    @FXML
    private TableColumn<PatientTM, String> colPayment;

    PatientBO patientBO =
            (PatientBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.PATIENT);

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle) {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("patientId"));

        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        colPhone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        colProgram.setCellValueFactory(
                new PropertyValueFactory<>("program"));

        colPayment.setCellValueFactory(
                new PropertyValueFactory<>("payment"));

        cmbProgram.getItems().addAll(
                "CBT",
                "Stress Therapy",
                "Anxiety Therapy"
        );

        loadAllPatients();
    }

    @FXML
    void savePatient(@SuppressWarnings("unused") ActionEvent event) {

        try {

            PatientDTO dto =
                    new PatientDTO(

                            txtPatientId.getText(),
                            txtName.getText(),
                            txtEmail.getText(),
                            txtPhone.getText(),
                            "N/A",
                            "N/A"
                    );

            boolean isSaved =
                    patientBO.savePatient(dto);

            if(isSaved){

                new Alert(
                        Alert.AlertType.INFORMATION,
                        "Patient Saved"
                ).show();

                loadAllPatients();

                clearForm(null);
            }

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    @FXML
    void updatePatient(@SuppressWarnings("unused") ActionEvent event) {

        try {

            PatientDTO dto =
                    new PatientDTO(

                            txtPatientId.getText(),
                            txtName.getText(),
                            txtEmail.getText(),
                            txtPhone.getText(),
                            "N/A",
                            "N/A"
                    );

            boolean isUpdated =
                    patientBO.updatePatient(dto);

            if(isUpdated){

                new Alert(
                        Alert.AlertType.INFORMATION,
                        "Patient Updated"
                ).show();

                loadAllPatients();
            }

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    @FXML
    void deletePatient(@SuppressWarnings("unused") ActionEvent event) {

        try {

            boolean isDeleted =
                    patientBO.deletePatient(
                            txtPatientId.getText()
                    );

            if(isDeleted){

                new Alert(
                        Alert.AlertType.INFORMATION,
                        "Patient Deleted"
                ).show();

                loadAllPatients();

                clearForm(null);
            }

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    @FXML
    void searchPatient(@SuppressWarnings("unused") ActionEvent event) {

        try {

            PatientDTO dto =
                    patientBO.searchPatient(
                            txtPatientId.getText()
                    );

            if(dto != null){

                txtName.setText(dto.getName());
                txtEmail.setText(dto.getEmail());
                txtPhone.setText(dto.getPhone());
                cmbProgram.setValue("N/A");
                txtPayment.setText("N/A");

            }else{

                new Alert(
                        Alert.AlertType.WARNING,
                        "Patient Not Found"
                ).show();
            }

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    @FXML
    void clearForm(@SuppressWarnings("unused") ActionEvent event) {

        txtPatientId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtPayment.clear();

        cmbProgram.setValue(null);

        dateRegister.setValue(null);
    }

    private void loadAllPatients() {

        ObservableList<PatientTM> obList =
                FXCollections.observableArrayList();

        List<PatientDTO> dtoList =
                patientBO.getAllPatients();

        for (PatientDTO dto : dtoList) {

            obList.add(

                    new PatientTM(

                            dto.getPatientId(),
                            dto.getName(),
                            dto.getEmail(),
                            dto.getPhone(),
                            "N/A",
                            "N/A"
                    )
            );
        }

        table.setItems(obList);
    }
}