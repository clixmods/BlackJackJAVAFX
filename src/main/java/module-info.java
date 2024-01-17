module com.example.blackjackjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.media;

    opens com.example.blackjackjavafx to javafx.fxml;
    exports com.example.blackjackjavafx;
    exports com.example.blackjackjavafx.Application.controller;
    opens com.example.blackjackjavafx.Application.controller to javafx.fxml;
    exports com.example.blackjackjavafx.Metier;
    opens com.example.blackjackjavafx.Metier to javafx.fxml;
    exports com.example.blackjackjavafx.Application.connection;
    opens com.example.blackjackjavafx.Application.connection to javafx.fxml;
    exports com.example.blackjackjavafx.Application.helper;
    opens com.example.blackjackjavafx.Application.helper to javafx.fxml;
}