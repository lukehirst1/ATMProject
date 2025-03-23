package com.atm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.plaf.IconUIResource;
import java.io.File;

import static javafx.application.Platform.exit;

// atm project Main class
// The code here creates the ATM GUI interface and model functionality, but the methods
// in the BankAccount class which actually do the banking functions are incomplete.
// You need to complete the BankAccount - make log in, deposit, withdraw, checkBalance work
// then add further functionality/features
// Tutors may not help directly with coding but will give you guidance

public class Main
{
    Bank b = new Bank();
    public Boolean stopped = true;
    Clip clip;
    long currentFrame;
    String currentState;
    public static Main mainHolder = new Main();
    public static String atm = "src/main/resources/atmBeep.wav";
    public static String welcomeATM = "src/main/resources/WelcomeSound.wav";
    public static String atmGoodbye = "src/main/resources/goodbye.wav";
    public static String atmAC = "src/main/resources/Action.wav";

    public void start(Stage welcome)
    {

        Welcome_View wView = new Welcome_View();
        Welcome_Model wModel = new Welcome_Model();
        Welcome_Controller wController = new Welcome_Controller();

        wModel.view = wView;
        wModel.controller = wController;

        wController.model = wModel;
        wController.view = wView;

        wView.model = wModel;
        wView.controller = wController;
        wView.start(welcome);
        wModel.initialise("Welcome to the ATM");


    }

    public void PlaySound(String location)
    {
        try
        {
            // Is the clip not empty and running?
            if (clip != null && clip.isRunning())
            {
                StopSound();
            }

            File path = new File(location);

            if (path.exists())
            {
                AudioInputStream audio = AudioSystem.getAudioInputStream(path);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                currentState = "Playing";
            }
            else
            {
                Debug.error("Can't find file");
            }
        }
        catch (Exception e)
        {
            Debug.trace(String.valueOf(e));
        }
    }


    public void StopSound()
    {
        try
        {
            // Is it not null or running?
            if (clip != null && clip.isRunning())
            {
                currentFrame = 0L;
                clip.stop();
                clip.close();
                currentState = "Stopped";
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    public void createNewAccount(Stage CNA)
    {
        // Create Account Functionality container
        //Bank b = new Bank();
        Create_Model cModel = new Create_Model(b);
        Create_View cView = new Create_View();
        Create_Controller cController = new Create_Controller();

        cModel.view = cView;
        cModel.controller = cController;

        cController.model = cModel;
        cController.view = cView;

        cView.model = cModel;
        cView.controller = cController;
        cView.start(new Stage());
    }

    public void banking(Stage window)
    {
        // set up debugging and print initial debugging message
        Debug.set(true);
        Debug.trace("atm starting");
        Debug.trace("Main::start");

        // Create a Bank object for this ATM
       // Bank b = new Bank();
        // add some test bank accounts
        b.addBankAccount(10001, 11111, 100);
        b.addBankAccount(10002, 22222, 50);

        // Create the Model, View and Controller objects
        Model model = new Model(b);   // the model needs the Bank object to 'talk to' the bank
        View view = new View();
        Controller controller = new Controller();


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
    }

    public static void goodbye(Stage goodBye)
    {
        Goodbye view = new Goodbye();
        Goodbye_Controller gController = new Goodbye_Controller();

        view.controller = gController;

        gController.view = view;
        view.start(goodBye);
    }


}
