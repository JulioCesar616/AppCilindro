module org.ejemplos.appcilindro {
    requires javafx.controls;
    requires javafx.fxml;
    opens org.ejemplos.appcilindro to javafx.fxml, org.junit.platform.commons;
    opens org.ejemplos.appcilindro.modelo to javafx.fxml, org.junit.platform.commons;
    exports org.ejemplos.appcilindro;
    exports org.ejemplos.appcilindro.modelo;
}