package org.ejemplos.appcilindro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PresentacionController {

    @FXML
    private Button btnIniciar;

    @FXML
    void onActionBtnIniciar(ActionEvent event) {
        try {
            // Carga la vista de captura y la muestra en una ventana nueva
            FXMLLoader loader = new FXMLLoader(CilindroApp.class.getResource("CilindroVista.fxml"));
            Scene escena = new Scene(loader.load());
            Stage ventanaCalculo = new Stage();
            ventanaCalculo.setTitle("Cálculo de depósitos cilíndricos");
            ventanaCalculo.setScene(escena);
            ventanaCalculo.setResizable(false);
            ventanaCalculo.show();

            // Cierra la ventana de presentación
            Stage ventanaActual = (Stage) btnIniciar.getScene().getWindow();
            ventanaActual.close();
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pudo abrir la ventana de cálculo");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }
}
