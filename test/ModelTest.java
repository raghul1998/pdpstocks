import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Objects;

import model.ModelControllerInteract;
import model.ModelControllerInteractImpl;
import model.StockCompositionData;
import model.TypeofAction;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the Model.
 */

public class ModelTest extends TestParentClass{
  @Test
  public void testModelCreatePortfolio() {
    String[] args = {"modelTest_esha"};
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO, args, 0);

    File dir = new File("userdata/user1/");
    File[] directoryListing = dir.listFiles();
    String expectedName = "pf_" + args[0] + ".csv";
    String pfName = null;
    if (directoryListing != null) {
      for (File i : directoryListing) {
        if (i.getName().equals("pf_" + args[0] + ".csv")) {
          pfName = i.getName();
          break;
        }
      }
    }
    assertEquals(expectedName, pfName);
  }

  @Test
  public void testModelDeleteEmptyPortfolio() {
    String[] args = {"modelTest_esha2"};
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO, args, 0);
    obj.modelControllerInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO, null, 0);

    File dir = new File("userdata/user1/");
    File[] directoryListing = dir.listFiles();

    int count = 0;
    if (directoryListing != null) {
      for (File i : directoryListing) {
        if (!i.getName().equals("pf_" + args[0] + ".csv")) {
          count++;
        }
      }
    }
    assertEquals(count, Objects.requireNonNull(dir.listFiles()).length);
  }

  @Test
  public void testModelGetStockData() {
    String[] args = {"TSLA","2022-01-01"};
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.GET_STOCK_DATA, args, 0);

    String line;
    String splitBy = ",";
    String expectedName = args[0];
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      System.out.println("Supported stocks file not found " + e.getMessage());
    }
    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      System.out.println("Error in reading Supported stocks csv file.");
    }

    assertEquals(expectedName, splitStockData[2]);
  }

  // check if you are able to read portfolio csv file

  @Test
  public void testModelBuyStocks() throws IOException {
    deleteFileInDirectory("modelTest_esha6.csv");
    String[] name = {"modelTest_esha6"};
    String[] args = {"GOOG","2022-11-11"};
    String[] args1 = {"1","modelTest_esha6"};


    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO, name, 0);
    obj.modelControllerInteract(TypeofAction.GET_STOCK_DATA, args, 0);
    obj.modelControllerInteract(TypeofAction.BUY_STOCKS, args1, 0);

    long time = System.currentTimeMillis() / 1000;
    String expected = super.readStockDataFromPortfolioCsv(name[0],
            1,1,false);
    String actual = String.valueOf((System.currentTimeMillis() / 1000));
    assertEquals(expected,actual);
    deleteFileInDirectory("modelTest_esha6.csv");
  }

  // review
  // show that selling happens based of number of stocks that are left

  @Test
  public void testSellStocks() throws IOException, ParseException {
    deleteFileInDirectory("modelTest_esha.csv");
    String[] name = {"modelTest_esha"};
    String[] args = {"GOOG","2022-11-11"};
    String[] args1 = {"3","modelTest_esha"};
    String[] args2 = {"2","modelTest_esha"};

    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO, name, 0);
    obj.modelControllerInteract(TypeofAction.GET_STOCK_DATA, args, 0);
    obj.modelControllerInteract(TypeofAction.BUY_STOCKS, args1, 0);
    obj.modelControllerInteract(TypeofAction.SELL_STOCKS, args2, 0);

    long time = System.currentTimeMillis() / 1000;
    String expected = super.readStockDataFromPortfolioCsv(name[0],
            1,1,false);
    String actual = String.valueOf((System.currentTimeMillis() / 1000));
    assertEquals(expected,actual);
    deleteFileInDirectory("modelTest_esha.csv");
  }

  @Test
  public void testCreateSupportedStocks() throws IOException {
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_SUPPORTED_STOCKS, null, 0);
    BufferedReader supportedStocksData = null;
    try {
      supportedStocksData = new BufferedReader(new FileReader("data/SupportedStocks.csv"));
    } catch (Exception e) {
      System.out.println("Supported stocks file not found " + e.getMessage());
    }
    String actual = null;
    try {
      StringBuilder sb = new StringBuilder();
      String line = supportedStocksData.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = supportedStocksData.readLine();
      }
      actual = sb.toString();
    } finally {
      supportedStocksData.close();
    }

    String expected = "StockID,StockName,StockSymbol\n" +
            "1,Apple,AAPL\n" +
            "2,Amazon,AMZN\n" +
            "3,Microsoft,MSFT\n" +
            "4,Tesla,TSLA\n" +
            "5,Meta,META\n" +
            "6,Walmart,WMT\n" +
            "7,Johnson,JNJ\n" +
            "8,JPMorgan Chase,JPM\n" +
            "9,Google,GOOG\n" +
            "10,UnitedHealth,UNH\n";

    assertEquals(expected, actual);
  }

//  @Test
//  public void testStockCompositionData(){
//
//  }
}



