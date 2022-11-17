package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import controller.GetStockData;
import controller.GetStockDataImpl;

/**
 * This class represents the stock composition data and methods that can be called upon to get the
 * data related to the stocks.
 * This method has the following variables.
 * <ul>
 *   <li>numberOfPortFolio - the number of portfolio that are available in the system</li>
 * </ul>
 */
public class StockCompositionDataImpl implements StockCompositionData {
  private final int numberOfPortFolio;

  /**
   * A constructor that reads the user directory to find the number of portfolios that the user
   * has or created and assigns the number to the global variable.
   */
  public StockCompositionDataImpl(String portfolioType) {
    int count = 0;
    Path path = Paths.get("userdata/user1");
    if (Files.exists(path)) {
      File directory = new File("userdata/user1");
      if (Objects.equals(portfolioType, "ALL")) {
        count = Objects.requireNonNull(directory.list()).length;
      } else {
        try {
          count = computeNumberOfPortfolioUsingTypes(portfolioType);
        } catch (Exception e) {
          // Do nothing
        }
      }
    }
    this.numberOfPortFolio = count;
  }

  private int computeNumberOfPortfolioUsingTypes(String portfolioType) {
    File dir = new File("userdata/user1");
    File[] files = dir.listFiles();
    assert files != null;
    int total = 0;

    for (File file : files) {
      if (isPortfolioOfGivenType("userdata/user1/" + file.getName(), portfolioType)) {
        total++;
      }
    }
    return total;
  }

  @Override
  public boolean isPortfolioOfGivenType(String filename, String portfolioType) {
    BufferedReader stockData;
    String lines;
    try {
      stockData = new BufferedReader(new FileReader(filename));
      stockData.readLine();
      lines = stockData.readLine();
      stockData.close();
    } catch (Exception e) {
      return false;
    }

    return (lines.split(",")[3].equalsIgnoreCase(portfolioType));
  }

  @Override
  public int getNumberOfPortFolio() {
    return numberOfPortFolio;
  }

  @Override
  public String getPortFolioFileNameByIndex(int index, String portfolioType) {
    File dir = new File("userdata/user1");
    File[] files = dir.listFiles();
    assert files != null;

    int temp = index;
    int count = index;
    if (!Objects.equals(portfolioType, "ALL")) {
      for (File file : files) {
        if (!isPortfolioOfGivenType("userdata/user1/" + file.getName(), portfolioType)) {
          count += 1;
        } else {
          temp--;
        }

        if (temp == -1) {
          index = count;
          break;
        }
      }
    }

    File file = files[index];
    return "userdata/user1/" + file.getName();
  }

