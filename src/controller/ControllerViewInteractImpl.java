package controller;

import java.util.Objects;

import model.TypeofAction;
import view.ViewControllerInteractImpl;
import view.TypeofViews;

import static java.lang.System.exit;

public class ControllerViewInteractImpl implements ControllerViewInteract {
  ControllerModelInteract cmiObj = new ControllerModelInteractImpl();

  @Override
  public void controllerViewInteract(ViewControllerInteractImpl vciObj, String options,
                                     TypeofViews type, String[] args, int length) {
    switch (type) {
      case MAIN: {
        performMainMenuAction(vciObj, options);
        break;
      }
      case CREATE_PORTFOLIO_NAME_SCREEN: {
        createPortfolioNameScreenAction(vciObj, options, args);
        break;
      }
      case CREATE_PORTFOLIO: {
        performCreatePortfolioMenuAction(vciObj, options, args);
        break;
      }
      case BUY_STOCKS_MAIN: {
        System.out.println("abc");
        break;
      }
      case LIST_OF_STOCKS: {
        performListOfStocksMenuAction(vciObj, options, args, length);
        break;
      }
      case BUY_STOCKS_VALUE: {
        buyStockValueMenuAction(vciObj, options);
        break;
      }
      case BUY_ANOTHER_STOCK: {
        buyAnotherStockMenuAction(vciObj, options);
        break;
      }
      default: {
        break;
      }
    }
  }

  private void createPortfolioNameScreenAction(ViewControllerInteractImpl vciObj,
                                               String options, String[] args) {
    if (options == null) {
      cmiObj.controllerModelInteract(TypeofAction.CREATE_PORTFOLIO, args, 1);
      vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO);
    }
  }

  private void buyAnotherStockMenuAction(ViewControllerInteractImpl vciObj, String options) {
    if (Objects.equals(options, "Y") || Objects.equals(options, "y")) {
      vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS);
    } else {
      vciObj.viewControllerInteract(TypeofViews.MAIN);
    }
  }

  private void buyStockValueMenuAction(ViewControllerInteractImpl vciObj, String option) {
    if (Objects.equals(option, "m")) {
      vciObj.viewControllerInteract(TypeofViews.MAIN);
    }
    String[] stock = new String[1];
    stock[0] = option;
    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 1);
    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK);
  }

  private void performListOfStocksMenuAction(ViewControllerInteractImpl vciObj, String options,
                                             String[] args, int length) {

    if (Objects.equals(options, "0")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return;
    }
    String[] stock = new String[1];
    stock[0] = args[Integer.parseInt(options) - 1];
    cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stock, 1);
    vciObj.viewControllerInteract(TypeofViews.SHOW_STOCK_DATA);
    vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_VALUE);
  }

  private void performMainMenuAction(ViewControllerInteractImpl viewObj, String option) {
    switch (option) {
      case "1": {
        // Create a portfolio
        viewObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO_NAME_SCREEN);
        break;
      }
      case "2": {
        System.out.println("Composition of the portfolio");
        break;
      }
      case "3": {
        System.out.println("Value of the portfolio");
        break;
      }
      case "4": {
        //Buy a stock
        viewObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS);
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

  private void performCreatePortfolioMenuAction(ViewControllerInteractImpl viewObj,
                                                String option, String[] args) {
    switch (option) {
      case "1": {
        //Buy a new stock
        viewObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS);
        break;
      }
      case "2": {
        System.out.println("Add exiting stock");
        break;
      }
      case "3": {
        // Go to Main menu
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        viewObj.viewControllerInteract(TypeofViews.MAIN);
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

}
