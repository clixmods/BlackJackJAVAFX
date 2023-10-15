module com.example.blackjackjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.blackjackjavafx to javafx.fxml;
    exports com.example.blackjackjavafx;
    exports com.example.blackjackjavafx.card;
    opens com.example.blackjackjavafx.card to javafx.fxml;
}