module com.example.blackjackjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.blackjackjavafx to javafx.fxml;
    exports com.example.blackjackjavafx.Application;
    opens com.example.blackjackjavafx.Application to javafx.fxml;
    exports com.example.blackjackjavafx;
    exports com.example.blackjackjavafx.card;
    opens com.example.blackjackjavafx.card to javafx.fxml;
    exports com.example.blackjackjavafx.gameState;
    opens com.example.blackjackjavafx.gameState to javafx.fxml;
    exports com.example.blackjackjavafx.jeton;
    opens com.example.blackjackjavafx.jeton to javafx.fxml;
}