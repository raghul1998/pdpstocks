package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Random;

import model.StockCompositionData;
import model.StockNameMap;

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
      case PORTFOLIO_INDIVIDUAL_LIST_WITH_DATE: {
        String opt = args[0];
        String date = args[1];
        showPortfolioIndividualWithDateScreen(opt, date);
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
        break;
      }
      case NOT_VALID_INPUT_SCREEN: {
        System.out.println("Not a valid input. Please enter the correct option.");
        break;
      }
      case NO_PORTFOLIO: {
        System.out.println("You dont have any portfolio.");
        break;
      }
      case PORTFOLIO_INVALID_ENTRY: {
        portfolioInvalidEntryScreen();
        break;
      }
      case PORTFOLIO_ALREADY_EXISTS: {
        System.out.println("This portfolio already exists.");
        break;
      }
      case PF_REENTER_DUPLICATE_NAME: {
        pfReenterDuplicateName();
        break;
      }
      case DATE_RENTER: {
        correctDateScreen();
        break;
      }
      default: {
        break;
      }
    }
  }

  private void correctDateScreen() {
    System.out.println("Not a valid input. Please enter the correct date.");
    System.out.println("Press 'b' to go back\n");
  }

  private void pfReenterDuplicateName() {
    System.out.println("If you want to override this portfolio, then press 'y'. "
            + "If you want to enter another name, press 'n'. If you want to go main screen, "
            + "press 'b'.\n");
  }

  private void portfolioInvalidEntryScreen() {
    System.out.println("Not a valid input. Please enter the correct portfolio.");
    System.out.println("Press 'b' to go back to the previous menu.\n");
  }

  private void showPortfolioReviewScreen() {
    System.out.println("Press 'b' to go back and 'm' for main menu.\n");
  }

  private void showPortfolioIndividualWithDateScreen(String option, String date) {
    int portfolioNumber = Integer.parseInt(option) - 1;
    StockCompositionData obj = new StockCompositionData();
    StockCompositionData.stockPortFolioData stkObj =
            obj.getAllStockDataInPortFolio(portfolioNumber);
    double totalPortFolioValue = 0;

    String pfCreatedDate = stkObj.createdTimeStamp;
    pfCreatedDate = pfCreatedDate.substring(0, 10);

    String[] portfolioNames = obj.getPortFolioNames();
    System.out.println("Value of " + portfolioNames[portfolioNumber].toUpperCase() + " PORTFOLIO");


    System.out.print("\nName");
    System.out.print(" (" + "Symbol" + ") ");
    System.out.print("\t " + "Quantity");
    System.out.print("\t " + "Share Value on " + date);
    System.out.println("\t " + "Total Value\n");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      System.out.print(stkObj.stockName[i]);
      System.out.print(" (" + stkObj.stockSymbol[i] + ") ");
      System.out.print("\t " + stkObj.stockQuantity[i]);
      // Display based on the date created
      if (pfCreatedDate.equals(date)) {
        System.out.print("\t $" + stkObj.valueOfSingleStock[i]);
        System.out.println("\t $" + stkObj.totalValue[i]);
      } else {
        double randomShareValue = getRandomShareValue(stkObj.valueOfSingleStock[i], date);
        System.out.print("\t $" + randomShareValue);
        totalPortFolioValue += Math.floor((randomShareValue * stkObj.stockQuantity[i]) * 100) / 100;
        System.out.println("\t $" + Math.floor((randomShareValue * stkObj.stockQuantity[i]) * 100) / 100);
      }
    }
    totalPortFolioValue = Math.floor(totalPortFolioValue * 100) / 100;
    System.out.println("\nTotal Portfolio Value is on " + date + ": $" + totalPortFolioValue);
  }

  private void showPortfolioIndividualScreen(String option) {
    int portfolioNumber = Integer.parseInt(option) - 1;
    StockCompositionData obj = new StockCompositionData();
    StockCompositionData.stockPortFolioData stkObj =
            obj.getAllStockDataInPortFolio(portfolioNumber);

    String[] portfolioNames = obj.getPortFolioNames();
    String date = stkObj.createdTimeStamp;
    date = date.substring(0, 10);
    System.out.println("\n" + portfolioNames[portfolioNumber].toUpperCase() + " PORTFOLIO"
            + " COMPOSITION - Created on " + date + "\n");

    System.out.print("Name");
    System.out.print(" (" + "Symbol" + ") \t ");
    System.out.print("Quantity \t ");
    System.out.print("Price of each share \t ");
    System.out.println("Total Value\n");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      System.out.print(stkObj.stockName[i]);
      System.out.print(" (" + stkObj.stockSymbol[i] + ") ");
      System.out.print("\t " + stkObj.stockQuantity[i]);
      System.out.print("\t $" + stkObj.valueOfSingleStock[i]);
      System.out.println("\t $" + stkObj.totalValue[i]);
    }

    System.out.println("\nTotal Portfolio Value as on " + date + ": $" + stkObj.totalPortFolioValue + "\n");
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
    System.out.println("\nHow many shares would you like to buy?");
    System.out.println("Press 'b' to go back to the previous menu, 'm' to main menu.\n");
  }

  private void showStockBuyInvalidRetryScreen() {
    System.out.println("Not a valid input. Please enter number of shares as whole number.");
    System.out.println("Press 'b' to go back to the previous menu, 'm' to main menu.\n");
  }

  private void wouldYouLikeToBuyAnotherStockScreen() {
    System.out.println("Would you like to buy another stock? (Y|N)");
  }

  private void showStockDataScreen() {
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
    } catch (Exception e) {
      System.out.println("Error in reading Supported stocks csv file.");
    }

    System.out.println("\nCURRENT STOCK PRICE");
    System.out.println("StockName: " + splitStockData[0]);
    System.out.println("Symbol: " + splitStockData[2]);
    System.out.println("Time: " + splitStockData[3]);
    System.out.println("Price: $" + splitStockData[1]);
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
    System.out.println("e. Exit\n");
    System.out.println("ENTER YOUR CHOICE: ");
  }

  private void showStockMainScreen(String name) {
    System.out.println("\nCREATE PORTFOLIO MENU\n");
    System.out.println(name.toUpperCase() + " Portfolio\n");
    System.out.println("1. Buy a share");
    System.out.println("2. Main Menu");
    System.out.println("e. Exit\n");
    System.out.println("ENTER YOUR CHOICE: ");
  }

  private void showPortfolioName() {
    System.out.println("Enter the name for this portfolio.");
  }

  private void showStockBuyReenter() {
    System.out.println("Not a valid input. Please enter the correct stock.");
    System.out.println("If you want to go back to main menu, press '0'.\n");
  }

  private void showPortfolioNameReenter() {
    System.out.println("Cannot create a portfolio with empty name. Enter a valid name.");
    System.out.println("If you want to go back to main menu, press '0'.\n");
  }

  private double getRandomShareValue(double stockValue, String date) {
    String[] dateSplit = date.split("-");
    StringBuilder dateSplitIndividual = new StringBuilder();
    for (String s : dateSplit) {
      dateSplitIndividual.append(s);
    }
    int dateLong = Integer.parseInt(dateSplitIndividual.toString());
    int decimalValue = sumOfNumber(dateLong);
    Random rand = new Random(dateLong);
    int randomShareValueInteger = (int) (rand.nextInt((int) ((stockValue + 30) -
            (stockValue - 10))) + stockValue - 10);
    String randomShareValueString = "" + randomShareValueInteger + "." + decimalValue;
    double value = Double.parseDouble(randomShareValueString);
    return Math.floor(value * 100) / 100;
  }

  private int sumOfNumber(int number) {
    int sum;
    for (sum = 0; number != 0; number = number / 10) {
      sum = sum + number % 10;
    }
    return sum;
  }

}
