import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the Controller.
 */
public class ControllerTest {

  private String readStockDateFromPortfolioCsv(String portfolioName, int lineNumber)
          throws IOException {
    String filename = "userdata/user1/" + "pf_" + portfolioName + ".csv";
    String[] cols = null;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    String dateLastKnown = null;
    int l = 3 + lineNumber;

    while ((line = br.readLine()) != null && l >= 0) {
      cols = line.split(",");
      if (l == 1) {
        dateLastKnown = cols[2];
      }
      l--;
    }
    return dateLastKnown;
  }

  private String readPurchaseDateFromCsv(String portfolioName, int lineNumber)
          throws IOException {
    String filename = "userdata/user1/" + "pf_" + portfolioName + ".csv";
    String[] cols = null;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    String datePurchase = null;
    int l = 3 + lineNumber;

    while ((line = br.readLine()) != null && l >= 0) {
      cols = line.split(",");
      if (l == 1) {
        datePurchase = cols[0];
      }
      l--;
    }
    return datePurchase.substring(0, 10);
  }

  private String readStockPriceFromPortfolioCsv(String portfolioName, int lineNumber)
          throws IOException {
    String filename = "userdata/user1/" + "pf_" + portfolioName + ".csv";
    String[] cols = null;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    String price = null;
    int l = 3 + lineNumber;

    while ((line = br.readLine()) != null && l >= 0) {
      cols = line.split(",");
      if (l == 1) {
        price = cols[6];
      }
      l--;
    }
    return price;
  }


