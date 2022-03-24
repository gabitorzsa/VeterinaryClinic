module com.example.projecttema1ps {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires json.simple;

    opens com.example.projecttema1ps to javafx.fxml;
    exports com.example.projecttema1ps.model;
    exports com.example.projecttema1ps;
    exports com.example.projecttema1ps.view;
    opens com.example.projecttema1ps.view to javafx.fxml;
}