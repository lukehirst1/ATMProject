package com.atm;

import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

// atm project Main class
// The code here creates the ATM GUI interface and model functionality, but the methods
// in the BankAccount class which actually do the banking functions are incomplete.
// You need to complete the BankAccount - make log in, deposit, withdraw, checkBalance work
// then add further functionality/features
// Tutors may not help directly with coding but will give you guidance

public class Main
{
    protected static Bank b = new Bank();
    Clip clip;
    long currentFrame;
    String currentState;
    protected static Main mainHolder = new Main();
    protected static Model model;
    protected static Transfer_Model tModel;
    protected static String atm = "src/main/resources/atmBeep.wav";
    protected static String welcomeATM = "src/main/resources/WelcomeSound.wav";
    protected static String atmGoodbye = "src/main/resources/goodbye.wav";
    protected static String atmAC = "src/main/resources/Action.wav";
    protected boolean isPremium = false;

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

    /**
     * Plays a specified sound from the path location.
     * @param location
     */
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

    /**
     * Stops the existing sound from running
     */
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

        if (b.info.exists())
        {
            b.loadFile();
        }
        else
        {
            Debug.error("Failed to open" + b.info);
        }


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

    public static void printed(Stage print)
    {
        Receipts view = new Receipts();

        view.start(print);
    }

    public static void warning(Stage warn)
    {
        lowBalWarn bal = new lowBalWarn();

        bal.start(warn);
    }

    /**
     * Responsible for transferring money between accounts.
     */

    public void transferMoney(Stage transfer)
    {
          Transfer_Model tModel = new Transfer_Model(b);
          Transfer_Controller tController = new Transfer_Controller();
          Transfer_View tView = new Transfer_View();

          tModel.view = tView;
          tModel.controller = tController;

          tView.model = tModel;
          tView.controller = tController;

          tController.model = tModel;
          tController.view = tView;

          tView.start(transfer);
    }
}
