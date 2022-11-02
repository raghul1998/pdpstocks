import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is the parent class for all other test classes
 */
public class TestParentClass {
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
    int l = 3 + lineNumber;

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
}
