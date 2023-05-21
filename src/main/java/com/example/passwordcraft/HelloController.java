package com.example.passwordcraft;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public Slider lengthSlider;
    @FXML
    public CheckBox letterBox;
    @FXML
    public CheckBox numBox;
    @FXML
    public CheckBox specialBox;

    @FXML
    private CheckBox mixedLetterBox;

    @FXML
    public Slider lengthSliderLetterBox;

    @FXML
    public Slider numSliderBox;

    @FXML
    public Slider specSliderBox;

    @FXML
    private Label lengthLabel;

    @FXML
    private Label lengthLetterLabel;

    @FXML
    private Label lengthNumberLabel;

    @FXML
    private Label lengthSpecialLabel;

    @FXML
    private Button confirmPasswordLength;

    @FXML
    private Button generatePasswordButton;



    @FXML
    private void handleLetterBoxAction() {
        lengthSliderLetterBox.setVisible(letterBox.isSelected());
        lengthLetterLabel.setVisible(letterBox.isSelected());
        mixedLetterBox.setDisable(!(letterBox.isSelected()));
        if(!letterBox.isSelected()){
            mixedLetterBox.setSelected(false);
        }

        if(!letterBox.isSelected())
        {

            lengthSliderLetterBox.setValue(0);
        }


    }


    @FXML
    private void handleNumBoxAction() {
        numSliderBox.setVisible(numBox.isSelected());
        lengthNumberLabel.setVisible(numBox.isSelected());


        if(!numBox.isSelected())
        {

            numSliderBox.setValue(0);
        }
    }


    @FXML
    private void handleSpecialBoxAction() {
        specSliderBox.setVisible(specialBox.isSelected());
        lengthSpecialLabel.setVisible(specialBox.isSelected());


        if(!specialBox.isSelected())
        {

            specSliderBox.setValue(0);
        }
    }

    @FXML
    public void handleLengthButton()
    {
        lengthSlider.setDisable(true);
        lengthLabel.setDisable(true);
        confirmPasswordLength.setDisable(true);

        letterBox.setDisable(false);
        numBox.setDisable(false);
        specialBox.setDisable(false);

        mixedLetterBox.setDisable(!(letterBox.isSelected()));


    }




    public String generatePassword()
    {
        int lengthSliderValueInt = (int) lengthSlider.getValue();
        int lengthSliderLetterBoxValueInt = (int) lengthSliderLetterBox.getValue();
        int numSliderBoxValueInt = (int) numSliderBox.getValue();
        int specSliderBoxValueInt = (int) specSliderBox.getValue();

        boolean letterBoxState = letterBox.isSelected();
        boolean numBoxState = numBox.isSelected();
        boolean specialBoxState = specialBox.isSelected();
        boolean mixedLetterBoxState = mixedLetterBox.isSelected();

        StringBuilder password = new StringBuilder();


        if(letterBoxState)
        {



                for(int i = 0; i < lengthSliderLetterBoxValueInt; i++)
                {
                    if(mixedLetterBoxState)
                    {
                        if(password.length()<(lengthSliderLetterBoxValueInt)-1)
                        {
                            password.append(HelloApplication.letters.charAt((int) (Math.random() * HelloApplication.letters.length())));
                            password.append(HelloApplication.Letters.charAt((int) (Math.random() * HelloApplication.Letters.length())));
                        }

                        if(password.length()<(lengthSliderLetterBoxValueInt))
                        {

                            password.append(HelloApplication.Letters.charAt((int) (Math.random() * HelloApplication.Letters.length())));
                        }


                    }
                    else
                    {
                        if(password.length()<lengthSliderLetterBoxValueInt)
                            password.append(HelloApplication.letters.charAt((int) (Math.random() * HelloApplication.letters.length())));

                    }




                }


        }


        if(numBoxState)
        {
            for(int i = 0; i < numSliderBoxValueInt; i++)
            {
                password.append(HelloApplication.numbers.charAt((int) (Math.random() * HelloApplication.numbers.length())));
            }
        }


        if(specialBoxState)
        {
            for(int i = 0; i < specSliderBoxValueInt; i++)
            {
                password.append(HelloApplication.specialChars.charAt((int) (Math.random() * HelloApplication.specialChars.length())));
            }
        }





if(lengthSliderValueInt > (lengthSliderLetterBoxValueInt + numSliderBoxValueInt + specSliderBoxValueInt))
        {
            int difference = lengthSliderValueInt - (lengthSliderLetterBoxValueInt + numSliderBoxValueInt + specSliderBoxValueInt);
            for(int i = 0; i < difference; i++)
            {
                int random = (int) (Math.random() * 3);
                if(random == 0)
                {
                    password.append(HelloApplication.letters.charAt((int) (Math.random() * HelloApplication.letters.length())));
                }
                else if(random == 1)
                {
                    password.append(HelloApplication.numbers.charAt((int) (Math.random() * HelloApplication.numbers.length())));
                }
                else if(random == 2)
                {
                    password.append(HelloApplication.specialChars.charAt((int) (Math.random() * HelloApplication.specialChars.length())));
                }
            }
        }




        List<String> chars = Arrays.asList(password.toString().split(""));
        Collections.shuffle(chars);
        StringBuilder shuffledPassword = new StringBuilder();
        for (String c : chars) {
            shuffledPassword.append(c);
        }





        return shuffledPassword.toString();
    }




    @FXML
    public void handleGenerateButton() throws IOException {
        String password = generatePassword();
        if (!password.isEmpty()) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/passwordcraft/PasswordDialog.fxml"));
            Parent dialogPane = fxmlLoader.load();

            PasswordDialogController dialogController = fxmlLoader.getController();
            dialogController.setPassword(password);

            Stage dialogStage = new Stage();
            dialogStage.setResizable(false);
            dialogStage.setWidth(400);
            dialogStage.setHeight(200);

            dialogStage.setTitle("PasswordCraft - Generated Password");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Stage primaryStage = (Stage) generatePasswordButton.getScene().getWindow();
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(dialogPane);
            dialogPane.setStyle("-fx-background-color: #DCE5EF;");
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
        }
    }

    @FXML
    private void handleRestartButton() {


        lengthSlider.setDisable(false);
        lengthLabel.setDisable(false);
        confirmPasswordLength.setDisable(false);

        //Restart all checkboxes to unchecked
        letterBox.setSelected(false);
        numBox.setSelected(false);
        specialBox.setSelected(false);
        mixedLetterBox.setSelected(false);
        //Restart all labels to invisible
        lengthLetterLabel.setVisible(false);
        lengthNumberLabel.setVisible(false);
        lengthSpecialLabel.setVisible(false);
        //Restart all sliders to invisible
        lengthSliderLetterBox.setVisible(false);
        numSliderBox.setVisible(false);
        specSliderBox.setVisible(false);


        //Restart all sliders to 0 values
        lengthSlider.valueProperty().removeListener(lengthSliderListener);
        //lengthSlider.setValue(0);
        lengthSliderLetterBox.setValue(0);
        numSliderBox.setValue(0);
        specSliderBox.setValue(0);

        lengthSlider.valueProperty().removeListener(lengthSliderListener);
        lengthSlider.valueProperty().addListener(lengthSliderListener);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lengthSlider.setValue(0);
            }
        });

        letterBox.setDisable(true);
        numBox.setDisable(true);
        specialBox.setDisable(true);
        mixedLetterBox.setDisable(true);





    }


    private ChangeListener<Number> lengthSliderListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            lengthLabel.setText("Aktualna dlugosc hasla: " + newValue.intValue()+"\n");



            if(newValue.intValue() >0)
            {
                confirmPasswordLength.setDisable(false);
            }

        }

    };

    @FXML
    public void initialize() {
        lengthSliderLetterBox.setVisible(false);
        numSliderBox.setVisible(false);
        specSliderBox.setVisible(false);
        confirmPasswordLength.setDisable(true);
        lengthSlider.valueProperty().addListener(lengthSliderListener);
        mixedLetterBox.setVisible(!(letterBox.isSelected()));




        lengthSliderLetterBox.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.intValue() + numSliderBox.getValue() + specSliderBox.getValue() > lengthSlider.getValue()) {
                    lengthSliderLetterBox.setValue(oldValue.intValue());
                }
                else
                {
                    lengthLetterLabel.setText("Aktualna liczba liter: " + newValue.intValue());
                }


            }
        });


        numSliderBox.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                if(lengthSliderLetterBox.getValue() + newValue.intValue() + specSliderBox.getValue() > lengthSlider.getValue()) {
                    numSliderBox.setValue(oldValue.intValue());
                }
                else
                {
                    lengthNumberLabel.setText("Aktualna liczba cyfr: " + newValue.intValue());
                }


            }
        });

        specSliderBox.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                if(lengthSliderLetterBox.getValue() + numSliderBox.getValue() + newValue.intValue() > lengthSlider.getValue()) {
                    specSliderBox.setValue(oldValue.intValue());
                }
                else
                {
                    lengthSpecialLabel.setText("Aktualna liczba znakow specjalnych: " + newValue.intValue());
                }

            }
        });




        letterBox.setDisable(true);
        numBox.setDisable(true);
        specialBox.setDisable(true);
        mixedLetterBox.setDisable(true);



    }








}