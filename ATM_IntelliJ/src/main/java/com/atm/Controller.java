package com.atm;


import javafx.stage.Stage;

// The ATM controller is quite simple - the process method is passed
// the label on the button that was pressed, and it calls different
// methods in the model depending what was pressed.
public class Controller
{
    public Model model;
    public View  view;

    // we don't really need a constructor method, but include one to print a
    // debugging message if required
    public Controller()
    {
        Debug.trace("Controller::<constructor>");
    }

    // This is how the View talks to the Controller
    // AND how the Controller talks to the Model
    // This method is called by the View to respond to some user interface event
    // The controller's job is to decide what to do. In this case it uses a switch
    // statement to select the right method in the Model

    // The transfer functionality has been disabled, due to time constraints and difficulty.
    public void process( String action )
    {
        Debug.trace("Controller::process: action = " + action);
        switch (action) {
            case "1" : case "2" : case "3" : case "4" : case "5" :
            case "6" : case "7" : case "8" : case "9" : case "0" :
                model.processNumber(action);
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "CLR":
                model.processClear();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "Ent":
                model.processEnter();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "W/D":
                model.processWithdraw();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "Dep":
                model.processDeposit();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "Bal":
                model.processBalance();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "Fin":
                model.processFinish();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "C/P":
                model.processPassword();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            case "CNA":
                Main.mainHolder.createNewAccount(new Stage());
                Main.mainHolder.PlaySound(Main.atm);
                Main.mainHolder.PlaySound(Main.atmAC);
                break;
            case "Tra":
                /*
                Main.mainHolder.transferMoney(new Stage());
                Main.mainHolder.PlaySound(Main.atm);
                Main.mainHolder.PlaySound(Main.atmAC);
                */
                Debug.error("File corrupted. Please contact a administrator. \n" +
                        "Error code 192: Functionality is not working properly.");
                break;
            case "STA":
                model.processReceipt();
                Main.mainHolder.PlaySound(Main.atm);
                break;
            default:
                model.processUnknownKey(action);
                break;
        }
    }

}

