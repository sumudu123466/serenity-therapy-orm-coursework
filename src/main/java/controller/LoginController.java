package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void login(ActionEvent event) throws Exception {

        Parent root =
                FXMLLoader.load(
                        getClass()
                                .getResource(
                                        "/view/dashboard-form.fxml"
                                )
                );

        Stage stage =
                (Stage) txtUsername
                        .getScene()
                        .getWindow();

        Scene scene = new Scene(root);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/css/style.css")
                        .toExternalForm()
        );

        stage.setScene(scene);

        stage.centerOnScreen();
    }
}