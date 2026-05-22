package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML
    private BorderPane rootPane;


    // 👉 PATIENT PAGE
    @FXML
    public void openPatients() throws Exception {

        var resource = getClass().getResource("/view/patient-form.fxml");
        if(resource == null) {
            throw new RuntimeException("patient-form.fxml not found!");
        }

        Pane pane = FXMLLoader.load(resource);

        rootPane.setCenter(pane);
    }

    // 👉 THERAPIST PAGE
    @FXML
    public void openTherapists() throws Exception {

        var resource = getClass().getResource("/view/therapist-form.fxml");
        if(resource == null) {
            throw new RuntimeException("therapist-form.fxml not found!");
        }

        Pane pane = FXMLLoader.load(resource);

        rootPane.setCenter(pane);
    }

    // 👉 PROGRAM PAGE
    @FXML
    public void openPrograms() throws Exception {

        var resource = getClass().getResource("/view/program-form.fxml");
        if(resource == null) {
            throw new RuntimeException("program-form.fxml not found!");
        }

        Pane pane = FXMLLoader.load(resource);

        rootPane.setCenter(pane);
    }

    // 👉 PAYMENT PAGE
    @FXML
    public void openPayments() throws Exception {

        var resource = getClass().getResource("/view/payment-form.fxml");
        if(resource == null) {
            throw new RuntimeException("payment-form.fxml not found!");
        }

        Pane pane = FXMLLoader.load(resource);

        rootPane.setCenter(pane);
    }

    // 👉 SESSION PAGE
    @FXML
    public void openSessions() throws Exception {

        var resource = getClass().getResource("/view/schedule-form.fxml");
        if(resource == null) {
            throw new RuntimeException("schedule-form.fxml not found!");
        }

        Pane pane = FXMLLoader.load(resource);

        rootPane.setCenter(pane);
    }
}