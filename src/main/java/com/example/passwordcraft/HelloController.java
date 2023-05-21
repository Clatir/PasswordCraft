package com.example.passwordcraft;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

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
    private void handleLetterBoxAction() {
        lengthSliderLetterBox.setVisible(letterBox.isSelected());
        lengthLetterLabel.setVisible(letterBox.isSelected());
        mixedLetterBox.setDisable(false);
    }


    @FXML
    private void handleNumBoxAction() {
        numSliderBox.setVisible(numBox.isSelected());
        lengthNumberLabel.setVisible(numBox.isSelected());
    }


    @FXML
    private void handleSpecialBoxAction() {
        specSliderBox.setVisible(specialBox.isSelected());
        lengthSpecialLabel.setVisible(specialBox.isSelected());
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
        mixedLetterBox.setDisable(false);


    }

    @FXML
    public void handleGenerateButton()
    {
        System.out.println("AAA");
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
        mixedLetterBox.setDisable(!(letterBox.isSelected()));

/*
        lengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                lengthLabel.setText("Aktualna dlugosc hasla: " + newValue.intValue()+"\n");



                if(newValue.intValue() >0)
                {
                    confirmPasswordLength.setDisable(false);
                }

            }
        });

 */


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







    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}