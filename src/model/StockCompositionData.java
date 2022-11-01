package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class StockCompositionData {
  private final int numberOfPortFolio;

  public StockCompositionData() {
    int count = 0;
    Path path = Paths.get("userdata/user1");
    if (Files.exists(path)) {
      File directory = new File("userdata/user1");
      count = Objects.requireNonNull(directory.list()).length;
    }
    this.numberOfPortFolio = count;
  }

  public int getNumberOfPortFolio() {
    return numberOfPortFolio;
  }

  private String getPortFolioFileNameByIndex(int index) {
    File dir = new File("userdata/user1");
    File[] files = dir.listFiles();
    assert files != null;
    File file = files[index];
    return "userdata/user1/" + file.getName();
  }

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

  public stockPortFolioData getAllStockDataInPortFolio(int index) {
    String filename = getPortFolioFileNameByIndex(index);
    int numberOfStocks = getNumberOfStockDataInAPortFolio(filename);

    boolean found;
    int nameIndex = 0;

    String createdTimeStamp = null;
    String[] names = new String[numberOfStocks];
    String[] symbol = new String[numberOfStocks];
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
        timesOfRead++;
        if (timesOfRead > 3) {
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
          if (createdTimeStamp == null || createdTimeStamp.length() == 0) {
            createdTimeStamp = splitStockData[0];
          }
          if (!found) {
            names[nameIndex] = splitStockData[4];
            symbol[nameIndex] = splitStockData[3];
            quantity[nameIndex] = Long.parseLong(splitStockData[5]);
            double val = quantity[nameIndex] *
                    Double.parseDouble(splitStockData[6]);
            value[nameIndex] = Math.floor(val * 100) / 100;
            totalPortFolioValue += value[nameIndex];
            valueOfSingleStock[nameIndex] = Math.floor(Double.parseDouble(splitStockData[6])
                    * 100) / 100;
            nameIndex++;
          } else {
            quantity[i] = quantity[i] + Long.parseLong(splitStockData[5]);
            double val = quantity[i] *
                    Double.parseDouble(splitStockData[6]);
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

    stockPortFolioData obj = new stockPortFolioData();
    obj.numberOfUniqueStocks = nameIndex;
    obj.stockName = names;
    obj.stockSymbol = symbol;
    obj.stockQuantity = quantity;
    obj.totalValue = value;
    obj.totalPortFolioValue = Math.floor(totalPortFolioValue * 100) / 100;
    obj.valueOfSingleStock = valueOfSingleStock;
    obj.createdTimeStamp = createdTimeStamp;
    return obj;
  }


  public static class stockPortFolioData {
    public int numberOfUniqueStocks;
    public String[] stockName;
    public String[] stockSymbol;
    public long[] stockQuantity;
    public double[] totalValue;
    public double totalPortFolioValue;
    public double[] valueOfSingleStock;
    public String createdTimeStamp;
  }
}
