package controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JFrame;

import model.AlphaVantageTimeSeriesDaily;
import model.CreateXML;
import model.ImportXML;
import model.MVCModel;
import model.PassiveInvest;
import model.Portfolio;
import model.PortfolioImpl;
import view.MVCView;
import view.MainMenu;

/**
 * This class represents controller and this interacts with model
 * and view to provide relevant functions.
 */
public class MVCCommandController {

  private final PrintStream output;
  Scanner sc;
  ArrayList<Portfolio> stock = new ArrayList<>();
  private MVCModel model;
  private MVCView commandView;
  private PortfolioImpl portfolios;

  /**
   * A construct for MVCCommandController class.
   */
  public MVCCommandController() {
    sc = new Scanner(System.in);
    this.output = new PrintStream(System.out);
  }

  /**
   * A constructor for MVCCommandController class which is called by Main class.
   *
   * @param model       model of MVC pattern
   * @param commandView view of MVC pattern
   */
  public MVCCommandController(MVCModel model, MVCView commandView) {
    this.model = model;
    this.commandView = commandView;
    sc = new Scanner(System.in);
    this.output = new PrintStream(System.out);

    portfolios = model.portfolioImpl;
    CreateXML createXML = model.createXML;
    ImportXML importXML = model.importXML;
  }

  /**
   * A construct for MVCCommandController class with input and output stream objects as input.
   */
  MVCCommandController(InputStream input, PrintStream output) {
    this.output = output;
    sc = new Scanner(input);
  }

  /**
   * A construct for MVCCommandController class with input and output stream objects, model and view
   * as parameters.
   */
  public MVCCommandController(MVCModel model, MVCView commandView, InputStream input,
                              PrintStream output) {
    this.model = model;
    this.commandView = commandView;
    this.output = output;
    sc = new Scanner(input);
    portfolios = model.portfolioImpl;
  }

  /**
   * Method to choose the gui type.
   */
  public void chooseGUI() {
    this.output.println("Which type of gui you want to choose");
    this.output.println("1. Swing based");
    this.output.println("2. Terminal based");
    int ch = inputInteger(null);
    if (ch == 1) {
      JFrame menu = new MainMenu();
      menu.setVisible(true);
    } else if (ch == 2) {
      menu();
    } else {
      this.output.println("Invalid Input!");
    }
  }

  /**
   * This method displays main menu and gets input for the menu.
   */
  public void menu() {
    this.output.println("Menu:");
    this.output.println("1. Create portfolio");
    this.output.println("2. Import portfolio");
    this.output.println("3. Create passive invest portfolio");
    this.output.println("4. Exit");
    this.output.print(": ");

    int input = inputInteger(null);

    switch (input) {
      case 1:
        portfolios = new PortfolioImpl();
        boolean count = true;
        while (count) {
          count = userInput(count);
        }
        break;
      case 2:
        this.output.println("Select the type of portfolio to import :");
        this.output.println("1. Inflexible Portfolio");
        this.output.println("2. Flexible Portfolio");
        int choice = inputInteger(null);
        if (choice == 1) {
          goToMenuOrCompoInflexible(stock);
        } else if (choice == 2) {
          goToMenuOrCompo(stock);
        } else {
          this.output.println("Invalid Input!");
        }
        break;
      case 3:
        userInputPassiveInvest(null);
        break;
      case 4:
        this.output.println("Thank you for using!");
        break;
      default:
        this.output.println("Invalid Input!");
        menu();
    }
  }

