module com.atm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.atm to javafx.fxml;
    exports com.atm;
}