import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * This class is the parent class for all other test classes.
 */
public class TestParentClass {

  // A helper function to read date from StockData.csv
  String readStockDateFromStockDataCsv() {
    String line;
    String splitBy = ",";
    BufferedReader stockData;
    String[] splitStockData;
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      throw new RuntimeException();
    }

    try {
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return splitStockData[3];
  }

  // A helper function to read price from StockData.csv
  String readStockPriceFromStockDataCsv() {
    String line;
    String splitBy = ",";
    BufferedReader stockData;
    String[] splitStockData;
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      throw new RuntimeException();
    }

    try {
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return splitStockData[1];
  }

  // A helper function to delete a file

  void deleteDirectory() {
    File directory = new File("userdata/user1");
    if (directory.exists()) {
      String[] filename = directory.list();
      assert filename != null;
      for (String fName : filename) {
        File currentFile = new File(directory.getPath(), fName);
        currentFile.delete();
      }
    }
  }

  // A helper function to get the stock data from the portfolio

  String readStockDataFromPortfolioCsv(String portfolioName, int lineNumber, int colNumber,
                                       boolean needSplit)
          throws IOException {
    String filename = "userdata/user1/" + "pf_" + portfolioName + ".csv";
    String[] cols;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    String value = null;
    int l = 4 + lineNumber;

    while ((line = br.readLine()) != null && l >= 0) {
      cols = line.split(",");
      if (l == 1) {
        value = cols[colNumber];
      }
      l--;
    }

    if (needSplit) {
      assert value != null;
      return value.substring(0, 10);
    }
    return value;
  }

  int readPortfolioCsvIndex(String portfolioName) throws IOException {
    String filename = "userdata/user1/" + "pf_" + portfolioName + ".csv";
    String[] cols;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    int value = -1;
    int l = 0;
    while ((line = br.readLine()) != null && l < 2) {
      cols = line.split(",");
      if (l == 1) {
        value = Integer.parseInt(cols[1]);
      }
      l++;
    }
    return value;
  }

  void deleteFileInDirectory(String fileName) {
    File directory = new File("userdata/user1");
    if (directory.exists()) {
      String[] filename = directory.list();
      assert filename != null;
      for (String fName : filename) {
        if (Objects.equals(fileName, fName)) {
          File currentFile = new File(directory.getPath(), fName);
          currentFile.delete();
          break;
        }
      }
    }
  }

}
