package com.example.dida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        scene = new Scene(fxmlLoader.load(), 1050, 604);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    static void changeFXMLto(String fxml){
        scene.setRoot(FXMLloader(fxml));
    }

    private static Parent FXMLloader(String fxml) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}