package com.atm;

/**
 * Abandoned. I (Luke Hirst), tried to implement this across the Easter Holidays to little success.
 * The code will remain to show what I was trying to do.
 */
public class Transfer_Controller {
    public Transfer_Model model;
    public Transfer_View view;

    public void process(String action) {
        Debug.trace("Controller::process: action = " + action);
        switch (action) {
            case "1" : case "2" : case "3" : case "4" : case "5" :
            case "6" : case "7" : case "8" : case "9" : case "0" :
                model.processNumbers(action);
                Main.mainHolder.PlaySound(Main.atm);
                break;

            case "EXT":
                model.quitApplication();
                break;

            case "CAN":
                model.cancelOperation();
                Main.mainHolder.PlaySound(Main.atm);
                break;

            case "Ent":
                    model.transferAccountA();
                    Main.mainHolder.PlaySound(Main.atm);
                    break;
//                if (model.transferredA)
//                {
//                    model.transferAccountB();
//                    Main.mainHolder.PlaySound(Main.atm);
//                    break;
//                }
//                else
//                {
//                    model.transferAccountA();
//                    Main.mainHolder.PlaySound(Main.atm);
//                    break;
//                }
        }
    }
}
