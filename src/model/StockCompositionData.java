package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * This class represents the stock composition data and methods that can be called upon to get the
 * data related to the stocks.
 * This method has the following variables.
 * <ul>
 *   <li>numberOfPortFolio - the number of portfolio that are available in the system</li>
 * </ul>
 */
public class StockCompositionData {
  private final int numberOfPortFolio;

  /**
   * A constructor that reads the user directory to find the number of portfolios that the user
   * has or created and assigns the number to the global variable.
   */
  public StockCompositionData() {
    int count = 0;
    Path path = Paths.get("userdata/user1");
    if (Files.exists(path)) {
      File directory = new File("userdata/user1");
      count = Objects.requireNonNull(directory.list()).length;
    }
    this.numberOfPortFolio = count;
  }

  /**
   * A method that tells the number of portfolio's that are available for the user.
   *
   * @return the number of portfolio's
   */
  public int getNumberOfPortFolio() {
    return numberOfPortFolio;
  }

  /**
   * A helper method that returns the name of the portfolio by the index that is provided.
   *
   * @param index the index of the portfolio
   * @return the name of the portfolio as a string
   */
  private String getPortFolioFileNameByIndex(int index) {
    File dir = new File("userdata/user1");
    File[] files = dir.listFiles();
    assert files != null;
    File file = files[index];
    return "userdata/user1/" + file.getName();
  }

  /**
   * A helper method that helps to find out the number of stocks that are present in a portfolio.
   *
   * @param filename the filename of the portfolio
   * @return the number of stocks as an integer
   */
  private int getNumberOfStockDataInAPortFolio(String filename) {
    Path path = Paths.get(filename);
    long lines = 0;
    try {
      lines = Files.lines(path).count();
    } catch (IOException e) {
      System.out.println("MODEL: Error in getting the number of Stocks in portfolio");
      //e.printStackTrace();
    }
    return (int) lines;
  }

  /**
   * This method finds out the names of all the portfolio that a user has in the system.
   *
   * @return the names of the portfolio as a string array
   */
  public String[] getPortFolioNames() {
    int index = 0;
    if (numberOfPortFolio == 0) {
      return null;
    } else {
      String[] data = new String[numberOfPortFolio];
      File dir = new File("userdata/user1");
      File[] files = dir.listFiles();
      assert files != null;
      for (File file : files) {
        String filename = file.getName();
        String[] arrOfStr = filename.split("pf_", 2);
        arrOfStr = arrOfStr[1].split("\\.", 2);
        // Name of the portFolio
        data[index] = arrOfStr[0];
        index++;
      }
      return data;
    }
  }

