package controller;

import model.TypeofAction;
import view.ViewControllerInteract;
import view.ViewControllerInteractImpl;
import view.TypeofViews;

import static java.lang.System.exit;

public class ControllerViewInteractImpl implements ControllerViewInteract {
  ControllerModelInteract cmiObj = new ControllerModelInteractImpl();

  private void performMainMenuAction(ViewControllerInteractImpl viewObj, char option) {
    switch (option) {
      case '1': {
        // Create a portfolio
        viewObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO);
        break;
      }
      case '2': {
        System.out.println("Composition of the portfolio");
        break;
      }
      case '3': {
        System.out.println("Value of the portfolio");
        break;
      }
      case '4': {
        //Buy a stock
        cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, null, 0);
        break;
      }
      case '5': {
        System.out.println("View stock");
        break;
      }
      case 'e':
      case 'E': {
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
                                                char option, String name) {
    switch (option) {
      case '1': {
        System.out.println("Buy a new stock");
        break;
      }
      case '2': {
        System.out.println("Add exiting stock");
        break;
      }
      case '3': {
        System.out.println("Back");
        break;
      }
      case '4': {
        // Go to Main menu
        viewObj.viewControllerInteract(TypeofViews.MAIN);
        break;
      }
      case 'e':
      case 'E': {
        System.out.println("\nExiting...");
        exit(0);
      }
      default: {
        System.out.println("Invalid command");
        break;
      }
    }
  }

  @Override
  public void controllerViewInteract(ViewControllerInteractImpl vciObj, char options,
                                     TypeofViews type, String[] args, int length) {
    switch (type) {
      case MAIN: {
        performMainMenuAction(vciObj, options);
        break;
      }
      case CREATE_PORTFOLIO: {
        String name = args[0];
        performCreatePortfolioMenuAction(vciObj, options, name);
        break;
      }
      case BUY_STOCKS: {
        System.out.println("abc");
        break;
      }
      default: {
        break;
      }
    }
  }
}
