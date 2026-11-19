package org.ejemplos.appcilindro;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.ejemplos.appcilindro.modelo.Cilindro;

import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class CilindroController {

    @FXML private TextField txtFldDiametro;
    @FXML private TextField txtFldAltura;
    @FXML private Button btnCalcular;
    @FXML private Button btnBorrar;
    @FXML private Button btnSalir;
    @FXML private Pane imgpane;

    // Modelo del patrón MVC
    private final Cilindro cilindro = new Cilindro();

    // Hasta 6 dígitos enteros, un punto decimal opcional y hasta 4 decimales
    private static final Pattern PATRON_DECIMAL = Pattern.compile("\\d{0,6}([.]\\d{0,4})?");

    @FXML
    public void initialize() {
        // Filtros: solo se pueden escribir números con punto decimal
        txtFldDiametro.setTextFormatter(crearFormatoDecimal());
        txtFldAltura.setTextFormatter(crearFormatoDecimal());

        // Confirmación al cerrar la ventana con la X
        Platform.runLater(() -> {
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.setOnCloseRequest(this::cerrarFormulario);
        });

        // Cilindro 3D decorativo
        Cylinder cyl = new Cylinder(25, 50);
        PhongMaterial material = new PhongMaterial(Color.GRAY);
        material.setSpecularColor(Color.RED);
        cyl.setMaterial(material);
        cyl.getTransforms().addAll(
                new Rotate(125, Rotate.X_AXIS),
                new Rotate(90, Rotate.Y_AXIS),
                new Rotate(90, Rotate.Z_AXIS)
        );
        Group grp3D = new Group(cyl);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(2000);
        camera.setTranslateZ(-400);

        SubScene subScene = new SubScene(grp3D, 0, 0, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);
        subScene.widthProperty().bind(imgpane.widthProperty());
        subScene.heightProperty().bind(imgpane.heightProperty());
        imgpane.getChildren().add(subScene);
    }

    // Crea un TextFormatter que solo acepta números reales de punto flotante
    private TextFormatter<String> crearFormatoDecimal() {
        UnaryOperator<TextFormatter.Change> filtro = cambio -> {
            String textoNuevo = cambio.getControlNewText();
            return PATRON_DECIMAL.matcher(textoNuevo).matches() ? cambio : null;
        };
        return new TextFormatter<>(filtro);
    }

    @FXML
    void onActionBtnCalcular(ActionEvent event) {
        try {
            double diametro = leerValor(txtFldDiametro, "Diámetro");
            double altura = leerValor(txtFldAltura, "Altura");

            // Conversión de diámetro a radio y actualización del modelo
            cilindro.setRadio(diametro / 2);
            cilindro.setAltura(altura);

            mostrarResultados(diametro);
        } catch (IllegalArgumentException e) {
            // NumberFormatException es subclase de IllegalArgumentException
            mostrarError(e.getMessage());
        }
    }

    // Lee y valida el contenido de un campo de texto
    private double leerValor(TextField campo, String nombre) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            campo.requestFocus();
            throw new IllegalArgumentException("El campo «" + nombre + "» está vacío.");
        }
        double valor;
        try {
            valor = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            campo.requestFocus();
            throw new IllegalArgumentException("El campo «" + nombre + "» no contiene un número válido.");
        }
        if (valor <= 0) {
            campo.requestFocus();
            throw new IllegalArgumentException("El campo «" + nombre + "» debe ser mayor que cero.");
        }
        return valor;
    }

    private void mostrarResultados(double diametro) {
        String mensaje = String.format(
                "Diámetro: %.2f m   Radio: %.2f m   Altura: %.2f m%n%n"
                        + "Lámina de acero requerida (área total): %.4f m²%n"
                        + "Capacidad de almacenamiento (volumen): %.4f m³%n"
                        + "Equivalente aproximado: %,.2f litros",
                diametro, cilindro.getRadio(), cilindro.getAltura(),
                cilindro.getArea(), cilindro.getVolumen(),
                cilindro.getVolumen() * 1000);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Resultados del cálculo");
        alerta.setHeaderText("Depósito cilíndrico de lámina de acero");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error en los datos");
        alerta.setHeaderText("Verifique la información capturada");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void onActionBtnBorrar(ActionEvent event) {
        txtFldDiametro.clear();
        txtFldAltura.clear();
        txtFldDiametro.requestFocus();
    }

    @FXML
    void onActionBtnSalir(ActionEvent event) {
        if (confirmarSalida()) {
            Platform.exit();
        }
    }

    // Si el usuario cancela la confirmación, no se cierra la ventana
    private void cerrarFormulario(WindowEvent windowEvent) {
        if (!confirmarSalida()) {
            windowEvent.consume();
        }
    }

    private boolean confirmarSalida() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro que quieres salir?");
        Optional<ButtonType> resp = alert.showAndWait();
        return resp.isPresent() && resp.get() == ButtonType.OK;
    }
}
