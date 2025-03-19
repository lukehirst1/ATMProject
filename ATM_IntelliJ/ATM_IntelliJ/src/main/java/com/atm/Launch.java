package com.atm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;
import static javafx.application.Platform.exit;

public class Launch extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {


    Button btnLNB = new Button("Launch New Banking");
    btnLNB.setOnAction(this::buttonClicked);
    Button btnCNA = new Button("Create New Account");
    btnCNA.setOnAction(this::buttonClicked);
    Button btnEXT = new Button("Exit All");

    btnEXT.setOnAction(this::buttonClicked);

    // make an imageView, add to vbox
        ImageView iv = new ImageView("atmWelcomePage.pNG");
        iv.setFitWidth(400);
        iv.setPreserveRatio(true);

    VBox vbox = new VBox(10,btnLNB,btnCNA,btnEXT, iv);
        Scene scene = new Scene(vbox, 400, 450);
        window.setScene(scene);
        window.setTitle("welcome to using ATM");
        window.show();


    }


    public void buttonClicked(ActionEvent event)  {
        // this line asks the event to provide the actual Button object that was clicked
        Button b = ((Button) event.getSource());
        String label = b.getText();   // get the button label
        if(label == "Launch New Banking"){
            new Main().banking(new Stage());
        }
        if(label == "Create New Account"){
            new Main().createNewAccount(new Stage());
        }
        if (label== "Exit All"){
            new Main().goodbye(new Stage());
            // Quits the application, regardless of how many windows are open.
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    exit();
                }
            });

        }
    }

}
