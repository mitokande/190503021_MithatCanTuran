module com.example._190503021_mithat_can {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example._190503021_mithat_can to javafx.fxml;
    exports com.example._190503021_mithat_can;
}