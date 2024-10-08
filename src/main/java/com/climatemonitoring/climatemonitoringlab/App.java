package com.climatemonitoring.climatemonitoringlab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    /**
    * Costruttore predefinito per la classe App.
    */
    public App() {}
    
    /**
     * Metodo start
     * @param stage parametro stage
     */
    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Metodo main
     * @param args argomento
     */
    public static void main(String[] args) {
        launch();
    }

}