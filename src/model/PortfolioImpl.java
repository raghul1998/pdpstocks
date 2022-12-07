package model;

import java.util.ArrayList;

/**
 * This class implement portfolio and provides functions for adding, buying, selling, getting value
 * of new stocks.
 */
public class PortfolioImpl implements IPortfolioImpl {

  private ArrayList<Portfolio> portfolios;
  private double invested;
  private double profit;
  private Commission commission = new Commission();

  public PortfolioImpl() {
    portfolios = new ArrayList<>();
  }

  public PortfolioImpl(ArrayList<Portfolio> data) {
    portfolios = data;
  }

  @Override
  public void insertPortfolioWithDate(String company, String symbol, String sNum, String date) {
    double value = Double.parseDouble(getValue(symbol, date));
    double num = Double.parseDouble(sNum);
    double money = value * num;

    Portfolio portfolio = getPortfolio(symbol);
    if (portfolio != null) { // This symbol already existed in the portfolios
      double total = Double.parseDouble(portfolio.getValueOfPurchase())
              * Double.parseDouble(portfolio.getNum()) + value * num;
      double avg = total / (Double.parseDouble(portfolio.getNum()) + num);
      setInvestedForBuy(money + commission.getFee());
      double tmp = Double.parseDouble(portfolio.getNum()) + num;
      tmp = Math.round(tmp * 100) / 100.0;
      portfolio.setNum(String.valueOf(tmp));
      portfolio.setValueOfPurchase(String.format("%.4f", avg));
      portfolio.setTotalValueOfPurchase();
      if (date.compareTo(portfolio.getDateOfPurchase()) > 0) {
        portfolio.setDateOfPurchase(date);
      }

      if (!portfolio.setDateNum(date, String.valueOf(num))) {
        portfolio.addDateNum(date, String.valueOf(num));
      }
      portfolio.toDateNumsStr();
      return;
    }

    setInvestedForBuy(money + commission.getFee());
    portfolio = new Portfolio(company, symbol, String.valueOf(num), date, getValue(symbol, date));
    portfolios.add(portfolio);
  }

  @Override
  public void insertPortfolioWithDateAndValue(String company, String symbol, String sNum,
                                              String date, String value) {
    double num = Double.parseDouble(sNum);
    double money = Double.parseDouble(value) * num;

    Portfolio portfolio = getPortfolio(symbol);
    if (portfolio != null) { // This symbol already existed in the portfolios
      double total =
              Double.parseDouble(portfolio.getValueOfPurchase())
                      * Double.parseDouble(portfolio.getNum()) + Double.parseDouble(value) * num;
      double avg = total / (Double.parseDouble(portfolio.getNum()) + num);
      setInvestedForBuy(money + commission.getFee());
      double tmp = Double.parseDouble(portfolio.getNum()) + num;
      tmp = Math.round(tmp * 100) / 100.0;
      portfolio.setNum(String.valueOf(tmp));
      portfolio.setValueOfPurchase(String.format("%.4f", avg));
      portfolio.setTotalValueOfPurchase();
      if (date.compareTo(portfolio.getDateOfPurchase()) > 0) {
        portfolio.setDateOfPurchase(date);
      }

      if (!portfolio.setDateNum(date, String.valueOf(num))) {
        portfolio.addDateNum(date, String.valueOf(num));
      }
      return;
    }

    setInvestedForBuy(money + commission.getFee());
    portfolio = new Portfolio(company, symbol, String.valueOf(num), date, value);
    portfolios.add(portfolio);
  }

  @Override
  public String sellPortfolio(String symbol, int num, String date) {
    Portfolio portfolio = getPortfolio(symbol);
    double money;

    money = Double.parseDouble(getValue(symbol, date)) * num;
    profit = money;

    boolean count = portfolio.removeDateNum(num);
    if (count) {
      System.out.println("You sold all shares of " + symbol);
    }

    double getN = Double.parseDouble(portfolio.getNum());
    if (getN == num) {
      portfolios.remove(portfolio);
    } else {
      portfolio.setNum(String.valueOf(getN - num));
    }

    setInvestedForSell();
    portfolio.setTotalValueOfPurchase();

    return String.valueOf(money);
  }

  @Override
  public Portfolio getPortfolio(String symbol) {
    for (Portfolio portfolio : portfolios) {
      if (portfolio.getSymbol().equals(symbol)) {
        return portfolio;
      }
    }
    return null;
  }

  @Override
  public ArrayList<Portfolio> getPortfolios() {
    return portfolios;
  }

  /**
   * This method gets value of share from local csv files and/or api.
   *
   * @param symbol company symbol
   * @param date   date input
   * @return value of shares
   */
  @Override
  public String getValue(String symbol, String date) {
    String value;

    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
    value = avtsd.getValue(symbol, date);

    return value;
  }

  /**
   * This method checks if input value undermines the real value.
   *
   * @param symbol company symbol
   * @param value  input value
   * @param date   input date
   * @return -1: invalid value
   */
  @Override
  public String checkValue(String symbol, String value, String date) {
    try {
      if (Double.parseDouble(value) >= Double.parseDouble(getValue(symbol, date))) {
        return value;
      }
    } catch (Exception e) {
      return "-1";
    }
    return "-1";
  }

  @Override
  public double getInvested() {
    return invested;
  }

  public void setInvested(double invested) {
    this.invested = invested;
  }

  @Override
  public void setInvestedForBuy(double invested) {
    this.invested += invested;
  }

  @Override
  public void setInvestedForSell() {
    this.invested += commission.getFee();
  }

  @Override
  public int getSize() {
    return portfolios.size();
  }

  public double getProfit() {
    return profit;
  }
}
