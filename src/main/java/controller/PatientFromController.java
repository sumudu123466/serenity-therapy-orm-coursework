package controller;

import dao.PatientDAO;
import entity.Patient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class PatientFromController {

    @FXML private TextField txtPatientId;
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private ComboBox cmbProgram;
    @FXML private DatePicker dateRegister;
    @FXML private TextField txtPayment;

    @FXML private TableView<Patient> table;

    PatientDAO dao = new PatientDAO();

    // ================= SAVE =================
    @FXML
    public void savePatient() {

        Patient p = new Patient();

        p.setPatientId(txtPatientId.getText());
        p.setName(txtName.getText());
        p.setEmail(txtEmail.getText());
        p.setPhone(txtPhone.getText());

        dao.savePatient(p);

        System.out.println("Saved Successfully");

        loadTable();
        clearForm();
    }

    // ================= UPDATE =================
    @FXML
    public void updatePatient() {

        Patient p = new Patient();

        p.setPatientId(txtPatientId.getText());
        p.setName(txtName.getText());
        p.setEmail(txtEmail.getText());
        p.setPhone(txtPhone.getText());

        dao.updatePatient(p);

        System.out.println("Updated Successfully");

        loadTable();
        clearForm();
    }

    // ================= DELETE =================
    @FXML
    public void deletePatient() {

        String id = txtPatientId.getText();

        dao.deletePatient(id);

        System.out.println("Deleted Successfully");

        loadTable();
        clearForm();
    }

    // ================= CLEAR =================
    @FXML
    public void clearForm() {

        txtPatientId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtPayment.clear();
    }

    // ================= LOAD TABLE =================
    @FXML
    public void loadTable() {

        List<Patient> list = dao.getAllPatients();

        table.setItems(FXCollections.observableArrayList(list));
    }

    // ================= SEARCH =================
    @FXML
    public void searchPatient() {

        String id = txtPatientId.getText();

        Patient p = dao.getById(id);

        if (p != null) {
            txtName.setText(p.getName());
            txtEmail.setText(p.getEmail());
            txtPhone.setText(p.getPhone());
        } else {
            System.out.println("Not Found");
        }
    }
}