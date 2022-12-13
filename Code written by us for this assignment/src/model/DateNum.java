package model;

import java.util.Comparator;

/**
 * This class represents date history of buying shares.
 */
public class DateNum implements Comparator<DateNum> {

  private String date;
  private String num;

  /**
   * A default constructor of DateNum class.
   */
  public DateNum() {
    date = null;
    num = null;
  }

  /**
   * A constructor of DataNum class and designates value of date and num.
   *
   * @param date date of purchase
   * @param num  the number of shares to buy
   */
  public DateNum(String date, String num) {
    this.date = date;
    this.num = num;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getDateNum() {
    return "(" + getDate() + ":" + getNum() + ")";
  }

  @Override
  public int compare(DateNum o1, DateNum o2) {
    return o1.getDate().compareTo(o2.getDate());
  }
}