  private boolean userInput(boolean count) {

    this.output.println("Enter the name of the Company");
    String company = sc.next();

    this.output.println("Enter the symbol of the Company");
    String symbol = sc.next();
    while (new AlphaVantageTimeSeriesDaily().openResource(symbol) == null) {
      this.output.println(symbol + " is not listed in the market!");
      symbol = sc.next();
    }

    this.output.println("Enter the number of stocks");
    String numTmp = sc.next();
    while (new Validation().validateNum(numTmp)) {
      this.output.println("The input number is wrong!");
      numTmp = sc.next();
    }
    int num = Integer.parseInt(numTmp);

    this.output.println("Enter the date of purchase[in YYYY-MM-DD format]");
    String date = sc.next();
    while (!new Validation().validateDate(symbol, date)) {
      date = sc.next();
    }
    while (new AlphaVantageTimeSeriesDaily().getValue(symbol, date) == null) {
      this.output.println("The stock market is closed on this date!");
      date = sc.next();
    }

    this.output.println("Enter the value of stock. Buy at today's price, enter 'N'");
    String value = sc.next();
    if (value.equalsIgnoreCase("N")) {
      portfolios.insertPortfolioWithDate(company, symbol, String.valueOf(num), date);
    } else {
      while (Objects.equals(portfolios.checkValue(symbol, value, date), "-1")) {
        this.output.println("Invalid value or The value is greater than the real value!");
        value = sc.next();
      }
      portfolios.insertPortfolioWithDateAndValue(company, symbol, String.valueOf(num), date, value);
    }
    this.output.println("Add another stock? Y or N");
    String choice = sc.next();
    while (!choice.equals("Y") && !choice.equals("N")) {
      this.output.println("Enter Y or N only");
      choice = sc.next();
    }

    if (choice.equals("N")) {
      count = false;
      this.output.println("Select the type of portfolio you want to create:");
      this.output.println("1. Inflexible Portfolio");
      this.output.println("2. Flexible Portfolio");
      int input = inputInteger(null);

      if (input == 1) {
        model.saveFile(portfolios, 1);
        goToMenuOrCompoInflexible(portfolios.getPortfolios());
      } else {
        model.saveFile(portfolios, 2);
        goToMenuOrCompo(portfolios.getPortfolios());
      }
    }
    return count;
  }

  private boolean userInputForSell(String path) {
    Portfolio portfolio;

    this.output.println("Enter the symbol of the Company to sell");
    String symbol = sc.next();
    portfolio = new Validation().validateSymbolForSell(portfolios, symbol);
    while (portfolio == null) {
      this.output.println(symbol + " is not in your portfolio");
      symbol = sc.next();
      portfolio = new Validation().validateSymbolForSell(portfolios, symbol);
    }

    this.output.println("Enter the date[in YYYY-MM-DD format]");
    String date = sc.next();
    while (!new Validation().validateDateForSell(portfolios, symbol, date)) {
      date = sc.next();
    }

    this.output.println("Enter the number of stocks to sell");
    String str = sc.next();
    while (!new Validation().validateNumForSell(portfolio, date, str)) {
      this.output.println("Input number is invalid to sell!");
      str = sc.next();
    }

    int num = Integer.parseInt(str);
    str = portfolios.sellPortfolio(symbol, num, date);

    boolean count = true; // duplicated codes
    this.output.println("Sell another stock? Y or N");
    String choice = sc.next();
    while (!choice.equals("Y") && !choice.equals("N")) {
      this.output.println("Enter Y or N only");
      choice = sc.next();
    }

    if (choice.equals("N")) {
      count = false;
      int type = new Validation().typePortfolioFile(path);
      model.saveFile(portfolios, type);
      goToMenuOrCompo(portfolios.getPortfolios());
    }

    return count;
  }

  private void goToMenuOrCompo(ArrayList<Portfolio> stockData) {
    this.output.println("Menu:");
    this.output.println("1. Buy shares");
    this.output.println("2. Sell shares");
    this.output.println("3. Examine the composition of a portfolio");
    this.output.println("4. View the total value of a portfolio on a certain date");
    this.output.println("5. View portfolio performance");
    this.output.println("6. Invest dollar-cost averaging");
    this.output.println("7. Go back to main menu");
    this.output.print(": ");

    int input = inputInteger(null);

    if (input == 7) {
      stock = new ArrayList<>();
      menu();
      return;
    }

    this.output.println("Enter the path of the file(ex. flexible_1.xml):");
    String path = sc.next();
    while (!new Validation().validateXmlPath(path)) {
      this.output.println("Path is wrong!");
      path = sc.next();
    }
    int type = new Validation().typePortfolioFile(path);
    portfolios = model.buildDocument(path);

    if (input == 1) {
      boolean count = true;
      while (count) {
        count = userInput(count);
      }
      return;
    } else if (input == 2) {
      boolean count = true;
      while (count) {
        count = userInputForSell(path);
      }
      return;
    } else if (input == 6) {
      userInputPassiveInvest(path);
      return;
    }

    this.output.println("Enter the date[in YYYY-MM-DD format]");
    String date = sc.next();
    while (!new Validation().validateDate("GOOG", date)) {
      date = sc.next();
    }

    switch (input) {
      case 3:
        commandView.examinePortfolioComposition(path, date);
        goToMenuOrCompo(stockData);
        break;
      case 4:
        commandView.displayTotalValueOfPortfolio(path, date);
        goToMenuOrCompo(stockData);
        break;
      case 5:
        commandView.viewPerformance(path, date);
        goToMenuOrCompo(stockData);
        break;
      default:
        this.output.println("Invalid Input");
        goToMenuOrCompo(stockData);
    }
  }