  private String readStockDateFromStockDataCsv() {
    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      throw new RuntimeException();
    }

    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return splitStockData[3];
  }

  private String readStockPriceFromStockDataCsv() {
    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      throw new RuntimeException();
    }

    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      throw new RuntimeException();
    }
    return splitStockData[1];
  }


  // creating portfolio

  @Test
  public void testControllerCreatePortfolio() throws IOException {
    String userInput = "1" + "\n" + "controllerTest1_health" + "\n" + "1" + "\n" + "1"
            + "\n" + "1" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST1_HEALTH Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest1_health", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest1_health", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST1_HEALTH PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // create portfolio and view portfolio

  @Test
  public void testControllerCreatePortfolioAndViewPortfolio() throws IOException {
    String userInput = "1" + "\n" + "controllerTest2_bharati" + "\n" + "1" + "\n"
            + "10" + "\n" + "11" + "\n" + "y" + "\n" + "5" + "\n" + "3" + "\n" + "y"
            + "\n" + "7" + "\n" + "100" + "\n" + "n" + "\n" + "2" + "\n" + "2" + "\n"
            + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST2_BHARATI Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Walmart\n"
            + "Symbol: WMT\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest2_bharati", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest2_bharati", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest2_bharati", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest2_bharati", 2)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Johnson\n"
            + "Symbol: JNJ\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest2_bharati", 3)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest2_bharati", 3)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST2_BHARATI PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\nCONTROLLERTEST2_BHARATI PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest2_bharati", 1) + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\nWalmart (WMT) \t 11\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2_bharati", 1)) * 100) / 100 + "\t $"
            + 11 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2_bharati", 1)) * 100) / 100
            + "\nTesla (TSLA) \t 3\t $" + Math.floor(
            Double.parseDouble(readStockPriceFromPortfolioCsv(
                    "controllerTest2_bharati", 2)) * 100) / 100
            + "\t $" + 3 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2_bharati", 2)) * 100) / 100
            + "\nJohnson (JNJ) \t 100\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2_bharati", 3)) * 100) / 100
            + "\t $" + 100 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2_bharati", 3)) * 100) / 100
            + "\n"
            + "\nTotal Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest2_bharati", 1)
            + ": $" + (11 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2_bharati", 1)) * 100) / 100
            + 3 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2_bharati", 2)) * 100) / 100 + 100
            * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2_bharati", 3)) * 100) / 100)
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // without buying anything, no portfolio created

  @Test
  public void testControllerWithoutBuyingAnythingNoPortfolioCreated() {
    String userInput = "1" + "\n" + "Neha" + "\n" + "1" + "\n" + "10" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "NEHA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Walmart\n"
            + "Symbol: WMT\n"
            + "Time: " + readStockDateFromStockDataCsv() + "\n"
            + "Price: $" + readStockPriceFromStockDataCsv() + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // two same stocks that are bought, can be seen combined in composition

  @Test
  public void testControllerClubTwoStocksOfSameTickerComposition() throws IOException {
    String userInput = "1" + "\n" + "controllerTest3_bharat" + "\n" + "1" + "\n" + "3"
            + "\n" + "3" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "n"
            + "\n" + "2" + "\n" + "3" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST3_BHARAT Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest3_bharat", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest3_bharat", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest3_bharat", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest3_bharat", 2)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST3_BHARAT PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\nCONTROLLERTEST3_BHARAT PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest3_bharat", 1)
            + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\nGoogle (GOOG) \t 5\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest3_bharat", 1)) * 100) / 100
            + "\t $" + 5 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv("controllerTest3_bharat",
                    1)) * 100) / 100 + "\n"
            + "\nTotal Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest3_bharat", 1)
            + ": $" + 5 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest3_bharat", 1)) * 100) / 100
            + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // overriding same name portfolio

  @Test
  public void testControllerOverrideSamePortfolio() throws IOException {
    String userInput = "1" + "\n" + "controllerTest3_bharat" + "\n" + "y" + "\n"
            + "1" + "\n" + "8" + "\n" + "7" + "\n" + "n" + "\n" + "2"
            + "\n" + "3" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "This portfolio already exists.\n"
            + "If you want to override this portfolio, then press 'y'. "
            + "If you want to enter another name, press 'n'. "
            + "If you want to go main screen, press 'b'.\n"
            + "\n"
            + "\nCREATE PORTFOLIO MENU\n"
            + "\nCONTROLLERTEST3_BHARAT Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Amazon\n"
            + "Symbol: AMZN\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest3_bharat", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest3_bharat", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST3_BHARAT PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\nCONTROLLERTEST3_BHARAT PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest3_bharat", 1) + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $"
            + Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest3_bharat", 1)) * 100) / 100
            + "\t $" + 7 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest3_bharat", 1)) * 100) / 100
            + "\n"
            + "\nTotal Portfolio Value as on "
            + readPurchaseDateFromCsv(
            "controllerTest3_bharat", 1)
            + ": $" + 7 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest3_bharat", 1)) * 100) / 100
            + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // viewing portfolio by inputting some previous date

  @Test
  public void testControllerInputDate() throws IOException {
    String userInput = "3" + "\n" + "4" + "\n" + "2022-10-10" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            +
            "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Value of ESHA PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-10-10\t Total Value\n"
            + "\nAmazon (AMZN) \t 4\t $106.8\t $427.2\n"
            + "\n"
            + "Total Portfolio Value is on 2022-10-10: $427.2\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // another example of viewing portfolio by inputting some previous date

  @Test
  public void testControllerInputDate2() {
    String userInput = "3" + "\n" + "4" + "\n" + "2019-12-12" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Value of ESHA PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2019-12-12\t Total Value\n"
            + "\nAmazon (AMZN) \t 4\t $110.18\t $440.72\n"
            + "\n"
            + "Total Portfolio Value is on 2019-12-12: $440.72"
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // inputting wrong date

  @Test
  public void testControllerInvalidDate() {
    String userInput = "3" + "\n" + "4" + "\n" + "9999-98-88" + "\n" + "2022-02-02"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "Value of ESHA PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nAmazon (AMZN) \t 4\t $118.1\t $472.4\n"
            + "\n"
            + "Total Portfolio Value is on 2022-02-02: $472.4"
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // another example of inputting wrong date

  @Test
  public void testControllerInvalidDate2() {
    String userInput = "3" + "\n" + "4" + "\n" + "123445" + "\n" + "2022-02-02"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "Value of ESHA PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nAmazon (AMZN) \t 4\t $118.1\t $472.4\n"
            + "\n"
            + "Total Portfolio Value is on 2022-02-02: $472.4"
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // inputting future date

  @Test
  public void testControllerInvalidDate3FutureDate() {
    String userInput = "3" + "\n" + "4" + "\n" + "2024-10-10" + "\n" + "2022-02-02"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "Value of ESHA PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nAmazon (AMZN) \t 4\t $118.1\t $472.4\n"
            + "\n"
            + "Total Portfolio Value is on 2022-02-02: $472.4"
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // entering wrong option for selecting portfolio

  @Test
  public void testControllerEnterWrongOptionForSelectingPortfolio() {
    String userInput = "2" + "\n" + "89" + "\n" + "b" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // entering wrong option for initial choice of inputs

  @Test
  public void testControllerInvalidInitialChoice() {
    String userInput = "7" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // testing set of all invalid inputs

  @Test
  public void testControllerInvalidInputs() {
    String userInput = "5" + "\n" + "1" + "\n" + "" + "\n" + "Riya" + "\n" + "6" + "\n" + "1"
            + "\n" + "11" + "\n" + "10" + "\n" + "-4" + "\n" + "4.3"
            + "\n" + "l" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "Cannot create a portfolio with empty name. Enter a valid name.\n"
            + "If you want to go back to main menu, press '0'.\n"
            + "\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "RIYA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "RIYA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "Not a valid input. Please enter the correct stock.\n"
            + "If you want to go back to main menu, press 'm'."
            + "\n"
            + "\n"
            + "\nCURRENT STOCK PRICE\n"
            + "StockName: Walmart\n"
            + "Symbol: WMT\n"
            + "Time: " + readStockDateFromStockDataCsv() + "\n"
            + "Price: $" + readStockPriceFromStockDataCsv() + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // test same price is seen on same date using view portfolio and value of portfolio

  @Test
  public void testControllerShowSamePriceOnSameDate() throws IOException {
    String userInput = "1" + "\n" + "controllerTest4_Anuja" + "\n" + "1" + "\n" + "1" + "\n" + "1"
            + "\n" + "n"
            + "\n" + "2" + "\n" + "4" + "\n" + "m" + "\n" + "3" + "\n" + "5" + "\n" + "2022-11-01"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST4_ANUJA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest4_Anuja", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest4_Anuja", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST4_ANUJA PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. CONTROLLERTEST4_ANUJA\n"
            + "5. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\n"
            + "CONTROLLERTEST4_ANUJA PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest4_Anuja", 1)
            + "\n"
            + "\n"
            + "Name (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 1\t $"
            + Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest4_Anuja", 1)) * 100) / 100
            + "\t $" + 1 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest4_Anuja", 1)) * 100) / 100
            + "\n"
            + "\n"
            + "Total Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest4_Anuja", 1)
            + ": $" + 1 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest4_Anuja", 1)) * 100) / 100
            + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. CONTROLLERTEST4_ANUJA\n"
            + "5. ESHA\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "Value of ESHA PORTFOLIO\n"
            + "\n"
            + "Name (Symbol) \t Quantity\t Share Value on 2022-11-01\t Total Value\n"
            + "\n"
            + "Amazon (AMZN) \t 4\t $102.4\t $409.6\n"
            + "\n"
            + "Total Portfolio Value is on 2022-11-01: $409.6\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // if how many stocks = 0, the portfolio is not created

  @Test
  public void testControllerHowManyStocksIsZeroSoPortfolioNotCreated() throws IOException {
    String userInput = "1" + "\n" + "Meghna" + "\n" + "1" + "\n" + "1" + "\n"
            + "0" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "MEGHNA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: " + readStockDateFromStockDataCsv() + "\n"
            + "Price: $" + readStockPriceFromStockDataCsv() + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // this testcase should not display entry in the portfolio when how many stocks = 0

  @Test
  public void testControllerHowManyStocksIsZeroSoStockNotDisplayedInPortfolio()
          throws IOException {
    String userInput = "1" + "\n" + "controllerTest5_Ash" + "\n" + "1" + "\n" + "4" + "\n"
            + "3" + "\n" + "y" + "\n" + "1" + "\n"
            + "0" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST5_ASH Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Apple\n"
            + "Symbol: AAPL\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest5_Ash", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest5_Ash", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: " + readStockDateFromStockDataCsv() + "\n"
            + "Price: $" + readStockPriceFromStockDataCsv() + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 4 stocks bought by user

  @Test
  public void testController4StocksBoughtByTheUser() throws IOException {
    String userInput = "1" + "\n" + "controllerTest6_Shubham" + "\n" + "1" + "\n"
            + "1" + "\n" + "10" + "\n" + "Y" + "\n"
            + "2" + "\n" + "10" + "\n" + "y" + "\n"
            + "3" + "\n" + "10" + "\n" + "y" + "\n"
            + "4" + "\n" + "10" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST6_SHUBHAM Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest6_Shubham", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6_Shubham", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest6_Shubham", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6_Shubham", 2)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest6_Shubham", 3)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6_Shubham", 3)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Apple\n"
            + "Symbol: AAPL\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest6_Shubham", 4)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6_Shubham", 4)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST6_SHUBHAM PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 5 stocks bought by a user

  @Test
  public void testController5ByTheUser() throws IOException {
    String userInput = "1" + "\n" + "controllerTest7_Sharayu" + "\n" + "1" + "\n"
            + "1" + "\n" + "10" + "\n" + "Y" + "\n"
            + "2" + "\n" + "10" + "\n" + "y" + "\n"
            + "3" + "\n" + "10" + "\n" + "y" + "\n"
            + "4" + "\n" + "10" + "\n" + "y" + "\n"
            + "5" + "\n" + "10" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST7_SHARAYU Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest7_Sharayu", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7_Sharayu", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest7_Sharayu", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7_Sharayu", 2)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest7_Sharayu", 3)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7_Sharayu", 3)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Apple\n"
            + "Symbol: AAPL\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest7_Sharayu", 4)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7_Sharayu", 4)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: "
            + readStockDateFromPortfolioCsv("controllerTest7_Sharayu", 5)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7_Sharayu", 5)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST7_SHARAYU PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // three same stocks that are bought, can be seen combined in composition and view
  // that composition

  @Test
  public void testControllerClubThreeStocksOfSameTickerComposition() throws IOException {
    String userInput = "1" + "\n" + "controllerTest8_shweta" + "\n" + "1" + "\n" + "1"
            + "\n" + "1" + "\n" + "y" + "\n" + "1" + "\n" + "1" + "\n" + "y"
            + "\n" + "1" + "\n" + "1" + "\n" + "n" + "\n" + "2" + "\n" + "8"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST8_SHWETA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest8_shweta", 1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest8_shweta", 1) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest8_shweta", 1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest8_shweta", 1) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest8_shweta", 2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest8_shweta", 2) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST8_SHWETA PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1_HEALTH\n"
            + "2. CONTROLLERTEST2_BHARATI\n"
            + "3. CONTROLLERTEST3_BHARAT\n"
            + "4. CONTROLLERTEST4_ANUJA\n"
            + "5. CONTROLLERTEST5_ASH\n"
            + "6. CONTROLLERTEST6_SHUBHAM\n"
            + "7. CONTROLLERTEST7_SHARAYU\n"
            + "8. CONTROLLERTEST8_SHWETA\n"
            + "9. ESHA\n"
            + "10. TEST\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\n"
            + "CONTROLLERTEST8_SHWETA PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest8_shweta", 1) + "\n"
            + "\n"
            + "Name (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 3\t $"
            + Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest8_shweta", 1)) * 100) / 100
            + "\t $" + 3 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest8_shweta", 1)) * 100) / 100 + "\n"
            + "\n"
            + "Total Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest8_shweta", 1)
            + ": $" + 3 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest8_shweta", 1)) * 100) / 100 + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 10 stocks bought by user

  @Test
  public void testController10StocksBoughtByUser() throws IOException {
    String userInput = "1" + "\n" + "controllerTest9_Steven" + "\n" + "1" + "\n"
            + "2" + "\n" + "1" + "\n" + "Y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "1" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST9_STEVEN Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            1) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            2) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            3) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            3) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            4) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            4) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            5) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            5) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            6) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            6) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            7) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            7) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            8) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            8) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            9) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            9) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9_Steven",
            10) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9_Steven",
            10) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST9_STEVEN PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  // 18 stocks bought by user

  @Test
  public void testController18StocksBoughtByUser() throws IOException {
    String userInput = "1" + "\n" + "controllerTest10_Kat" + "\n" + "1" + "\n"
            + "3" + "\n" + "2" + "\n" + "Y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "y" + "\n"
            + "3" + "\n" + "2" + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST10_KAT Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            1) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            2) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            3) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            3) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            4) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            4) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            5) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            5) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            6) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            6) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            7) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            7) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            8) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            8) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            9) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            9) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            10) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            10) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            11) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            11) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            12) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            12) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            13) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            13) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            14) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            14) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            15) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            15) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            16) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            16) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            17) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            17) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10_Kat",
            18) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10_Kat",
            18) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST10_KAT PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 28 stocks bought by user

  @Test
  public void testController28StocksBoughtByUser() throws IOException {
    String userInput = "1" + "\n" + "controllerTest11_Camella" + "\n" + "1" + "\n"
            + "5" + "\n" + "1" + "\n" + "Y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "y" + "\n"
            + "5" + "\n" + "1" + "\n" + "y" + "\n" + "5" + "\n" + "1" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST11_CAMELLA Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            1) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            2) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            3) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            3) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            4) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            4) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            5) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            5) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            6) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            6) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            7) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            7) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            8) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            8) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            9) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            9) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            10) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            10) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            11) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            11) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            12) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            12) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            13) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            13) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            14) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            14) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            15) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            15) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            16) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            16) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            17) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            17) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            18) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            18) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            19) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            19) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            20) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            20) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            21) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            21) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            22) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            22) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            23) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            23) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            24) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            24) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            25) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            25) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            26) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            26) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            27) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            27) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "\n"
            + "1. Microsoft (MSFT)\n"
            + "2. Meta (META)\n"
            + "3. Google (GOOG)\n"
            + "4. Apple (AAPL)\n"
            + "5. Tesla (TSLA)\n"
            + "6. JPMorgan Chase (JPM)\n"
            + "7. Johnson (JNJ)\n"
            + "8. Amazon (AMZN)\n"
            + "9. UnitedHealth (UNH)\n"
            + "10. Walmart (WMT)\n"
            + "\n"
            + "Which stock would you like to buy?\n"
            + "\n"
            + "CURRENT STOCK PRICE\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11_Camella",
            28) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11_Camella",
            28) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST11_CAMELLA PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // initial choice exit

  @Test
  public void testInitialChoiceExit() {
    String userInput = "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }
}
