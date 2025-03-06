package com.atm;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.*;

public class Create_View
{
    int H = 420;         // Height of window pixels
    int W = 500;

    Label      title;         // Title area (not the window title)
    TextField  accNumber;       // Message area, where numbers appear
    TextField  accPasswd;
    TextField selectedTf = new TextField() ;
    GridPane   grid;          // main layout grid
    TilePane   buttonPane;

    public Create_Model model;
    public Create_Controller controller;

    public void start(Stage window)
    {
        grid = new GridPane();
        grid.setId("Layout");            //assign an id to be used in css file
        buttonPane = new TilePane();
        buttonPane.setId("Buttons");

        title  = new Label();           // Message bar at the top for the title
        grid.add( title, 0, 0);         // Add to GUI at the top

        accNumber  = new TextField();
        accNumber.setOnMouseClicked(mouseEvent -> selectedTf = accNumber);                                // text field for numbers and error messages
        grid.add( accNumber, 0, 1);       // Add to GUI on second row

        accPasswd = new TextField();
        accPasswd.setOnMouseClicked(mouseEvent -> selectedTf = accPasswd);
        grid.add(accPasswd, 0, 2);

        String labels[][] = {
                {"7",    "8",  "9",  "",  "NAN",  ""},
                {"4",    "5",  "6",  "",  "NAP",  ""},
                {"1",    "2",  "3",  "",  "CAN",  ""},
                {"CLR",  "0",  "",   "",  "CNA",     "Ent"} };

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

        grid.add(buttonPane,0,3); // add the tiled pane of buttons to the grid

        // add the complete GUI to the window and display it
        Scene scene = new Scene(grid, W, H);
        scene.getStylesheets().add("atm.css"); // tell the app to use our css file
        window.setScene(scene);
        window.show();
    }

    public void buttonClicked(ActionEvent event) {
        // this line asks the event to provide the actual Button object that was clicked
        Button b = ((Button) event.getSource());
        //if ( controller != null )
       // {
            String label = b.getText();   // get the button label
            Debug.trace( "View::buttonClicked: label = "+ label );
            // Try setting a breakpoint here
            controller.process( label );  // Pass it to the controller's process method
        //}
    }

    public void update()
    {
//        if (model != null) {
//            Debug.trace( "View::update" );
//            String message1 = model.title;        // get the new title from the model
//            title.setText(message1);              // set the message text to be the title
//            String message2 = model.display1;     // get the new message1 from the model
//            message.setText( message2 );          // add it as text of GUI control output1
//            String message3 = model.display2;     // get the new message2 from the model
//            reply.setText( message3 );            // add it as text of GUI control output2
//        }
    }

}
