package com.atm;

public class Create_Model extends Bank
{
    public Create_View view;
    public Create_Controller controller;

    // the ATM model is always in one of three states - waiting for an account number,
    // waiting for a password, or logged in and processing account requests.
    // We use string values to represent each state:
    // (the word 'final' tells Java we won't ever change the values of these variables)

    // variables representing the ATM model// the state it is currently in     // current number displayed in GUI (as a number, not a string)
    Bank  bank = null;              // The ATM talks to a bank, represented by the Bank object.
    int accNumber = -1;             // Account number typed in
    int accPasswd = -1;             // Password typed in
    int accBalance = -1;               // Initial Balance
    public Boolean isPremium = false;
    // These three are what are shown on the View display
    String title = "Bank ATM Account Creation";      // The contents of the title message
    String display1 = null;         // The contents of the Message 1 box (a single line)
    String display2 = null;         // The contents of the Message 2 box (may be on multiple lines)

    // Model constructor - we pass it a Bank object representing the bank we want to talk to
    public Create_Model(Bank b)
    {
        Debug.trace("Model::<constructor>");
        bank = b;
    }

    // Initialising the ATM (or resetting after an error or logout)
    // set state to ACCOUNT_NO, number to zero, and display message
    // provided as argument and standard instruction message
    public void initialise(String message) {
        display1 = message;
        display2 =  "Please enter a new account number and account password\n" +
                "Followed by \"Ent\"";
    }

    public void createAccount()
    {
        String addNumber = view.accNumber.getText();
        String addPasswd = view.accPasswd.getText();
        String balance = view.accBalance.getText();

        if (!view.accNumber.getText().isEmpty() && !view.accPasswd.getText().isEmpty() && !view.accBalance.getText().isEmpty())
        {
            if (view.premium.isSelected())
            {
                bank.addBankAccount(makeBankAccount(Integer.parseInt(addNumber), Integer.parseInt(addPasswd), Integer.parseInt(balance)));
                Debug.trace("Premium account created!");
                Main.mainHolder.isPremium = true;
                Main.mainHolder.b.accounts.add(account);
                Main.mainHolder.saveFile();
            }
            else if (!view.premium.isSelected())
            {
                bank.addBankAccount(makeBankAccount(Integer.parseInt(addNumber), Integer.parseInt(addPasswd), Integer.parseInt(balance)));
                Debug.trace("Basic account created!");
                Main.mainHolder.isPremium = false;
                Main.mainHolder.b.accounts.add(account);
                Main.mainHolder.saveFile();
            }
        }
        else if (addNumber.length() != 5 || addPasswd.length() != 5)
        {
            Debug.error("Cannot make account, invalid values");
        }
        else if (balance.length() <= 0)
        {
            Debug.error("The balance cannot be below zero.");
        }
    }

    public void quitApplication()
    {
        System.exit(0);
    }

    public void cancelOperation()
    {
        view.accBalance.setText("");
        view.accNumber.setText("");
        view.accPasswd.setText("");
        Debug.trace("All values cleared");
    }

    // These methods are called by the Controller to change the Model
    // when particular buttons are pressed on the GUI
    public void processNumbers(String label)
    {
        view.selectedTf.appendText(label);
    }

