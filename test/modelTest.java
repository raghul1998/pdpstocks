import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import model.ModelControllerInteract;
import model.ModelControllerInteractImpl;
import model.StockCompositionData;
import model.TypeofAction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class modelTest {

  @Test
  public void testModelCreatePortfolio(){
    String[] args = {"modelTest_esha"};
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO,args,0);

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
  public void testModelDeleteEmptyPortfolio(){
    String[] args = {"modelTest_esha2"};
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO,args,0);
    obj.modelControllerInteract(TypeofAction.DELETE_EMPTY_PORTFOLIO,null,0);

    File dir = new File("userdata/user1/");
    File[] directoryListing = dir.listFiles();

    int count=0;
    if (directoryListing != null) {
      for (File i : directoryListing) {
        if (!i.getName().equals("pf_" + args[0] + ".csv")) {
          count++;
        }
      }
    }
    assertEquals(count,dir.listFiles().length);
  }

  @Test
  public void testModelGetStockData(){
    String[] args = {"GOOG"};
    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.GET_STOCK_DATA,args,0);

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
  public void testModelBuyStocks() throws FileNotFoundException {
    // check inside the given portfolio.csv if the current timestamp present in csv file
    String[] name = {"modelTest_esha2"};
    String[] stockTicker = {"META"};


    ModelControllerInteract obj = new ModelControllerInteractImpl();
    obj.modelControllerInteract(TypeofAction.CREATE_PORTFOLIO,name,0);
    obj.modelControllerInteract(TypeofAction.GET_STOCK_DATA,stockTicker,0);
    obj.modelControllerInteract(TypeofAction.BUY_STOCKS,stockTicker,0);

    Timestamp instant= Timestamp.from(Instant.now());
    String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(instant);

    String filename = "userdata/user1/" + "pf_"+ name[0].toString()+".csv";
//    BufferedReader stockData = null;
//    try {
//      stockData = new BufferedReader(new FileReader(filename));
//    } catch (Exception e) {
//      System.out.println("Supported stocks file not found " + e.getMessage());
//    }

    if(new Scanner(new File(filename))
            .next().contains(s)){
      System.out.println("matched");
    }
    System.out.println("not matched");
  }
}
