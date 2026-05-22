package controller;

import bo.BOFactory;
import bo.custom.TherapistBO;
import dto.TherapistDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.TherapistTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {

    @FXML
    private TextField txtTherapistId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSpecialization;

    @FXML
    private ComboBox<String> cmbAvailability;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private TableView<TherapistTM> tblTherapist;

    @FXML
    private TableColumn<TherapistTM, String> colId;

    @FXML
    private TableColumn<TherapistTM, String> colName;

    @FXML
    private TableColumn<TherapistTM, String> colSpecialization;

    @FXML
    private TableColumn<TherapistTM, String> colProgram;

    @FXML
    private TableColumn<TherapistTM, String> colAvailability;

    TherapistBO therapistBO =
            (TherapistBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.THERAPIST);

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle) {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("therapistId"));

        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        colSpecialization.setCellValueFactory(
                new PropertyValueFactory<>("specialization"));

        colProgram.setCellValueFactory(
                new PropertyValueFactory<>("program"));

        colAvailability.setCellValueFactory(
                new PropertyValueFactory<>("availability"));

        cmbAvailability.getItems().addAll(
                "Morning",
                "Evening",
                "Full Day"
        );

        cmbProgram.getItems().addAll(
                "CBT",
                "Stress Therapy",
                "Anxiety Therapy"
        );

        loadAllTherapists();
    }

    @FXML
    void saveTherapist(ActionEvent event) {

        try {

            TherapistDTO dto =
                    new TherapistDTO(

                            txtTherapistId.getText(),
                            txtName.getText(),
                            "",
                            "",
                            txtSpecialization.getText(),
                            cmbAvailability.getValue()
                    );

            therapistBO.saveTherapist(dto);

            new Alert(
                    Alert.AlertType.INFORMATION,
                    "Therapist Saved"
            ).show();

            loadAllTherapists();

            clearFields();

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    @FXML
    void updateTherapist(ActionEvent event) {

        try {

            TherapistDTO dto =
                    new TherapistDTO(

                            txtTherapistId.getText(),
                            txtName.getText(),
                            "",
                            "",
                            txtSpecialization.getText(),
                            cmbAvailability.getValue()
                    );

            therapistBO.updateTherapist(dto);

            new Alert(
                    Alert.AlertType.INFORMATION,
                    "Therapist Updated"
            ).show();

            loadAllTherapists();

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    @FXML
    void deleteTherapist(ActionEvent event) {

        try {

            therapistBO.deleteTherapist(
                    txtTherapistId.getText()
            );

            new Alert(
                    Alert.AlertType.INFORMATION,
                    "Therapist Deleted"
            ).show();

            loadAllTherapists();

            clearFields();

        } catch (Exception e) {

            new Alert(
                    Alert.AlertType.ERROR,
                    e.getMessage()
            ).show();
        }
    }

    private void loadAllTherapists() {

        ObservableList<TherapistTM> obList =
                FXCollections.observableArrayList();

        List<TherapistDTO> dtoList =
                therapistBO.getAllTherapists();

        for (TherapistDTO dto : dtoList) {

            obList.add(

                    new TherapistTM(

                            dto.getTherapistId(),
                            dto.getName(),
                            dto.getSpecialization(),
                            cmbProgram.getValue(),
                            dto.getAvailability()
                    )
            );
        }

        tblTherapist.setItems(obList);
    }

    private void clearFields() {

        txtTherapistId.clear();
        txtName.clear();
        txtSpecialization.clear();

        cmbAvailability.setValue(null);
        cmbProgram.setValue(null);
    }
}