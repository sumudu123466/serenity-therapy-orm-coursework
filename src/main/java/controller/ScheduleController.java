package controller;

import bo.BOFactory;
import bo.custom.PatientBO;
import bo.custom.ProgramBO;
import bo.custom.SessionBO;
import bo.custom.TherapistBO;
import dto.PatientDTO;
import dto.TherapistDTO;
import dto.TherapyProgramDTO;
import dto.TherapySessionDTO;
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
import tm.SessionTM;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    @FXML
    private TextField txtSessionId;

    @FXML
    private ComboBox<String> cmbPatient;

    @FXML
    private ComboBox<String> cmbTherapist;

    @FXML
    private DatePicker dpSessionDate;

    @FXML
    private TextField txtSessionTime;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private TableView<SessionTM> tblSession;

    @FXML
    private TableColumn<SessionTM, String> colSessionId;

    @FXML
    private TableColumn<SessionTM, String> colPatient;

    @FXML
    private TableColumn<SessionTM, String> colTherapist;

    @FXML
    private TableColumn<SessionTM, String> colDate;

    @FXML
    private TableColumn<SessionTM, String> colTime;

    @FXML
    private TableColumn<SessionTM, String> colProgram;

    private final SessionBO sessionBO =
            (SessionBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.SESSION);

    private final PatientBO patientBO =
            (PatientBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.PATIENT);

    private final TherapistBO therapistBO =
            (TherapistBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.THERAPIST);

    private final ProgramBO programBO =
            (ProgramBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.PROGRAM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colTherapist.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programId"));

        try {
            loadPatients();
        } catch (Exception e) {
            System.err.println("Error loading patients: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            loadTherapists();
        } catch (Exception e) {
            System.err.println("Error loading therapists: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            loadPrograms();
        } catch (Exception e) {
            System.err.println("Error loading programs: " + e.getMessage());
            e.printStackTrace();
        }

        tblSession.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, selected) -> {
            if (selected != null) {
                txtSessionId.setText(selected.getSessionId());
                cmbPatient.setValue(selected.getPatientId());
                cmbTherapist.setValue(selected.getTherapistId());
                try {
                    dpSessionDate.setValue(LocalDate.parse(selected.getDate()));
                } catch (Exception e) {
                    System.err.println("Error parsing session date: " + e.getMessage());
                }
                txtSessionTime.setText(selected.getTime());
                cmbProgram.setValue(selected.getProgramId());
            }
        });

        try {
            loadAllSessions();
        } catch (Exception e) {
            System.err.println("Error loading sessions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void bookSession(ActionEvent event) {
        try {
            TherapySessionDTO dto = buildDTO();
            boolean isSaved = sessionBO.saveSession(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Session Booked").show();
                loadAllSessions();
                clearForm(event);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void updateSession(ActionEvent event) {
        try {
            TherapySessionDTO dto = buildDTO();
            boolean isUpdated = sessionBO.updateSession(dto);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Session Updated").show();
                loadAllSessions();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void deleteSession(ActionEvent event) {
        try {
            boolean isDeleted = sessionBO.deleteSession(txtSessionId.getText());

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Session Cancelled").show();
                loadAllSessions();
                clearForm(event);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void clearForm(ActionEvent event) {
        txtSessionId.clear();
        cmbPatient.setValue(null);
        cmbTherapist.setValue(null);
        dpSessionDate.setValue(null);
        txtSessionTime.clear();
        cmbProgram.setValue(null);
        tblSession.getSelectionModel().clearSelection();
    }

    private TherapySessionDTO buildDTO() {
        if (dpSessionDate.getValue() == null) {
            throw new IllegalArgumentException("Session date is required");
        }
        if (txtSessionTime.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Session time is required");
        }
        if (cmbPatient.getValue() == null) {
            throw new IllegalArgumentException("Patient is required");
        }
        if (cmbTherapist.getValue() == null) {
            throw new IllegalArgumentException("Therapist is required");
        }
        if (cmbProgram.getValue() == null) {
            throw new IllegalArgumentException("Program is required");
        }

        String rawTime = txtSessionTime.getText().trim();

        try {
            LocalTime localTime = LocalTime.parse(rawTime);
            return new TherapySessionDTO(
                    txtSessionId.getText(),
                    Date.valueOf(dpSessionDate.getValue()),
                    Time.valueOf(localTime),
                    cmbPatient.getValue(),
                    cmbTherapist.getValue(),
                    cmbProgram.getValue()
            );
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Session time must be HH:mm or HH:mm:ss");
        }
    }

    private void loadAllSessions() {
        ObservableList<SessionTM> obList = FXCollections.observableArrayList();
        List<TherapySessionDTO> dtoList = sessionBO.getAllSessions();

        for (TherapySessionDTO dto : dtoList) {
            obList.add(new SessionTM(
                    dto.getSessionId(),
                    dto.getPatientId(),
                    dto.getTherapistId(),
                    dto.getSessionDate().toString(),
                    dto.getSessionTime().toString(),
                    dto.getProgramId()
            ));
        }

        tblSession.setItems(obList);
    }

    private void loadPatients() {
        List<PatientDTO> patients = patientBO.getAllPatients();
        ObservableList<String> patientIds = FXCollections.observableArrayList();
        for (PatientDTO patient : patients) {
            patientIds.add(patient.getPatientId());
        }
        cmbPatient.setItems(patientIds);
    }

    private void loadTherapists() {
        List<TherapistDTO> therapists = therapistBO.getAllTherapists();
        ObservableList<String> therapistIds = FXCollections.observableArrayList();
        for (TherapistDTO therapist : therapists) {
            therapistIds.add(therapist.getTherapistId());
        }
        cmbTherapist.setItems(therapistIds);
    }

    private void loadPrograms() {
        List<TherapyProgramDTO> programs = programBO.getAllPrograms();
        ObservableList<String> programIds = FXCollections.observableArrayList();
        for (TherapyProgramDTO program : programs) {
            programIds.add(program.getProgramId());
        }
        cmbProgram.setItems(programIds);
    }
}

