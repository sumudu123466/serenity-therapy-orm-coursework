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

        Pane pane = FXMLLoader.load(
                getClass().getResource("/view/patient-form.fxml")
        );

        rootPane.setCenter(pane);
    }

    // 👉 THERAPIST PAGE
    @FXML
    public void openTherapists() throws Exception {

        Pane pane = FXMLLoader.load(
                getClass().getResource("/view/therapist-form.fxml")
        );

        rootPane.setCenter(pane);
    }

    // 👉 PROGRAM PAGE
    @FXML
    public void openPrograms() throws Exception {

        Pane pane = FXMLLoader.load(
                getClass().getResource("/view/program-form.fxml")
        );

        rootPane.setCenter(pane);
    }

    // 👉 PAYMENT PAGE
    @FXML
    public void openPayments() throws Exception {

        Pane pane = FXMLLoader.load(
                getClass().getResource("/view/payment-form.fxml")
        );

        rootPane.setCenter(pane);
    }

    // 👉 SESSION PAGE
    @FXML
    public void openSessions() throws Exception {

        Pane pane = FXMLLoader.load(
                getClass().getResource("/view/schedule-form.fxml")
        );

        rootPane.setCenter(pane);
    }
}