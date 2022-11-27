package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

import model.TypeofAction;
import view.GUIView;
import view.TypeofViews;
import view.ViewControllerInteract;

public class GUIController extends ControllerViewInteractImpl implements Features {
  private GUIView viewGUI;
  private ViewControllerInteract vciObj;

  /**
   * Constructor for the controller that interacts with the view that takes in two arguments and
   * initializes them to the global variables.
   *
   * @param input  the input stream
   * @param output the output stream
   */
  public GUIController(InputStream input, PrintStream output) {
    super(input, output);
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
  public void valueAndCompositionGUI() {

  }

  @Override
  public void CreatePortfolioScreenSubmit(int type, String name, int optionSelected) {

    // reenter if not entered anything
    // if the user didn't enter a string, display the error message and then the same screen
    if (name.length() == 0) {
      viewGUI.errorReenterName();
      return;
    }
    // override

    String typeStr = null;
    if (type == 0) {
      typeStr = "1";
    } else if (type == 1) {
      typeStr = "2";
    }

    if (checkIfPortfolioExists(name)) {
      int yesToOverride = viewGUI.jOptionPortfolioAlreadyExists();
      if (yesToOverride == 0) {
        String[] args = new String[2];
        args[0] = name;
        args[1] = typeStr;
        super.currentPortfolioName = name;
        createPortfolioNameScreenAction(null, args);
      } else if(yesToOverride == 1){
        viewGUI.resetFlexibleScreen();

      }

    }
    viewGUI.screen3();
  }

  @Override
  public void buyStockSubmit() {

//    if (Objects.equals(option, "m") || Objects.equals(option, "M")) {
//      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
//      return;
//    }


//    String[] stock = new String[2];
//    stock[0] = option;
//    stock[1] = currentPortfolioName;
//    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 2);
//    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK, null, 0);
//    String options;
//    options = scan.nextLine();
//    while ((options == null || options.length() == 0) || ((!options.equals("Y"))
//            && (!options.equals("y")) && (!options.equals("N")) && (!options.equals("n")))) {
//      vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
//      options = scan.nextLine();
//    }
//    buyAnotherStockMenuAction(options, portfolioType, args, length);
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
