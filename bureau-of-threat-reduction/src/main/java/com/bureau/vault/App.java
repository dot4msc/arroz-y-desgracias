package com.bureau.vault;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main entry point for the JavaFX application.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        DatabaseManager.initialize();

        Label label = new Label("BUREAU OF THREAT REDUCTION: THE VAULT");
        label.getStyleClass().add("header-label");

        StackPane root = new StackPane(label);
        root.getStyleClass().add("main-container");

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("SIS | Local Document Vault");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
