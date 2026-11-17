module org.ejemplos.appcilindro {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.ejemplos.appcilindro to javafx.fxml;
    exports org.ejemplos.appcilindro;
}