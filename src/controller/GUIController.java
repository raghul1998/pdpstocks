package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import model.StockNameMap;
import model.StockNameMapImpl;
import model.TypeofAction;
import view.GUIView;
import view.TypeofViews;
import view.ViewControllerInteract;

public class GUIController extends ControllerViewInteractImpl implements Features {
  private GUIView viewGUI;
  private ViewControllerInteract vciObj;
  boolean visited = false;
  //private ControllerModelInteract cmiObj;

  // todo handle back and back to main menu

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
  public void createPortfolioScreenSubmit(int type, String name, int optionSelected) {

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
        // do nothing
      } else if(yesToOverride == 1){
        viewGUI.resetCreatePortfolioScreen();
        return;
      }
    }

    String[] args = new String[2];
    args[0] = name;
    args[1] = typeStr;
    super.currentPortfolioName = name;
    super.cmiObj.controllerModelInteract(TypeofAction.CREATE_PORTFOLIO, args, 1);
    //createPortfolioNameScreenAction(null, args);
    viewGUI.flexiblePortfolioScreenWithDateInput();
  }



  @Override
  public void checkCurrentPrice(String date, int stockSelected) {


    // ControllerViewInteractImpl performListOfStocksMenuAction
//    if (Objects.equals(options, "m") || Objects.equals(options, "M")) {
//      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
//      return false;
//    }
    if (!super.validateDate(date, "yyyy-MM-dd", 0)) {
      // vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      viewGUI.invalidDate();
      viewGUI.resetDateInput();
      return;
    }
    String[] stock = new String[2];

    StockNameMap snp = new StockNameMapImpl();
    Map<String, String> map = snp.getMap();
    String[] stockSymbolIndexArray = new String[snp.getMapSize()];

    int index = 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      stockSymbolIndexArray[index++] = entry.getKey();
    }
    stock[0] = stockSymbolIndexArray[stockSelected - 1];
    stock[1] = date;
    super.cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stock, 2);

    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      System.out.println("Supported stocks file not found " + e.getMessage());
    }

    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
      stockData.close();
    } catch (Exception e) {
      System.out.println("Error in reading Supported stocks csv file.");
    }
    // vciObj.viewControllerInteract(TypeofViews.SHOW_STOCK_DATA, null, 0);
    viewGUI.displayStockDataUsingJOption(splitStockData[0],splitStockData[2],splitStockData[3],splitStockData[1]);

//
//    String option;
//    option = scan.nextLine();
//    while ((option == null || option.length() == 0) || (!validateBuyStockOption(option))) {
//      if (Objects.equals(option, "b") || Objects.equals(option, "B")) {
//        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
//        return true;
//      }
//    }
//    buyStockValueMenuAction(option, portfolioType, args, length);
//    return false;
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void buyStockSubmit(String date, int stockSelected, String noOfStocks, String portfolioName) {
// ControllerViewInteractImpl buyStockValueMenuAction

//    if (Objects.equals(option, "m") || Objects.equals(option, "M")) {
//      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
//      return;
//    }

    // invalid date
    if (!super.validateDate(date, "yyyy-MM-dd", 0)) {
      // vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      viewGUI.invalidDate();
      viewGUI.resetDateInput();
      return;
    }

    // empty no of stocks
    if(noOfStocks.equals("")){
      viewGUI.noOfSharesNotEntered();
      return;
    }

    StockNameMap snp = new StockNameMapImpl();
    Map<String, String> map = snp.getMap();
    String[] stockSymbolIndexArray = new String[snp.getMapSize()];
    String[] stock = new String[2];
    int index = 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      stockSymbolIndexArray[index++] = entry.getKey();
    }
    stock[0] = stockSymbolIndexArray[stockSelected];
    stock[1] = date;
    super.cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stock, 2);



    super.currentPortfolioName = portfolioName;
    //String[] stock = new String[2];
    stock[0] = noOfStocks;
    stock[1] = super.currentPortfolioName;
    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 2);
    if(!visited) {
      viewGUI.displayBoughtSuccessfulAndWouldLikeToBuyAgainButtonWindow(portfolioName);
    }
    else{
      visited = true;
      viewGUI.displayBoughtSuccessfulScreenForAnotherBoughtStock();
    }


    cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
  }

  @Override
  public void buyAnotherStockButton(String portfolioName) {
//    if (Objects.equals(options, "Y") || Objects.equals(options, "y")) {
//      performCreatePortfolioMenuAction("1", portfolioType, null, 0);
//    } else {
    //viewGUI.resetCreatePortfolioScreen();
    viewGUI.resetFlexiblePortfolioScreen();
    viewGUI.flexiblePortfolioScreenWithDateInput();
    

  }
  @Override
  public void checkHowManyShares(){
    viewGUI.errorNotNumber();
    viewGUI.resetHowManyShares();
  }
}
