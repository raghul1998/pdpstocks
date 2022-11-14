package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class PortfolioPerformance {
  public Map<String, Double> computePerformanceData(String[] args, int length) throws IOException {
    Map<String, String[]> dateStockSymbolMap = new HashMap<>();
    Map<String, long[]> dateStockMapQuantity = new HashMap<>();
    Map<String, Double> finalDateAmountMap = new TreeMap<>();

    int pfNumber = Integer.parseInt(args[length - 2]);
    for (int i = 0; i < length - 3; i++) {
      StockCompositionData stk = new StockCompositionData();
      StockCompositionData.StockPortFolioData obj =
              stk.getAllStockDataInPortFolio(pfNumber - 1, true,
                      args[i], true, false);
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

    GetStockData gsd = new GetStockData();
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
    }
    return finalDateAmountMap;
  }
}
