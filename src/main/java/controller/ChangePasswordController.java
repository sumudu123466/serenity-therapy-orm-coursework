package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {

    @FXML
    private PasswordField txtCurrent;

    @FXML
    private PasswordField txtNew;

    @FXML
    private PasswordField txtConfirm;

    @FXML
    public void updatePassword() {

        if (txtNew.getText().isEmpty() || txtConfirm.getText().isEmpty()) {
            System.out.println("Fields empty");
            return;
        }

        if (!txtNew.getText().equals(txtConfirm.getText())) {
            System.out.println("Passwords not match");
            return;
        }

        System.out.println("Password updated");
    }
}