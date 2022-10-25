package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

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
    String fileName = "userdata/user1/" + "pf_" + portFolioName + ".json";
    try {
      File file = new File(fileName);
      file.delete();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createPortFolio(String name) throws IOException {
    String fileName = "userdata/user1/" + "pf_" + name + ".json";
    File file = new File(fileName);
    file.createNewFile();
    JSONObject jsonWriteObject = new JSONObject();
    jsonWriteObject.put("PortfolioName", name);
    long time = System.currentTimeMillis() / 1000;
    jsonWriteObject.put("PortfolioID", "user1_" + Long.toString(time, 0));
    JSONArray ja = new JSONArray();
    jsonWriteObject.put("StockHoldings", ja);
    PrintWriter write = new PrintWriter(fileName);
    write.write(jsonWriteObject.toJSONString());
    write.flush();
    write.close();
  }

  private void buyStockData(String[] args, int length) {
    Object obj;
    try {
      obj = new JSONParser().parse(new FileReader("./data/StockData.json"));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObj = (JSONObject) obj;
    JSONArray jsonArray = (JSONArray) jsonObj.get("AllStockData");
    JSONObject stk = (JSONObject) jsonArray.get(0);

    JSONObject arr = new JSONObject();

    arr.put("StockSymbol", stk.get("StockSymbol"));
    arr.put("NameOfStock", stk.get("StockName"));

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    arr.put("PurchaseTimestamp", dtf.format(now));

    arr.put("StockLastKnownValueTimestamp", stk.get("Timestamp"));
    arr.put("NumberOfStocksPurchased", args[0]);
    arr.put("PriceOfShareAtPurchase", stk.get("Price"));

    long time = System.currentTimeMillis() / 1000;
    arr.put("PurchaseID", Long.toString(time, 0));


    try {
      writeArrayToPortfolio(arr);
    } catch (Exception e) {
      System.out.println("Error in writing to portfolio" + e.getMessage());
    }
  }

  private void writeArrayToPortfolio(JSONObject arr) throws FileNotFoundException {
    Object obj;
    String filename = "userdata/user1/" + "pf_" + portFolioName + ".json";
    try {
      obj = new JSONParser().parse(new FileReader(filename));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObj = (JSONObject) obj;
    JSONArray jsonArray = (JSONArray) jsonObj.get("StockHoldings");
    jsonArray.add(arr);

    PrintWriter write = new PrintWriter(filename);
    write.write(jsonObj.toJSONString());
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
