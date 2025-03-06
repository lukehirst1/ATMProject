package com.atm;

import javafx.application.Application;
import javafx.stage.Stage;

public class Create_Main extends Application
{
    public void start(Stage window)
    {
        Debug.set(true);
        Debug.trace("atm starting");
        Debug.trace("Main::start");

        // Create a Bank object for this ATM
        Bank b = new Bank();
//        // add some test bank accounts
//        b.addBankAccount(10001, 11111, 100);
//        b.addBankAccount(10002, 22222, 50);

        // Create the Model, View and Controller objects
        Create_Model model = new Create_Model(b);   // the model needs the Bank object to 'talk to' the bank
        Create_View  view  = new Create_View();;
        Create_Controller controller  = new Create_Controller();

        // Link them together, so they can talk to each other
        // Each one has instances variable for the other two
        model.view = view;
        model.controller = controller;

        controller.model = model;
        controller.view = view;

        view.model = model;
        view.controller = controller;

        // start up the GUI (view), and then tell the model to initialise and display itself
        view.start(window);
        model.initialise("Welcome to the account creation page.");
       // model.display();

        // application is now running
        Debug.trace("atm running");
    }

    public static void main(String args[])
    {
        // The main method only gets used when launching from the command line
        // launch initialises the system and then calls start
        // In BlueJ, BlueJ calls start itself
        launch(args);
    }
}
