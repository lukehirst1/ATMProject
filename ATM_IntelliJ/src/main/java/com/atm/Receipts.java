package com.atm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Receipts
{
    public void start(Stage window)
    {
        String fullText = Main.mainHolder.b.account.receipt;
        TextArea area = new TextArea(fullText);
        area.setEditable(false);
        VBox vbox = new VBox(10, area);
        Scene scene = new Scene(vbox, 400, 450);
        window.setScene(scene);
        window.setTitle("Printed Receipts");
        window.show();
    }

}
