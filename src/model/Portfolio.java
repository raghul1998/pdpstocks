package model;

import java.util.ArrayList;

/**
 * This class represents a single portfolio.
 * It has getter and setter for all the data stored for each portfolio.
 */
public class Portfolio {
  public String valueOfPurchase;
  public String totalValueOfPurchase;
  public ArrayList<DateNum> dateNumsList = new ArrayList<>();
  public String dateNumsStr;
  private String company;
  private String symbol;
  private String num;
  private String dateOfPurchase;

  public Portfolio() {
    //Constructor with no arguments for Portfolio class.
  }

  /**
   * This constructs a portfolio.
   *
   * @param company         company name
   * @param symbol          company symbol
   * @param num             num of shares
   * @param date            date of purchase
   * @param valueOfPurchase value of purchase
   */
  public Portfolio(String company, String symbol, String num, String date, String valueOfPurchase) {
    this.company = company;
    this.symbol = symbol;
    this.num = num;
    this.dateOfPurchase = date;
    this.valueOfPurchase = valueOfPurchase;
    this.setTotalValueOfPurchase();
    dateNumsList.add(new DateNum(date, num));
    toDateNumsStr();
  }

  /**
   * This method sets total value of purchase.
   */
  public void setTotalValueOfPurchase() {
    double total;
    total = Double.parseDouble(num) * Double.parseDouble(valueOfPurchase);
    total = Math.round(total * 100) / 100.0;
    this.totalValueOfPurchase = String.valueOf(total);
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getDateOfPurchase() {
    return dateOfPurchase;
  }

  public void setDateOfPurchase(String dateOfPurchase) {
    this.dateOfPurchase = dateOfPurchase;
  }

  public String getValueOfPurchase() {
    return valueOfPurchase;
  }

  public void setValueOfPurchase(String valueOfPurchase) {
    this.valueOfPurchase = valueOfPurchase;
  }

  public String getTotalValueOfPurchase() {
    return totalValueOfPurchase;
  }

  /**
   * This method set buying history of specific shares.
   *
   * @param date date of purchase
   * @param num  the number of shares to buy
   * @return true: if existed date, false: otherwise
   */
  public boolean setDateNum(String date, String num) {
    for (DateNum dateNum : dateNumsList) {
      if (dateNum.getDate().equals(date)) {
        double add = Double.parseDouble(dateNum.getNum()) + Double.parseDouble(num);
        dateNum.setNum(String.valueOf(add));
        return true;
      }
    }
    return false;
  }

  /**
   * This method add buying history to trace.
   *
   * @param date date of purchase
   * @param num  the number of shares to buy
   */
  public void addDateNum(String date, String num) {
    dateNumsList.add(new DateNum(date, num));
    dateNumsList.sort(new DateNum());
    toDateNumsStr();
  }

  public ArrayList<DateNum> getDateNumsList() {
    return dateNumsList;
  }


  /**
   * This method removes buying history due to selling shares.
   *
   * @param sell the number of shares that user is holding
   * @return true: if user sells all shares of this company, false: otherwise
   */
  public boolean removeDateNum(double sell) {
    ArrayList<Integer> del = new ArrayList<Integer>();

    if (sell == Double.parseDouble(num)) {
      dateNumsList.clear();
      return true;
    }

    for (int i = 0; i < dateNumsList.size(); i++) {
      if (sell < Double.parseDouble(dateNumsList.get(i).getNum())) {
        dateNumsList.get(i).setNum(
                String.valueOf(Double.parseDouble(dateNumsList.get(i).getNum()) - sell));
      } else {
        sell -= Double.parseDouble(dateNumsList.get(i).getNum());
        del.add(i);
      }
      if (sell == 0) {
        break;
      }
    }

    int count = 0;

    for (int i = 0; i < del.size(); i++) {
      int idx = del.get(i);
      idx = idx - count;
      dateNumsList.remove(idx);
      count++;
    }
    toDateNumsStr();

    return false;
  }

  /**
   * This method converts dateNumsList(Array List) to dateNumsStr(String).
   */
  public void toDateNumsStr() {
    StringBuilder str = new StringBuilder();
    int i;

    for (i = 0; i < dateNumsList.size() - 1; i++) {
      str.append(dateNumsList.get(i).getDateNum()).append(",");
    }
    str.append(dateNumsList.get(i).getDateNum());

    dateNumsStr = String.valueOf(str);
  }

  /**
   * This method converts dateNumsList(Array List) to dateNumsStr(String).
   *
   * @param dateNumsStr string type of buying history
   */
  public void toDateNumList(String dateNumsStr) {
    String[] tokens = dateNumsStr.split(",");

    for (String token : tokens) {
      token = token.substring(1, token.length() - 1);
      String[] dateNum = token.split(":");
      dateNumsList.add(new DateNum(dateNum[0], dateNum[1]));
    }
  }

  public String getDateNumsStr() {
    return dateNumsStr;
  }

  public void setDateNumsStr(String dateNumsStr) {
    this.dateNumsStr = dateNumsStr;
  }
}
