package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * This class represents the model and provides the implementation to dispatch the actions to the
 * corresponding methods.
 * This class has the following variables.
 * <ul>
 *   <li> portFolioName - name of the portfolio that is being currently read or written </li>
 * </ul>
 */
public class ModelControllerInteractImpl implements ModelControllerInteract {
  String portFolioName;

  @Override
  public void modelControllerInteract(TypeofAction type, String[] args, int length) {
    switch (type) {
      case BUY_STOCKS: {
        buyStockData(args, length);
        break;
      }
      case GET_STOCK_DATA: {
        getStockData(args[0]);
        break;
      }
      case CREATE_PORTFOLIO: {
        try {
          createPortFolio(args[0]);
          portFolioName = args[0];
        } catch (Exception e) {
          System.out.println("Exception in creating a portfolio" + e.getMessage());
        }
        break;
      }
      case DELETE_EMPTY_PORTFOLIO: {
        deleteEmptyPortFolio(null);
        break;
      }
      case CREATE_SUPPORTED_STOCKS: {
        try {
          createSupportedStocksFile();
          deleteEmptyPortFolioAll();
        } catch (Exception e) {
          System.out.println("MODEL: Error in creating supported stocks file");
        }
        break;
      }
      default: {
        //No Action Needed
        break;
      }
    }
  }

  /**
   * This method runs during the start of the application and helps in deleting any empty
   * portfolio that was created during the previous session of the application that did not
   * terminate gracefully.
   */
  private void deleteEmptyPortFolioAll() {
    File directory = new File("userdata/user1");
    if (directory.exists()) {
      directory.mkdir();
      File[] files = directory.listFiles();
      assert files != null;
      for (File file : files) {
        String filename = file.getName();
        String[] arrOfStr = filename.split("pf_", 2);
        arrOfStr = arrOfStr[1].split("\\.", 2);
        // Name of the portFolio
        deleteEmptyPortFolio(arrOfStr[0]);
      }
    }
  }

  /**
   * This method runs during the start that creates a 'SupportedStocks.csv' file that represents
   * the stocks that this application supports.
   *
   * @throws IOException if there is any issue in creating a file
   */
  private void createSupportedStocksFile() throws IOException {
    File directory = new File("data");
    if (!directory.exists()) {
      directory.mkdir();
    }
    String fileName = "data/SupportedStocks.csv";
    File file = new File(fileName);
    if (!file.exists()) {
      file.createNewFile();
      PrintWriter write = new PrintWriter(fileName);
      String supportedStockData = supportedStocksCSVData();
      write.write(supportedStockData);
      write.flush();
      write.close();
    }
  }

  /**
   * This is a helper function that returns the supported stocks, it's ID, stock name and the
   * symbol as a string but written in a CSV format.
   *
   * @return the supported stocks as a string
   */
  private String supportedStocksCSVData() {
    return "StockID,StockName,StockSymbol\n"
            + "1,Apple,AAPL\n"
            + "2,Amazon,AMZN\n"
            + "3,Microsoft,MSFT\n"
            + "4,Tesla,TSLA\n"
            + "5,Meta,META\n"
            + "6,Walmart,WMT\n"
            + "7,Johnson,JNJ\n"
            + "8,JPMorgan Chase,JPM\n"
            + "9,Google,GOOG\n"
            + "10,UnitedHealth,UNH";
  }

