package view;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class StockCompositionData {
  private int numberOfPortFolio;

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

  public JSONArray getPortFolioStockData(int index) {
    File dir = new File("userdata/user1");
    File[] files = dir.listFiles();
    assert files != null;
    File file = files[index];
    String filename = "userdata/user1/" + file.getName();

    Object obj;
    try {
      obj = new JSONParser().parse(new FileReader(filename));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObj = (JSONObject) obj;
    JSONArray jsonArray = (JSONArray) jsonObj.get("StockHoldings");
    return jsonArray;
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
    JSONArray jsonArr = getPortFolioStockData(index);
    boolean found;
    int nameIndex = 0;

    String[] names = new String[jsonArr.size()];
    String[] symbol = new String[jsonArr.size()];
    long[] quantity = new long[jsonArr.size()];
    double[] value = new double[jsonArr.size()];
    double totalPortFolioValue = 0;

    for (Object stkObj : jsonArr) {
      found = false;
      JSONObject stk = (JSONObject) stkObj;
      int i;
      for (i = 0; i < symbol.length; i++) {
        if (symbol[i] == null) {
          break;
        }
        if (Objects.equals(symbol[i], (String) stk.get("StockSymbol"))) {
          found = true;
          break;
        }
      }
      if (!found) {
        names[nameIndex] = (String) stk.get("NameOfStock");
        symbol[nameIndex] = (String) stk.get("StockSymbol");
        quantity[nameIndex] = Long.parseLong((String) stk.get("NumberOfStocksPurchased"));
        double val = quantity[nameIndex] *
                Double.parseDouble((String) stk.get("PriceOfShareAtPurchase"));
        value[nameIndex] = Math.floor(val * 100) / 100;
        totalPortFolioValue += value[nameIndex];
        nameIndex++;
      } else {
        quantity[i] = quantity[i] + Long.parseLong((String) stk.get("NumberOfStocksPurchased"));
        double val = value[i] + (quantity[i] *
                Double.parseDouble((String) stk.get("PriceOfShareAtPurchase")));
        totalPortFolioValue -= value[i];
        value[i] = Math.floor(val * 100) / 100;
        totalPortFolioValue += value[i];
      }
    }

    stockPortFolioData obj = new stockPortFolioData();
    obj.numberOfUniqueStocks = nameIndex;
    obj.stockName = names;
    obj.stockSymbol = symbol;
    obj.stockQuantity = quantity;
    obj.totalValue = value;
    obj.totalPortFolioValue = totalPortFolioValue;
    return obj;
  }


  static class stockPortFolioData {
    public int numberOfUniqueStocks;
    public String[] stockName;
    public String[] stockSymbol;
    public long[] stockQuantity;
    public double[] totalValue;
    public double totalPortFolioValue;
  }

}
