module com.example.blackjackjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.blackjackjavafx to javafx.fxml;
    exports com.example.blackjackjavafx.Application;
    opens com.example.blackjackjavafx.Application to javafx.fxml;
    exports com.example.blackjackjavafx;
    exports com.example.blackjackjavafx.Application.controller;
    opens com.example.blackjackjavafx.Application.controller to javafx.fxml;
}