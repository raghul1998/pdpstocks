package model;

/**
 * Interface with all the methods related to stock data access.
 */
public interface IAlphaVantage {
  /**
   * This method gets the stock value from the local data files.
   *
   * @param symbol symbol of company
   * @param date   input date
   * @return the value of stock
   */
  String getValue(String symbol, String date);

  /**
   * This saves the csv file for a particular symbol locally.
   * Saves the csv with symbol as its name.
   *
   * @param symbol ticker symbol of the company.
   */
  void saveCSV(String symbol);

  /**
   * This method just opens the locally stored csv file.
   *
   * @param symbol symbol of the company.
   * @return the data in string format.
   */

  StringBuilder openCSV(String symbol);

  /**
   * This method reads locally stored data files.
   *
   * @param symbol symbol of company
   * @return contents of data file
   */

  StringBuilder openResource(String symbol);
}
