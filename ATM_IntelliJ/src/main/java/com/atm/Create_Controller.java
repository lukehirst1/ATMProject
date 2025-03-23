package com.atm;

public class Create_Controller
{
    public Create_Model model;
    public Create_View view;

    public void process( String action )
    {
        Debug.trace("Controller::process: action = " + action);
        switch (action) {
            case "1" : case "2" : case "3" : case "4" : case "5" :
            case "6" : case "7" : case "8" : case "9" : case "0" :
                model.processNumbers(action);
                Main.mainHolder.PlaySound(Main.atm);
                break;

                // REDUNDANT CODE, please do NOT USE - Luke
//            case "NAN":
//                model.processNumber();
//                break;
//            case "NAP":
//                model.processPassword();
//                break;
//            case "CNA":
//                model.processFinish();
//                break;

            case "EXIT":
                model.quitApplication();
                break;

            case "CAN":
                model.cancelOperation();
                Main.mainHolder.PlaySound(Main.atm);
                break;
//            case "CLR":
//                model.processClear();
//                break;
            case "Ent":
                model.createAccount();
                Main.mainHolder.PlaySound(Main.atmAC);
                break;
            default:
//                model.processUnknownKey(action);
                break;
        }
    }
}
