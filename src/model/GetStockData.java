package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import supportedstocks.StockNameMap;
import supportedstocks.SupportedStocks;

public class GetStockData {
  JSONObject jsonWriteObject = new JSONObject();
  JSONArray jsonArr = new JSONArray();

  public void getValue(String stock) throws ParseException, IOException {
    StockNameMap snp = new StockNameMap();
    Map<String, String> stockMap = snp.getMap();
    String apiKey = "L6T2FNC0UY2K71XI";
    URL url;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_INTRADAY"
              + "&outputsize=compact"
              + "&symbol"
              + "=" + stock.toString() + "&apikey=" + apiKey + "&datatype=json"
              + "&interval=1min");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stock);
    }

    // Parse the json
    Object obj = new JSONParser().parse(output.toString());
    // typecasting obj to JSONObject
    JSONObject jsonObj = (JSONObject) obj;
    JSONObject MetaData = (JSONObject) jsonObj.get("Meta Data");
    String timestamp = (String) MetaData.get("3. Last Refreshed");
    String stockName = stockMap.get(stock);
    JSONObject data = (JSONObject) jsonObj.get("Time Series (1min)");
    JSONObject priceData = (JSONObject) data.get(timestamp);
    String price = (String) priceData.get("1. open");

    writeToJsonObject(stockName, stock, price, timestamp);

    writeDataToFile();
  }

  private void writeDataToFile() throws IOException {
    jsonWriteObject.put("AllStockData", jsonArr);
    String fileName = "Data/StockData.json";
    File file = new File(fileName);
    file.createNewFile();
    PrintWriter write = new PrintWriter(fileName);
    write.write(jsonWriteObject.toJSONString());
    write.flush();
    write.close();
  }

  private void writeToJsonObject(String stockName, String stock, String price,
                                 String timestamp) {
    JSONObject jsonObj = new JSONObject();
    jsonObj.put("StockName", stockName);
    jsonObj.put("StockSymbol", stock);
    jsonObj.put("Price", price);
    jsonObj.put("Timestamp", timestamp);

    jsonArr.add(jsonObj);
  }

/*  public static void main(String []args) {
    //the API key needed to use this web service.
    //Please get your own free API key here: https://www.alphavantage.co/
    //Please look at documentation here: https://www.alphavantage.co/documentation/
    String apiKey = "L6T2FNC0UY2K71XI";
    URL url = null;

    try {
      *//*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       *//*
      url = new URL("https://www.alphavantage"
                    + ".co/query?function=TIME_SERIES_DAILY"
                    + "&outputsize=full"
                    + "&symbol"
                    + "=" + "GOOG" + "&apikey="+apiKey+"&datatype=json");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
                                 + "no longer works");
    }

    InputStream in;
    StringBuilder output = new StringBuilder();

    try {
      *//*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, the highest
      price for that date, the lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       *//*
      in = url.openStream();
      int b;

      while ((b=in.read())!=-1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for "+"GOOG");
    }
    System.out.println("Return value: ");
    System.out.println(output);
  }*/
}
