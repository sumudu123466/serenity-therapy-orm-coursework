package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    UserBO userBO =
            (UserBO) BOFactory
                    .getInstance()
                    .getBO(BOFactory.BOTypes.USER);

    @FXML
    void login(@SuppressWarnings("unused") ActionEvent event) {

        try {

            String username =
                    txtUsername.getText();

            String password =
                    txtPassword.getText();

            boolean isLoginSuccessful =
                    userBO.login(
                            username,
                            password
                    );

            if(isLoginSuccessful){

                var dashboardResource = getClass()
                        .getResource(
                                "/view/dashboard-form.fxml"
                        );

                if(dashboardResource == null) {
                    throw new RuntimeException("dashboard-form.fxml not found!");
                }

                Parent root =
                        FXMLLoader.load(dashboardResource);

                Stage stage =
                        (Stage) txtUsername
                                .getScene()
                                .getWindow();

                Scene scene =
                        new Scene(root);

                var styleResource = getClass()
                                .getResource(
                                        "/css/style.css"
                                );

                if(styleResource == null) {
                    throw new RuntimeException("style.css not found!");
                }

                scene.getStylesheets().add(
                        styleResource.toExternalForm()
                );

                stage.setScene(scene);

                stage.centerOnScreen();

                stage.show();

            }else{

                new Alert(

                        Alert.AlertType.ERROR,
                        "Invalid Username or Password"

                ).show();
            }

        } catch (Exception e) {

            new Alert(

                    Alert.AlertType.ERROR,
                    e.getMessage()

            ).show();
        }
    }
}