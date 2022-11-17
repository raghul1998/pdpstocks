package controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import model.StockCompositionData;
import model.StockNameMap;
import model.StockPortFolioDataImpl;
import model.TypeofAction;
import model.StockNameMapImpl;
import model.StockCompositionDataImpl;
import view.ViewControllerInteract;
import view.ViewControllerInteractImpl;
import view.TypeofViews;

import static java.lang.System.exit;
import static java.lang.System.out;
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
    storeCommissionCost("1.27"); // Default value
    String option;
    do {
      try {
        sleep(500);
      } catch (Exception e) {
        // Exception while sleep
      }
      vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);
      option = scan.nextLine();
      performMainMenuAction(option);
    }
    while (!option.equals("e"));
  }

  /**
   * This method takes in the date for the view portfolio menu and tells the view to display the
   * value of the portfolio on the given date.
   *
   * @param options the portfolio number
   * @return false if the user entered back option, else true
   */
  private boolean portfolioViewAction(String options, String type, String portfolioType) {
    String[] args = new String[4];
    args[0] = options;

    output.println("Enter the year in format (YYYY-MM-DD) (2000 to "
            + LocalDate.now().getYear() + "): ");
    String date;
    date = scan.nextLine();
    while (!validateDate(date, "yyyy-MM-dd", 0)) {
      vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      date = scan.nextLine();
      if (Objects.equals(date, "b") || Objects.equals(date, "B")) {
        return false;
      }
    }
    args[1] = date;
    args[2] = type;
    args[3] = portfolioType;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST_WITH_DATE, args, 4);
    return true;
  }

  /**
   * This is a helper method that helps in validating the date input the user enters when the user
   * wants to see the value of the portfolio on a certain date.
   *
   * @param dateStr the date that user entered
   * @return true of the input is valid, else false
   */
  private boolean validateDate(String dateStr, String format, int minDifference) {
    if (dateStr == null || dateStr.length() == 0) {
      return false;
    }

    Date date;
    try {
      DateFormat sdf = new SimpleDateFormat(format);
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


      if (minDifference != 0) {
        switch (format) {
          case "yyyy":
            if (calendar.get(Calendar.YEAR) + minDifference > LocalDate.now().getYear()) {
              return false;
            }
            break;
          case "yyyy-MM":
            String tempDateStr = dateStr + "-01";
            if (ChronoUnit.MONTHS.between(LocalDate.parse(tempDateStr),
                    LocalDate.now()) < minDifference) {
              return false;
            }
            break;
          case "yyyy-MM-dd":
            if (ChronoUnit.DAYS.between(LocalDate.parse(dateStr),
                    LocalDate.now()) < minDifference) {
              return false;
            }
            break;
          default:
            // No action to be taken
            break;
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
      if (performCreatePortfolioMenuAction(option, args[1], null, 0)) {
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
  private void buyAnotherStockMenuAction(String options, String portfolioType, String[] arg,
                                         int length) {
    if (Objects.equals(options, "Y") || Objects.equals(options, "y")) {
      performCreatePortfolioMenuAction("1", portfolioType, null, 0);
    } else {
      String[] args = new String[1];
      args[0] = currentPortfolioName;
      if (arg == null || length == 0) {
        vciObj.viewControllerInteract(TypeofViews.DISPLAY_PORTFOLIO_CREATED, args, 0);
      } else if (arg[0].equals("addStockScreen")) {
        output.println("\nStock successfully added to the portfolio...!!!");
      }
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
  private void buyStockValueMenuAction(String option, String portfolioType, String[] args,
                                       int length) {
    if (Objects.equals(option, "m") || Objects.equals(option, "M")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return;
    }
    String[] stock = new String[2];
    stock[0] = option;
    stock[1] = currentPortfolioName;
    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 2);
    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK, null, 0);
    String options;
    options = scan.nextLine();
    while ((options == null || options.length() == 0) || ((!options.equals("Y"))
            && (!options.equals("y")) && (!options.equals("N")) && (!options.equals("n")))) {
      vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
      options = scan.nextLine();
    }
    buyAnotherStockMenuAction(options, portfolioType, args, length);
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
  private boolean performListOfStocksMenuAction(String options, String portfolioType,
                                                String[] args, int length) {

    if (Objects.equals(options, "m") || Objects.equals(options, "M")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return false;
    }

    String date;
    if (Objects.equals(portfolioType, "1")) {
      output.println("Enter the date on which you would like to purchase the stock (YYYY-MM-DD)");
      date = scan.nextLine();
      while (!validateDate(date, "yyyy-MM-dd", 0)) {
        vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
        date = scan.nextLine();
        if (Objects.equals(date, "b") || Objects.equals(date, "B")) {
          return false;
        }
      }
    } else {
      SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
      Date now = new Date();
      date = sdfDate.format(now);
    }

    StockNameMap snp = new StockNameMapImpl();
    Map<String, String> map = snp.getMap();
    String[] stockSymbolIndexArray = new String[snp.getMapSize()];
    String[] stock = new String[2];
    int index = 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      stockSymbolIndexArray[index++] = entry.getKey();
    }
    stock[0] = stockSymbolIndexArray[Integer.parseInt(options) - 1];
    stock[1] = date;
    cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stock, 2);
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
    buyStockValueMenuAction(option, portfolioType, args, length);
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
        createAPortfolio(option);
        break;
      }
      case "2": {
        // Value of the portfolio on certain date
        compositionOfPortfolio();
        break;
      }
      case "3": {
        // Value of the portfolio on a certain date full composition
        valueOfPortfolio();
        break;
      }
      case "4": {
        addStockToPortfolioMainMenu();
        break;
      }
      case "5": {
        sellStockFromPortfolio();
        break;
      }
      case "6": {
        portfolioPerformanceMainMenu();
        break;
      }
      case "7": {
        costBasisByDate();
        break;
      }
      case "8": {
        commissionCostMainMenu();
        break;
      }
      case "e":
      case "E": {
        output.println("\nExiting...");
        //exit(0);
        return;
      }
      default: {
        output.println("Invalid command. Enter the right option number.");
        break;
      }
    }
  }

  private void commissionCostMainMenu() {
    StockCompositionData stk = new StockCompositionDataImpl("ALL");
    output.println("\nCONFIGURE COMMISSION COST\n");
    output.println("Current commission cost is: $" + stk.getCommissionCost());
    output.println("Enter the commission cost per transaction:");
    String cost;
    cost = scan.nextLine();
    while (!validateCommissionCost(cost)) {
      vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
      output.println("Commission cost should be greater than 0. Enter 'm' for main menu.");
      cost = scan.nextLine();
      if (cost.equalsIgnoreCase("m")) {
        return;
      }
    }
    storeCommissionCost(cost);
    output.println("Commission cost set to " + cost);
  }

  private void storeCommissionCost(String cost) {
    String[] args = new String[1];
    args[0] = cost;
    cmiObj.controllerModelInteract(TypeofAction.STORE_COMMISSION_COST, args, 1);
  }

  /**
   * A helper function to validate the commission cost.
   *
   * @param cost cost as string
   * @return true if cost is valid
   */
  private boolean validateCommissionCost(String cost) {
    double commissionCost;
    if (cost == null || cost.length() == 0) {
      return false;
    }
    try {
      commissionCost = Double.parseDouble(cost);
    } catch (Exception e) {
      return false;
    }

    if (commissionCost <= 0) {
      return false;
    }
    return true;
  }

  /**
   * This method helps in determining the cost invested in a portfolio on a certain date.
   */
  private void costBasisByDate() {
    while (true) {
      StockCompositionData obj = new StockCompositionDataImpl("FLEXIBLE");
      int numberOfPortFolio = obj.getNumberOfPortFolio();
      if (numberOfPortFolio == 0) {
        vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
        return;
      }
      String[] portfolioNames = obj.getPortFolioNames("FLEXIBLE");
      String[] args = new String[portfolioNames.length + 1];
      System.arraycopy(portfolioNames, 0, args, 0, portfolioNames.length);
      args[portfolioNames.length] = "FLEXIBLE";

      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, args,
              args.length);
      vciObj.viewControllerInteract(TypeofViews.WHICH_PORTFOLIO_CHECK, portfolioNames,
              numberOfPortFolio);
      String options;
      options = scan.nextLine();
      while ((options == null || options.length() == 0)
              || (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
        options = scan.nextLine();
        if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
          return;
        }
      }

      if (!portfolioViewAction(options, "COST", "FLEXIBLE")) {
        continue;
      }

      if (endMenu()) {
        return;
      }
    }
  }

  /**
   * This method provides the main menu options for calculating the performance of a portfolio.
   */
  private void portfolioPerformanceMainMenu() {
    while (true) {
      StockCompositionData obj = new StockCompositionDataImpl("FLEXIBLE");
      int numberOfPortFolio = obj.getNumberOfPortFolio();
      if (numberOfPortFolio == 0) {
        vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
        return;
      }
      String[] portfolioNames = obj.getPortFolioNames("FLEXIBLE");
      String[] args = new String[portfolioNames.length + 1];
      System.arraycopy(portfolioNames, 0, args, 0, portfolioNames.length);
      args[portfolioNames.length] = "FLEXIBLE";

      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, args,
              args.length);
      vciObj.viewControllerInteract(TypeofViews.WHICH_PORTFOLIO_CHECK, portfolioNames,
              numberOfPortFolio);
      String options;
      options = scan.nextLine();
      while ((options == null || options.length() == 0)
              || (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
        options = scan.nextLine();
        if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
          return;
        }
      }

      currentPortfolioName = obj.getPortFolioNames("FLEXIBLE")[Integer.parseInt(options) - 1];
      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_PERFORMANCE_DATE_INPUT, null, 0);
      String choice;
      choice = scan.nextLine();
      while (choice == null || choice.length() == 0 || !validatePortfolioSelectOption(choice, 3)) {
        vciObj.viewControllerInteract(TypeofViews.NOT_VALID_MAIN_MENU, null, 0);
        choice = scan.nextLine();
        if (Objects.equals(choice, "m") || Objects.equals(choice, "M")) {
          return;
        }
      }
      if (portfolioPerformance(options, choice)) {
        break;
      }
    }
  }

  /**
   * This is a helper method for calculating the performance of a portfolio.
   *
   * @param pfNumber the portfolio number
   * @param choice   type of option the user choose
   * @return true if user wants to return to main menu
   */
  private boolean portfolioPerformance(String pfNumber, String choice) {
    String date;
    String format = null;
    LocalDate today = LocalDate.now();
    int remainder = 0;

    if (Objects.equals(choice, "1")) {
      output.println("Enter the start year in format (YYYY) from year 2000 to "
              + today.minusYears(4).getYear() + ":");
      format = "yyyy";
    } else if (Objects.equals(choice, "2")) {
      output.println("Enter the start month in format (YYYY-MM) from year 2000 to "
              + today.minusMonths(4).getMonth() + " "
              + today.minusMonths(4).getYear() + ":");
      format = "yyyy-MM";
    } else if (Objects.equals(choice, "3")) {
      output.println("Enter the start date in format (YYYY-MM-DD) from year 2000 to "
              + today.minusDays(4).getYear() + "-"
              + today.minusDays(4).getMonth() + "-"
              + today.minusDays(4).getDayOfMonth() + ":");
      format = "yyyy-MM-dd";
    }

    date = scan.nextLine();
    while (!validateDate(date, format, 4)) {
      vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      date = scan.nextLine();
      if (Objects.equals(date, "b") || Objects.equals(date, "B")) {
        return false;
      }
    }

    if (Objects.equals(choice, "1")) {
      String tempDateStr = date + "-01-01";
      remainder = (int) ChronoUnit.YEARS.between(LocalDate.parse(tempDateStr),
              LocalDate.now().withDayOfMonth(1));
      if (remainder != 4) {
        remainder += 1;
        if (remainder > 30) {
          remainder = 30;
        }
        output.println("Enter the number of years (5 to " + remainder + "): ");
      }
    } else if (Objects.equals(choice, "2")) {
      String tempDateStr = date + "-01";
      remainder = (int) ChronoUnit.MONTHS.between(LocalDate.parse(tempDateStr),
              LocalDate.now().withDayOfMonth(1));
      if (remainder != 4) {
        remainder += 1;
        if (remainder > 30) {
          remainder = 30;
        }
        output.println("Enter the number of months (5 to " + remainder + "): ");
      }
    } else if (Objects.equals(choice, "3")) {
      remainder = (int) ChronoUnit.DAYS.between(LocalDate.parse(date), LocalDate.now());
      if (remainder != 4) {
        remainder += 1;
        if (remainder > 30) {
          remainder = 30;
        }
        output.println("Enter the number of days (5 to " + remainder + "): ");
      }
    }

    String number;
    if (remainder != 4) {
      number = scan.nextLine();
      while (!validateStockSelectOption(number, 5, remainder)) {
        vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
        vciObj.viewControllerInteract(TypeofViews.GOBACK_MAINMENU_OPTION, null, 0);
        number = scan.nextLine();
        if (Objects.equals(number, "b") || Objects.equals(number, "B")) {
          return false;
        }
      }
      if (Objects.equals(number, "m") || Objects.equals(number, "M")) {
        return true;
      }
    } else {
      number = "5";
    }

    String[] dates;

    try {
      dates = getDatesFromUserInput(choice, date, number, format);
    } catch (Exception e) {
      output.println("Error in parsing the dates. Try again");
      return true;
    }

    dates[Integer.parseInt(number)] = currentPortfolioName;
    dates[Integer.parseInt(number) + 1] = pfNumber;
    dates[Integer.parseInt(number) + 2] = choice;
    cmiObj.controllerModelInteract(TypeofAction.GET_PORTFOLIO_PERFORMANCE, dates, dates.length);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_PERFORMANCE, dates, dates.length);

    return endMenu();
  }

  /**
   * A helper method to get user input to go back to previous option or to main menu.
   *
   * @return true if user wants to go to main menu
   */
  private boolean endMenu() {
    String options;
    vciObj.viewControllerInteract(TypeofViews.GOBACK_MAINMENU_OPTION, null, 0);
    options = scan.nextLine();
    while ((options == null || options.length() == 0)
            || !((options.equals("M")) || (options.equals("m")) || (options.equals("b"))
            || (options.equals("B")))) {
      vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
      vciObj.viewControllerInteract(TypeofViews.GOBACK_MAINMENU_OPTION, null, 0);
      options = scan.nextLine();
    }

    return Objects.equals(options, "m") || Objects.equals(options, "M");
    // When user presses 'B' continue
  }

  /**
   * This is a helper method that helps in getting the range of dates based on user input while
   * calculating performance of the portfolio.
   *
   * @param choice the input choice of the user
   * @param date   date, month or the year that the user inputted
   * @param number the number of days, month or the year the user needs data for
   * @param format format of the input date
   * @return the dates as array of strings
   * @throws ParseException if error while parsing the data
   */
  private String[] getDatesFromUserInput(String choice, String date, String number,
                                         String format) throws ParseException {
    String[] dates = new String[Integer.parseInt(number) + 3];
    Date convertedDate;

    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    convertedDate = dateFormat.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(convertedDate);

    if (Objects.equals(choice, "1")) {
      cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
      Date lastDayOfYear = cal.getTime();
      DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      LocalDate startDate = LocalDate.parse(sdf.format(lastDayOfYear));
      dates[0] = String.valueOf(startDate);
      for (int i = 1; i < Integer.parseInt(number); i++) {
        dates[i] = String.valueOf(startDate.plusYears(i));
      }
    } else if (Objects.equals(choice, "2")) {
      cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
      Date lastDayOfMonth = cal.getTime();
      DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      LocalDate startDate = LocalDate.parse(sdf.format(lastDayOfMonth));
      dates[0] = String.valueOf(startDate);
      for (int i = 1; i < Integer.parseInt(number); i++) {
        startDate = startDate.plusMonths(1);
        convertedDate = sdf.parse(String.valueOf(startDate));
        cal.setTime(convertedDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        lastDayOfMonth = cal.getTime();
        startDate = LocalDate.parse(sdf.format(lastDayOfMonth));
        dates[i] = String.valueOf(startDate);
      }
    } else if (Objects.equals(choice, "3")) {
      LocalDate startDate = LocalDate.parse(date);
      dates[0] = String.valueOf(startDate);
      for (int i = 1; i < Integer.parseInt(number); i++) {
        dates[i] = String.valueOf(startDate.plusDays(i));
      }
    }
    return dates;
  }

  /**
   * A helper method for selling the stocks from the portfolio.
   */
  private void sellStockFromPortfolio() {
    while (true) {
      StockCompositionData obj = new StockCompositionDataImpl("FLEXIBLE");
      int numberOfPortFolio = obj.getNumberOfPortFolio();
      if (numberOfPortFolio == 0) {
        vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
        return;
      }
      String[] portfolioNames = obj.getPortFolioNames("FLEXIBLE");
      String[] arg = new String[portfolioNames.length + 1];
      System.arraycopy(portfolioNames, 0, arg, 0, portfolioNames.length);
      arg[portfolioNames.length] = "FLEXIBLE";

      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, arg,
              arg.length);
      output.println("\nSelect the portfolio to sell stocks from.");
      String options;
      options = scan.nextLine();
      while ((options == null || options.length() == 0)
              || (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
        options = scan.nextLine();
        if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
          return;
        }
      }

      String date = sellStockDateEnter();
      if (date == null) {
        continue;
      }

      String[] args = new String[3];
      args[0] = date;
      args[1] = options;
      args[2] = "FLEXIBLE";
      vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS_ON_DATE, args, 3);

      StockPortFolioDataImpl stkObj;
      try {
        stkObj = (StockPortFolioDataImpl)
                obj.getAllStockDataInPortFolio(Integer.parseInt(options) - 1,
                        true, date, true, true, "FLEXIBLE");
      } catch (Exception e) {
        output.println("Controller: Error in getting stock data " + e.getMessage());
        return;
      }

      if (stkObj.numberOfUniqueStocks == 0) {
        return;
      }

      String stockOptions = scan.nextLine();

      while ((stockOptions == null || stockOptions.length() == 0)
              || (!validateStockSelectOption(stockOptions, 1, stkObj.numberOfUniqueStocks))) {
        vciObj.viewControllerInteract(TypeofViews.STOCK_BUY_REENTER, null, 0);
        stockOptions = scan.nextLine();
        if (Objects.equals(stockOptions, "m") || Objects.equals(stockOptions, "M")) {
          return;
        }
      }

      if (sellSharesOnAStock(Integer.parseInt(options) - 1,
              stkObj.stockSymbol[Integer.parseInt(stockOptions) - 1], date)) {
        return;
      }
    }
  }

  /**
   * A helper method for getting the input date from the user for selling the stocks.
   *
   * @return the user input as a string
   */
  private String sellStockDateEnter() {
    String date;
    output.println("Enter the date on which you would like to sell the stock (YYYY-MM-DD)");
    date = scan.nextLine();
    while (!validateDate(date, "yyyy-MM-dd", 0)) {
      vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      date = scan.nextLine();
      if (Objects.equals(date, "b") || Objects.equals(date, "B")) {
        return null;
      }
    }
    return date;
  }

  /**
   * This method sells number of shares of a stock from a portfolio on a certain date.
   *
   * @param pfNumber    the portfolio number
   * @param stockSymbol the symbol of the stock
   * @param date        date on which the user needs to sell
   * @return true if user wants to return to main menu
   */
  private boolean sellSharesOnAStock(int pfNumber, String stockSymbol, String date) {
    StockCompositionData stk = new StockCompositionDataImpl("FLEXIBLE");
    currentPortfolioName = stk.getPortFolioNames("FLEXIBLE")[pfNumber];
    int numberOfAvailableShares;

    try {
      numberOfAvailableShares = stk.sharesAvailableOnTheDateForSale(pfNumber, stockSymbol, date,
              "FLEXIBLE");
    } catch (Exception e) {
      output.println("Unable to get remaining share details on the stock for this date.");
      return true;
    }

    if (numberOfAvailableShares == 0) {
      output.println("You cannot sell any shares of this stock on this date as they are already"
              + " sold.");
      return true;
    }

    String[] stockGetData = new String[2];
    stockGetData[0] = stockSymbol;
    stockGetData[1] = date;
    cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stockGetData, 2);
    vciObj.viewControllerInteract(TypeofViews.SHOW_STOCK_DATA, null, 0);

    output.println("\nYou can sell only " + numberOfAvailableShares
            + " shares of this stock on " + date);
    output.println("How many share would you like to sell?\n");

    String sellShares = scan.nextLine();
    while ((sellShares == null || sellShares.length() == 0)
            || (!validateStockSelectOption(sellShares, 1, numberOfAvailableShares))) {
      String[] args = new String[1];
      args[0] = String.valueOf(numberOfAvailableShares);
      vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_INVALID_RETRY, args, 1);
      sellShares = scan.nextLine();
      if (Objects.equals(sellShares, "m") || Objects.equals(sellShares, "M")) {
        return true;
      }
      if (Objects.equals(sellShares, "B") || Objects.equals(sellShares, "b")) {
        return false;
      }
    }

    String[] data = new String[3];
    data[0] = sellShares;
    data[1] = currentPortfolioName;
    cmiObj.controllerModelInteract(TypeofAction.SELL_STOCKS, data, 3);
    output.println("Shares successfully sold.");

    return endMenu();
  }

  /**
   * This method provides the options for users to adds stocks to the portfolio.
   */
  private void addStockToPortfolioMainMenu() {
    StockCompositionData obj = new StockCompositionDataImpl("FLEXIBLE");
    int numberOfPortFolio = obj.getNumberOfPortFolio();
    if (numberOfPortFolio == 0) {
      vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
      return;
    }
    String[] portfolioNames = obj.getPortFolioNames("FLEXIBLE");
    String[] arg = new String[portfolioNames.length + 1];
    System.arraycopy(portfolioNames, 0, arg, 0, portfolioNames.length);
    arg[portfolioNames.length] = "FLEXIBLE";

    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, arg,
            arg.length);
    output.println("\nSelect the portfolio to which you would like to add the stock.");
    String options;
    options = scan.nextLine();
    while ((options == null || options.length() == 0)
            || (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
      options = scan.nextLine();
      if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
        return;
      }
    }
    //addStockToPortfolio(options);
    StockCompositionData stk = new StockCompositionDataImpl("FLEXIBLE");
    currentPortfolioName = stk.getPortFolioNames("FLEXIBLE")[Integer.parseInt(options) - 1];
    String[] args = new String[1];
    args[0] = "addStockScreen";
    performCreatePortfolioMenuAction("1", "1", args, 1);
  }

  /**
   * This method performs the menu actions when user wants to view the value of a portfolio on
   * a certain date.
   */
  private void valueOfPortfolio() {
    while (true) {
      StockCompositionData obj = new StockCompositionDataImpl("ALL");
      int numberOfPortFolio = obj.getNumberOfPortFolio();
      if (numberOfPortFolio == 0) {
        vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
        return;
      }
      String[] portfolioNames = obj.getPortFolioNames("ALL");
      String[] args = new String[portfolioNames.length + 1];
      System.arraycopy(portfolioNames, 0, args, 0, portfolioNames.length);
      args[portfolioNames.length] = "ALL";

      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, args,
              args.length);
      vciObj.viewControllerInteract(TypeofViews.WHICH_PORTFOLIO_CHECK, portfolioNames,
              numberOfPortFolio);
      String options;
      options = scan.nextLine();
      while ((options == null || options.length() == 0)
              || (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
        options = scan.nextLine();
        if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
          return;
        }
      }
      if (!portfolioViewAction(options, "FULL", "ALL")) {
        continue;
      }

      if (endMenu()) {
        return;
      }
    }
  }

  /**
   * This method performs menu action for the case where user wants to view the composition
   * of a portfolio.
   */
  private void compositionOfPortfolio() {
    while (true) {
      StockCompositionData obj = new StockCompositionDataImpl("ALL");
      int numberOfPortFolio = obj.getNumberOfPortFolio();
      if (numberOfPortFolio == 0) {
        vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);
        return;
      }
      String[] portfolioNames = obj.getPortFolioNames("ALL");
      String[] args = new String[portfolioNames.length + 1];
      System.arraycopy(portfolioNames, 0, args, 0, portfolioNames.length);
      args[portfolioNames.length] = "ALL";

      vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, args,
              args.length);
      vciObj.viewControllerInteract(TypeofViews.WHICH_PORTFOLIO_CHECK, portfolioNames,
              numberOfPortFolio);
      String options;
      options = scan.nextLine();
      while ((options == null || options.length() == 0)
              || (!validatePortfolioSelectOption(options, numberOfPortFolio))) {
        vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);
        options = scan.nextLine();
        if (Objects.equals(options, "b") || Objects.equals(options, "B")) {
          return;
        }
      }

      String fileName = obj.getPortFolioFileNameByIndex(Integer.parseInt(options) - 1,
              "ALL");

      if (obj.isPortfolioOfGivenType(fileName, "INFLEXIBLE")) {
        portfolioCompositionAction(options, "ALL");
      } else {
        if (!portfolioViewAction(options, "TRUE", "ALL")) {
          continue;
        }
      }

      if (endMenu()) {
        return;
      }
    }
  }

  /**
   * This method tells the view to display the composition of the portfolio along with the stocks
   * details like name, symbol, quantity, price of each share and total value.
   *
   * @param options the portfolio number
   */
  private void portfolioCompositionAction(String options, String portfolioType) {
    String[] args = new String[2];
    args[0] = options;
    args[1] = portfolioType;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST, args, 2);
  }

  /**
   * This method performs the menu actions for the case where user wants to create a new portfolio.
   *
   * @param option the option which user entered
   */
  private void createAPortfolio(String option) {
    while (true) {
      String type;
      vciObj.viewControllerInteract(TypeofViews.TYPEOF_PORTFOLIO_SCREEN, null, 0);
      type = scan.nextLine();
      while (type.length() == 0 || !(type.equals("1") || type.equals("2"))) {
        vciObj.viewControllerInteract(TypeofViews.NOT_VALID_MAIN_MENU, null, 0);
        type = scan.nextLine();
        if (type.equals("m")) {
          return;
        }
      }

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
        while ((input.length() == 0) || !(input.equals("B") || input.equals("b")
                || input.equals("Y") || input.equals("y") || input.equals("N")
                || input.equals("n"))) {
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

      String[] args = new String[2];
      args[0] = name;
      args[1] = type;
      currentPortfolioName = name;
      createPortfolioNameScreenAction(option, args);
      break;
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

    return val >= 1 && val <= numberOfPortFolio;
  }

  /**
   * After user creates a portfolio with the name, the user has to buy stocks to fill the portfolio
   * with date. This method tells the view to display the options for the user to buy the stocks.
   *
   * @param option the option that user entered after entering name for the portfolio
   * @param args   helper arguments if passed by the callee
   * @return false if the option entered is invalid, else true
   */
  private boolean performCreatePortfolioMenuAction(String option, String portfolioType,
                                                   String[] args, int length) {
    switch (option) {
      case "1": {
        //Buy a new stock
        while (true) {
          vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
          String options;
          options = scan.nextLine();
          StockNameMap snp = new StockNameMapImpl();
          while (!validateStockSelectOption(options, 1, snp.getMapSize())) {
            vciObj.viewControllerInteract(TypeofViews.STOCK_BUY_REENTER, null, 0);
            options = scan.nextLine();
          }
          if (!performListOfStocksMenuAction(options, portfolioType, args, length)) {
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
        break;
      }
      default: {
        output.println("Invalid command. Enter the right option number.");
        return false;
      }
    }
    return true;
  }

  /**
   * A helper method that helps in validating where the option that was entered by the user to buy
   * a stock is valid or not.
   *
   * @param option the stock option that was entered by the user
   * @return true if the input is valid, else false
   */
  private boolean validateStockSelectOption(String option, int minVal, int maxSize) {
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

    return val >= minVal && val <= maxSize;
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

    return val > 0;
  }
}
