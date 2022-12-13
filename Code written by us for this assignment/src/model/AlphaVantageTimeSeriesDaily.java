package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class represents read and write stock data.
 */
public class AlphaVantageTimeSeriesDaily implements IAlphaVantage {

  /**
   * This method gets stock data from AlphaVantage.
   *
   * @param symbol symbol of company to get data
   * @return stock data from AlphaVantage
   */
  public StringBuilder timeSeriesDaily(String symbol) {
    //the API key needed to use this web service.
    //Please get your own free API key here: https://www.alphavantage.co/
    //Please look at documentation here: https://www.alphavantage.co/documentation/
    String apiKey = "HL0ASZAVKGT6ZNHO";
    String stockSymbol = symbol; //ticker symbol for Google
    URL url = null;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, the highest
      price for that date, the lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

    return output;
  }

  /**
   * This method gets the stock value from the local data files.
   *
   * @param symbol symbol of company
   * @param date   input date
   * @return the value of stock
   */
  @Override
  public String getValue(String symbol, String date) {
    int idx;
    String newLine;
    String lastDate;
    StringBuilder output = openResource(symbol); // openCSV(symbol)

    newLine = output.substring(37, 38);
    lastDate = output.substring(38, 48);
    if (lastDate.compareTo(date) < 0) {
      output = timeSeriesDaily(symbol);
    }
    idx = output.indexOf(date);
    if (idx == -1) {
      return null;
    }
    String tmpStr = output.substring(idx);
    idx = tmpStr.indexOf(newLine);
    String dateLine = tmpStr.substring(0, idx);
    String[] tokens = dateLine.split(",");

    return tokens[4];
  }

  /**
   * This saves the csv file for a particular symbol locally.
   * Saves the csv with symbol as its name.
   *
   * @param symbol ticker symbol of the company.
   */
  @Override
  public void saveCSV(String symbol) {
    String dirPath =
            System.getProperty("user.dir") + File.separator + "data" + File.separator;
    String filePath = symbol + ".csv";

    System.out.println("Loading data from the server. Please be patient.");
    StringBuilder output = timeSeriesDaily(symbol);

    if (output.indexOf("Error Message") != -1) {
      System.out.println("This stock is not listed in the market!");
      return;
    }

    File file = new File(dirPath);
    boolean dirCreated = file.mkdir();

    file = new File(dirPath, filePath);
    BufferedWriter bw = null;

    try {
      bw = new BufferedWriter(new FileWriter(file));
      bw.write(String.valueOf(output));
      Thread.sleep(1000);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Thank you for your patience!");
  }

  /**
   * This method just opens the locally stored csv file.
   *
   * @param symbol symbol of the company.
   * @return the data in string format.
   */
  @Override
  public StringBuilder openCSV(String symbol) {
    String dirPath =
            System.getProperty("user.dir") + File.separator + "src" + File.separator + "data"
                    + File.separator;
    String filePath = symbol + ".csv";
    File file = new File(dirPath, filePath);
    StringBuilder output = new StringBuilder();
    String line;
    BufferedReader br;

    try {
      br = new BufferedReader(new FileReader(file));
      while ((line = br.readLine()) != null) {
        output.append(line + System.lineSeparator());
      }
    } catch (IOException e) {
      return null;
    }

    return output;
  }

  /**
   * This method reads locally stored data files.
   *
   * @param symbol symbol of company
   * @return contents of data file
   */
  @Override
  public StringBuilder openResource(String symbol) {
    StringBuilder output = new StringBuilder();
    InputStream in;
    try {
      in = getClass().getClassLoader().getResource("src/data/" + symbol + ".csv").openStream();
      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (Exception e) {
      output = timeSeriesDaily(symbol);
      if (output.indexOf("Error") != -1) {
        return null;
      }
    }
    return output;
  }
}
