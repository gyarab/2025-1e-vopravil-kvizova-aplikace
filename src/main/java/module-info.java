module org.example.kvizovaaplikace2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens com.example.kvizovaaplikace2 to javafx.fxml;
    exports com.example.kvizovaaplikace2;
}