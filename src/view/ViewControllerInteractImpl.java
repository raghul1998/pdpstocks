package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Random;

import model.GetStockData;
import model.StockCompositionData;
import model.StockNameMap;

/**
 * This class represents the view and provides the implementation to dispatch the actions to the
 * corresponding methods based on commands provided by the controller.
 * This class has the following variables.
 * <ul>
 *   <li>output - the output stream which is the print stream</li>
 * </ul>
 */
public class ViewControllerInteractImpl implements ViewControllerInteract {
  private final PrintStream output;

  /**
   * A constructor that assigns the print stream to the global variable.
   *
   * @param out the print stream
   */
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

  /**
   * This method renders the mentioned output when user tries to enter an invalid date while trying
   * to view the value of a portfolio on a certain date.
   */
  private void correctDateScreen() {
    output.println("Not a valid input. Please enter the correct date.");
    output.println("Press 'b' to go back\n");
  }

  /**
   * This method renters the below output when the user tries to create a portfolio with a name
   * which already exists.
   */
  private void pfReenterDuplicateName() {
    output.println("If you want to override this portfolio, then press 'y'. "
            + "If you want to enter another name, press 'n'. If you want to go main screen, "
            + "press 'b'.\n");
  }

  /**
   * This method renders the below-mentioned output when user tries to input an invalid portfolio
   * number.
   */
  private void portfolioInvalidEntryScreen() {
    output.println("Not a valid input. Please enter the correct portfolio.");
    output.println("Press 'b' to go back to the previous menu.\n");
  }

  /**
   * This method renders the mentioned output if the user has bought a stock and would like to know
   * more options other than buying a stock.
   */
  private void showPortfolioReviewScreen() {
    output.println("Press 'b' to go back and 'm' for main menu.\n");
  }

  /**
   * This method helps in rendering the output when the user wants to view the value of the
   * portfolio on a certain date.
   *
   * @param option the index of the portfolio
   * @param date   the date entered by the user
   */
  private void showPortfolioIndividualWithDateScreen(String option, String date) {
    int portfolioNumber = Integer.parseInt(option) - 1;
    StockCompositionData obj = new StockCompositionData();
    StockCompositionData.StockPortFolioData stkObj =
            obj.getAllStockDataInPortFolio(portfolioNumber, true);
    double totalPortFolioValue = 0;

    if (stkObj.numberOfUniqueStocks == 0) {
      output.println("The portfolio is empty.\n");
      return;
    }

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
      // Display based on the date purchased on
      double shareValue = getShareValueOnDate(stkObj.stockSymbol[i], date);
      shareValue = Math.floor((shareValue) * 100) / 100;
      output.print("\t $" + shareValue);
      output.println("\t $" + Math.floor((shareValue * stkObj.stockQuantity[i]) * 100) / 100);
      totalPortFolioValue += Math.floor((shareValue * stkObj.stockQuantity[i]) * 100) / 100;
    }

