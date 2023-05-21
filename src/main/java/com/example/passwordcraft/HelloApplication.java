package com.example.passwordcraft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    private final String letters = "abcdefghijklmnopqrstuvwxyz";
    private final String numbers = "0123456789";
    private final String specialChars = "!@#$%^&*()_-+=[]{};:,.<>?";


    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/passwordcraft/hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        /*
        scene.setFill(new RadialGradient(
                0, 0, 0, 0, 1, true,                  //sizing
                CycleMethod.NO_CYCLE,                 //cycling
                new Stop(0, Color.web("#3371D4")),    //colors
                new Stop(1, Color.web("#37B9FC")))

        );

         */
        scene.setFill(Color.web("#D1EAEF"));


        stage.setTitle("Hello!");


        stage.setScene(scene);

        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}