    // process a number key (the key is specified by the label argument)
//    public void processNumber()
//    {
//        if (state.equals(CREATE_ACCNUM))
//        {
//            bank.processNumber(number);
//            display2 = "Please enter your new account number.";
//            initialise("Account number created! Please continue.");
//        }
//        else
//        {
//            initialise("Account number already exists.");
//        }
//    }
//
//    // process the Clear button - reset the number (and number display string)
//    public void processClear()
//    {
//        // clear the number stored in the model
//        number = 0;
//        display1 = "";
//        display();  // update the GUI
//    }
//
//    public void processPassword()
//    {
//        // Is the number valid?
//        if (state.equals(CREATE_ACCPWD))
//        {
//            // Update the password
//            bank.processPassword(number);
//            display2 = "Please enter your new password:";
//            initialise("Password created. Please continue.");
//        }
//        else
//        {
//            initialise("Password already exists.");
//        }
//    }
//
//    // process the Enter button
//    // this is the most complex operation - the Enter key causes the ATM to change state
//    // from account number, to password, to logged_in and back to account number
//    // (when you log out)
//    public void processEnter()
//    {
//        // Enter was pressed - what we do depends what state the ATM is already in
//        switch ( state )
//        {
//            case CREATE_ACCNUM:
//                // we were waiting for a complete account number - save the number we have
//                // reset the typed in number to 0 and change to the state where we are expecting
//                // a password
//                accNumber = number;
//                number = 0;
//                setState(CREATE_ACCPWD);
//                display1 = "";
//                display2 = "Now enter your new password\n" +
//                        "Followed by \"NAP\"";
//                break;
//            case CREATE_ACCPWD:
//                // we were waiting for a password - save the number we have as the password
//                // and then contact the bank with account number and accPasswd to try and login to
//                // an account
//                accPasswd = number;
//                number = 0;
//                display1 = "";
//                // now check the account/password combination. If it's ok go into the LOGGED_IN
//                // state, otherwise go back to the start (by re-initialsing)
//                if ( bank.login(accNumber, accPasswd) )
//                {
//                    setState(ACCOUNT_CREATED);
//                    display2 = "Account has been created.\n" +
//                            "Please log out of this page.";
//                } else {
//                    initialise("Account already exists.");
//                }
//                break;
//            case ACCOUNT_CREATED:
//            default:
//                // do nothing in any other state (ie logged in)
//        }
//        display();  // update the GUI
//    }

    // Withdraw button - check we are logged in and if so try and withdraw some money from
    // the bank (number is the amount showing in the interface display)
//    public void processWithdraw()
//    {
//        if (state.equals(LOGGED_IN) ) {
//            if ( bank.withdraw( number ) )
//            {
//                display2 =   "Withdrawn: " + number;
//            } else {
//                display2 =   "You do not have sufficient funds";
//            }
//            number = 0;
//            display1 = "";
//        } else {
//            initialise("You are not logged in");
//        }
//        display();  // update the GUI
//    }
//
//    // Deposit button - check we are logged in and if so try and deposit some money into
//    // the bank (number is the amount showing in the interface display)
//    public void processDeposit()
//    {
//        if (state.equals(LOGGED_IN) ) {
//            bank.deposit( number );
//            display1 = "";
//            display2 = "Deposited: " + number;
//            number = 0;
//        } else {
//            initialise("You are not logged in");
//        }
//        display();  // update the GUI
//    }
//
//    // Balance button - check we are logged in and if so access the current balance
//    // and display it
//    public void processBalance()
//    {
//        if (state.equals(LOGGED_IN) ) {
//            number = 0;
//            display2 = "Your balance is: " + bank.getBalance();
//        } else {
//            initialise("You are not logged in");
//        }
//        display();  // update the GUI
//    }

    // Finish button - check we are logged in and if so log out
//    public void processFinish()
//    {
//        if (state.equals(ACCOUNT_CREATED) ) {
//            // go back to the log in state
//            setState(CREATE_ACCNUM);
//            number = 0;
//            display2 = "Welcome: Enter your account number";
//            bank.logout();
//        } else {
//            initialise("You are not logged in");
//        }
//        display();  // update the GUI
//    }
//
//    // Any other key results in an error message and a reset of the GUI
//    public void processUnknownKey(String action)
//    {
//        // unknown button, or invalid for this state - reset everything
//        Debug.trace("Model::processUnknownKey: unknown button \"" + action + "\", re-initialising");
//        // go back to initial state
//        initialise("Invalid command");
//        display();
//    }

    // This is where the Model talks to the View, by calling the View's update method
    // The view will call back to the model to get new information to display on the screen
//    public void display()
//    {
//        Debug.trace("Model::display");
//        view.update();
//    }
}
