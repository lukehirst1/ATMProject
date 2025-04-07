package com.atm;

public class Transfer_Model
{
    // The container objects for this class.
    public Transfer_Controller controller;
    public Transfer_View view;
    Bank bank = null;

    String title = "Transfer money between accounts";
    int number = 0;
    String display1 = null;
    String display2 = null;
    public Boolean transferredA = false;

    public Transfer_Model(Bank b)
    {
        Debug.trace("Model::<constructor>");
        bank = b;
    }

    public void initialise(String message)
    {
        display1 = message;
        display2 =  "Welcome to the transfer screen. Please enter the account number you wish to transfer from\n" +
                "Followed by \"Ent\"";
    }

    public void transferAccountA()
    {
        String accountA = view.accNumber.getText();

        if (!view.accNumber.getText().isEmpty())
        {
            bank.processCheck();

            if (bank.checked)
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

    public void transferAccountB()
    {
        int monAmount = 0;
        String accountB = view.accNumber.getText();
        String amount = view.tranAccountBal.getText();

        if (!view.accNumber.getText().isEmpty() && !view.tranAccountBal.getText().isEmpty())
        {
            bank.processTransfer(monAmount);
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
        System.exit(0);
    }

    public void processNumbers(String label)
    {
        view.selectedTf.appendText(label);
    }
}
