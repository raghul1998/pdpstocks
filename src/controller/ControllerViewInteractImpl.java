package controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import model.TypeofAction;
import model.StockNameMap;
import model.StockCompositionData;
import view.ViewControllerInteract;
import view.ViewControllerInteractImpl;
import view.TypeofViews;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

/**
 * This class represents the controller that interacts with the view.
 * This class provides the implementation that helps controller to interact with the view.
 * This class also interacts with the controller that interacts with the model.
 * This class has the following variables.
 * <ul>
 *   <li> cmiObj - an object for the controller that interacts with the model </li>
 *   <li> vciObj - an object for the view </li>
 *   <li> currentPortfolioName - the name of the current portfolio that is being used or read </li>
 *   <li> output - print stream object </li>
 *   <li> scan - scanner object </li>
 * </ul>
 */
public class ControllerViewInteractImpl implements ControllerViewInteract {
  private final ControllerModelInteract cmiObj = new ControllerModelInteractImpl();
  private final ViewControllerInteract vciObj;
  private String currentPortfolioName;
  private final PrintStream output;
  private final Scanner scan;

  /**
   * Constructor for the controller that interacts with the view that takes in two arguments and
   * initializes them to the global variables.
   *
   * @param input  the input stream
   * @param output the output stream
   */
  public ControllerViewInteractImpl(InputStream input, PrintStream output) {
    this.output = output;
    vciObj = new ViewControllerInteractImpl(output);
    scan = new Scanner(input);
  }

