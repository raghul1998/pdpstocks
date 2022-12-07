package model;

import java.util.ArrayList;

/**
 * This interface represents creating passive investment portfolio with the entered information.
 */
public interface IPassiveInvest {
  /**
   * This method creates a portfolio of passive investment.
   *
   * @param companyList name list of companies to buy
   * @param symbolList  symbol list of companies to buy
   * @param portionList portion list of each company
   * @param amount      invest money on each month
   * @param freq        frequency of buying and the unit is date
   * @param range       start and finish date to buy
   */
  void buyPassive(String path, ArrayList<String> companyList, ArrayList<String> symbolList,
                  ArrayList<Integer> portionList, String amount, int freq, ArrayList<String> range);
}
