package com.example.passwordcraft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    public static final String letters = "abcdefghijklmnopqrstuvwxyz";

    public static final String Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String numbers = "0123456789";
    public static final String specialChars = "!@#$%^&*()_-+=[]{};:,.<>?";


    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);




        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/passwordcraft/hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 500, 620);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        scene.setFill(new RadialGradient(
                0, 0, 0, 0, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#E6E6FA")),
                new Stop(1, Color.web("#E6E6FA")))

        );


        //scene.setFill(Color.web("#DCE5EF"));


        stage.setTitle("PasswordCraft");


        stage.setScene(scene);

        stage.show();



    }

    public static void main(String[] args) {
        launch();
    }
}