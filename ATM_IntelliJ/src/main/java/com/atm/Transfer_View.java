package com.atm;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.*;

public class Transfer_View
{
    int H = 420;         // Height of window pixels
    int W = 500;

    Label      title;         // Title area (not the window title)
    Label      accNumberL;
    Label      tranAccountBalL;
    TextField  accNumber;       // Message area, where numbers appear
    TextField  tranAccountBal;
    TextField selectedTf = new TextField() ;
    GridPane   grid;          // main layout grid
    TilePane   buttonPane;

    public Transfer_Model model;
    public Transfer_Controller controller;

    public void start(Stage window)
    {
        grid = new GridPane();
        grid.setId("Layout");            //assign an id to be used in css file
        buttonPane = new TilePane();
        buttonPane.setId("Buttons");

        title  = new Label("Transfer money between accounts");           // Message bar at the top for the title
        grid.add( title, 0, 0);         // Add to GUI at the top

        accNumber  = new TextField();
        accNumberL = new Label("Account Number");
        accNumber.setOnMouseClicked(mouseEvent -> selectedTf = accNumber);
        grid.add (accNumberL, 0, 1);// text field for numbers and error messages
        grid.add( accNumber, 1, 1);       // Add to GUI on second row

        tranAccountBal = new TextField();
        tranAccountBalL = new Label("Account Balance");
        tranAccountBalL.setOnMouseClicked(mouseEvent -> selectedTf = tranAccountBal);
        grid.add(tranAccountBalL, 0, 2);
        grid.add(tranAccountBal, 1, 2);

        String[][] labels = {
                {"7",    "8",  "9",  "",  "",  ""},
                {"4",    "5",  "6",  "",  "",  ""},
                {"1",    "2",  "3",  "",  "CAN",  ""},
                {"",  "0",  "",   "",  "EXT",     "Ent"} };

        for ( String[] row: labels ) {
            for (String label: row) {
                if ( label.length() >= 1 ) {
                    // non-empty string - make a button
                    Button b = new Button( label );
                    b.setOnAction( this::buttonClicked ); // set the method to call when pressed
                    buttonPane.getChildren().add( b );    // and add to tiled pane
                } else {
                    // empty string - add an empty text element as a spacer
                    buttonPane.getChildren().add( new Text() );

                }
            }
        }

        grid.add(buttonPane,1,5); // add the tiled pane of buttons to the grid

        // add the complete GUI to the window and display it
        Scene scene = new Scene(grid, W, H);
        scene.getStylesheets().add("atm.css"); // tell the app to use our css file
        window.setScene(scene);
        window.setTitle("Transfer money between accounts");
        window.setX(200);
        window.setY(200);

        if (!model.transferredA)
        {
            tranAccountBalL.setText("");
            tranAccountBal.setVisible(false);
        }
        window.show();
    }

    public void buttonClicked(ActionEvent event) {
        // this line asks the event to provide the actual Button object that was clicked
        Button b = ((Button) event.getSource());
        if ( controller != null )
        {
            String label = b.getText();   // get the button label
            Debug.trace( "View::buttonClicked: label = "+ label );
            // Try setting a breakpoint here
            controller.process( label );  // Pass it to the controller's process method
        }
    }
}
