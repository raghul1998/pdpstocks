package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * This class represents the stock data and methods that can be called upon to get the value of a
 * certain stock using APIs.
 */
public class GetStockData {
  /**
   * This method helps in getting the real-time value of a stock by performing an API call to the
   * Alpha Vantage.
   *
   * @param stock the ticker symbol of the stock that the data is required for
   */
  public void getValue(String stock) {
    StockNameMap snp = new StockNameMap();
    Map<String, String> stockMap = snp.getMap();
    String apiKey = "L6T2FNC0UY2K71XI";
    URL url;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_INTRADAY"
              + "&outputsize=compact"
              + "&symbol"
              + "=" + stock + "&apikey=" + apiKey + "&datatype=csv"
              + "&interval=1min");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the Alpha Vantage API has either changed or "
              + "no longer works");
    }

    InputStream in;
    String[] readLine = new String[10];

    try {
      in = url.openStream();

      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line;
      String splitBy = ",";
      int timesOfRead = 2;

      while ((line = reader.readLine()) != null && timesOfRead != 0) {
        readLine = line.split(splitBy);    // use comma as separator
        timesOfRead--;
      }
      reader.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock);
    }

    String stockName = stockMap.get(stock);
    String timestamp = readLine[0];
    String price = readLine[1];

    String csvBuffer = stockName + "," + price + "," + stock + "," + timestamp;

    try {
      writeDataToFile(csvBuffer);
    } catch (Exception e) {
      System.out.println("Exception in writing stock info to the file " + e.getMessage());
    }
  }

  /**
   * This method writes the stock data that was obtained by performing the API call to a file
   * called 'StockData.csv'.
   *
   * @param stockData the stock data to be written to the file
   * @throws IOException if the file is not found
   */
  private void writeDataToFile(String stockData) throws IOException {
    File directory = new File("data");
    if (!directory.exists()) {
      directory.mkdir();
    }
    String fileName = "data/StockData.csv";
    File file = new File(fileName);
    file.createNewFile();
    PrintWriter write = new PrintWriter(fileName);
    write.write(stockData);
    write.flush();
    write.close();
  }
}
