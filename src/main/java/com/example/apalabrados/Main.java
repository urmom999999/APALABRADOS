package com.example.apalabrados;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//C:\Users\alvarod\IdeaProjects\CartasJavaFX\target
//C:\Users\alvarod\IdeaProjects\CartasJavaFX\target\classes
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene scene = new Scene(root, 720, 480);

        scene.getStylesheets().add(getClass().getResource("menuCSS.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Apalabrados");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