  private void goToMenuOrCompoInflexible(ArrayList<Portfolio> stockData) {
    this.output.println("Menu:");
    this.output.println("1. Examine the composition of a portfolio");
    this.output.println("2. View the total value of a portfolio on a certain date");
    this.output.println("3. View portfolio performance");
    this.output.println("4. Go back to main menu");
    this.output.print(": ");

    int input = inputInteger(null);

    if (input == 4) {
      stock = new ArrayList<>();
      menu();
      return;
    }

    this.output.println("Enter the path of the file(ex. inflexible_1.xml):");
    String path = sc.next();
    while (!new Validation().validateXmlPath(path)) {
      this.output.println("Path is wrong!");
      path = sc.next();
    }
    int type = new Validation().typePortfolioFile(path);

    this.output.println("Enter the date[in YYYY-MM-DD format]");
    String date = sc.next();
    while (!new Validation().validateDate("GOOG", date)) {
      date = sc.next();
    }

    switch (input) {
      case 1:
        commandView.examinePortfolioComposition(path, date);
        goToMenuOrCompoInflexible(stockData);
        break;
      case 2:
        commandView.displayTotalValueOfPortfolio(path, date);
        goToMenuOrCompoInflexible(stockData);
        break;
      case 3:
        commandView.viewPerformance(path, date);
        goToMenuOrCompoInflexible(stockData);
        break;
      default:
        this.output.println("Invalid Input");
        goToMenuOrCompoInflexible(stockData);
    }
  }

  private int inputInteger(String input) {
    int num = 0;
    try {
      num = Integer.parseInt(sc.next());
    } catch (Exception e) {
      this.output.println("Invalid input");
      return inputInteger(sc.next());
    }
    return num;
  }

  private void userInputPassiveInvest(String path) {
    ArrayList<String> companyList = new ArrayList<>();
    ArrayList<String> symbolList = new ArrayList<>();
    ArrayList<Integer> portionList = new ArrayList<>();
    ArrayList<String> range = new ArrayList<>();

    String company = "A";
    while (!company.equals("N")) {
      this.output.println("Enter the list of companies to buy. Enter N to stop.");
      company = sc.next();
      companyList.add(company);
    }

    if (companyList.get(0).equals("N")) {
      this.output.println("You didn't add any company to buy!");
      menu();
      return;
    }

    int cnt = 1;
    while (cnt < companyList.size()) {
      this.output.println("Enter the list of symbols to buy.");
      String symbol = sc.next();
      if (new AlphaVantageTimeSeriesDaily().openResource(symbol) == null) {
        this.output.println(symbol + " is not listed in the market.");
        continue;
      }
      if (!new Validation().validateDuplicatedSymbol(symbolList, symbol)) {
        continue;
      }
      symbolList.add(symbol);
      cnt++;
    }

    String portion = null;
    int num = 0;
    boolean tf = true;
    for (int i = 0; i < symbolList.size(); i++) {
      this.output.println("Enter the portion for " + symbolList.get(i) + " in integer.");
      portion = sc.next();
      tf = new Validation().validateNum(portion);
      while (tf) {
        this.output.println("Portion input is invalid!");
        portion = sc.next();
        tf = new Validation().validateNum(portion);
      }
      num = Integer.parseInt(portion);
      portionList.add(num);
    }

    this.output.println("Enter the amount of money on each case.");
    String amount = sc.next();
    while (!new Validation().validateAmount(amount)) {
      this.output.println("The format of amount is invalid!");
      amount = sc.next();
    }

    this.output.println("Enter the frequency in integer.");
    String freq = sc.next();
    while (new Validation().validateNum(freq)) {
      this.output.println("The input is not positive integer!");
      freq = sc.next();
    }

    this.output.println("Enter start date.");
    String date = sc.next();
    while (!new Validation().validateDate("GOOG", date)) {
      this.output.println("Start date is invalid!");
      date = sc.next();
    }
    range.add(date);

    this.output.println("Enter finish date. Enter 'N' to not designate.");
    date = sc.next();
    while (!new Validation().validateDate("GOOG", date) ||
            date.compareTo(range.get(0)) < 0) {
      if (date.equals("N")) {
        break;
      }
      this.output.println("Finish date in invalid!");
      date = sc.next();
    }
    range.add(date);

    PassiveInvest pi = new PassiveInvest();
    pi.buyPassive(path, companyList, symbolList, portionList, amount,
            Integer.parseInt(freq), range);
    goToMenuOrCompo(portfolios.getPortfolios());
  }
}
