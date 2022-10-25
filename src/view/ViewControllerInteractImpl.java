package view;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;
import supportedstocks.StockNameMap;
import supportedstocks.SupportedStocks;

public class ViewControllerInteractImpl implements ViewControllerInteract {
  private final ControllerViewInteract controllerViewInteractObj = new ControllerViewInteractImpl();
  private String currentPortfolioName;

  @Override
  public void viewControllerInteract(TypeofViews type, String[] args, int length) {
    switch (type) {
      case MAIN: {
        mainScreen();
        break;
      }
      case CREATE_PORTFOLIO_NAME_SCREEN: {
        showPortfolioName();
        break;
      }
      case CREATE_PORTFOLIO: {
        showStockMainScreen();
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
      case BUY_STOCKS_VALUE: {
        showBuyStockValueScreen();
        break;
      }
      case BUY_ANOTHER_STOCK: {
        wouldYouLikeToBuyAnotherStockScreen();
        break;
      }
      case DISPLAY_PORTFOLIO_CREATED: {
        showDisplayPortFolioCreated();
        break;
      }
      case PORTFOLIO_COMPOSITION: {
        showPortFolioCompositionScreen();
        break;
      }
      case PORTFOLIO_INDIVIDUAL_LIST: {
        String opt = args[0];
        showPortfolioIndividualScreen(opt);
        break;
      }
      default: {
        break;
      }
    }
  }

  private void showPortfolioIndividualScreen(String option) {
    int portfolioNumber = Integer.parseInt(option) - 1;
    StockCompositionData obj = new StockCompositionData();
    StockCompositionData.stockPortFolioData stkObj =
            obj.getAllStockDataInPortFolio(portfolioNumber);

    String[] portfolioNames = obj.getPortFolioNames();
    System.out.println(portfolioNames[portfolioNumber].toUpperCase() + "PORTFOLIO");

    System.out.print("\nName");
    System.out.print(" (" + "Symbol" + ") ");
    System.out.print("\t " + "Quantity");
    System.out.println("\t " + "Value");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      System.out.print(stkObj.stockName[i]);
      System.out.print(" (" + stkObj.stockSymbol[i] + ") ");
      System.out.print("\t " + stkObj.stockQuantity[i]);
      System.out.println("\t " + stkObj.totalValue[i]);
    }
  }

  private void showPortFolioCompositionScreen() {
    System.out.println("\nLIST OF PORTFOLIO");
    StockCompositionData obj = new StockCompositionData();
    int numberOfPortFolio = obj.getNumberOfPortFolio();
    if (numberOfPortFolio == 0) {
      System.out.println("You dont have any portfolio.");
      return;
    }

    String[] portfolioNames = obj.getPortFolioNames();
    System.out.println();
    for (int i = 0; i < numberOfPortFolio; i++) {
      System.out.println(i + 1 + ". " + portfolioNames[i].toUpperCase());
    }

    System.out.println("\nWhich portfolio would you like to check?");
    String option;
    Scanner scan = new Scanner(System.in);
    option = scan.nextLine();
    while ((option == null || option.length() == 0) ||
            (!validatePortfolioSelectOption(option, numberOfPortFolio))) {
      System.out.println("Not a valid input. Please enter the correct portfolio");
      System.out.println("Press 'b' to go back to the previous menu\n");
      option = scan.nextLine();
      if (Objects.equals(option, "b")) {
        return;
      }
    }
    controllerViewInteractObj.controllerViewInteract(this, option,
            TypeofViews.PORTFOLIO_COMPOSITION, null, 0);
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

  private void showDisplayPortFolioCreated() {
    System.out.println("\n" + currentPortfolioName.toUpperCase() + " PORTFOLIO CREATED...!!!");
  }

  private void showBuyStockValueScreen() {
    System.out.println("How many stocks would you like to buy?");
    System.out.println("Press 'b' to go back to the previous menu, 'm' to main menu");
    String option;
    Scanner scan = new Scanner(System.in);
    option = scan.nextLine();
    while ((option == null || option.length() == 0) || (!validateBuyStockOption(option))) {
      System.out.println("Not a valid input. Please enter the correct stock");
      System.out.println("Press 'b' to go back to the previous menu, 'm' to main menu\n");
      option = scan.nextLine();
      if (Objects.equals(option, "b")) {
        return;
      }
    }
    controllerViewInteractObj.controllerViewInteract(this, option,
            TypeofViews.BUY_STOCKS_VALUE, null, 0);
  }

  private void wouldYouLikeToBuyAnotherStockScreen() {
    System.out.println("Would you like to buy another stock? (Y|N)");
    String option;
    Scanner scan = new Scanner(System.in);
    option = scan.nextLine();
    while ((option == null || option.length() == 0) || ((!option.equals("Y")) &&
            (!option.equals("y")) && (!option.equals("N")) && (!option.equals("n")))) {
      System.out.println("Not a valid input. Please enter the correct option\n");
      option = scan.nextLine();
      if (Objects.equals(option, "b")) {
        return;
      }
    }
    controllerViewInteractObj.controllerViewInteract(this, option,
            TypeofViews.BUY_ANOTHER_STOCK, null, 0);
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
    // Else pass the input to the controller
    controllerViewInteractObj.controllerViewInteract(this, option,
            TypeofViews.LIST_OF_STOCKS, stockSymbolIndexArray, stockSymbolIndexArray.length);
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

  private void mainScreen() {
    char option;
    Scanner scan = new Scanner(System.in);
    do {
      System.out.println("\nMENU\n");
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

  private void showStockMainScreen() {
    char option;
    Scanner scan = new Scanner(System.in);

    System.out.println("\n\nCREATE PORTFOLIO MENU\n");

    System.out.println(currentPortfolioName.toUpperCase() + " Portfolio\n");
    System.out.println("1. Buy a share");
    System.out.println("2. Add already bought shares");
    System.out.println("3. Main Menu");
    System.out.println("e. Exit\n");
    System.out.println("ENTER YOUR CHOICE: ");
    option = scan.next().charAt(0);
    controllerViewInteractObj.controllerViewInteract(this,
            String.valueOf(option), TypeofViews.CREATE_PORTFOLIO, null, 0);

  }

  private void showPortfolioName() {
    String name;
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the name for this portfolio");
    name = scan.nextLine();
    while (name.length() == 0) {
      System.out.println("Cannot create a portfolio with empty name. Enter a valid name.");
      System.out.println("If you want to go back to main menu, press '0'\n");
      name = scan.nextLine();
      if (name.equals("0")) {
        controllerViewInteractObj.controllerViewInteract(this,
                name, TypeofViews.CREATE_PORTFOLIO_NAME_SCREEN, null, 0);
      }
    }
    String[] args = new String[1];
    args[0] = name;
    currentPortfolioName = name;
    controllerViewInteractObj.controllerViewInteract(this,
            null, TypeofViews.CREATE_PORTFOLIO_NAME_SCREEN, args, 0);
  }


  public static void main(String[] args) {
    ViewControllerInteract obj = new ViewControllerInteractImpl();
    obj.viewControllerInteract(TypeofViews.MAIN, null, 0);
  }
}
