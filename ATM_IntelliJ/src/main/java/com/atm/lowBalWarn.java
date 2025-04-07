package com.atm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class lowBalWarn
{
    public Button btnWarn;

    public void start(Stage window)
    {
        btnWarn = new Button("Okay");
        btnWarn.setOnAction(this::buttonClicked);
        TextField warn = new TextField("Your balance is running low. Please top your account up soon.");
        warn.setEditable(false);
        VBox vbox = new VBox(10, warn, btnWarn);
        Scene scene = new Scene(vbox, 550, 150);
        window.setScene(scene);
        window.setTitle("Low Balance Warning");
        window.show();
    }
    public void buttonClicked(ActionEvent event)
    {
        // this line asks the event to provide the actual Button object that was clicked
        Button b = ((Button) event.getSource());
        String label = b.getText(); // get the button label

        if (label.equals("Okay"))
        {
            Stage stage = (Stage) btnWarn.getScene().getWindow();
            stage.close();
        }
    }

}
