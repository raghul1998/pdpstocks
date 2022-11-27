package controller;

import view.GUIView;
import view.TypeofViews;
import view.ViewControllerInteract;

public class GUIController implements Features {
  private GUIView viewGUI;
  private ViewControllerInteract vciObj;

  public GUIController() {
  }

  @Override
  public void setView(GUIView guiView) {
    viewGUI = guiView;
    viewGUI.addFeatures(this);
  }

  public void createAPortfolioGUI() {
    viewGUI.displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
    }

  @Override
  public void flexibleScreenSubmit(int type,String name,int stockSelected) {

    // reenter if not entered anything
    // if the user didn't enter a string, display the error message and then the same screen
      if (name.length() == 0) {
        viewGUI.errorReenter();
        viewGUI.displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
      }
//        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_NAME_REENTER, null, 0);
//        name = scan.nextLine();
//        if (name.equals("0")) {
//          return;
//        }
//      }
//
//      // override
//      if (checkIfPortfolioExists(name)) {
//        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_ALREADY_EXISTS, null, 0);
//        vciObj.viewControllerInteract(TypeofViews.PF_REENTER_DUPLICATE_NAME, null, 0);
//        String input;
//        input = scan.nextLine();
//        while ((input.length() == 0) || !(input.equals("B") || input.equals("b")
//                || input.equals("Y") || input.equals("y") || input.equals("N")
//                || input.equals("n"))) {
//          vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
//          vciObj.viewControllerInteract(TypeofViews.PF_REENTER_DUPLICATE_NAME,
//                  null, 0);
//          input = scan.nextLine();
//        }
//        if (input.equals("B") || input.equals("b")) {
//          return;
//        }
//        if (input.equals("N") || input.equals("n")) {
//          continue;
//        }
//        // This case is 'Y', we want to override.
//      }
//
//      String[] args = new String[2];
//      args[0] = name;
//      args[1] = type;
//      currentPortfolioName = name;
//      createPortfolioNameScreenAction(option, args);
//      break;
//    }

    viewGUI.screen3();
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
