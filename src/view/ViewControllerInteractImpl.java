package view;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;
import supportedstocks.StockNameMap;
import supportedstocks.SupportedStocks;

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
      case LIST_OF_STOCKS: {
        listOfSupportedStocksScreen();
        break;
      }
      case SHOW_STOCK_DATA: {
        showStockDataScreen();
        break;
      }
      default: {
        break;
      }
    }
  }

  private void showStockDataScreen() {
    Object obj;
    try {
      obj = new JSONParser().parse(new FileReader("./data/StockData.json"));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObj = (JSONObject) obj;
    JSONArray jsonArray = (JSONArray) jsonObj.get("AllStockData");
    JSONObject stk = (JSONObject) jsonArray.get(0);

    System.out.println("StockName: " + stk.get("StockName"));
    System.out.println("Symbol: " + stk.get("StockSymbol"));
    System.out.println("Time: " + stk.get("Timestamp"));
    System.out.println("Price: " + stk.get("Price"));
  }

  private void listOfSupportedStocksScreen() {
    String option;
    Scanner scan = new Scanner(System.in);
    int index = 0;
    StockNameMap snp = new StockNameMap();
    Map<String, String> map = snp.getMap();
    String[] stockSymbolIndexArray = new String[snp.getMapSize()];
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.print(++index + ". ");
      System.out.print(entry.getValue());
      System.out.println(" (" + entry.getKey() + ")");
      stockSymbolIndexArray[index - 1] = entry.getKey();
    }

    System.out.println("Which stock would you like to buy?");
    option = scan.nextLine();

    while ((option == null || option.length() == 0) || (!validateStockSelectOption(option))) {
      System.out.println("Not a valid input. Please enter the correct stock");
      System.out.println("If you want to go back to main menu, press '0'\n");
      option = scan.nextLine();
    }
    // If entered main menu, return.
    if (option.equals("0")) {
      return;
    }
    // Else pass the input to the controller
    controllerViewInteractObj.controllerViewInteract(this, option,
            TypeofViews.LIST_OF_STOCKS, stockSymbolIndexArray, stockSymbolIndexArray.length);
  }

  private boolean validateStockSelectOption(String option) {
    if (option == null || option.length() == 0) {
      return false;
    }
    // The selected option should be a number, and it should be a valid digit.
    int val;
    try {
      val = Integer.parseInt(option);
    } catch (NumberFormatException e) {
      return false;
    }

    if (val < 0 || val > SupportedStocks.values().length) {
      return false;
    } else {
      return true;
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
      controllerViewInteractObj.controllerViewInteract(this, String.valueOf(option),
              TypeofViews.MAIN, null, 0);
    } while (option != 'e');
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
      controllerViewInteractObj.controllerViewInteract(this,
              String.valueOf(option), TypeofViews.CREATE_PORTFOLIO, args, args.length);
    }
  }

  private String createPortfolioName() {
    String name;
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the name for this portfolio");
    name = scan.nextLine();
    while (name.length() == 0) {
      System.out.println("Cannot create a portfolio with empty name. Enter a valid name.");
      System.out.println("If you want to go back to main menu, press '0'\n");
      name = scan.nextLine();
    }
    return name;
  }


  public static void main(String[] args) {
    ViewControllerInteract obj = new ViewControllerInteractImpl();
    obj.viewControllerInteract(TypeofViews.MAIN);
  }
}
