
package com.atm;


// Bank class - simple implementation of a bank, with a list of bank accounts, an
// a current account that we are logged in to.

// This class contains one method ('login') which you need to complete as part of
// the lab exercise to make the basic ATM work. Tutors can help you get this part
// working in lab sessions.

import java.io.*;
import java.util.ArrayList;

// If you choose the ATM for your project, you should make other modifications to
// the system yourself, based on similar examples we will cover in lectures and labs.
public class Bank {
    // Instance variables containing the bank information
    protected ArrayList<BankAccount> accounts = new ArrayList<>();  // array to hold the bank accounts
    BankAccount account = null;  // currently logged in account ('null' if no-one is logged in)
    BankAccount account2 = null;
    BankAccount currentAccount = null;
    protected int newAccNum = 0;
    protected int newAccPasswd = 0;
    protected boolean checked = false;
    protected static File info = new File("atmUsers.dat");

    // Constructor method - this provides a couple of example bank accounts to work with
    public Bank() {
        Debug.trace("Bank::<constructor>");
    }

    // a method to create new BankAccounts - this is known as a 'factory method' and is a more
    // flexible way to do it than just using the 'new' keyword directly.
    public BankAccount makeBankAccount(int accNumber, int accPasswd, int balance) {
        return new BankAccount(accNumber, accPasswd, balance);
    }

    // a method to add a new bank account to the bank - it returns true if it succeeds
    // or false if it fails (because the bank is 'full')
    public boolean addBankAccount(BankAccount a) {
        if (accounts.add((a))) {
            Debug.trace("Bank::addBankAccount: added " +
                    a.accNumber + " " + a.accPasswd + " £" + a.balance);
            return true;
        } else {
            Debug.trace("Bank::addBankAccount: can't add bank account - too many accounts");
            return false;
        }
    }

    public boolean processPassword(int number) {
        if (loggedIn()) {
            account.processPassword(number);
            return true;
        } else {
            Debug.trace("Bank::changePassword: cannot change password.");
            return false;
        }
    }

    public boolean accountCheck(int accNumber)
    {
        return accounts.stream().anyMatch(account -> account.getAccNumber() == accNumber);
    }

    /**
     * Prints off however many receipts.
     *
     * @param amount
     * @return
     */
    public boolean processReceipt(int amount) {
        if (loggedIn()) {
            return account.printStatement(amount);
        } else {
            Debug.trace("Bank::printReceipt: cannot print receipt.");
            return false;
        }
    }

    /**
     * Warns the user if the balance is less than or equal to 20. Returns false otherwise.
     * @return
     */
    public boolean processWarn() {
        if (loggedIn() && account.balance <= 20) {
            return account.lowBal();
        } else {
            Debug.trace("Bank::processWarn: Balance is fine.");
            return false;
        }
    }

    /**
     * This class would have processed the transfer, and checked if both accounts were found.
     * Abandoned development.
     * @param amount
     * @return
     */
    public boolean processTransfer(int amount) {
        if (accounts.contains(account) && accounts.contains(account2)) {
            return account2.transferMoney(amount);
        } else {
            Debug.trace("Bank::processTransfer: Invalid account.");
            return false;
        }
    }

    /**
     * Part of the transfer system. Abandoned development.
     * @return
     */
    public boolean processCheck() {
        if (accounts.contains(account)) {
            checked = true;
            return true;
        } else {
            Debug.trace("Bank::processCheck: Account not in ArrayList. Have you entered the right accNumber?");
            return false;
        }
    }

    public boolean processNumber(int number) {
        if (!loggedIn()) {
            Debug.trace("Bank::addNewAccount: Account already exists / Null values");
            return false;
        } else {
            account.processNumber(number);
            return true;
        }
    }

    public boolean processPasswd(int number)
    {
        if (!loggedIn())
        {
            Debug.trace("Bank::addNewAccount: Already exists / Null values");
            return false;
        }
        else
        {
            account.processPassword(number);
            return true;
        }
    }

    /**
     * Deprecated. Would have been responsible for the password logic.
     * @param number
     * @return
     */
    protected boolean processPasswdLoggedOut(int number)
    {
        if (newAccPasswd != number)
        {
            newAccPasswd = number;
            return true;
        }
        else
        {
            Debug.error("Failed");
            return false;
        }
    }

