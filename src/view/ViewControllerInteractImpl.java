package view;

import java.util.Scanner;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

public class ViewControllerInteractImpl implements ViewControllerInteract {
  private final ControllerViewInteract controllerViewInteractObj = new ControllerViewInteractImpl();

  @Override
  public void viewControllerInteract(TypeofViews type) {
    switch (type) {
      case MAIN: {
        mainScreen();
        break;
      }
      case CREATE_PORTFOLIO: {
        createStockMainScreen();
        break;
      }
      default: {
        break;
      }
    }
  }

  private void mainScreen() {
    char option;
    Scanner scan = new Scanner(System.in);
    do {
      System.out.println("\n\nWelcome to Stock Manager\n");
      System.out.println("MENU\n");
      System.out.println("1. Create a portfolio");
      System.out.println("2. View portfolio");
      System.out.println("3. Value of portfolio");
      System.out.println("4. Buy stocks");
      System.out.println("5. View stocks");
      System.out.println("e. Exit\n");
      System.out.println("ENTER YOUR CHOICE: ");
      option = scan.next().charAt(0);
      controllerViewInteractObj.controllerViewInteract(this, option, TypeofViews.MAIN, null, 0);
    } while (option != 4);
  }

  private void createStockMainScreen() {
    char option;
    Scanner scan = new Scanner(System.in);
    String name;
    while (true) {
      System.out.println("\n\nCREATE PORTFOLIO MENU\n");
      name = createPortfolioName();
      if (name.equals("0")) {
        return;
      }
      System.out.println(name.toUpperCase() + " Portfolio\n");
      System.out.println("1. Buy a share");
      System.out.println("2. Add already bought shares");
      System.out.println("3. Back");
      System.out.println("4. Main Menu");
      System.out.println("e. Exit\n");
      System.out.println("ENTER YOUR CHOICE: ");
      option = scan.next().charAt(0);
      String[] args = new String[1];
      args[0] = name;
      controllerViewInteractObj.controllerViewInteract(this, option, TypeofViews.CREATE_PORTFOLIO, args, args.length);
    }
  }

  private String createPortfolioName() {
    String name;
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the name for this portfolio");
    name = scan.nextLine();
    while ((name == null || name.length() == 0) && (!name.equals("0"))) {
      System.out.println("Cannot create a portfolio with empty name. Enter a valid name.");
      System.out.println("If you want to go back to main menu, press 0\n");
      name = scan.nextLine();
    }
    return name;
  }


  public static void main(String[] args) {
    ViewControllerInteract obj = new ViewControllerInteractImpl();
    obj.viewControllerInteract(TypeofViews.MAIN);
  }
}
