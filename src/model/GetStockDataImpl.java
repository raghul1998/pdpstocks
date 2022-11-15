package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * This class represents the stock data and methods that can be called upon to get the value of a
 * certain stock using APIs.
 */
public class GetStockDataImpl implements GetStockData {
  @Override
  public void getValue(String stock, String[] dateStr) throws ParseException {
    StockNameMapImpl snp = new StockNameMapImpl();
    Map<String, String> stockMap = snp.getMap();
    String apiKey = "L6T2FNC0UY2K71XI";
    URL url;

    try {
      /*url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_INTRADAY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stock + "&apikey=" + apiKey + "&datatype=csv"
              + "&interval=1min");*/

      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stock + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the Alpha Vantage API has either changed or "
              + "no longer works");
    }

    InputStream in;
    String[] readLine;

    String[] timestamp = new String[dateStr.length];
    String[] price = new String[dateStr.length];

    try {
      in = url.openStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line;
      String splitBy = ",";
      int index = 0;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      reader.mark(1000000000);

      for (int i = 0; i < dateStr.length; i++) {
        if (dateStr[i] == null) {
          break;
        }

        Date date1 = sdf.parse(dateStr[i]);

        if (i != 0) {
          reader.reset();
          index = 0;
        }

        while ((line = reader.readLine()) != null) {
          readLine = line.split(splitBy);    // use comma as separator

          if (index == 0) {
            index++;
            continue;
          }

          Date date2 = sdf.parse(readLine[0]);
          // Check if both dates are same
          if (date1.compareTo(date2) == 0) {
            timestamp[i] = readLine[0];
            price[i] = readLine[1];
            break;
          }
          // If the date is in the future, then take the first date in the list
          if (date1.compareTo(date2) > 0) {
            readLine[0] = dateStr[i];
            timestamp[i] = readLine[0];
            price[i] = readLine[1];
            break;
          }

          // If no stock info is available on this date, then assign the last know value
          readLine[0] = dateStr[i];
          timestamp[i] = readLine[0];
          price[i] = readLine[1];
        }
      }
      reader.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock);
    }

    String stockName = stockMap.get(stock);

    StringBuilder csvBuffer = new StringBuilder();
    for (int i = 0; i < dateStr.length; i++) {
      if (price[i] == null) {
        break;
      }
      csvBuffer.append(stockName).append(",").append(price[i]).append(",")
              .append(stock).append(",").append(timestamp[i]);
      csvBuffer.append('\n');
    }

    try {
      writeDataToFile(String.valueOf(csvBuffer));
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
