
package com.atm;

import javafx.application.Platform;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;

/**
 * Abandoned. I (Luke Hirst), tried to implement this across the Easter Holidays to little success.
 * The code will remain to show what I was trying to do.
 */
public class Transfer_Model
{
    // The container objects for this class.
    public Transfer_Controller controller;
    public Transfer_View view;

    String title = "Transfer money between accounts";
    int number = 0;
    String display1 = null;
    String display2 = null;
    public Boolean transferredA = false;

    /**
     * [ABANDONED] Calls a reference to the Bank class.
     * @param b
     */
    public Transfer_Model(Bank b)
    {
        Debug.trace("Model::<constructor>");
        Main.mainHolder.b = b;
    }

    public void initialise(String message)
    {
        display1 = message;
        display2 =  "Welcome to the transfer screen. Please enter the account number you wish to transfer from\n" +
                "Followed by \"Ent\"";
    }

    /**
     * This code would have been responsible for collecting the first account information, and checking if was valid or not.
     */
    public void transferAccountA()
    {
        String accountA = view.accNumber.getText();

        if (!view.accNumber.getText().isEmpty())
        {
            Main.mainHolder.b.processCheck();

            if (Main.mainHolder.b.checked)
            {
                view.tranAccountBalL.setText("Account Balance: ");
                view.tranAccountBal.setVisible(true);
                transferAccountB();
            }
        }
        else
        {
            Debug.error("Failed.");
        }
    }

    /**
     * This code would have allowed the user to transfer money from the first account, to another.
     */
    public void transferAccountB()
    {
        int monAmount = 0;
        String accountB = view.accNumber.getText();
        String amount = view.tranAccountBal.getText();

        if (!view.accNumber.getText().isEmpty() && !view.tranAccountBal.getText().isEmpty())
        {
            Main.mainHolder.b.processTransfer(monAmount);
        }
        else if (view.accNumber.getText().isEmpty() && view.tranAccountBal.getText().isEmpty())
        {
            Debug.error("All fields must be filled in!");
        }
    }

    public void cancelOperation()
    {
        view.tranAccountBal.setText("");
        view.accNumber.setText("");
        Debug.trace("All values cleared");
    }

    public void quitApplication()
    {
        Main.mainHolder.goodbye(new Stage());
        Main.mainHolder.PlaySound(Main.atmGoodbye);
        Main.mainHolder.StopSound();
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

    public void processNumbers(String label)
    {
        view.selectedTf.appendText(label);
    }
}
