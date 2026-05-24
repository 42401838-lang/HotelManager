module teoria.hotelmanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens teoria.hotelmanager to javafx.fxml;
    exports teoria.hotelmanager;
}
