package supportedstocks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class StockNameMap {
  public Map<String, String> stockNameMap = new HashMap<>();

  public StockNameMap() {
    String line = "";
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

  public Map<String, String> getMap() {
    return stockNameMap;
  }

  public int getMapSize() {
    return stockNameMap.size();
  }

}