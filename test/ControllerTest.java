import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the Controller.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest {
  // creating portfolio
  @Test
  public void testAControllerCreatePortfolio() throws IOException {
    deleteDirectory();
    deleteFileInDirectory("pf_controllerTest1.csv");
    String userInput = "1" + "\n" + "controllerTest1" + "\n" + "1" + "\n" + "1"
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
            + "CONTROLLERTEST1 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest1", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest1", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST1 PORTFOLIO CREATED...!!!\n"
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

  // create portfolio and view portfolio and show composition

  @Test
  public void testBControllerCreatePortfolioAndViewPortfolio() throws IOException {
    deleteFileInDirectory("pf_controllerTest2.csv");
    String userInput = "1" + "\n" + "controllerTest2" + "\n" + "1" + "\n"
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
            + "CONTROLLERTEST2 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest2", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest2", 1)
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
            + readStockDateFromPortfolioCsv("controllerTest2", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest2", 2)
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
            + readStockDateFromPortfolioCsv("controllerTest2", 3)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest2", 3)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST2 PORTFOLIO CREATED...!!!\n"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\nCONTROLLERTEST2 PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest2", 1) + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\nWalmart (WMT) \t 11\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2", 1)) * 100) / 100 + "\t $"
            + 11 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2", 1)) * 100) / 100
            + "\nTesla (TSLA) \t 3\t $" + Math.floor(
            Double.parseDouble(readStockPriceFromPortfolioCsv(
                    "controllerTest2", 2)) * 100) / 100
            + "\t $" + 3 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2", 2)) * 100) / 100
            + "\nJohnson (JNJ) \t 100\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2", 3)) * 100) / 100
            + "\t $" + 100 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2", 3)) * 100) / 100
            + "\n"
            + "\nTotal Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest2", 1)
            + ": $" + (11 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2", 1)) * 100) / 100
            + 3 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest2", 2)) * 100) / 100 + 100
            * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest2", 3)) * 100) / 100)
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
  public void testCControllerWithoutBuyingAnythingNoPortfolioCreated() {
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
  public void testDControllerClubTwoStocksOfSameTickerComposition() throws IOException {
    deleteFileInDirectory("pf_controllerTest3.csv");
    String userInput = "1" + "\n" + "controllerTest3" + "\n" + "1" + "\n" + "3"
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
            + "CONTROLLERTEST3 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest3", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest3", 1)
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
            + readStockDateFromPortfolioCsv("controllerTest3", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest3", 2)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST3 PORTFOLIO CREATED...!!!\n"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\nCONTROLLERTEST3 PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest3", 1)
            + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\nGoogle (GOOG) \t 5\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest3", 1)) * 100) / 100
            + "\t $" + 5 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv("controllerTest3",
                    1)) * 100) / 100 + "\n"
            + "\nTotal Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest3", 1)
            + ": $" + 5 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest3", 1)) * 100) / 100
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
  public void testEControllerOverrideSamePortfolio() throws IOException {
    String userInput = "1" + "\n" + "controllerTest3" + "\n" + "y" + "\n"
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
            + "\nCONTROLLERTEST3 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest3", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest3", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST3 PORTFOLIO CREATED...!!!\n"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\nCONTROLLERTEST3 PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest3", 1) + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $"
            + Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest3", 1)) * 100) / 100
            + "\t $" + 7 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest3", 1)) * 100) / 100
            + "\n"
            + "\nTotal Portfolio Value as on "
            + readPurchaseDateFromCsv(
            "controllerTest3", 1)
            + ": $" + 7 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest3", 1)) * 100) / 100
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
  public void testFControllerInputDate() throws IOException {
    String userInput = "3" + "\n" + "3" + "\n" + "2022-10-10" + "\n" + "m" + "\n" + "e";
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-10-10\t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $100.8\t $705.6\n"
            + "\n"
            + "Total Portfolio Value is on 2022-10-10: $705.6\n"
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
  public void testGControllerInputDate2() {
    String userInput = "3" + "\n" + "3" + "\n" + "2019-12-12" + "\n" + "m" + "\n" + "e";
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2019-12-12\t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $104.18\t $729.26\n"
            + "\n"
            + "Total Portfolio Value is on 2019-12-12: $729.26"
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
  public void testHControllerInvalidDate() {
    String userInput = "3" + "\n" + "3" + "\n" + "9999-98-88" + "\n" + "2022-02-02"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $112.1\t $784.7\n"
            + "\n"
            + "Total Portfolio Value is on 2022-02-02: $784.7"
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
  public void testIControllerInvalidDate2() {
    String userInput = "3" + "\n" + "3" + "\n" + "123445" + "\n" + "2022-02-02"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $112.1\t $784.7\n"
            + "\n"
            + "Total Portfolio Value is on 2022-02-02: $784.7"
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
  public void testJControllerInvalidDate3FutureDate() {
    String userInput = "3" + "\n" + "3" + "\n" + "2024-10-10" + "\n" + "2022-02-02"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): "
            + "\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nAmazon (AMZN) \t 7\t $112.1\t $784.7\n"
            + "\n"
            + "Total Portfolio Value is on 2022-02-02: $784.7"
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
  public void testKControllerEnterWrongOptionForSelectingPortfolio() {
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
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
  public void testLControllerInvalidInitialChoice() {
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
  public void testMControllerInvalidInputs() {
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
  public void testNControllerShowSamePriceOnSameDate() throws IOException {
    deleteFileInDirectory("pf_controllerTest4.csv");
    String userInput = "1" + "\n" + "controllerTest4" + "\n" + "1" + "\n" + "1" + "\n" + "1"
            + "\n" + "n"
            + "\n" + "2" + "\n" + "4" + "\n" + "m" + "\n" + "3" + "\n" + "4" + "\n" + "2022-11-01"
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
            + "CONTROLLERTEST4 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest4", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest4", 1)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST4 PORTFOLIO CREATED...!!!\n"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "4. CONTROLLERTEST4\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\n"
            + "CONTROLLERTEST4 PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest4", 1)
            + "\n"
            + "\n"
            + "Name (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 1\t $"
            + Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest4", 1)) * 100) / 100
            + "\t $" + 1 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest4", 1)) * 100) / 100
            + "\n"
            + "\n"
            + "Total Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest4", 1)
            + ": $" + 1 * Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest4", 1)) * 100) / 100
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "4. CONTROLLERTEST4\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "Value of CONTROLLERTEST4 PORTFOLIO\n"
            + "\n"
            + "Name (Symbol) \t Quantity\t Share Value on 2022-11-01\t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 1\t $255.9\t $255.9\n"
            + "\n"
            + "Total Portfolio Value is on 2022-11-01: $255.9\n"
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
  public void testOControllerHowManyStocksIsZeroSoPortfolioNotCreated() throws IOException {
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
  public void testPControllerHowManyStocksIsZeroSoStockNotDisplayedInPortfolio()
          throws IOException {
    deleteFileInDirectory("pf_controllerTest5.csv");
    String userInput = "1" + "\n" + "controllerTest5" + "\n" + "1" + "\n" + "4" + "\n"
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
            + "CONTROLLERTEST5 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest5", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest5", 1)
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
  public void testQController4StocksBoughtByTheUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest6.csv");
    String userInput = "1" + "\n" + "controllerTest6" + "\n" + "1" + "\n"
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
            + "CONTROLLERTEST6 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest6", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6", 1)
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
            + readStockDateFromPortfolioCsv("controllerTest6", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6", 2)
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
            + readStockDateFromPortfolioCsv("controllerTest6", 3)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6", 3)
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
            + readStockDateFromPortfolioCsv("controllerTest6", 4)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest6", 4)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST6 PORTFOLIO CREATED...!!!\n"
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
  public void testRController5ByTheUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest7.csv");
    String userInput = "1" + "\n" + "controllerTest7" + "\n" + "1" + "\n"
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
            + "CONTROLLERTEST7 Portfolio\n"
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
            + readStockDateFromPortfolioCsv("controllerTest7", 1)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7", 1)
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
            + readStockDateFromPortfolioCsv("controllerTest7", 2)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7", 2)
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
            + readStockDateFromPortfolioCsv("controllerTest7", 3)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7", 3)
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
            + readStockDateFromPortfolioCsv("controllerTest7", 4)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7", 4)
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
            + readStockDateFromPortfolioCsv("controllerTest7", 5)
            + "\n"
            + "Price: $"
            + readStockPriceFromPortfolioCsv("controllerTest7", 5)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST7 PORTFOLIO CREATED...!!!\n"
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
  public void testSControllerClubThreeStocksOfSameTickerComposition() throws IOException {
    deleteFileInDirectory("pf_controllerTest8.csv");
    String userInput = "1" + "\n" + "controllerTest8" + "\n" + "1" + "\n" + "1"
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
            + "CONTROLLERTEST8 Portfolio\n"
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest8", 1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest8", 1) + "\n"
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest8", 1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest8", 1) + "\n"
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest8", 2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest8", 2) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST8 PORTFOLIO CREATED...!!!\n"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "4. CONTROLLERTEST4\n"
            + "5. CONTROLLERTEST5\n"
            + "6. CONTROLLERTEST6\n"
            + "7. CONTROLLERTEST7\n"
            + "8. CONTROLLERTEST8\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "\n"
            + "CONTROLLERTEST8 PORTFOLIO COMPOSITION - Created on "
            + readPurchaseDateFromCsv("controllerTest8", 1) + "\n"
            + "\n"
            + "Name (Symbol) \t Quantity \t Price of each share \t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 3\t $"
            + Math.floor(Double.parseDouble(
            readStockPriceFromPortfolioCsv(
                    "controllerTest8", 1)) * 100) / 100
            + "\t $" + 3 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest8", 1)) * 100) / 100 + "\n"
            + "\n"
            + "Total Portfolio Value as on "
            + readPurchaseDateFromCsv("controllerTest8", 1)
            + ": $" + 3 * Math.floor(Double.parseDouble(readStockPriceFromPortfolioCsv(
            "controllerTest8", 1)) * 100) / 100 + "\n"
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
  public void testTController10StocksBoughtByUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest9.csv");
    String userInput = "1" + "\n" + "controllerTest9" + "\n" + "1" + "\n"
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
            + "CONTROLLERTEST9 Portfolio\n"
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            3) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            4) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            5) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            6) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            7) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            8) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            9) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest9",
            10) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest9",
            10) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST9 PORTFOLIO CREATED...!!!\n"
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
  public void testUController18StocksBoughtByUser() throws IOException {
    String userInput = "1" + "\n" + "controllerTest10" + "\n" + "1" + "\n"
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
            + "CONTROLLERTEST10 Portfolio\n"
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            3) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            4) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            5) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            6) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            7) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            8) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            9) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            10) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            11) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            12) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            13) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            14) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            15) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            16) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            17) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest10",
            18) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest10",
            18) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST10 PORTFOLIO CREATED...!!!\n"
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
  public void testVController28StocksBoughtByUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest11.csv");
    String userInput = "1" + "\n" + "controllerTest11" + "\n" + "1" + "\n"
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
            + "CONTROLLERTEST11 Portfolio\n"
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            2) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            3) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            4) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            5) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            6) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            7) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            8) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            9) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            10) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            11) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            12) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            13) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            14) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            15) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            16) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            17) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            18) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            19) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            20) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            21) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            22) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            23) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            24) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            25) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            26) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            27) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
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
            + "Time: " + readStockDateFromPortfolioCsv("controllerTest11",
            28) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv("controllerTest11",
            28) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST11 PORTFOLIO CREATED...!!!\n"
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
  public void testWInitialChoiceExit() {
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

  // how many = large number
  @Test
  public void testXInvalidHowMany() throws IOException {
    deleteFileInDirectory("pf_controllerTest12.csv");
    String userInput = "1" + "\n" + "controllerTest12" + "\n" + "2"
            + "1" + "\n" + "controllerTest12" + "\n"
            + "1" + "\n" + "10" + "\n" + "14000000000000000000"
            + "\n" + "10000000" + "\n" + "n" + "\n" + "e";
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
            + "CONTROLLERTEST12 Portfolio\n"
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
            + "CONTROLLERTEST12 Portfolio\n"
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
            + "CONTROLLERTEST12 Portfolio\n"
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
            + "Time: " + readStockDateFromPortfolioCsv(
            "controllerTest12", 1) + "\n"
            + "Price: $" + readStockPriceFromPortfolioCsv(
            "controllerTest12", 1) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST12 PORTFOLIO CREATED...!!!\n"
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

  // string is empty for portfolio name

  @Test
  public void testYStringEmptyForName() {
    String userInput = "1" + "\n" + "" + "\n" + "0" + "\n" + "e";
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
            + "Cannot create a portfolio with empty name. Enter a valid name.\n"
            + "If you want to go back to main menu, press '0'.\n"
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

  // view list of portfolios and entering wrong option

  @Test
  public void testZAViewPortfolioAndEnterWrongOption() {
    String userInput = "2" + "\n" + "-52" + "\n" + "b" + "\n" + "e";
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST10\n"
            + "3. CONTROLLERTEST11\n"
            + "4. CONTROLLERTEST12\n"
            + "5. CONTROLLERTEST2\n"
            + "6. CONTROLLERTEST3\n"
            + "7. CONTROLLERTEST4\n"
            + "8. CONTROLLERTEST5\n"
            + "9. CONTROLLERTEST6\n"
            + "10. CONTROLLERTEST7\n"
            + "11. CONTROLLERTEST8\n"
            + "12. CONTROLLERTEST9\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
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

  // value of portfolio. enter wrong portfolio option

  @Test
  public void testZBValueOfPortfolioEnterWrongPortfolioNumber() {
    String userInput = "3" + "\n" + "-9" + "\n" + "b" + "\n" + "e";
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST10\n"
            + "3. CONTROLLERTEST11\n"
            + "4. CONTROLLERTEST12\n"
            + "5. CONTROLLERTEST2\n"
            + "6. CONTROLLERTEST3\n"
            + "7. CONTROLLERTEST4\n"
            + "8. CONTROLLERTEST5\n"
            + "9. CONTROLLERTEST6\n"
            + "10. CONTROLLERTEST7\n"
            + "11. CONTROLLERTEST8\n"
            + "12. CONTROLLERTEST9\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
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

  // buy a stock, enter wrong stock option
  @Test
  public void testZCBuyStockEnterWrongStockOption() {
    String userInput = "1" + "\n" + "ram" + "\n" + "1" + "\n"
            + "0" + "\n" + "90" + "\n" + "m" + "\n" + "e";
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
            + "RAM Portfolio\n"
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
            + "If you want to go back to main menu, press 'm'.\n"
            + "\n"
            + "Not a valid input. Please enter the correct stock.\n"
            + "If you want to go back to main menu, press 'm'.\n"
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

  // buy a stock, enter invalid how many stocks
  @Test
  public void testZDBuyStockEnterInvalidHowManyStocks() {
    String userInput = "1" + "\n" + "e" + "\n" + "1" + "\n" + "9" + "\n" + "0"
            + "\n" + "-85" + "\n" + "8.90" + "\n" + "m" + "\n" + "e";
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
            + "E Portfolio\n"
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
            + "StockName: UnitedHealth\n"
            + "Symbol: UNH\n"
            + "Time: 2022-11-01 18:12:00\n"
            + "Price: $547.3100\n"
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

  // toggle between main menu / back
  @Test
  public void testZEToggleBetweenMainMenuAndBack() {
    String userInput = "1" + "\n" + "Mahi" + "\n" + " 2" + "\n" + "2" + "\n"
            + "-1" + "\n" + "b" + "\n" + "3" + "\n" + "1" + "\n" + "-1" + "\n" + "b"
            + "\n" + "-1" + "\n" + "b" + "\n" + "1" + "\n"
            + "Mahi" + "\n" + "1" + "\n" + "-1" + "\n" + "m" + "\n" + "e";
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
            + "MAHI Portfolio\n"
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
            + "MAHI Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
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
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST10\n"
            + "3. CONTROLLERTEST11\n"
            + "4. CONTROLLERTEST12\n"
            + "5. CONTROLLERTEST2\n"
            + "6. CONTROLLERTEST3\n"
            + "7. CONTROLLERTEST4\n"
            + "8. CONTROLLERTEST5\n"
            + "9. CONTROLLERTEST6\n"
            + "10. CONTROLLERTEST7\n"
            + "11. CONTROLLERTEST8\n"
            + "12. CONTROLLERTEST9\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n"
            + "\n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1\n"
            + "2. CONTROLLERTEST10\n"
            + "3. CONTROLLERTEST11\n"
            + "4. CONTROLLERTEST12\n"
            + "5. CONTROLLERTEST2\n"
            + "6. CONTROLLERTEST3\n"
            + "7. CONTROLLERTEST4\n"
            + "8. CONTROLLERTEST5\n"
            + "9. CONTROLLERTEST6\n"
            + "10. CONTROLLERTEST7\n"
            + "11. CONTROLLERTEST8\n"
            + "12. CONTROLLERTEST9\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
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
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "MAHI Portfolio\n"
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
            + "If you want to go back to main menu, press 'm'.\n"
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

  // A helper function to delete a file
  private void deleteFileInDirectory(String FileName) {
    File directory = new File("userdata/user1");
    if (directory.exists()) {
      String[] filename = directory.list();
      for (String fName : filename) {
        if (Objects.equals(FileName, fName)) {
          File currentFile = new File(directory.getPath(), fName);
          currentFile.delete();
          break;
        }
      }
    }
  }

  private void deleteDirectory() {
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
}
