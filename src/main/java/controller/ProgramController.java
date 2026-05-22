package controller;

import bo.BOFactory;
import bo.custom.ProgramBO;
import dto.TherapyProgramDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.ProgramTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProgramController implements Initializable {

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableView<ProgramTM> tblProgram;

    @FXML
    private TableColumn<ProgramTM, String> colProgramId;

    @FXML
    private TableColumn<ProgramTM, String> colProgramName;

    @FXML
    private TableColumn<ProgramTM, String> colDuration;

    @FXML
    private TableColumn<ProgramTM, Double> colFee;

    private final ProgramBO programBO =
            (ProgramBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        tblProgram.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, selected) -> {
            if (selected != null) {
                txtProgramId.setText(selected.getProgramId());
                txtProgramName.setText(selected.getProgramName());
                txtDuration.setText(selected.getDuration());
                txtFee.setText(String.valueOf(selected.getFee()));

                try {
                    TherapyProgramDTO fullProgram = programBO.searchProgram(selected.getProgramId());
                    txtDescription.setText(fullProgram == null ? "" : fullProgram.getDescription());
                } catch (Exception e) {
                    System.err.println("Error loading program description: " + e.getMessage());
                }
            }
        });

        try {
            loadAllPrograms();
        } catch (Exception e) {
            System.err.println("Error loading programs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void saveProgram(ActionEvent event) {
        try {
            TherapyProgramDTO dto = buildDTO();
            boolean isSaved = programBO.saveProgram(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Program Saved").show();
                loadAllPrograms();
                clearForm(event);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void updateProgram(ActionEvent event) {
        try {
            TherapyProgramDTO dto = buildDTO();
            boolean isUpdated = programBO.updateProgram(dto);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Program Updated").show();
                loadAllPrograms();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void deleteProgram(ActionEvent event) {
        try {
            boolean isDeleted = programBO.deleteProgram(txtProgramId.getText());

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Program Deleted").show();
                loadAllPrograms();
                clearForm(event);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void clearForm(ActionEvent event) {
        txtProgramId.clear();
        txtProgramName.clear();
        txtDuration.clear();
        txtFee.clear();
        txtDescription.clear();
        tblProgram.getSelectionModel().clearSelection();
    }

    private TherapyProgramDTO buildDTO() {
        double fee;

        try {
            fee = Double.parseDouble(txtFee.getText().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Fee must be a valid number");
        }

        return new TherapyProgramDTO(
                txtProgramId.getText(),
                txtProgramName.getText(),
                txtDuration.getText(),
                fee,
                txtDescription.getText()
        );
    }

    private void loadAllPrograms() {
        ObservableList<ProgramTM> obList = FXCollections.observableArrayList();
        List<TherapyProgramDTO> dtoList = programBO.getAllPrograms();

        for (TherapyProgramDTO dto : dtoList) {
            obList.add(new ProgramTM(
                    dto.getProgramId(),
                    dto.getProgramName(),
                    dto.getDuration(),
                    dto.getFee()
            ));
        }

        tblProgram.setItems(obList);
    }
}