    totalPortFolioValue = Math.floor(totalPortFolioValue * 100) / 100;
    output.println("\nTotal Portfolio Value is on " + date + ": $" + totalPortFolioValue + "\n");
  }

  /**
   * This method helps in rendering the composition of the portfolio when the user tries to view
   * the same.
   *
   * @param option the index of the portfolio
   */
  private void showPortfolioIndividualScreen(String option) {
    int portfolioNumber = Integer.parseInt(option) - 1;
    StockCompositionData obj = new StockCompositionData();
    StockCompositionData.StockPortFolioData stkObj =
            obj.getAllStockDataInPortFolio(portfolioNumber, false);

    String[] portfolioNames = obj.getPortFolioNames();
    String date = stkObj.createdTimeStamp;
    if (date == null) {
      output.println("The portfolio is empty.\n");
      return;
    }
    date = date.substring(0, 10);
    output.println("\n" + portfolioNames[portfolioNumber].toUpperCase() + " PORTFOLIO"
            + " COMPOSITION - Created on " + date + "\n");

    output.print("Name");
    output.print(" (" + "Symbol" + ") \t ");
    output.print("Quantity \t ");
    output.print("Date of Purchase(DOP)\t");
    output.println("Price of a share on DOP\n");

    for (int i = 0; i < stkObj.numberOfUniqueStocks; i++) {
      output.print(stkObj.stockName[i]);
      output.print(" (" + stkObj.stockSymbol[i] + ") ");
      output.print("\t " + stkObj.stockQuantity[i]);
      output.print("\t " + stkObj.stockLastKnownValueDate[i]);
      output.println("\t $" + stkObj.valueOfSingleStock[i]);
    }
    output.println();
  }

  /**
   * This method renders the list of all the portfolio that a user has created in the application.
   *
   * @param portfolioNames    the names of all the available portfolio
   * @param numberOfPortFolio the number of portfolios
   */
  private void showPortFolioCompositionScreen(String[] portfolioNames, int numberOfPortFolio) {
    output.println("\nLIST OF PORTFOLIO");
    output.println();
    for (int i = 0; i < numberOfPortFolio; i++) {
      output.println(i + 1 + ". " + portfolioNames[i].toUpperCase());
    }
    output.println("\nWhich portfolio would you like to check?");
  }

  /**
   * This method renders a success message when a portfolio is successfully created.
   *
   * @param currentPortfolioName the name of the portfolio which user created
   */
  private void showDisplayPortFolioCreated(String currentPortfolioName) {
    output.println("\n" + currentPortfolioName.toUpperCase() + " PORTFOLIO CREATED...!!!");
  }

  /**
   * This method renders the mentioned output when user tries to buy a share.
   */
  private void showBuyStockValueScreen() {
    output.println("\nHow many shares would you like to buy?");
    output.println("Press 'b' to go back to the previous menu, 'm' to main menu.\n");
  }

  /**
   * This method renders the below-mentioned output if the user inputs an invalid entry while
   * trying to buy the shares.
   */
  private void showStockBuyInvalidRetryScreen() {
    output.println("Not a valid input. Please enter number of shares as natural numbers.");
    output.println("Press 'b' to go back to the previous menu, 'm' to main menu.\n");
  }

  /**
   * This method renders the below-mentioned output if the user wants to buy another share after
   * successfully buying a share.
   */
  private void wouldYouLikeToBuyAnotherStockScreen() {
    output.println("Would you like to buy another stock? (Y|N)");
  }

  /**
   * This method helps in showing the current stock data, and it's share price that the user wants
   * to pruchase.
   */
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

  /**
   * This method renders the list of all the supported stocks by the application.
   */
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

  /**
   * This is the main screen and it's options. This method renders the below output when user
   * wants to view the main screen.
   */
  private void mainScreen() {
    output.println("\nMENU\n");
    output.println("1. Create a portfolio");
    output.println("2. View portfolio");
    output.println("3. Value of portfolio");
    output.println("e. Exit\n");
    output.println("ENTER YOUR CHOICE: ");
  }

  /**
   * This method is called when a user has inputted a name of the portfolio and would like to
   * do more on that portfolio like when a user wants to buy a stock.
   *
   * @param name the name of the portfolio that the user inputted
   */
  private void showStockMainScreen(String name) {
    output.println("\nCREATE PORTFOLIO MENU\n");
    output.println(name.toUpperCase() + " Portfolio\n");
    output.println("1. Buy a stock");
    output.println("2. Main Menu");
    output.println("e. Exit\n");
    output.println("ENTER YOUR CHOICE: ");
  }

  /**
   * This method is rendered when the user is trying to create a portfolio. This method basically
   * tells the user to enter the name for the portfolio.
   */
  private void showPortfolioName() {
    output.println("Enter the name for this portfolio.");
  }

  /**
   * When a user tries to input an invalid input while buying a stock, the below method is called
   * and the mentioned output is rendered on the screen.
   */
  private void showStockBuyReenter() {
    output.println("Not a valid input. Please enter the correct stock.");
    output.println("If you want to go back to main menu, press 'm'.\n");
  }

  /**
   * This method is called when a user tries to create a portfolio with an empty name. The below
   * output is rendered during that time.
   */
  private void showPortfolioNameReenter() {
    output.println("Cannot create a portfolio with empty name. Enter a valid name.");
    output.println("If you want to go back to main menu, press '0'.\n");
  }

  /**
   * A helper method that calculates the pseudo-random share values based on the stock value during
   * purchase and the date provided.
   *
   * @param stockValue the value of the share at the time of purchase
   * @param date       the date inputted by the user
   * @return the pseudo-random value of the stock as a double
   */
  private double getRandomShareValue(double stockValue, String date) {
    String[] dateSplit = date.split("-");
    StringBuilder dateSplitIndividual = new StringBuilder();
    for (String s : dateSplit) {
      dateSplitIndividual.append(s);
    }
    int dateLong = Integer.parseInt(dateSplitIndividual.toString());
    int decimalValue = sumOfNumber(dateLong);
    Random rand = new Random(dateLong);
    int randomShareValueInteger = (int) (rand.nextInt((int) ((stockValue + 30)
            - (stockValue - 10))) + stockValue - 10);
    String randomShareValueString = "" + randomShareValueInteger + "." + decimalValue;
    double value = Double.parseDouble(randomShareValueString);
    return Math.floor(value * 100) / 100;
  }

  /**
   * This helper method helps in calculating the sum of all the integers with a given integer.
   *
   * @param number the number for which the sum needs to be calculated
   * @return the sum as an integer
   */
  private int sumOfNumber(int number) {
    int sum;
    for (sum = 0; number != 0; number = number / 10) {
      sum = sum + number % 10;
    }
    return sum;
  }

  private double getShareValueOnDate(String stockSymbol, String date) {
    GetStockData gsd = new GetStockData();
    try {
      gsd.getValue(stockSymbol, date);
    } catch (Exception e) {
      output.println("Error in getting stock data on " + date);
      return 0;
    }

    BufferedReader stockData;
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String line;
    String splitBy = ",";
    String[] splitStockData;

    try {
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
      stockData.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return Double.parseDouble(splitStockData[1]);
  }

}
