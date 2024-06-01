module bakery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens bakery to javafx.fxml;

    exports bakery;
}
