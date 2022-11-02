package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the supported stocks and the methods that can be called upon related to
 * the supported stocks. This class has the following variable.
 * <ul>
 *   <li>stockNameMap - hashmap of supported stock names and its ticker symbols</li>
 * </ul>
 */
public class StockNameMap {
  private final Map<String, String> stockNameMap = new HashMap<>();

  /**
   * Constructor that reads the 'SupportedStocks.csv' file and reads the list of supported stocks
   * and creates a hashmap of the stock name, and it's ticker symbol.
   */
  public StockNameMap() {
    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    try {
      stockData = new BufferedReader(new FileReader("data/SupportedStocks.csv"));
    } catch (Exception e) {
      System.out.println("Supported stocks file not found " + e.getMessage());
    }

    boolean isTitleRead = false;
    try {
      while ((line = stockData.readLine()) != null) {
        if (isTitleRead) {
          String[] row = line.split(splitBy);
          this.stockNameMap.put(row[2], row[1]);
          continue;
        }
        isTitleRead = true;
      }
      stockData.close();
    } catch (Exception e) {
      System.out.println("Error in reading Supported stocks csv file");
    }
  }

  /**
   * This method provides the hashmap of the stock mapping that was created by the constructor.
   *
   * @return the hashmap of the supported stocks data
   */
  public Map<String, String> getMap() {
    return stockNameMap;
  }

  /**
   * This method tell the number of stocks supported by the application.
   *
   * @return the size of the hashmap as an integer
   */
  public int getMapSize() {
    return stockNameMap.size();
  }

}