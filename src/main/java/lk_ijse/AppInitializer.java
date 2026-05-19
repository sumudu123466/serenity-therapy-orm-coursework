package lk_ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(

                FXMLLoader.load(
                        getClass()
                                .getResource(
                                        "/view/login-form.fxml"
                                )
                )

        );

        scene.getStylesheets().add(

                getClass()
                        .getResource("/css/style.css")
                        .toExternalForm()

        );

        stage.setScene(scene);

        stage.setTitle("Therapy Center");

        stage.centerOnScreen();

        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}