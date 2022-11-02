import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Objects;

import model.ModelControllerInteract;
import model.ModelControllerInteractImpl;
import model.TypeofAction;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the Model.
 */

public class ModelTest {

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
    String[] args = {"GOOG"};
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

  @Test
  public void testModelBuyStocks() throws IOException {
    String[] name = {"modelTest_esha2"};
    String[] stockTicker = {"META"};


    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO, name, 0);
    obj.modelControllerInteract(TypeofAction.GET_STOCK_DATA, stockTicker, 0);
    obj.modelControllerInteract(TypeofAction.BUY_STOCKS, stockTicker, 0);

    Timestamp instant = Timestamp.from(Instant.now());
    String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(instant);

    String filename = "userdata/user1/" + "pf_" + name[0] + ".csv";
    String[] words;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String str;
    while ((str = br.readLine()) != null) {
      words = str.split(",");
      for (String word : words) {
        if (word.equals(currentTime)) {
          assertEquals(currentTime, word);
          return;
        }
      }
    }
    assert (false);
  }
}
