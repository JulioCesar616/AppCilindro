package org.ejemplos.appcilindro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CilindroApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Carga la primera vista (presentación) desde su archivo FXML
        FXMLLoader loader = new FXMLLoader(CilindroApp.class.getResource("PresentacionVista.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Depósitos cilíndricos - CBTis 182");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