  @Override
  public void start() {
    cmiObj.controllerModelInteract(TypeofAction.CREATE_SUPPORTED_STOCKS, null, 0);
    String option;
    do {
      vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);
      option = scan.nextLine();
      performMainMenuAction(option);
    } while (!option.equals("e"));
  }

  /**
   * This method tells the view to display the composition of the portfolio along with the stocks
   * details like name, symbol, quantity, price of each share and total value.
   *
   * @param options the portfolio number
   */
  private void portfolioCompositionAction(String options) {
    String[] args = new String[1];
    args[0] = options;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST, args, 1);
  }

  /**
   * This method takes in the date for the view portfolio menu and tells the view to display the
   * value of the portfolio on the given date.
   *
   * @param options the portfolio number
   * @return false if the user entered back option, else true
   */
  private boolean portfolioViewAction(String options) {
    String[] args = new String[2];
    args[0] = options;

    output.println("Enter the year in format (YYYY-MM-DD) (2000 to "
            + LocalDate.now().getYear() + "): ");
    String date;
    date = scan.nextLine();
    while (!validateDate(date)) {
      vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      date = scan.nextLine();
      if (Objects.equals(date, "b") || Objects.equals(date, "B")) {
        return false;
      }
    }
    args[1] = date;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST_WITH_DATE, args, 1);
    return true;
  }

  /**
   * This is a helper method that helps in validating the date input the user enters when the user
   * wants to see the value of the portfolio on a certain date.
   *
   * @param dateStr the date that user entered
   * @return true of the input is valid, else false
   */
  private boolean validateDate(String dateStr) {
    if (dateStr == null || dateStr.length() == 0) {
      return false;
    }

    Date date;
    try {
      DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      date = sdf.parse(dateStr);
      if (!dateStr.equals(sdf.format(date))) {
        return false;
      }

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      if (calendar.get(Calendar.YEAR) > LocalDate.now().getYear()
              || calendar.get(Calendar.YEAR) < 2000) {
        return false;
      }

      if (calendar.get(Calendar.YEAR) == LocalDate.now().getYear()) {
        if (calendar.get(Calendar.MONTH) + 1 > LocalDate.now().getMonthValue()) {
          return false;
        }
        if (calendar.get(Calendar.MONTH) + 1 == LocalDate.now().getMonthValue()) {
          if (calendar.get(Calendar.DATE) > LocalDate.now().getDayOfMonth()) {
            return false;
          }
        }
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method tells the view to display the list of menu options that can be performed when a
   * user is trying to create a portfolio. The options being, whether the user wants to buy a share
   * or wants to go back to main menu or exit the application.
   *
   * @param options the options that are provided by the user
   * @param args    the helper arguments that are passed by the callee
   */
  private void createPortfolioNameScreenAction(String options, String[] args) {
    try {
      sleep(100);
    } catch (Exception e) {
      // Exception while sleep
    }
    cmiObj.controllerModelInteract(TypeofAction.CREATE_PORTFOLIO, args, 1);
    String[] name = new String[1];
    name[0] = currentPortfolioName;
    while (true) {
      vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO, name, 0);
      String option;
      option = scan.nextLine();
      if (performCreatePortfolioMenuAction(option, null)) {
        break;
      }
    }
  }

  /**
   * This method tells the view to display stock buying screen provided that the user wanted to
   * buy another share after purchasing a share. If the user doesn't want to buy another share,
   * then this method tells the controller to display the portfolio created screen.
   *
   * @param options the option that was provided by the user that whether the user wants
   *                to buy a share
   */
  private void buyAnotherStockMenuAction(String options) {
    if (Objects.equals(options, "Y") || Objects.equals(options, "y")) {
      performCreatePortfolioMenuAction("1", null);
    } else {
      String[] args = new String[1];
      args[0] = currentPortfolioName;
      vciObj.viewControllerInteract(TypeofViews.DISPLAY_PORTFOLIO_CREATED, args, 0);
    }
  }

  /**
   * This method tells the model to buy the share that the user asked for. If the user wanted to
   * go back to main menu, this method asks the model to delete the empty portfolio and returns
   * to the main screen. This method also instructs view to display the buy another stock menu if
   * the user wants to buy another stock.
   *
   * @param option the option that was provided by the user that whether the user
   *               wants to buy a share
   */
  private void buyStockValueMenuAction(String option) {
    if (Objects.equals(option, "m") || Objects.equals(option, "M")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return;
    }
    String[] stock = new String[1];
    stock[0] = option;
    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 1);
    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK, null, 0);
    String options;
    options = scan.nextLine();
    while ((options == null || options.length() == 0) || ((!options.equals("Y")) &&
            (!options.equals("y")) && (!options.equals("N")) && (!options.equals("n")))) {
      vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
      options = scan.nextLine();
    }
    buyAnotherStockMenuAction(options);
  }

  /**
   * This method tells the model to get the stock data that user wants to buy. Also, this method
   * tells the view to display the stock data that the user wants to buy.
   *
   * @param options the stock option that user entered
   * @param args    helper arguments if any
   * @param length  length of the arguments
   * @return true if user wants to go back to main menu, else false
   */
  private boolean performListOfStocksMenuAction(String options, String[] args, int length) {

    if (Objects.equals(options, "m") || Objects.equals(options, "M")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return false;
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
    option = scan.nextLine();
    while ((option == null || option.length() == 0) || (!validateBuyStockOption(option))) {
      if (Objects.equals(option, "b") || Objects.equals(option, "B")) {
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        return true;
      }
      vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_INVALID_RETRY, null, 0);
      option = scan.nextLine();
    }
    buyStockValueMenuAction(option);
    return false;
  }

  /**
   * This method tells the view to render the corresponding screen based on the user input to the
   * main menu. The options being, to create a portfolio, view composition, price of share on
   * certain date.
   *
   * @param option the option the user entered the main menu screen
   */
  private void performMainMenuAction(String option) {
    switch (option) {
      case "1": {
        // Create a portfolio
        while (true) {
          String name;
          vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO_NAME_SCREEN, null, 0);
          name = scan.nextLine();
          while (name.length() == 0) {
            vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_NAME_REENTER, null, 0);
            name = scan.nextLine();
            if (name.equals("0")) {
              return;
            }
          }

          if (checkIfPortfolioExists(name)) {
            vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_ALREADY_EXISTS, null, 0);
            vciObj.viewControllerInteract(TypeofViews.PF_REENTER_DUPLICATE_NAME, null, 0);
            String input;
            input = scan.nextLine();
            while ((input.length() == 0) || !(input.equals("B") || input.equals("b") ||
                    input.equals("Y") || input.equals("y") || input.equals("N") ||
                    input.equals("n"))) {
              vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
              vciObj.viewControllerInteract(TypeofViews.PF_REENTER_DUPLICATE_NAME,
                      null, 0);
              input = scan.nextLine();
            }
            if (input.equals("B") || input.equals("b")) {
              return;
            }
            if (input.equals("N") || input.equals("n")) {
              continue;
            }
            // This case is 'Y', we want to override.
          }

          String[] args = new String[1];
          args[0] = name;
          currentPortfolioName = name;
          createPortfolioNameScreenAction(option, args);
          break;
        }
        break;
      }
      case "2": {
        // Composition of the portfolio
        while (true) {
          StockCompositionData obj = new StockCompositionData();
          int numberOfPortFolio = obj.getNumberOfPortFolio();
          if (numberOfPortFolio == 0) {
            vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
            return;
          }
          String[] portfolioNames = obj.getPortFolioNames();
          vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, portfolioNames, numberOfPortFolio);
          String options;
          options = scan.nextLine();
          while ((options == null || options.length() == 0) ||
                  (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
            vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
            options = scan.nextLine();
            if (Objects.equals(options, "b")) {
              return;
            }
          }
          portfolioCompositionAction(options);
          vciObj.viewControllerInteract(TypeofViews.REVIEW_STOCK, null, 0);
          options = scan.nextLine();
          while ((options == null || options.length() == 0) ||
                  ((options.equals("M")) || (options.equals("m")) || (options.equals("b")) ||
                          (options.equals("B")))) {
            if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
              break;
            } else if (Objects.equals(options, "m") || Objects.equals(options, "M")) {
              return;
            }
            vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
            vciObj.viewControllerInteract(TypeofViews.REVIEW_STOCK, null, 0);
            options = scan.nextLine();
          }
        }
      }
      case "3": {
        // Value of the portfolio on a certain date
        while (true) {
          StockCompositionData obj = new StockCompositionData();
          int numberOfPortFolio = obj.getNumberOfPortFolio();
          if (numberOfPortFolio == 0) {
            vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
            return;
          }
          String[] portfolioNames = obj.getPortFolioNames();
          vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, portfolioNames, numberOfPortFolio);
          String options;
          options = scan.nextLine();
          while ((options == null || options.length() == 0) ||
                  (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
            vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
            options = scan.nextLine();
            if (Objects.equals(options, "b")) {
              return;
            }
          }
          if (!portfolioViewAction(options)) {
            continue;
          }
          vciObj.viewControllerInteract(TypeofViews.REVIEW_STOCK, null, 0);
          options = scan.nextLine();
          while ((options == null || options.length() == 0) ||
                  ((options.equals("M")) || (options.equals("m")) || (options.equals("b")) ||
                          (options.equals("B")))) {
            if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
              break;
            } else if (Objects.equals(options, "m") || Objects.equals(options, "M")) {
              return;
            }
            vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
            vciObj.viewControllerInteract(TypeofViews.REVIEW_STOCK, null, 0);
            options = scan.nextLine();
          }
        }
      }
      case "e":
      case "E": {
        output.println("\nExiting...");
        //exit(0);
        return;
      }
      default: {
        output.println("Invalid command. Enter the right option number.");
        try {
          sleep(500);
        } catch (Exception e) {
          // Exception while calling sleep
        }
        break;
      }
    }
  }

  /**
   * A helper method that check if the portfolio that the user entered while creating one already
   * exists or not.
   *
   * @param name the name of the portfolio that user wants to create
   * @return true if the portfolio exists, else false
   */
  private boolean checkIfPortfolioExists(String name) {
    String directoryName = "userdata/user1/";
    File directory = new File(directoryName);
    if (!directory.exists()) {
      return false;
    }
    File dir = new File("userdata/user1");
    File[] files = dir.listFiles();
    assert files != null;
    for (File file : files) {
      String fullName = file.getName();
      fullName = fullName.substring(3);
      String[] fName = fullName.split("\\.");
      if (Objects.equals(fName[0], name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * A helper method that helps in validation the option the user entered for the portfolio for
   * which the user needs to know the information about.
   *
   * @param option            the index of the portfolio the user entered
   * @param numberOfPortFolio the number of portfolios that are present in the system
   * @return true if the input is valid, else false
   */
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

  /**
   * After user creats a portfolio with the name, the user has to buy stocks to fill the portfolio
   * with date. This method tells the view to display the options for the user to buy the stocks.
   *
   * @param option the option that user entered after entering name for the portfolio
   * @param args   helper arguments if passed by the callee
   * @return false if the option entered is invalid, else true
   */
  private boolean performCreatePortfolioMenuAction(String option, String[] args) {
    switch (option) {
      case "1": {
        //Buy a new stock
        while (true) {
          vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
          String options;
          options = scan.nextLine();
          while (!validateStockSelectOption(options)) {
            vciObj.viewControllerInteract(TypeofViews.STOCK_BUY_REENTER, null, 0);
            options = scan.nextLine();
          }
          if (!performListOfStocksMenuAction(options, null, 0)) {
            break;
          }
        }
        // Delete if portfolio is empty
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        break;
      }
      case "2": {
        // Go to Main menu
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        break;
      }
      case "e":
      case "E": {
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        output.println("\nExiting...");
        exit(0);
      }
      default: {
        output.println("Invalid command. Enter the right option number.");
        try {
          sleep(500);
        } catch (Exception e) {
          // Exception while calling sleep
        }
        return false;
      }
    }
    return true;
  }

  /**
   * A helper method that helps in validating where the option that was entered buy the user to buy
   * a stock is valid or not.
   *
   * @param option the stock option that was entered by the user
   * @return true if the input is valid, else false
   */
  private boolean validateStockSelectOption(String option) {
    if (option == null || option.length() == 0) {
      return false;
    }

    if (option.equals("m") || option.equals("M")) {
      return true;
    }
    // The selected option should be a number, and it should be a valid digit.
    int val;
    try {
      val = Integer.parseInt(option);
    } catch (Exception e) {
      return false;
    }

    StockNameMap snp = new StockNameMap();
    if (val < 1 || val > snp.getMapSize()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * A helper method that helps in validation the user input for the number of shares the user
   * wants to buy for a stock.
   *
   * @param option the number of shares that the user wants to buy
   * @return true if the input is valid, else false
   */
  private boolean validateBuyStockOption(String option) {
    if (option == null || option.length() == 0) {
      return false;
    }

    if (option.equals("m") || (option.equals("M"))) {
      return true;
    }

    // The selected option should be a number, and it should be a valid digit.
    int val;
    try {
      val = Integer.parseInt(option);
    } catch (Exception e) {
      return false;
    }

    if (val <= 0) {
      return false;
    } else {
      return true;
    }
  }
}
