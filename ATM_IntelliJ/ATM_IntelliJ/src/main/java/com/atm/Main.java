package com.atm;

import javafx.application.Application;
import javafx.stage.Stage;

// atm project Main class
// The code here creates the ATM GUI interface and model functionality, but the methods
// in the BankAccount class which actually do the banking functions are incomplete.
// You need to complete the BankAccount - make log in, deposit, withdraw, checkBalance work
// then add further functionality/features
// Tutors may not help directly with coding but will give you guidance

public class Main extends Application
{
    public void start(Stage window)
    {
        // set up debugging and print initial debugging message
        Debug.set(true);
        Debug.trace("atm starting");
        Debug.trace("Main::start");

        // Create a Bank object for this ATM
       Bank b = new Bank();
        // add some test bank accounts
        b.addBankAccount(10001, 11111, 100);
        b.addBankAccount(10002, 22222, 50);

        // Create the Model, View and Controller objects
        Model model = new Model(b);   // the model needs the Bank object to 'talk to' the bank
        View  view  = new View();
        Controller controller  = new Controller();


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
        model.initialise("Welcome to the ATM");
        model.display();

        // application is now running
        Debug.trace("atm running");

        // Create Account Functionality container

        Create_Model cModel = new Create_Model(b);
        Create_View   cView  = new Create_View();
        Create_Controller cController  = new Create_Controller();

        cModel.view = cView;
        cModel.controller = cController;

        cController.model = cModel;
        cController.view = cView;

        cView.model = cModel;
        cView.controller = cController;
        cView.start(new Stage());

        // Welcome page container

        Welcome_View wView = new Welcome_View();
        Welcome_Model wModel = new Welcome_Model();
        Welcome_Controller wController = new Welcome_Controller();

        wModel.view = wView;
        wModel.controller = wController;

        wController.model = wModel;
        wController.view = wView;

        wView.model = wModel;
        wView.controller = wController;
        wView.start(new Stage());
    }

    public static void main( String args[])
    {
        // The main method only gets used when launching from the command line
        // launch initialises the system and then calls start
        // In BlueJ, BlueJ calls start itself
        launch(args);
    }
}
