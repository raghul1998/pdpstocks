package controller;

import java.io.InputStream;
import java.io.PrintStream;

import view.GUIView;
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
  public void flexibleScreenSubmit(int type, String name, int stockSelected) {

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
      } else {
        return;
      }

    }


    viewGUI.screen3();
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
