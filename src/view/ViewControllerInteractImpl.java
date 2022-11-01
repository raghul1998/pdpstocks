package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Random;

import model.StockCompositionData;
import model.StockNameMap;

public class ViewControllerInteractImpl implements ViewControllerInteract {
  private final PrintStream output;

  public ViewControllerInteractImpl(PrintStream out) {
    this.output = out;
  }

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
        output.println("Not a valid input. Please enter the correct option.");
        break;
      }
      case NO_PORTFOLIO: {
        output.println("You dont have any portfolio.");
        break;
      }
      case PORTFOLIO_INVALID_ENTRY: {
        portfolioInvalidEntryScreen();
        break;
      }
      case PORTFOLIO_ALREADY_EXISTS: {
        output.println("This portfolio already exists.");
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
    output.println("Not a valid input. Please enter the correct date.");
    output.println("Press 'b' to go back\n");
  }

  private void pfReenterDuplicateName() {
    output.println("If you want to override this portfolio, then press 'y'. "
            + "If you want to enter another name, press 'n'. If you want to go main screen, "
            + "press 'b'.\n");
  }

  private void portfolioInvalidEntryScreen() {
    output.println("Not a valid input. Please enter the correct portfolio.");
    output.println("Press 'b' to go back to the previous menu.\n");
  }

  private void showPortfolioReviewScreen() {
    output.println("Press 'b' to go back and 'm' for main menu.\n");
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
    output.println("Value of " + portfolioNames[portfolioNumber].toUpperCase() + " PORTFOLIO");


    output.print("\nName");
    output.print(" (" + "Symbol" + ") ");
    output.print("\t " + "Quantity");
    output.print("\t " + "Share Value on " + date);
    output.println("\t " + "Total Value\n");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      output.print(stkObj.stockName[i]);
      output.print(" (" + stkObj.stockSymbol[i] + ") ");
      output.print("\t " + stkObj.stockQuantity[i]);
      // Display based on the date created
      if (pfCreatedDate.equals(date)) {
        output.print("\t $" + stkObj.valueOfSingleStock[i]);
        output.println("\t $" + stkObj.totalValue[i]);
        totalPortFolioValue = stkObj.totalPortFolioValue;
      } else {
        double randomShareValue = getRandomShareValue(stkObj.valueOfSingleStock[i], date);
        output.print("\t $" + randomShareValue);
        totalPortFolioValue += Math.floor((randomShareValue * stkObj.stockQuantity[i]) * 100) / 100;
        output.println("\t $" + Math.floor((randomShareValue * stkObj.stockQuantity[i]) * 100) / 100);
      }
    }
    totalPortFolioValue = Math.floor(totalPortFolioValue * 100) / 100;
    output.println("\nTotal Portfolio Value is on " + date + ": $" + totalPortFolioValue + "\n");
  }

  private void showPortfolioIndividualScreen(String option) {
    int portfolioNumber = Integer.parseInt(option) - 1;
    StockCompositionData obj = new StockCompositionData();
    StockCompositionData.stockPortFolioData stkObj =
            obj.getAllStockDataInPortFolio(portfolioNumber);

    String[] portfolioNames = obj.getPortFolioNames();
    String date = stkObj.createdTimeStamp;
    date = date.substring(0, 10);
    output.println("\n" + portfolioNames[portfolioNumber].toUpperCase() + " PORTFOLIO"
            + " COMPOSITION - Created on " + date + "\n");

    output.print("Name");
    output.print(" (" + "Symbol" + ") \t ");
    output.print("Quantity \t ");
    output.print("Price of each share \t ");
    output.println("Total Value\n");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      output.print(stkObj.stockName[i]);
      output.print(" (" + stkObj.stockSymbol[i] + ") ");
      output.print("\t " + stkObj.stockQuantity[i]);
      output.print("\t $" + stkObj.valueOfSingleStock[i]);
      output.println("\t $" + stkObj.totalValue[i]);
    }

    output.println("\nTotal Portfolio Value as on " + date + ": $" + stkObj.totalPortFolioValue + "\n");
  }

  private void showPortFolioCompositionScreen(String[] portfolioNames, int numberOfPortFolio) {
    output.println("\nLIST OF PORTFOLIO");
    output.println();
    for (int i = 0; i < numberOfPortFolio; i++) {
      output.println(i + 1 + ". " + portfolioNames[i].toUpperCase());
    }

    output.println("\nWhich portfolio would you like to check?");
  }


  private void showDisplayPortFolioCreated(String currentPortfolioName) {
    output.println("\n" + currentPortfolioName.toUpperCase() + " PORTFOLIO CREATED...!!!");
  }

  private void showBuyStockValueScreen() {
    output.println("\nHow many shares would you like to buy?");
    output.println("Press 'b' to go back to the previous menu, 'm' to main menu.\n");
  }

  private void showStockBuyInvalidRetryScreen() {
    output.println("Not a valid input. Please enter number of shares as natural numbers.");
    output.println("Press 'b' to go back to the previous menu, 'm' to main menu.\n");
  }

  private void wouldYouLikeToBuyAnotherStockScreen() {
    output.println("Would you like to buy another stock? (Y|N)");
  }

  private void showStockDataScreen() {
    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      output.println("Supported stocks file not found " + e.getMessage());
    }

    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      output.println("Error in reading Supported stocks csv file.");
    }

    output.println("\nCURRENT STOCK PRICE");
    output.println("StockName: " + splitStockData[0]);
    output.println("Symbol: " + splitStockData[2]);
    output.println("Time: " + splitStockData[3]);
    output.println("Price: $" + splitStockData[1]);
  }

  private void listOfSupportedStocksScreen() {
    int index = 0;
    StockNameMap snp = new StockNameMap();
    Map<String, String> map = snp.getMap();
    output.println("\n");
    for (Map.Entry<String, String> entry : map.entrySet()) {
      output.print(++index + ". ");
      output.print(entry.getValue());
      output.println(" (" + entry.getKey() + ")");
    }
    output.println("\nWhich stock would you like to buy?");
  }

  private void mainScreen() {
    output.println("\nMENU\n");
    output.println("1. Create a portfolio");
    output.println("2. View portfolio");
    output.println("3. Value of portfolio");
    output.println("e. Exit\n");
    output.println("ENTER YOUR CHOICE: ");
  }

  private void showStockMainScreen(String name) {
    output.println("\nCREATE PORTFOLIO MENU\n");
    output.println(name.toUpperCase() + " Portfolio\n");
    output.println("1. Buy a share");
    output.println("2. Main Menu");
    output.println("e. Exit\n");
    output.println("ENTER YOUR CHOICE: ");
  }

  private void showPortfolioName() {
    output.println("Enter the name for this portfolio.");
  }

  private void showStockBuyReenter() {
    output.println("Not a valid input. Please enter the correct stock.");
    output.println("If you want to go back to main menu, press 'm'.\n");
  }

  private void showPortfolioNameReenter() {
    output.println("Cannot create a portfolio with empty name. Enter a valid name.");
    output.println("If you want to go back to main menu, press '0'.\n");
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
