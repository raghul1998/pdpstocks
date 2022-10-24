package supportedstocks;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StockNameMap {
  public Map<String, String> stockNameMap = new HashMap<>();

  public StockNameMap() {
    Object obj;
    try {
      obj = new JSONParser().parse(new FileReader("./data/SupportedStocks.json"));
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObj = (JSONObject) obj;
    JSONArray jsonArray = (JSONArray) jsonObj.get("SupportedStocks");
    for (Object stkObj : jsonArray) {
      JSONObject stk = (JSONObject) stkObj;
      this.stockNameMap.put((String) stk.get("StockSymbol"), (String) stk.get("StockName"));
    }
  }

  public Map<String, String> getMap() {
    return stockNameMap;
  }

  public int getMapSize() {
    return stockNameMap.size();
  }
}