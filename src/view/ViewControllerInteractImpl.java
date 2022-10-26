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
        showStockMainScreen(args[0]);
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
        showDisplayPortFolioCreated(args[0]);
        break;
      }
      case PORTFOLIO_COMPOSITION: {
        showPortFolioCompositionScreen(args, length);
        break;
      }
      case PORTFOLIO_INDIVIDUAL_LIST: {
        String opt = args[0];
        showPortfolioIndividualScreen(opt);
        break;
      }
      case PORTFOLIO_NAME_REENTER: {
        showPortfolioNameReenter();
        break;
      }
      case STOCK_BUY_REENTER: {
        showStockBuyReenter();
        break;
      }
      case BUY_STOCKS_INVALID_RETRY: {
        showStockBuyInvalidRetryScreen();
        break;
      }
      case REVIEW_STOCK: {
        showPortfolioReviewScreen();
      }
      default: {
        break;
      }
    }
  }

  private void showPortfolioReviewScreen() {
    System.out.println("\nPress 'b' to go back and 'm' for main menu\n");
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
    System.out.println("\t " + "Value\n");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      System.out.print(stkObj.stockName[i]);
      System.out.print(" (" + stkObj.stockSymbol[i] + ") ");
      System.out.print("\t " + stkObj.stockQuantity[i]);
      System.out.println("\t " + stkObj.totalValue[i]);
    }

    System.out.println("\nTotal Portfolio Value is: " + stkObj.totalPortFolioValue);
  }

  private void showPortFolioCompositionScreen(String[] portfolioNames, int numberOfPortFolio) {
    System.out.println("\nLIST OF PORTFOLIO");
    System.out.println();
    for (int i = 0; i < numberOfPortFolio; i++) {
      System.out.println(i + 1 + ". " + portfolioNames[i].toUpperCase());
    }

    System.out.println("\nWhich portfolio would you like to check?");
  }


  private void showDisplayPortFolioCreated(String currentPortfolioName) {
    System.out.println("\n" + currentPortfolioName.toUpperCase() + " PORTFOLIO CREATED...!!!");
  }

  private void showBuyStockValueScreen() {
    System.out.println("\nHow many stocks would you like to buy?");
    System.out.println("Press 'b' to go back to the previous menu, 'm' to main menu");
  }

  private void showStockBuyInvalidRetryScreen() {
    System.out.println("Not a valid input. Please enter the correct stock");
    System.out.println("Press 'b' to go back to the previous menu, 'm' to main menu\n");
  }

  private void wouldYouLikeToBuyAnotherStockScreen() {
    System.out.println("Would you like to buy another stock? (Y|N)");
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

    System.out.println("\nCURRENT STOCK PRICE");
    System.out.println("StockName: " + stk.get("StockName"));
    System.out.println("Symbol: " + stk.get("StockSymbol"));
    System.out.println("Time: " + stk.get("Timestamp"));
    System.out.println("Price: " + stk.get("Price"));
  }

  private void listOfSupportedStocksScreen() {
    int index = 0;
    StockNameMap snp = new StockNameMap();
    Map<String, String> map = snp.getMap();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.print(++index + ". ");
      System.out.print(entry.getValue());
      System.out.println(" (" + entry.getKey() + ")");
    }
    System.out.println("\nWhich stock would you like to buy?");
  }

  private void mainScreen() {
    System.out.println("\nMENU\n");
    System.out.println("1. Create a portfolio");
    System.out.println("2. View portfolio");
    System.out.println("3. Value of portfolio");
    System.out.println("4. Buy stocks");
    System.out.println("5. View stocks");
    System.out.println("e. Exit\n");
    System.out.println("ENTER YOUR CHOICE: ");
  }

  private void showStockMainScreen(String name) {
    System.out.println("\nCREATE PORTFOLIO MENU\n");
    System.out.println(name.toUpperCase() + " Portfolio\n");
    System.out.println("1. Buy a share");
    System.out.println("2. Add already bought shares");
    System.out.println("3. Main Menu");
    System.out.println("e. Exit\n");
    System.out.println("ENTER YOUR CHOICE: ");
  }

  private void showPortfolioName() {
    System.out.println("Enter the name for this portfolio");
  }

  private void showStockBuyReenter() {
    System.out.println("Not a valid input. Please enter the correct stock");
    System.out.println("If you want to go back to main menu, press '0'\n");
  }

  private void showPortfolioNameReenter() {
    System.out.println("Cannot create a portfolio with empty name. Enter a valid name.");
    System.out.println("If you want to go back to main menu, press '0'\n");
  }
}
