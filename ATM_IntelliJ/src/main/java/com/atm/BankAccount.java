package com.atm;

import java.util.Scanner;
import java.time.LocalDateTime;

// BankAccount class
// This class has instance variables for the account number, password and balance, and methods
// to withdraw, deposit, check balance etc.

// This class contains methods which you need to complete to make the basic ATM work.
// Tutors can help you get this part working in lab sessions.

// If you choose the ATM for your project, you should make other modifications to
// the system yourself, based on similar examples we will cover in lectures and labs.
public class BankAccount
{
    // Integers and booleans for the BankAccount constructor.
    public int accNumber = 0;
    public int accPasswd = 0;
    public int balance;
    public int startBal;
    public int withdrawalLimit = 5000;
    public int higherWithdrawalLimit = 10000;
    public int overdraftLimit = 1500;
    public boolean isPremium;
    public boolean canOverdraft;
    public boolean canHigherWD;
    public int withdrawVal = 0;
    public int depositVal = 0;
    protected String receipt;
    LocalDateTime dateT = LocalDateTime.now();

    public BankAccount() { // an empty constructor

    }

    public BankAccount(int a, int p, int b) {
        accNumber = a;
        accPasswd = p;
        balance = b;
        startBal = balance;
    }

    // withdraw money from the account. Return true if successful, or
    // false if the amount is negative, or less than the amount in the account

    // ADD LOW NOTIFICATION WARNING FOR BALANCE.
    public boolean withdraw(int amount) {
        Debug.trace("BankAccount::withdraw: amount =" + amount);

        // CHANGE CODE HERE TO WITHDRAW MONEY FROM THE ACCOUNT
        if (amount < 0)
        {
            Debug.error("Insufficient funds");
            return false;
        }

//        if (amount > withdrawalLimit)
//        {
//            Debug.error("Over withdrawal Limit");
//            return false;
//        }

        if (isPremium && canOverdraft && canHigherWD)
        {
            if ((balance - amount) < -overdraftLimit)
            {
                Debug.error("Overdraft exceeded");
                return false;
            }
            if (amount > higherWithdrawalLimit)
            {
                Debug.error("Over withdrawal Limit");
                return false;
            }
            else
            {
                balance = balance - amount;
                return true;
            }
        }
        else
        {
            if (amount > withdrawalLimit)
            {
                Debug.error("Over withdrawal limit");
                return false;
            }
            else
            {
                balance = balance - amount;
                withdrawVal = amount;
                return true;
            }
        }
    }

    // deposit the amount of money into the account. Return true if successful,
    // or false if the amount is negative
    public boolean deposit( int amount )
    {
        Debug.trace( "LocalBank::deposit: amount = " + amount );
        // CHANGE CODE HERE TO DEPOSIT MONEY INTO THE ACCOUNT
        if (amount < 0)
        {
            return false;
        }
        else
        {
            balance = balance + amount;
            depositVal = amount;
            return true;
        }
    }

    public boolean processPassword(int number)
    {
        if (accPasswd != number)
        {
            Debug.trace("New password set.");
            accPasswd = number;
            return true;
        }
        else
        {
            Debug.error("That password is already in use.");
            return false;
        }
    }

    public boolean processNumber(int number)
    {
        if (accNumber != number)
        {
            Debug.trace("New account number created.");
            accPasswd = number;
            return true;
        }
        else
        {
            Debug.error("Account number already exists.");
            return false;
        }
    }

    public boolean printStatement()
    {
        // Next steps: Add account number to the receipt.
        receipt = "************************ \n ************************\n Statement generated at: " + dateT + "\n Initial Balance: " + startBal +
              "\n Current Balance now: " + balance + "\n Withdrawn: " + withdrawVal + "\n Deposited: " + depositVal + "\n Thank you for banking with us today!"
        + "\n ************************ \n ************************";
        System.out.println(receipt);
        return true;
    }

    // Returns the initial balance in the account
    public int getStartBal()
    {;
        Debug.trace("LocalBank::getStartBal");

        return startBal;
    }


    // Return the current balance in the account
    public int getBalance()
    {
        Debug.trace( "LocalBank::getBalance" );

        // CHANGE CODE HERE TO RETURN THE BALANCE
        return balance;
    }
}

