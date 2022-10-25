package controller;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import model.ModelControllerInteract;
import model.TypeofAction;
import supportedstocks.StockNameMap;
import supportedstocks.SupportedStocks;
import view.StockCompositionData;
import view.ViewControllerInteract;
import view.ViewControllerInteractImpl;
import view.TypeofViews;

import static java.lang.System.exit;

public class ControllerViewInteractImpl implements ControllerViewInteract {
  ControllerModelInteract cmiObj = new ControllerModelInteractImpl();
  ViewControllerInteract vciObj = new ViewControllerInteractImpl();
  private String currentPortfolioName;

  @Override
  public void controllerViewInteract(ViewControllerInteractImpl vciObj, String options,
                                     TypeofViews type, String[] args, int length) {
    switch (type) {
      case BUY_STOCKS_MAIN: {
        System.out.println("abc");
        break;
      }
      default: {
        break;
      }
    }
  }

  @Override
  public void start() {
    char option;
    Scanner scan = new Scanner(System.in);
    do {
      vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);
      option = scan.next().charAt(0);
      performMainMenuAction(String.valueOf(option));
    } while (option != 'e');
  }

  private void portfolioCompositionAction(String options) {
    String[] args = new String[1];
    args[0] = options;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST, args, 1);
  }

  private void createPortfolioNameScreenAction(String options, String[] args) {
    if (options == null) {
      cmiObj.controllerModelInteract(TypeofAction.CREATE_PORTFOLIO, args, 1);
      String[] name = new String[1];
      name[0] = currentPortfolioName;
      vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO, name, 0);
      char option;
      Scanner scan = new Scanner(System.in);
      option = scan.next().charAt(0);
      performCreatePortfolioMenuAction(String.valueOf(option), null);
    }
  }

  private void buyAnotherStockMenuAction(String options) {
    if (Objects.equals(options, "Y") || Objects.equals(options, "y")) {
      vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
    } else {
      String[] args = new String[1];
      args[0] = currentPortfolioName;
      vciObj.viewControllerInteract(TypeofViews.DISPLAY_PORTFOLIO_CREATED, args, 0);
    }
  }

  private void buyStockValueMenuAction(String option) {
    if (Objects.equals(option, "m")) {
      vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);
    }
    String[] stock = new String[1];
    stock[0] = option;
    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 1);
    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK, null, 0);
    String options;
    Scanner scan = new Scanner(System.in);
    options = scan.nextLine();
    while ((options == null || options.length() == 0) || ((!options.equals("Y")) &&
            (!options.equals("y")) && (!options.equals("N")) && (!options.equals("n")))) {
      System.out.println("Not a valid input. Please enter the correct option\n");
      options = scan.nextLine();
      if (Objects.equals(options, "b")) {
        return;
      }
    }
    buyAnotherStockMenuAction(options);
  }

  private void performListOfStocksMenuAction(String options, String[] args, int length) {

    if (Objects.equals(options, "0")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return;
    }
    StockNameMap snp = new StockNameMap();
    Map<String, String> map = snp.getMap();
    String[] stockSymbolIndexArray = new String[snp.getMapSize()];
    String[] stock = new String[1];
    int index = 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      stockSymbolIndexArray[index++] = entry.getKey();
    }
    stock[0] = stockSymbolIndexArray[Integer.parseInt(options) - 1];
    cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stock, 1);
    vciObj.viewControllerInteract(TypeofViews.SHOW_STOCK_DATA, null, 0);
    vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_VALUE, null, 0);
    String option;
    Scanner scan = new Scanner(System.in);
    option = scan.nextLine();
    while ((option == null || option.length() == 0) || (!validateBuyStockOption(option))) {
      vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_INVALID_RETRY, null, 0);
      option = scan.nextLine();
      if (Objects.equals(option, "b")) {
        return;
      }
    }
    buyStockValueMenuAction(option);
  }

  private void performMainMenuAction(String option) {
    switch (option) {
      case "1": {
        // Create a portfolio
        String name;
        Scanner scan = new Scanner(System.in);
        vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO_NAME_SCREEN, null, 0);
        name = scan.nextLine();
        while (name.length() == 0) {
          System.out.println("Cannot create a portfolio with empty name. Enter a valid name.");
          System.out.println("If you want to go back to main menu, press '0'\n");
          name = scan.nextLine();
          if (name.equals("0")) {
            return;
          }
        }
        String[] args = new String[1];
        args[0] = name;
        currentPortfolioName = name;
        createPortfolioNameScreenAction(option, args);
        break;
      }
      case "2": {
        // Composition of the portfolio
        StockCompositionData obj = new StockCompositionData();
        int numberOfPortFolio = obj.getNumberOfPortFolio();
        if (numberOfPortFolio == 0) {
          System.out.println("You dont have any portfolio.");
          return;
        }
        String[] portfolioNames = obj.getPortFolioNames();
        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, portfolioNames, numberOfPortFolio);
        String options;
        Scanner scan = new Scanner(System.in);
        options = scan.nextLine();
        while ((options == null || options.length() == 0) ||
                (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
          System.out.println("Not a valid input. Please enter the correct portfolio");
          System.out.println("Press 'b' to go back to the previous menu\n");
          options = scan.nextLine();
          if (Objects.equals(options, "b")) {
            return;
          }
        }
        portfolioCompositionAction(options);
        break;
      }
      case "3": {
        System.out.println("Value of the portfolio");
        break;
      }
      case "4": {
        //Buy a stock
        vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
        //cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, null, 0);
        break;
      }
      case "5": {
        System.out.println("View stock");
        break;
      }
      case "e":
      case "E": {
        System.out.println("\nExiting...");
        exit(0);
      }
      default: {
        System.out.println("Invalid command");
        break;
      }
    }
  }

  private boolean validatePortfolioSelectOption(String option, int numberOfPortFolio) {
    // The selected option should be a number, and it should be a valid digit.
    int val;
    try {
      val = Integer.parseInt(option);
    } catch (Exception e) {
      return false;
    }

    if (val < 1 || val > numberOfPortFolio) {
      return false;
    } else {
      return true;
    }
  }

  private void performCreatePortfolioMenuAction(String option, String[] args) {
    switch (option) {
      case "1": {
        //Buy a new stock
        vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
        String options;
        Scanner scan = new Scanner(System.in);
        options = scan.nextLine();
        while ((options == null || options.length() == 0) || (!validateStockSelectOption(options))) {
          vciObj.viewControllerInteract(TypeofViews.STOCK_BUY_REENTER, null, 0);
          options = scan.nextLine();
        }
        performListOfStocksMenuAction(option, null, 0);
        break;
      }
      case "2": {
        System.out.println("Add exiting stock");
        break;
      }
      case "3": {
        // Go to Main menu
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);
        break;
      }
      case "e":
      case "E": {
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        System.out.println("\nExiting...");
        exit(0);
      }
      default: {
        System.out.println("Invalid command");
        break;
      }
    }
  }

  private boolean validateStockSelectOption(String option) {
    if (option == null || option.length() == 0) {
      return false;
    }

    if (option.equals("0")) {
      return true;
    }
    // The selected option should be a number, and it should be a valid digit.
    int val;
    try {
      val = Integer.parseInt(option);
    } catch (Exception e) {
      return false;
    }

    if (val < 0 || val > SupportedStocks.values().length) {
      return false;
    } else {
      return true;
    }
  }

  private boolean validateBuyStockOption(String option) {
    if (option == null || option.length() == 0) {
      return false;
    }

    if (option.equals("m")) {
      return true;
    }

    // The selected option should be a number, and it should be a valid digit.
    int val;
    try {
      val = Integer.parseInt(option);
    } catch (Exception e) {
      return false;
    }

    if (val < 0) {
      return false;
    } else {
      return true;
    }
  }
}
