module com.example.apalabrados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.apalabrados to javafx.fxml;
    exports com.example.apalabrados;
}