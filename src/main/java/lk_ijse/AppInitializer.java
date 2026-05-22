package lk_ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        var loginResource = getClass()
                .getResource(
                        "/view/login-form.fxml"
                );

        if(loginResource == null) {
            throw new RuntimeException("login-form.fxml not found!");
        }

        Scene scene = new Scene(
                FXMLLoader.load(loginResource)
        );

        var styleResource = getClass()
                .getResource("/css/style.css");

        if(styleResource == null) {
            throw new RuntimeException("style.css not found!");
        }

        scene.getStylesheets().add(
                styleResource.toExternalForm()
        );

        stage.setScene(scene);

        stage.setTitle("Therapy Center");

        stage.centerOnScreen();

        stage.show();
    }

    public static void main(@SuppressWarnings("unused") String[] args) {

        launch();

    }
}