    /**
     * Deprecated. Would have been responsible for handling number log in.
     * @param number
     * @return
     */
    protected boolean processNumberLoggedOut(int number)
    {
        if (newAccNum != number)
        {
            newAccNum = number;
            return true;
        }
        else
        {
            Debug.error("Failed");
            return false;
        }
    }

    // a variant of addBankAccount which makes the account and adds it all in one go.
    // Using the same name for this method is called 'method overloading' - two methods
    // can have the same name if they take different argument combinations
    public boolean addBankAccount(int accNumber, int accPasswd, int balance) {
        return addBankAccount(makeBankAccount(accNumber, accPasswd, balance));
    }

    /**
     * Loops through the array, and determines whether the new account matches the accNumber. Returns false if not true.
     * @param newAccNumber
     * @param newAccPasswd
     * @return
     */
    // Check whether the current saved account and password correspond to
    // an actual bank account, and if so login to it (by setting 'account' to it)
    // and return true. Otherwise, reset the account to null and return false
    // YOU NEED TO ADD CODE TO THIS METHOD FOR THE LAB EXERCISE
    public boolean login(int newAccNumber, int newAccPasswd) {
        Debug.trace("Bank::login: accNumber = " + newAccNumber);
        logout(); // logout of any previous account

        for (BankAccount b : accounts) {
            if (b.accNumber == newAccNumber && b.accPasswd == newAccPasswd) {
                Debug.trace("Bank::login: logged in, accNumber - " + newAccNumber);
                account = b;
                return true;
            }
        }
        // not found - return false
        account = null;
        return false;
        // search the array to find a bank account with matching account and password.
        // If you find it, store it in the variable currentAccount and return true.
        // If you don't find it, reset everything and return false

        // YOU NEED TO ADD CODE HERE TO FIND THE RIGHT ACCOUNT IN THE accounts ARRAY,
        // SET THE account VARIABLE AND RETURN true
    }

    /**
     * Logs the user out of the ATM if they are logged in.
     */
    // Reset the bank to a 'logged out' state
    public void logout() {
        if (loggedIn()) {
            Debug.trace("Bank::logout: logging out, accNumber = " + account.accNumber);
            account = null;
        }
    }

    /**
     * Returns null if the account does not exist. Otherwise, returns true.
     * @return
     */
    // test whether the bank is logged in to an account or not
    public boolean loggedIn() {
        if (account == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Deposits the specified amount into the account. Returns false otherwise.
     * @param amount
     * @return
     */
    // try to deposit money into the account (by calling the deposit method on the
    // BankAccount object)
    public boolean deposit(int amount) {
        if (loggedIn()) {
            return account.deposit(amount);
        } else {
            return false;
        }
    }

    /**
     * Withdraws money by the specified amount if logged in, or returns false otherwise.
     * @param amount
     * @return
     */
    // try to withdraw money into the account (by calling the withdrawal method on the
    // BankAccount object)
    public boolean withdraw(int amount) {
        if (loggedIn()) {
            return account.withdraw(amount);
        } else {
            return false;
        }
    }

    /**
     * Get the initial balance at the start, or -1 is no balance is found.
     * @return
     */
    public int getStartBal() {
        if (loggedIn()) {
            return account.getStartBal();
        } else {
            return -1;
        }
    }

    /**
     * Returns the current account balance, or -1, if nothing is found.
     * @return
     */
    // get the account balance (by calling the balance method on the
    // BankAccount object)
    public int getBalance() {
        if (loggedIn()) {
            return account.getBalance();
        } else {
            return -1; // use -1 as an indicator of an error
        }
    }

    /**
     * Saves the ArrayList into a file, which is later read by the ATM.
     */

    protected void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream(info);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accounts);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    /**
     * Loads the requested file from the directory, and uses the ArrayList to "fetch" the information.
     */
    protected void loadFile()
    {
        try {
            FileInputStream fls = new FileInputStream(info);
            ObjectInputStream ols = new ObjectInputStream(fls);
            accounts = (ArrayList<BankAccount>) ols.readObject();
            ols.close();
            fls.close();
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            System.err.println(e);
        }
    }
}
