package com.atm;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 * Deprecated. Please see Launch for the replaced functionality.
 */
public class Welcome_View
{
    int H = 420;         // Height of window pixels
    int W = 500;

    Label      title;         // Title area (not the window title)
//    Label      accNumberL;
//    Label      accPasswdL;
//    Label      accBalanceL;
//    TextField  accNumber;       // Message area, where numbers appear
//    TextField  accPasswd;
//    TextField  accBalance;
//    TextField selectedTf = new TextField() ;
    GridPane   grid;          // main layout grid
    TilePane   buttonPane;

    public Welcome_Controller controller;
    public Welcome_Model model;
    public Main main = new Main();

    public void start(Stage window)
    {

        grid = new GridPane();
        grid.setId("Layout");            //assign an id to be used in css file
        buttonPane = new TilePane();
        buttonPane.setId("Buttons");

        title  = new Label();           // Message bar at the top for the title
        grid.add( title, 0, 0);         // Add to GUI at the top

//        accNumber  = new TextField();
//        accNumberL = new Label("New Account Number");
//        accNumber.setOnMouseClicked(mouseEvent -> selectedTf = accNumber);
//        grid.add (accNumberL, 1, 1);// text field for numbers and error messages
//        grid.add( accNumber, 0, 1);       // Add to GUI on second row
//
//        accPasswd = new TextField();
//        accPasswdL = new Label("New Account Password");
//        accPasswd.setOnMouseClicked(mouseEvent -> selectedTf = accPasswd);
//        grid.add(accPasswd, 0, 2);
//        grid.add(accPasswdL, 1, 2);
//
//        accBalance = new TextField();
//        accBalanceL = new Label("Set Balance");
//        accBalance.setOnMouseClicked(mouseEvent -> selectedTf = accBalance);
//        grid.add(accBalance, 0, 3);
//        grid.add(accBalanceL, 1, 3);

        String labels[][] = {
                {"LNB", "GOO", "", ""},
                {"CNA", "", "", "", ""},
                {"EXT", "", "", "", ""},
                {"", "", "", "", ""} };

        for ( String[] row: labels ) {
            for (String label: row) {
                if ( label.length() >= 1 ) {
                    // non-empty string - make a button
                    Button b = new Button( label );
                    b.setOnAction( this::buttonClicked ); // set the method to call when pressed
                    buttonPane.getChildren().add( b );    // and add to tiled pane
                } else {
                    // empty string - add an empty text element as a spacer
                    buttonPane.getChildren().add( new Text());

                }
            }
        }

        grid.add(buttonPane,0,4); // add the tiled pane of buttons to the grid

        // add the complete GUI to the window and display it
        Scene scene = new Scene(grid, W, H);
        scene.getStylesheets().add("atm.css"); // tell the app to use our css file
        window.setScene(scene);
        window.setTitle("Welcome Page");
        window.setY(600);
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

    public void openBanking()

    {
        main.banking(new Stage());
    }

    public void createNewAccount()
    {
        main.createNewAccount(new Stage());
    }

    public void openGoodbye()
    {
        main.goodbye(new Stage());
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
