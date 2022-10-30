package controller;

import java.io.File;
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

public class ControllerViewInteractImpl implements ControllerViewInteract {
  ControllerModelInteract cmiObj = new ControllerModelInteractImpl();
  ViewControllerInteract vciObj = new ViewControllerInteractImpl();
  private String currentPortfolioName;

  @Override
  public void start() {
    String option;
    Scanner scan = new Scanner(System.in);
    do {
      vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);
      option = scan.nextLine();
      performMainMenuAction(option);
    } while (!option.equals("e"));
  }

  private void portfolioCompositionAction(String options) {
    String[] args = new String[1];
    args[0] = options;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST, args, 1);
  }

  private boolean portfolioViewAction(String options) {
    String[] args = new String[2];
    args[0] = options;

    System.out.println("Enter the year in format (YYYY-MM-DD) (2000 to "
            + LocalDate.now().getYear() + "): ");
    String date;
    Scanner scan = new Scanner(System.in);
    date = scan.nextLine();
    while (!validateDate(date)) {
      vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);
      date = scan.nextLine();
      if (Objects.equals(date, "b")) {
        return false;
      }
    }
    args[1] = date;
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST_WITH_DATE, args, 1);
    return true;
  }

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
      Scanner scan = new Scanner(System.in);
      option = scan.nextLine();
      if (performCreatePortfolioMenuAction(option, null)) {
        break;
      }
    }
  }

  private void buyAnotherStockMenuAction(String options) {
    if (Objects.equals(options, "Y") || Objects.equals(options, "y")) {
      //vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
      performCreatePortfolioMenuAction("1", null);
    } else {
      String[] args = new String[1];
      args[0] = currentPortfolioName;
      vciObj.viewControllerInteract(TypeofViews.DISPLAY_PORTFOLIO_CREATED, args, 0);
    }
  }

  private void buyStockValueMenuAction(String option) {
    if (Objects.equals(option, "m")) {
      cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
      return;
    }
    String[] stock = new String[1];
    stock[0] = option;
    cmiObj.controllerModelInteract(TypeofAction.BUY_STOCKS, stock, 1);
    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK, null, 0);
    String options;
    Scanner scan = new Scanner(System.in);
    options = scan.nextLine();
    while ((options == null || options.length() == 0) || ((!options.equals("Y")) &&
            (!options.equals("y")) && (!options.equals("N")) && (!options.equals("n")))) {
      vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);
      options = scan.nextLine();
    }
    buyAnotherStockMenuAction(options);
  }

  private boolean performListOfStocksMenuAction(String options, String[] args, int length) {

    if (Objects.equals(options, "0")) {
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
    Scanner scan = new Scanner(System.in);
    option = scan.nextLine();
    while ((option == null || option.length() == 0) || (!validateBuyStockOption(option))) {
      if (Objects.equals(option, "b")) {
        cmiObj.controllerModelInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);
        return true;
      }
      vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_INVALID_RETRY, null, 0);
      option = scan.nextLine();
    }
    buyStockValueMenuAction(option);
    return false;
  }

  private void performMainMenuAction(String option) {
    switch (option) {
      case "1": {
        // Create a portfolio
        while (true) {
          String name;
          Scanner scan = new Scanner(System.in);
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
          Scanner scan = new Scanner(System.in);
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
          Scanner scan = new Scanner(System.in);
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
        System.out.println("\nExiting...");
        exit(0);
      }
      default: {
        System.out.println("Invalid command. Enter the right option number.");
        try {
          sleep(500);
        } catch (Exception e) {
          // Exception while calling sleep
        }
        break;
      }
    }
  }

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

  private boolean performCreatePortfolioMenuAction(String option, String[] args) {
    switch (option) {
      case "1": {
        //Buy a new stock
        while (true) {
          vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);
          String options;
          Scanner scan = new Scanner(System.in);
          options = scan.nextLine();
          while (!validateStockSelectOption(options)) {
            vciObj.viewControllerInteract(TypeofViews.STOCK_BUY_REENTER, null, 0);
            options = scan.nextLine();
          }
          if (!performListOfStocksMenuAction(options, null, 0)) {
            break;
          }
        }
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
        System.out.println("\nExiting...");
        exit(0);
      }
      default: {
        System.out.println("Invalid command. Enter the right option number.");
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

    StockNameMap snp = new StockNameMap();
    if (val < 0 || val > snp.getMapSize()) {
      return false;
    } else {
      return true;
    }
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
}