  @Override
  public int getNumberOfTransactionsInAPortFolio(String filename) {
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

  @Override
  public String[] getPortFolioNames(String portfolioType) {
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

        if (Objects.equals(portfolioType, "ALL")) {
          data[index] = arrOfStr[0];
          index++;
        } else if (isPortfolioOfGivenType("userdata/user1/" + file.getName(),
                portfolioType)) {
          // Name of the portFolio
          data[index] = arrOfStr[0];
          index++;
        }
      }
      return data;
    }
  }

  @Override
  public StockPortFolioDataImpl getAllStockDataInPortFolio(int index, boolean unique,
                                                           String dateStr, boolean includeSale,
                                                           boolean realValue,
                                                           String portfolioType) {
    String filename = getPortFolioFileNameByIndex(index, portfolioType);
    int numberOfStocks = getNumberOfTransactionsInAPortFolio(filename);

    boolean found;
    int nameIndex = 0;

    if (dateStr == null) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date dateToday = new Date();
      dateStr = formatter.format(dateToday);
    }

    String createdTimeStamp = null;
    String[] names = new String[numberOfStocks];
    String[] symbol = new String[numberOfStocks];
    String[] stockLastKnownValueDate = new String[numberOfStocks];
    long[] quantity = new long[numberOfStocks];
    double[] valueOfSingleStock = new double[numberOfStocks];
    int numberOfTransactions = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date1;
    try {
      date1 = sdf.parse(dateStr);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

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
        Date date2 = sdf.parse(splitStockData[2]);
        int i;

        if (date2.compareTo(date1) <= 0) {
          numberOfTransactions++;
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
            if (Objects.equals(splitStockData[0], "BUY")) {
              quantity[nameIndex] += Long.parseLong(splitStockData[5]);
            } else if (Objects.equals(splitStockData[0], "SALE") && includeSale) {
              quantity[nameIndex] -= Long.parseLong(splitStockData[5]);
            }
            valueOfSingleStock[nameIndex] = Math.floor(Double.parseDouble(splitStockData[6])
                    * 100) / 100;
            stockLastKnownValueDate[nameIndex] = splitStockData[2];
            nameIndex++;
          } else {
            if (!unique) {
              // Combine if the stock last known value date are same
              if (Objects.equals(stockLastKnownValueDate[i], splitStockData[2])) {
                if (Objects.equals(splitStockData[0], "BUY")) {
                  quantity[i] = quantity[i] + Long.parseLong(splitStockData[5]);
                } else if (Objects.equals(splitStockData[0], "SALE") && includeSale) {
                  quantity[i] = quantity[i] - Long.parseLong(splitStockData[5]);
                }
              } else {
                names[nameIndex] = splitStockData[4];
                symbol[nameIndex] = splitStockData[3];
                if (Objects.equals(splitStockData[0], "BUY")) {
                  quantity[nameIndex] += Long.parseLong(splitStockData[5]);
                } else if (Objects.equals(splitStockData[0], "SALE") && includeSale) {
                  quantity[nameIndex] -= Long.parseLong(splitStockData[5]);
                }
                valueOfSingleStock[nameIndex] = Math.floor(Double.parseDouble(splitStockData[6])
                        * 100) / 100;
                stockLastKnownValueDate[nameIndex] = splitStockData[2];
                nameIndex++;
              }
            } else {
              if (Objects.equals(splitStockData[0], "BUY")) {
                quantity[i] = quantity[i] + Long.parseLong(splitStockData[5]);
              } else if (Objects.equals(splitStockData[0], "SALE") && includeSale) {
                quantity[i] = quantity[i] - Long.parseLong(splitStockData[5]);
              }
            }
          }
        }
      }
      stockData.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    StockPortFolioDataImpl obj = new StockPortFolioDataImpl();
    obj.numberOfUniqueStocks = nameIndex;

    if (realValue) {
      int indexReal = 0;
      for (int i = 0; i < names.length; i++) {
        if (quantity[i] != 0) {
          quantity[indexReal] = quantity[i];
          symbol[indexReal] = symbol[i];
          names[indexReal] = names[i];
          indexReal++;
        }
      }
      obj.numberOfUniqueStocks = indexReal;
    }

    obj.stockName = names;
    obj.stockSymbol = symbol;
    obj.stockQuantity = quantity;
    obj.valueOfSingleStock = valueOfSingleStock;
    obj.createdTimeStamp = createdTimeStamp;
    obj.stockLastKnownValueDate = stockLastKnownValueDate;
    obj.numberOfTransactions = numberOfTransactions;

    return obj;
  }

  @Override
  public int sharesAvailableOnTheDateForSale(int pfIndex, String stockSymbol, String dateStr,
                                             String portfolioType) throws ParseException {
    String filename = getPortFolioFileNameByIndex(pfIndex, portfolioType);
    Map<Date, Integer> map = new TreeMap<>();
    BufferedReader stockData;
    int result = 0;
    int futureResult = 0;

    try {
      stockData = new BufferedReader(new FileReader(filename));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    String line;
    String splitBy = ",";
    String[] splitStockData;
    int timesOfRead = 0;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = sdf.parse(dateStr);
    boolean isBuyFoundAfterSale = false;

    try {
      while ((line = stockData.readLine()) != null) {
        if (timesOfRead > 3) {
          splitStockData = line.split(splitBy);
          Date date2 = sdf.parse(splitStockData[2]);
          if (Objects.equals(splitStockData[3], stockSymbol)
                  && date2.compareTo(date1) <= 0) {
            if (Objects.equals(splitStockData[0], "BUY")) {
              result += Integer.parseInt(splitStockData[5]);
            } else if (Objects.equals(splitStockData[0], "SALE")) {
              result -= Integer.parseInt(splitStockData[5]);
            }
          }

          if (Objects.equals(splitStockData[3], stockSymbol)
                  && date2.compareTo(date1) > 0) {
            if (Objects.equals(splitStockData[0], "BUY")) {
              map.put(date2, Integer.parseInt(splitStockData[5]));
            } else if (Objects.equals(splitStockData[0], "SALE")) {
              map.put(date2, Integer.parseInt(splitStockData[5]) * -1);
            }
          }

        } else {
          timesOfRead++;
        }
      }
      stockData.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (Map.Entry element : map.entrySet()) {
      if ((int) element.getValue() > 0) {
        isBuyFoundAfterSale = true;
        futureResult += (int) element.getValue();
      } else {
        if (!isBuyFoundAfterSale) {
          result += (int) element.getValue();
        } else {
          futureResult += (int) element.getValue();
        }
      }

      if (result == 0) {
        return 0;
      }
      if (futureResult < 0) {
        result -= futureResult;
      }

    }

    return result;
  }

  @Override
  public Map<String, Double> computePerformanceData(String[] args, int length,
                                                    String portfolioType) throws IOException {
    Map<String, String[]> dateStockSymbolMap = new HashMap<>();
    Map<String, long[]> dateStockMapQuantity = new HashMap<>();
    Map<String, Double> finalDateAmountMap = new TreeMap<>();

    int pfNumber = Integer.parseInt(args[length - 2]);
    for (int i = 0; i < length - 3; i++) {
      StockPortFolioDataImpl obj = getAllStockDataInPortFolio(pfNumber - 1, true,
              args[i], true, false, portfolioType);
      dateStockSymbolMap.put(args[i], obj.stockSymbol);
      dateStockMapQuantity.put(args[i], obj.stockQuantity);
    }

    Map<String, String[]> stockSymbolWithDates = new TreeMap<>();
    for (Map.Entry<String, String[]> set : dateStockSymbolMap.entrySet()) {
      String[] stockSet;
      for (int i = 0; i < set.getValue().length; i++) {
        if (set.getValue()[i] == null) {
          // Assign null if the first stock entry is after this date
          if (i == 0) {
            finalDateAmountMap.put(set.getKey(), null);
          }
          break;
        }
        stockSet = stockSymbolWithDates.get(set.getValue()[i]);
        if (stockSet != null) {
          for (int j = 0; j < stockSet.length; j++) {
            if (stockSet[j] == null) {
              stockSet[j] = set.getKey();
              break;
            }
          }
          stockSymbolWithDates.put(set.getValue()[i], stockSet);
        } else {
          String[] tempDateSet = new String[31];
          tempDateSet[0] = set.getKey();
          stockSymbolWithDates.put(set.getValue()[i], tempDateSet);
        }
      }
    }

    GetStockData gsd = new GetStockDataImpl();
    for (Map.Entry<String, String[]> set : stockSymbolWithDates.entrySet()) {
      try {
        gsd.getValue(set.getKey(), set.getValue());
      } catch (Exception e) {
        System.out.println("MODEL: Unable to get stock data\n");
        return null;
      }

      BufferedReader stockData;
      try {
        stockData = new BufferedReader(new FileReader("data/StockData.csv"));
      } catch (IOException e) {
        System.out.println("MODEL: Unable to get stock data\n");
        return null;
      }

      String line;
      String splitBy = ",";
      String[] splitStockData;

      while ((line = stockData.readLine()) != null) {
        splitStockData = line.split(splitBy);    // use comma as separator
        String[] stockSym = dateStockSymbolMap.get(splitStockData[3]);
        int quantityInd = -1;

        for (int i = 0; i < stockSym.length; i++) {
          if (Objects.equals(stockSym[i], splitStockData[2])) {
            quantityInd = i;
            break;
          }
        }

        // If no stock found on that date, some error, continue
        if (quantityInd == -1) {
          continue;
        }

        long[] stockQuantityArray = dateStockMapQuantity.get(splitStockData[3]);
        int stockQuantity = (int) stockQuantityArray[quantityInd];
        double finalStockValue;

        if (finalDateAmountMap.get(splitStockData[3]) == null) {
          // First entry of that date
          finalStockValue = stockQuantity * Double.parseDouble(splitStockData[1]);
        } else {
          finalStockValue = finalDateAmountMap.get(splitStockData[3]);
          finalStockValue += stockQuantity * Double.parseDouble(splitStockData[1]);
        }
        finalDateAmountMap.put(splitStockData[3], Math.floor(finalStockValue * 100) / 100);
      }
      stockData.close();
    }
    return finalDateAmountMap;
  }

}
