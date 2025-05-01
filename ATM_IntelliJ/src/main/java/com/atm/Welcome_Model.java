package com.atm;

import javafx.stage.Stage;

/**
 * Deprecated. Please see Launch for new functionality.
 */
public class Welcome_Model
{
    public Welcome_View view;
    public Welcome_Controller controller;
    public Main main;
    String display1 = null;
    String display2 = null;

    public Welcome_Model()
    {

    }

    public void initialise(String message)
    {
        display1 = message;
        display2 = "Which method would you like to perform first?";
    }

//    public void launchBanking()
//    {
//        mView.start(new Stage());
//    }
//    public void launchCNA()
//    {
//        main.cView.start(new Stage());
//    }
}
