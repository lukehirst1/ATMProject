package com.atm;

import javafx.stage.Stage;

/**
 * Deprecated. Please see Launch for replacement class.
 */
public class Welcome_Controller
{
    public Welcome_View view;
    public Welcome_Model model;

    public void process(String action) {
        Debug.trace("Controller::process: action = " + action);
        switch (action) {
//            case "1" : case "2" : case "3" : case "4" : case "5" :
//            case "6" : case "7" : case "8" : case "9" : case "0" :
//                model.processNumbers(action);
//                break;

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
            case "LNB":
                view.openBanking();
                break;

            case "GOO":
                view.openGoodbye();
                break;

            case "CNA":
                view.createNewAccount();
                break;

            case "EXT":
                System.exit(0);
                break;

//            case "CAN":
//                model.cancelOperation();
//                break;
////            case "CLR":
////                model.processClear();
////                break;
//            case "Ent":
//                model.createAccount();
//                break;
            default:
//                model.processUnknownKey(action);
                break;
        }
    }
}
