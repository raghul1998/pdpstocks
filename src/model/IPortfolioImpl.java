package model;

import java.util.ArrayList;

/**
 * Interface which declares all the methods required for inserting new stocks, buying, selling and
 * getting values.
 */
public interface IPortfolioImpl {

  /**
   * Method to insert portfolio with just the date.
   */
  void insertPortfolioWithDate(String company, String symbol, String num, String date);

  /**
   * Method to insert portfolio with the date and user value.
   */
  void insertPortfolioWithDateAndValue(String company, String symbol, String num,
                                       String date, String value);

  /**
   * Method to sell stocks in a portfolio.
   */
  String sellPortfolio(String symbol, int num, String date);

  /**
   * Method to get instance of a portfolio.
   */
  Portfolio getPortfolio(String symbol);

  /**
   * Method to get values in a portfolio.
   */
  ArrayList<Portfolio> getPortfolios();

  /**
   * Method to get value of as stock on a date.
   */
  String getValue(String symbol, String date);

  /**
   * Method to check value on a particular date.
   */
  String checkValue(String symbol, String value, String date);

  /**
   * Method to get invested value.
   */
  double getInvested();

  /**
   * Method to set investment value after buying.
   */
  void setInvestedForBuy(double invested);

  /**
   * Method to set investment value after selling.
   */
  void setInvestedForSell();

  /**
   * Method to get number of stocks.
   */
  int getSize();
}
