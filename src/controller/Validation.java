package controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import model.AlphaVantageTimeSeriesDaily;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * This class represents validation methods required to provide
 * necessary validations wherever required.
 */
public class Validation {

  /**
   * This method checks the validation of input date.
   *
   * @param date date input from the user
   * @return true: if corrects input, false: otherwise
   */
  public boolean validateDate(String symbol, String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate today = LocalDate.now(ZoneId.of("US/Eastern"));
      LocalDate parse = LocalDate.parse(date, formatter);

      if (new AlphaVantageTimeSeriesDaily().getValue(symbol, date) == null) {
        System.out.println("The market is closed!");
        return false;
      }

      if (parse.isBefore(today)) {
        return true;
      }

      System.out.println("Date should be before today!");
      return false;
    } catch (DateTimeParseException ignored) {
      if (date.equals("N")) {
        return false; // for passive invest finish date
      }
      System.out.println("The format of date is wrong!");
      return false;
    }
  }

  /**
   * Method for validation of selling a stock.
   *
   * @param pfImpl the portfolio object
   * @param symbol symbol of company to sell
   * @param sDate  date for sale.
   * @return true if sale is valid.
   */
  public boolean validateDateForSell(PortfolioImpl pfImpl, String symbol, String sDate) {
    Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Portfolio portfolio = pfImpl.getPortfolio(symbol);

    try {
      date = formatter.parse(sDate);
      if (sDate.compareTo(portfolio.getDateNumsList().get(0).getDate()) < 0) {
        System.out.println("It can't be before the date of first purchase!");
        return false;
      }
      return true;
    } catch (ParseException e) {
      System.out.println("The format of date is wrong!");
      return false;
    }
  }

  /**
   * This method checks the validation of input number.
   *
   * @param num number input from the user
   * @return true: if corrects input, false: otherwise
   */
  public boolean validateNum(String num) {
    try {
      int n = Integer.parseInt(num);
      return n <= 0;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  /**
   * The method checks the validation of input path.
   *
   * @param path path input from the user
   * @return true: if corrects input, false: otherwise
   */
  public boolean validateXmlPath(String path) {
    File file = new File(path);
    return file.exists();
  }

  /**
   * This method checks the validation of symbol to sell.
   *
   * @param portfolios portfolioImpl to sell
   * @param symbol     symbol to sell
   * @return portfolio to sell
   */
  public Portfolio validateSymbolForSell(PortfolioImpl portfolios, String symbol) {
    for (Portfolio portfolio : portfolios.getPortfolios()) {
      if (portfolio.getSymbol().equals(symbol)) {
        return portfolio;
      }
    }

    return null;
  }

  /**
   * This method checks the validation of the number to sell.
   *
   * @param portfolio portfolioImpl to sell
   * @param date      certain date to sell
   * @param num       the number of shares to sell
   * @return true: possible to sell, false: otherwise
   */
  public boolean validateNumForSell(Portfolio portfolio, String date, String num) {
    int hold = 0;
    boolean ret = validateNum(num);

    if (ret) {
      return false;
    }

    for (int i = 0; i < portfolio.getDateNumsList().size(); i++) {
      if (portfolio.getDateNumsList().get(i).getDate().compareTo(date) > 0) {
        break;
      }
      hold += Double.parseDouble(portfolio.getDateNumsList().get(i).getNum());
    }
    return hold >= Integer.parseInt(num);
  }

  /**
   * This method checks the type of portfolio.
   *
   * @param path path to load portfolio
   * @return 1: infelxible, 2: flexible
   */
  public int typePortfolioFile(String path) {
    if (path.indexOf("inflexible") == -1) {
      return 2;
    }
    return 1;
  }

  /**
   * Method to validate the input amount.
   *
   * @param amount the value to validate.
   * @return true if valid.
   */
  public boolean validateAmount(String amount) {
    double dAmount = Double.parseDouble(amount);
    return dAmount > 0;
  }

  /**
   * Method to check id the symbol already exists.
   *
   * @param symbolList List of symbols already in portfolio.
   * @param symbol     the current symbol.
   * @return true if the symbol is unique.
   */
  public boolean validateDuplicatedSymbol(ArrayList<String> symbolList, String symbol) {
    for (int i = 0; i < symbolList.size(); i++) {
      if (symbol.equals(symbolList.get(i))) {
        System.out.println(symbol + " is already included!");
        return false;
      }
    }
    return true;
  }
}