  /**
   * This method deletes the portfolio if it is empty.
   */
  private void deleteEmptyPortFolio(String pfName) {
    if (pfName == null) {
      pfName = portFolioName;
    }

    String fileName = "userdata/user1/" + "pf_" + pfName + ".csv";
    try {
      File file = new File(fileName);
      BufferedReader stockData;

      try {
        stockData = new BufferedReader(new FileReader(fileName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      String line;
      String splitBy = ",";
      String[] splitStockData = new String[8];

      while ((line = stockData.readLine()) != null) {
        splitStockData = line.split(splitBy);
      }

      // Delete only if the portfolio is empty
      if (Objects.equals(splitStockData[0], "PurchaseTimestamp")) {
        stockData.close();
        file.delete();
      }
    } catch (Exception e) {
      //System.out.println("Unable to delete the empty portfolio");
    }
  }

  /**
   * This method helps in creating a portfolio and adding starter contents to it like the name of
   * the portfolio, unique ID and the titles for the stock data.
   *
   * @param name the name of the portfolio
   * @throws IOException if there is an issue in creating a file
   */
  private void createPortFolio(String name) throws IOException {
    String directoryName = "userdata/user1/";
    File directory = new File(directoryName);
    if (!directory.exists()) {
      directory.mkdirs();
    }

    String fileName = directoryName + "pf_" + name + ".csv";
    File file = new File(fileName);
    file.createNewFile();

    StringBuilder csvData = new StringBuilder();
    csvData.append("PortfolioName,").append(name);
    csvData.append('\n');

    long time = System.currentTimeMillis() / 1000;
    csvData.append("PortfolioID,").append(Long.toString(time, 0));
    csvData.append('\n');

    csvData.append("PurchaseTimestamp," + "PurchaseID," + "StockLastKnownValueTimestamp,"
            + "StockSymbol," + "NameOfStock," + "NumberOfStocksPurchased,"
            + "PriceOfShareAtPurchase");
    csvData.append('\n');

    PrintWriter write = new PrintWriter(fileName);
    write.write(csvData.toString());
    write.flush();
    write.close();
  }

  /**
   * When a user tries to buy a stock, this method helps in writing that data to the portfolio that
   * the user wants to enter the data to.
   *
   * @param args   helper arguments like the number of shares the user wants to purchase
   * @param length length of the arguments
   */
  private void buyStockData(String[] args, int length) {
    BufferedReader stockData;
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String line;
    String splitBy = ",";
    String[] splitStockData;

    try {
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
      stockData.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    StringBuilder pfBoughtStockData = new StringBuilder();
    // PurchaseTimestamp
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    pfBoughtStockData.append(dtf.format(now)).append(",");
    // PurchaseID
    long time = System.currentTimeMillis() / 1000;
    pfBoughtStockData.append(Long.toString(time, 0)).append(",");
    // StockLastKnownValueTimestamp
    pfBoughtStockData.append(splitStockData[3]).append(",");
    //stockName + "," + price + "," + stock + "," + timestamp;
    // StockSymbol
    pfBoughtStockData.append(splitStockData[2]).append(",");
    // NameOfStock
    pfBoughtStockData.append(splitStockData[0]).append(",");
    // NumberOfStocksPurchased
    pfBoughtStockData.append(args[0]).append(",");
    // PriceOfShareAtPurchase
    pfBoughtStockData.append(splitStockData[1]).append('\n');

    try {
      writeStockDataToPortfolio(pfBoughtStockData.toString());
    } catch (Exception e) {
      System.out.println("Error in writing to portfolio" + e.getMessage());
    }
  }

  /**
   * This is a helper method helps in writing the provided data to the portfolio file that user
   * is trying to create.
   *
   * @param data the data to be written to the portfolio
   * @throws FileNotFoundException if the portfolio doesn't exists
   */
  private void writeStockDataToPortfolio(CharSequence data) throws FileNotFoundException {
    String filename = "userdata/user1/" + "pf_" + portFolioName + ".csv";
    PrintWriter write = new PrintWriter(new FileOutputStream(filename, true));
    write.append(data);
    write.flush();
    write.close();
  }

  /**
   * This method helps in getting the real-time stock data of the stock that the user wants to buy.
   *
   * @param arg the stock symbol that the user wants to buy
   */
  private void getStockData(String arg) {
    GetStockData obj = new GetStockData();
    try {
      obj.getValue(arg);
    } catch (Exception e) {
      System.out.println("Get stock data failed");
    }
  }
}