  /**
   * This method gets all the stock related data that is in a portfolio and some data relevant to
   * the portfolio.
   *
   * @param index the index of the portfolio
   * @return the stock related data as 'stockPortFolioData' object
   */
  public StockPortFolioData getAllStockDataInPortFolio(int index, boolean unique) {
    String filename = getPortFolioFileNameByIndex(index);
    int numberOfStocks = getNumberOfStockDataInAPortFolio(filename);

    boolean found;
    int nameIndex = 0;

    String createdTimeStamp = null;
    String[] names = new String[numberOfStocks];
    String[] symbol = new String[numberOfStocks];
    String[] stockLastKnownValueDate = new String[numberOfStocks];
    long[] quantity = new long[numberOfStocks];
    double[] value = new double[numberOfStocks];
    double[] valueOfSingleStock = new double[numberOfStocks];
    double totalPortFolioValue = 0;

    BufferedReader stockData;
    try {
      stockData = new BufferedReader(new FileReader(filename));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    stockData.lines();
    String line;
    String splitBy = ",";
    String[] splitStockData;
    int timesOfRead = 0;

    try {
      while ((line = stockData.readLine()) != null) {
        if (timesOfRead == 2) {
          createdTimeStamp = line.split(splitBy)[1].split(" ")[0]; // Extract date from CSV
        }

        if (timesOfRead < 4) {
          timesOfRead++;
          continue;
        }

        splitStockData = line.split(splitBy);
        found = false;
        int i;
        for (i = 0; i < symbol.length; i++) {
          if (symbol[i] == null) {
            break;
          }
          if (Objects.equals(symbol[i], splitStockData[3])) {
            found = true;
            break;
          }
        }
        if (!found) {
          names[nameIndex] = splitStockData[4];
          symbol[nameIndex] = splitStockData[3];
          quantity[nameIndex] = Long.parseLong(splitStockData[5]);
          double val = quantity[nameIndex]
                  * Double.parseDouble(splitStockData[6]);
          value[nameIndex] = Math.floor(val * 100) / 100;
          totalPortFolioValue += value[nameIndex];
          valueOfSingleStock[nameIndex] = Math.floor(Double.parseDouble(splitStockData[6])
                  * 100) / 100;
          stockLastKnownValueDate[nameIndex] = splitStockData[2];
          nameIndex++;
        } else {
          if (!unique) {
            // Combine if the stock last known value date are same
            if (Objects.equals(stockLastKnownValueDate[i], splitStockData[2])) {
              quantity[i] = quantity[i] + Long.parseLong(splitStockData[5]);
              double val = quantity[i]
                      * Double.parseDouble(splitStockData[6]);
              totalPortFolioValue -= value[i];
              value[i] = Math.floor(val * 100) / 100;
              totalPortFolioValue += value[i];
            } else {
              names[nameIndex] = splitStockData[4];
              symbol[nameIndex] = splitStockData[3];
              quantity[nameIndex] = Long.parseLong(splitStockData[5]);
              double val = quantity[nameIndex]
                      * Double.parseDouble(splitStockData[6]);
              value[nameIndex] = Math.floor(val * 100) / 100;
              totalPortFolioValue += value[nameIndex];
              valueOfSingleStock[nameIndex] = Math.floor(Double.parseDouble(splitStockData[6])
                      * 100) / 100;
              stockLastKnownValueDate[nameIndex] = splitStockData[2];
              nameIndex++;
            }
          } else {
            quantity[i] = quantity[i] + Long.parseLong(splitStockData[5]);
            double val = quantity[i]
                    * Double.parseDouble(splitStockData[6]);
            totalPortFolioValue -= value[i];
            value[i] = Math.floor(val * 100) / 100;
            totalPortFolioValue += value[i];
          }
        }
      }
      stockData.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    StockPortFolioData obj = new StockPortFolioData();
    obj.numberOfUniqueStocks = nameIndex;
    obj.stockName = names;
    obj.stockSymbol = symbol;
    obj.stockQuantity = quantity;
    obj.totalValue = value;
    obj.totalPortFolioValue = Math.floor(totalPortFolioValue * 100) / 100;
    obj.valueOfSingleStock = valueOfSingleStock;
    obj.createdTimeStamp = createdTimeStamp;
    obj.stockLastKnownValueDate = stockLastKnownValueDate;
    return obj;
  }

  /**
   * A static class inside the 'StockCompositionData' that holds the data related to the stocks and
   * shares in a portfolio.
   * This class has the following members.
   * <ul>
   *   <li>numberOfUniqueStocks</li>
   *   <li>stockName - name of all the stocks in a portfolio</li>
   *   <li>stockSymbol - symbol of all the stocks in a portfolio</li>
   *   <li>stockQuantity - number of shares for each stock in a portfolio</li>
   *   <li>totalValue - total value of each stock in a portfolio</li>
   *   <li>totalPortFolioValue - total value of the complete portfolio</li>
   *   <li>valueOfSingleStock - value of each share of a stock in the portfolio</li>
   *   <li>createdTimeStamp - timestamp on which the portfolio was created</li>
   * </ul>
   */
  public static class StockPortFolioData {
    public int numberOfUniqueStocks;
    public String[] stockName;
    public String[] stockSymbol;
    public long[] stockQuantity;
    public double[] totalValue;
    public double totalPortFolioValue;
    public double[] valueOfSingleStock;
    public String createdTimeStamp;
    public String[] stockLastKnownValueDate;
  }
}
