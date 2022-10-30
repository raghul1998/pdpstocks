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
        deleteEmptyPortFolio();
      }
    }
  }

  private void deleteEmptyPortFolio() {
    String fileName = "userdata/user1/" + "pf_" + portFolioName + ".csv";
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
      System.out.println("Unable to delete the empty portfolio");
    }
  }

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
      writeArrayToPortfolio(pfBoughtStockData.toString());
    } catch (Exception e) {
      System.out.println("Error in writing to portfolio" + e.getMessage());
    }
  }

  private void writeArrayToPortfolio(CharSequence data) throws FileNotFoundException {
    String filename = "userdata/user1/" + "pf_" + portFolioName + ".csv";
    PrintWriter write = new PrintWriter(new FileOutputStream(filename, true));
    write.append(data);
    write.flush();
    write.close();
  }

  private void getStockData(String arg) {
    GetStockData obj = new GetStockData();
    try {
      obj.getValue(arg);
    } catch (Exception e) {
      System.out.println("Get stock data failed");
    }
  }
}
