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
import java.time.LocalDate;
import java.util.Objects;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the Controller.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTest extends TestParentClass {

  // 1
  /**
   * This test displays the creation of portfolio.
   *
   */
  @Test
  public void testAControllerCreatePortfolio() throws IOException {
    deleteDirectory();
    deleteFileInDirectory("pf_controllerTest1.csv");
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest1" + "\n" + "1" + "\n" + "1"
            + "\n" + "2022-11-11" + "\n" + "2" + "\n"+ "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest1", 1,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest1", 1,
            6, false)
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 2
  /**
   * This test displays the output for view portfolio on certain date.
   *
   */
  @Test
  public void testBControllerViewPortfolio() throws IOException {
    String userInput = "2" + "\n" + "1" + "\n" + "2022-11-11" + "\n"
            + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "\n"
            + "Value of CONTROLLERTEST1 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on " + "2022-11-11" + "\t Total Value\n"
            +"\n"
            + "Microsoft (MSFT) \t 2\t $"
            + Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest1",
            1,6, false)) * 100) / 100
            + "\t $"
            + 2 * Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest1",
            1,6, false)) * 100) / 100 + "\n"
            +"\nTotal Portfolio Value is on 2022-11-11: $"
            + 2 * Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest1",
            1,6, false)) * 100) / 100 + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 3
  /**
   * This test displays that portfolio is not created, if user doesn't buy any stock.
   *
   */
  @Test
  public void testCControllerWithoutBuyingAnythingNoPortfolioCreated() {
    String userInput = "1" + "\n" + "1"+ "\n" + "controller2" + "\n" + "1" + "\n" + "1" + "\n" + "2022-11-11"
            + "\n" + "m" + "\n"+ "2"+ "\n"+ "e"+ "\n"+"b"+ "\n"+"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLER2 Portfolio\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)"
            + "\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Microsoft\n" +
            "Symbol: MSFT\n"
            + "Time: " + readStockDateFromStockDataCsv() + "\n"
            + "Price: $" + readStockPriceFromStockDataCsv() + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "LIST OF PORTFOLIO\n"
            + "\n"
            + "1. CONTROLLERTEST1\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 4
  /**
   * This test displays two stocks of same ticker symbol that are bought on same day
   * and is seen combined in the view portfolio.
   *
   */
  @Test
  public void testDControllerClubTwoStocksOfSameTickerComposition() throws IOException {
    deleteFileInDirectory("pf_controllerTest2.csv");
    String userInput = "1" + "\n" + "1"+ "\n" +"controllerTest2" + "\n"+ "1" + "\n"+ "1" + "\n"+ "2022-11-11"
            + "\n"+ "2" + "\n"+ "y" + "\n"+ "1" + "\n"+ "2022-11-11" + "\n"+ "3" + "\n"+ "n" + "\n"
            + "2" + "\n"+ "2" + "\n"+ "2022-11-11" + "\n" + "m" + "\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest2", 1,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest2", 1,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest2", 2,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest2", 2,
            6, false)
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "\n"
            + "Value of CONTROLLERTEST2 PORTFOLIO\n"
            + "\n"
            + "Name (Symbol) \t Quantity\t Share Value on 2022-11-11\t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 5\t $"
            + Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest2",
            1,6, false)) * 100) / 100
            +"\t $"
            + 5 *  Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
            "controllerTest2",
            1,6, false)) * 100) / 100
            + "\n"
            + "\n"
            + "Total Portfolio Value is on 2022-11-11: $"
            + 5 *  Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
            "controllerTest2",
            1,6, false)) * 100) / 100
            + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 5
  /**
   * This test displays two stocks of same ticker symbol that are bought on different day
   * and is seen combined in the view portfolio.
   *
   */
  @Test
  public void testEControllerClubTwoStocksOfSameTickerComposition2() throws IOException {
    deleteFileInDirectory("pf_controllerTest3.csv");
    String userInput = "1" + "\n" + "1"+ "\n" +"controllerTest3" + "\n"+ "1" + "\n"+ "1" + "\n"+ "2022-11-11"
            + "\n"+ "2" + "\n"+ "y" + "\n"+ "1" + "\n"+ "2022-11-12" + "\n"+ "3" + "\n"+ "n" + "\n"
            + "2" + "\n"+ "3" + "\n"+ "2022-11-12" + "\n" + "m" + "\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: 2022-11-11"
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest3", 1,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: 2022-11-12"
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest3", 2,
            6, false)
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\n"
            + "Name (Symbol) \t Quantity\t Share Value on 2022-11-12\t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 5\t $"

            + Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest3",
            2,6, false)) * 100) / 100
            + "\t $"+ 5 *  Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
            "controllerTest3",
            1,6, false)) * 100) / 100
            + "\n"
            + "\n"
            + "Total Portfolio Value is on 2022-11-12: $"
            + 5 *  Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
            "controllerTest3",
            1,6, false)) * 100) / 100
            + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //6
  /**
   * This test displays how the same name portfolio gets overridden
   *
   */
  @Test
  public void testFControllerOverrideSamePortfolio() throws IOException {
    String userInput = "1" + "\n" + "1"+ "\n" + "controllerTest3" + "\n" + "y" + "\n"
            + "1" + "\n" + "1" + "\n" + "2022-11-11" + "\n" + "5" + "\n" + "n"
            + "\n" + "2"+ "\n" + "3"+ "\n" + "2022-11-11"+ "\n" + "m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest3", 1,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest3", 1,
            6, false)
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-11-11\t Total Value\n"
            + "\n"
            + "Microsoft (MSFT) \t 5\t $"
            + Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest3",
            1,6, false)) * 100) / 100
            + "\t $"
            + 5 * Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest3",
            1,6, false)) * 100) / 100 + "\n"
            + "\n"
            + "Total Portfolio Value is on 2022-11-11: $"
            + 5 * Math.floor(Double.parseDouble(readStockDataFromPortfolioCsv(
                    "controllerTest3",
            1,6, false)) * 100) / 100 + "\n"
            + "\n"
            + "Press 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 7
  /**
   * This test displays portfolio stocks after inputting some previous date.
   *
   */
  @Test
  public void testGControllerInputDate() throws IOException {
    String userInput = "3" + "\n" + "3" + "\n" + "2022-11-12" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-11-12\t Total Value\n"
            + "\nMicrosoft (MSFT) \t 5\t $"
            + Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100) / 100
            + "\t $"
            + 5 * (Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100) / 100)
            + "\n\nTotal Portfolio Value is on 2022-11-12: $"
            + 5 * (Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100) / 100)
            + "\n\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 8
  /**
   * This test displays portfolio stocks after inputting some previous date.
   *
   */
  @Test
  public void testHControllerInputDate2() {
    String userInput = "3" + "\n" + "3" + "\n" + "2019-12-12" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n"
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2019-12-12\t Total Value\n"
            + "\nMicrosoft (MSFT) \t 5\t $"
            +  Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100) / 100
            + "\t $"
            + (5 * (Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100)) / 100)
            + "\n\nTotal Portfolio Value is on 2019-12-12: $"
            + (5 * (Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100)) / 100)
            + "\n\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 9
  /**
   * This test displays message when wrong input date is given by the user.
   *
   */
  @Test
  public void testIControllerInvalidDate() {
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "\n"
            + "Value of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nMicrosoft (MSFT) 	 5	 $"
            +  Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100) / 100
            + "\t $"
            + (5 * (Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100)) / 100)
            + "\n\nTotal Portfolio Value is on 2022-02-02: $"
            + (5 * (Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())* 100)) / 100)
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 10
  /**
   * This test displays message when wrong input date is given by the user.
   *
   */
  @Test
  public void testJControllerInvalidDate2() {
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "\nValue of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nMicrosoft (MSFT) \t 5\t $" + Math.floor(Double.parseDouble(
                    readStockPriceFromStockDataCsv())*100)/100+"\t $"
            + 5*Math.floor(Double.parseDouble(readStockPriceFromStockDataCsv())*100)/100+"\n" +
            "\n" +
            "Total Portfolio Value is on 2022-02-02: $" + 5*Math.floor(Double.parseDouble(
                    readStockPriceFromStockDataCsv())*100)/100+"\n" +
            "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 11
  /**
   * This test displays message when a future date is given by the user.
   *
   */
  @Test
  public void testKControllerInvalidDate3FutureDate() {
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "\nValue of CONTROLLERTEST3 PORTFOLIO\n"
            + "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n"
            + "\nMicrosoft (MSFT) \t 5\t $"+ Math.floor(Double.parseDouble(
                    readStockPriceFromStockDataCsv())*100)/100 +
            "\t $"+ 5*Math.floor(Double.parseDouble(
                    readStockPriceFromStockDataCsv())*100)/100+"\n" +
            "\n" +
            "Total Portfolio Value is on 2022-02-02: $" + 5*Math.floor(Double.parseDouble(
                    readStockPriceFromStockDataCsv())*100)/100
            + "\n"
            + "\nPress 'b' to go back and 'm' for main menu.\n"
            + "\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 12
  /**
   * This test displays an invalid message when the user inputs
   * wrong option for selecting portfolio.
   *
   */
  @Test
  public void testLControllerEnterWrongOptionForSelectingPortfolio() {
    String userInput = "2" + "\n" + "89" + "\n" + "b" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 13
  /**
   * This test displays an invalid message when the user inputs
   * wrong option for initial choice of inputs.
   *
   */
  @Test
  public void testMControllerInvalidInitialChoice() {
    String userInput = "8" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 14
  /**
   * This test tests set of all invalid inputs
   *
   */
  @Test
  public void testNControllerInvalidInputs() {
    String userInput = "8" + "\n" + "1" + "\n" + "1" + "\n"+ "" + "\n" + "controllerTest4" + "\n" + "6"
            + "\n" + "1" + "\n" + "11" + "\n" + "10" + "\n" + "9900-72-00" + "\n" + "2022-01-01"
            + "\n" +"-4" + "\n" + "4.3" +  "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
            + "Enter the name for this portfolio.\n"
            + "Cannot create a portfolio with empty name. Enter a valid name.\n"
            + "If you want to go back to main menu, press '0'.\n"
            + "\n"
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
            + "Invalid command. Enter the right option number.\n"
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
            + "Not a valid input. Please enter the correct stock.\n"
            + "If you want to go back to main menu, press 'm'.\n"
            + "\nEnter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back"
            + "\n"
            + "\n"
            + "\nSTOCK DETAILS\n"
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
            + "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  // 15
  /**
   * This test displays no portfolio creation if the number of stocks inputted by the user is zero.
   *
   */
  @Test
  public void testOControllerHowManyStocksIsZeroSoPortfolioNotCreated() {
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest4" + "\n" + "1" + "\n" + "1" + "\n"
            + "2022-08-08" + "\n" + "0" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 16
  /**
   * This test does not display the entry inside the portfolio when
   * the number of stocks inputted by the user is zero.
   *
   */
  @Test
  public void testPControllerHowManyStocksIsZeroSoStockNotDisplayedInPortfolio()
          throws IOException {
    deleteFileInDirectory("pf_controllerTest4.csv");
    String userInput = "1" + "\n" + "1"+ "\n" + "controllerTest4" + "\n" + "1" + "\n" + "4" + "\n"
            + "2022-08-09" + "3" + "\n" + "y" + "\n" + "1" + "\n" + "2022-09-08" + "\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Which stock would you like to buy?"
            + "\nEnter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n"
            + "\nNot a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back"+
            "\n"
            + "\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Apple\n"
            + "Symbol: AAPL\n"
            + "Time: "
            + readStockDateFromStockDataCsv()
            + "\n"
            + "Price: $"
            + readStockPriceFromStockDataCsv()
            + "\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 17
  /**
   * This test displays 4 stocks bought by user.
   *
   */
  @Test
  public void testQController4StocksBoughtByTheUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest4.csv");
    String userInput = "1" + "\n" + "1"+ "\n" + "controllerTest4" + "\n" + "1" + "\n"
            + "1" + "\n" + "2022-11-11" + "\n" + "10" + "\n" + "Y" + "\n"
            + "2" + "\n" + "2022-11-11" + "\n" + "10" + "\n" + "y" + "\n"
            + "3" + "\n" + "2022-11-11" + "\n" + "10" + "\n" + "y" + "\n"
            + "4" + "\n" + "2022-11-11" + "\n" + "10" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest4", 1,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest4", 1,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest4", 2,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest4", 2,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest4", 3,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest4", 3,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Apple\n"
            + "Symbol: AAPL\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest4", 4,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest4", 4,
            6, false)
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 18
  /**
   * This test displays 5 stocks bought by user on different dates.
   *
   */
  @Test
  public void testRController5ByTheUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest5.csv");
    String userInput = "1" + "\n" + "1"+ "\n" + "controllerTest5" + "\n" + "1" + "\n"
            + "1" + "\n" + "2022-10-01" + "\n" + "10" + "\n" + "Y" + "\n"
            + "2" + "\n" + "2022-10-02" + "\n" + "10" + "\n" + "y" + "\n"
            + "3" + "\n" + "2022-10-03" + "\n" + "10" + "\n" + "y" + "\n"
            + "4" + "\n" + "2022-10-04" + "\n" + "10" + "\n" + "y" + "\n"
            + "5" + "\n" + "2022-10-05" + "\n" + "10" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Microsoft\n"
            + "Symbol: MSFT\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest5", 1,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest5", 1,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest5", 2,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest5", 2,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Google\n"
            + "Symbol: GOOG\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest5", 3,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest5", 3,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Apple\n"
            + "Symbol: AAPL\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest5", 4,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest5", 4,
            6, false)
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Tesla\n"
            + "Symbol: TSLA\n"
            + "Time: "
            + readStockDataFromPortfolioCsv("controllerTest5", 5,
            2, false)
            + "\n"
            + "Price: $"
            + readStockDataFromPortfolioCsv("controllerTest5", 5,
            6, false)
            + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST5 PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  // 19
  /**
   * This test displays 10 stocks bought by user.
   *
   */
  @Test
  public void testSController10StocksBoughtByUser() throws IOException {
    deleteFileInDirectory("pf_controllerTest6.csv");
    String userInput = "1" + "\n" + "1"+ "\n" + "controllerTest6" + "\n" + "1" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "Y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "y" + "\n"
            + "2" + "\n" + "2022-10-10" + "\n" + "1" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            1, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            1,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            2, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            2,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            3, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            3,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            4, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            4,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            5, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            5,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            6, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            6,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            7, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            7,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            8, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            8,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            9, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            9,
            6, false) + "\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: " + readStockDataFromPortfolioCsv("controllerTest6",
            10, 2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv("controllerTest6",
            10,
            6, false) + "\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 20
  /**
   * This test takes initial input as exit.
   *
   */
  @Test
  public void testTInitialChoiceExit() {
    String userInput = "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 21
  /**
   * This test tests for large input for the number of stocks.
   *
   */
  @Test
  public void testUInvalidHowMany() throws IOException {
    deleteFileInDirectory("pf_controllerTest7.csv");
    String userInput = "1" + "\n" + "1"+ "\n" + "controllerTest7" + "\n" + "2"
            + "1" + "\n" + "controllerTest7" + "\n"
            + "1" + "\n" + "10" + "\n" + "2022-08-07"+ "\n" + "14000000000000000000"
            + "\n" + "10000000" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Invalid command. Enter the right option number.\n"
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
            + "Invalid command. Enter the right option number.\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: Walmart\n"
            + "Symbol: WMT\n"
            + "Time: " + readStockDataFromPortfolioCsv(
            "controllerTest7", 1,
            2, false) + "\n"
            + "Price: $" + readStockDataFromPortfolioCsv(
            "controllerTest7", 1,
            6, false) + "\n"
            + "\n"
            + "How many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"
            + "\n"
            + "Would you like to buy another stock? (Y|N)\n"
            + "\n"
            + "CONTROLLERTEST7 PORTFOLIO CREATED...!!!\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";
    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 22
  /**
   * This test throws a message when portfolio name is an empty string.
   *
   */
  @Test
  public void testVStringEmptyForName() {
    String userInput = "1" + "\n" + "1"+ "\n" + "" + "\n" + "0" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
            + "Enter the name for this portfolio.\n"
            + "Cannot create a portfolio with empty name. Enter a valid name.\n"
            + "If you want to go back to main menu, press '0'.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 23
  /**
   * This test throws error message when user inputs wrong option for displaying portfolio
   * from a list of portfolio.
   *
   */
  @Test
  public void testWViewPortfolioAndEnterWrongOption() {
    String userInput = "2" + "\n" + "-52" + "\n" + "b" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 24
  /**
   * This test throws error message when user inputs wrong option for displaying portfolio
   * from a list of portfolio.
   */
  @Test
  public void testXValueOfPortfolioEnterWrongPortfolioNumber() {
    String userInput = "3" + "\n" + "-9" + "\n" + "b" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  // 25
  /**
   * This test displays invalid input for buying stock.
   *
   */
  @Test
  public void testYBuyStockEnterWrongStockOption() {
    String userInput = "1" + "\n" + "1" + "\n" + "test" + "\n" + "1" + "\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "TEST Portfolio\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 26
  /**
   * This test displays invalid input entry for the number of stocks.
   *
   */
  @Test
  public void testZBuyStockEnterInvalidHowManyStocks() {
    String userInput = "1" + "\n" + "1"+ "\n" + "e" + "\n" + "1" + "\n" + "9" + "\n" + "2022-11-11"+"\n" + "0"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\nSTOCK DETAILS\n"
            + "StockName: UnitedHealth\n"
            + "Symbol: UNH\n"
            +"Time: 2022-11-11\n"
            +"Price: $" + readStockPriceFromStockDataCsv() +"\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 27
  /**
   * This test displays the operations of back and main menu.
   *
   */
  @Test
  public void testZAToggleBetweenMainMenuAndBack() {
    String userInput = "1" + "\n" + "1"+ "\n" + "test" + "\n" + " 2" + "\n" + "2" + "\n"
            + "-1" + "\n" + "b" + "\n" + "3" + "\n" + "1" + "\n" + "-1" + "\n" + "b"
            + "\n" + "-1" + "\n" + "b" + "\n" + "1" + "\n" + "1" + "\n"
            + "test" + "\n" + "1" + "\n" + "-1" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "TEST Portfolio\n"
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
            + "TEST Portfolio\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "Invalid command. Enter the right option number.\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "2. CONTROLLERTEST2\n"
            + "3. CONTROLLERTEST3\n"
            + "4. CONTROLLERTEST4\n"
            + "5. CONTROLLERTEST5\n"
            + "6. CONTROLLERTEST6\n"
            + "7. CONTROLLERTEST7\n"
            + "\n"
            + "Which portfolio would you like to check?\n"
            + "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n"
            + "\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
            + "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "TEST Portfolio\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 28
  /**
   * This test checks when stock purchased on a specifc date
   * and sold the same stock at a later date.
   *
   */
  @Test
  public void testZBBuyAndSell1(){
    deleteFileInDirectory("pf_controllerTest8.csv");
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest8" + "\n" + "1" + "\n"
            + "2" + "\n" + "2022-02-02" + "\n" + "100" + "\n" + "n" + "\n"
            + "5" + "\n" + "8" + "\n" + "2022-07-07" + "\n" + "1" + "\n" + "1" +"\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            +"\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: 2022-02-02\n"
            + "Price: $327.8200\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
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
            + "Select the portfolio to sell stocks from.\n"
            + "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n"
            + "\n"
            + "List of stocks available on date: 2022-07-07\n"
            + "\n"
            + "S.No\tName (Symbol) \n"
            + "\n"
            + "1.\tMeta (META) \n"
            + "\n"
            + "Which stock would you like to sell?\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: 2022-07-07\n"
            + "Price: $169.4500\n"
            + "\n"
            + "You can sell only 100 shares of this stock on 2022-07-07\n"
            + "How many share would you like to sell?\n"
            + "\n"
            + "Shares successfully sold.\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  // 29
  /**
   * This test throws a message when stock purchased on a specifc date
   * and sold the same stock at a previous date than that of the purchase.
   */
  @Test
  public void testZCBuyAndSell2(){
    deleteFileInDirectory("pf_controllerTest9.csv");
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest9" + "\n" + "1" + "\n"
            + "2" + "\n" + "2022-02-02" + "\n" + "100" + "\n" + "n" + "\n"
            + "5" + "\n" + "2" + "\n" + "2022-01-01" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n" +
            "ENTER YOUR CHOICE: \n"
            + "\n"
            + "What type of portfolio would you like to create?\n"
            + "\n"
            + "1. Flexible / Customizable Portfolio\n"
            + "2. Inflexible / Non Customizable Portfolio\n"
            + "\n"
            + "Enter your choice:\n"
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
            + "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n"
            + "\n"
            + "STOCK DETAILS\n"
            + "StockName: Meta\n"
            + "Symbol: META\n"
            + "Time: 2022-02-02\n"
            + "Price: $327.8200\n"
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
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n" +
            "ENTER YOUR CHOICE: \n"
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
            + "9. CONTROLLERTEST9\n"
            + "\n"
            + "Select the portfolio to sell stocks from.\n"
            + "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n"
            + "You don't own any stocks before this date\n"
            + "\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            +"\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //30
  /**
   * This test displays the combinations of stocks purchased while creating portfolio,
   * stocks added and sold.
   *
   */

  @Test
  public void testZDBuyAndSell3(){
    deleteFileInDirectory("controllerTest90.csv");
    String userInput = "1" + "\n" + "1" + "\n" +"controllerTest90" + "\n" + "1" + "\n" + "5" + "\n"
            + "2022-03-03" + "\n" +"100" + "\n" +"y"+ "\n" + "6" + "\n" + "2022-01-01"
            + "\n" + "50" + "\n" + "y" + "\n" + "10" + "\n" + "2022-02-02" + "\n" + "150"
            + "\n" + "n" + "\n" +"5" + "\n" + "10" + "\n" + "2022-01-01" + "\n" + "1" + "\n"
            + "20" + "\n" + "5" + "\n" + "10" + "\n" + "2022-03-03" + "\n" + "3" + "\n" + "30"
            + "\n" + "4" + "\n" +"10" + "\n" +"10" + "\n" +"2022-02-14" + "\n" +"7" + "\n" +"n"
            + "\n" + "5" + "\n" +"10" + "\n" +"2022-02-15"+ "\n" + "2" + "\n" +"6" + "\n" +"e";

    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n"
            + "MENU\n"
            + "\n"
            + "1. Create a portfolio\n"
            + "2. Value portfolio on certain date\n"
            + "3. Value of portfolio on full composition\n"
            + "4. Add a stock to portfolio\n"
            + "5. Sell a stock from portfolio\n"
            + "6. Performance of portfolio\n"
            + "7. Total amount invested on certain date\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
+ "\n"
+ "What type of portfolio would you like to create?\n"
+ "\n"
+ "1. Flexible / Customizable Portfolio\n"
+ "2. Inflexible / Non Customizable Portfolio\n"
+ "\n"
+ "Enter your choice:\n"
+ "Enter the name for this portfolio.\n"
            + "\n"
            + "CREATE PORTFOLIO MENU\n"
            + "\n"
            + "CONTROLLERTEST90 Portfolio\n"
            + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n"
            + "\n"
            + "ENTER YOUR CHOICE: \n"
            + "\n"
            + "\n"
            +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Tesla\n" +
            "Symbol: TSLA\n" +
            "Time: 2022-03-03\n" +
            "Price: $878.7700\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: JPMorgan Chase\n" +
            "Symbol: JPM\n" +
            "Time: 2022-01-01\n" +
            "Price: $158.4500\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-02-02\n" +
            "Price: $141.0000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST90 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-01-01\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tJPMorgan Chase (JPM) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: JPMorgan Chase\n" +
            "Symbol: JPM\n" +
            "Time: 2022-01-01\n" +
            "Price: $158.4500\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2022-01-01\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-03-03\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tTesla (TSLA) \n" +
            "2.\tJPMorgan Chase (JPM) \n" +
            "3.\tWalmart (WMT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-03-03\n" +
            "Price: $137.2900\n" +
            "\n" +
            "You can sell only 150 shares of this stock on 2022-03-03\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Select the portfolio to which you would like to add the stock.\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-02-14\n" +
            "Price: $135.3300\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "Stock successfully added to the portfolio...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-02-15\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tJPMorgan Chase (JPM) \n" +
            "2.\tWalmart (WMT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-02-15\n" +
            "Price: $134.7400\n" +
            "\n" +
            "You can sell only 127 shares of this stock on 2022-02-15\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //31
  /**
   * This test displays value of portfolio (option 2) on various dates
   * for portfolio controllerTest90
   *
   */
  @Test
  public void testZEAddtoExistingPortfolioAndSell1(){
    String userInput = "2" + "\n" + "10"+ "\n" + "2022-01-01"+ "\n" + "m"+ "\n" + "2" + "\n"
            +"10" + "\n" +"2022-02-02"+ "\n" + "m"+ "\n" + "2"+ "\n" + "10" + "\n"
            +"2022-03-03" + "\n" +"m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-01-01\t Total Value\n" +
            "\n" +
            "JPMorgan Chase (JPM) \t 30\t $158.44\t $4753.2\n" +
            "\n" +
            "Total Portfolio Value is on 2022-01-01: $4753.2\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n" +
            "\n" +
            "JPMorgan Chase (JPM) \t 30\t $150.5\t $4515.0\n" +
            "Walmart (WMT) \t 150\t $141.0\t $21150.0\n" +
            "\n" +
            "Total Portfolio Value is on 2022-02-02: $25665.0\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-03-03\t Total Value\n" +
            "\n" +
            "Tesla (TSLA) \t 100\t $878.77\t $87877.0\n" +
            "JPMorgan Chase (JPM) \t 30\t $139.84\t $4195.2\n" +
            "Walmart (WMT) \t 121\t $137.29\t $16612.09\n" +
            "\n" +
            "Total Portfolio Value is on 2022-03-03: $108684.29\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //32
  /**
   * This test displays value of portfolio on full composition (option 3) on various dates
   * for portfolio controllerTest90
   *
   */
  @Test
  public void testZFddtoExistingPortfolioAndSell2(){
    String userInput = "3" + "\n" + "10"+ "\n" + "2022-01-01"+ "\n" + "m"+ "\n" + "3" + "\n"
            +"10" + "\n" +"2022-02-02"+ "\n" + "m"+ "\n" + "3"+ "\n" + "10" + "\n"
            +"2022-03-03" + "\n" +"m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-01-01\t Total Value\n" +
            "\n" +
            "Tesla (TSLA) \t 100\t $1073.44\t $107344.0\n" +
            "JPMorgan Chase (JPM) \t 30\t $158.44\t $4753.2\n" +
            "Walmart (WMT) \t 121\t $143.19\t $17325.98\n" +
            "\n" +
            "Total Portfolio Value is on 2022-01-01: $129423.18\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n" +
            "\n" +
            "Tesla (TSLA) \t 100\t $928.18\t $92818.0\n" +
            "JPMorgan Chase (JPM) \t 30\t $150.5\t $4515.0\n" +
            "Walmart (WMT) \t 121\t $141.0\t $17061.0\n" +
            "\n" +
            "Total Portfolio Value is on 2022-02-02: $114394.0\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-03-03\t Total Value\n" +
            "\n" +
            "Tesla (TSLA) \t 100\t $878.77\t $87877.0\n" +
            "JPMorgan Chase (JPM) \t 30\t $139.84\t $4195.2\n" +
            "Walmart (WMT) \t 121\t $137.29\t $16612.09\n" +
            "\n" +
            "Total Portfolio Value is on 2022-03-03: $108684.29\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //33
  /**
   * This test adds stock to the current portfolio.
   *
   */
  @Test
  public void testZG(){
    String userInput = "4" + "\n" + "10" + "\n" + "6" + "\n" + "2022-11-11" + "\n" + "500" + "\n" +
            "y" + "\n" + "4" + "\n" +"10" + "\n" + "5" + "\n" + "2022-11-12" + "\n" + "40" + "\n" +
            "n" + "\n" +"2" + "\n" + "10" + "\n" +"2022-11-11" + "\n" +"m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Select the portfolio to which you would like to add the stock.\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: JPMorgan Chase\n" +
            "Symbol: JPM\n" +
            "Time: 2022-11-11\n" +
            "Price: $135.1900\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Apple\n" +
            "Symbol: AAPL\n" +
            "Time: 2022-11-12\n" +
            "Price: $145.8200\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST90 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-11-11\t Total Value\n" +
            "\n" +
            "Tesla (TSLA) \t 100\t $186.0\t $18600.0\n" +
            "JPMorgan Chase (JPM) \t 530\t $135.19\t $71650.7\n" +
            "Walmart (WMT) \t 121\t $142.66\t $17261.86\n" +
            "\n" +
            "Total Portfolio Value is on 2022-11-11: $107512.56\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //34
  /**
   * This test sells stocks from current portfolio
   *
   */
  @Test
  public void testZH(){
    deleteFileInDirectory("controllerTest90");
    String userInput = "1" + "\n" + "1" + "\n" +"controllerTest90" + "\n" + "1" + "\n" + "5" + "\n"
            + "2022-03-03" + "\n" +"100" + "\n" +"y"+ "\n" + "6" + "\n" + "2022-01-01"
            + "\n" + "50" + "\n" + "y" + "\n" + "10" + "\n" + "2022-02-02" + "\n" + "150"
            + "\n" + "n" + "\n" +"5" + "\n" + "10" + "\n" + "2022-01-01" + "\n" + "1" + "\n" +
            "20" + "\n" + "5" + "\n" + "10" + "\n" + "2022-03-03" + "\n" + "3" + "\n" + "30"
            + "\n" + "4" + "\n" +"10" + "\n" +"10" + "\n" +"2022-02-14" + "\n" +"7" + "\n" +"n"
            + "\n" + "5" + "\n" +"10" + "\n" +"2022-02-15"+ "\n" + "2" + "\n" +"6" + "\n"+"5" + "\n"
            + "10" + "\n" + "2022-09-09" + "\n" + "1" + "\n" +"100"+ "\n" +
    "2"+ "\n" + "10" + "\n" + "2022-09-09" + "\n" + "m" + "\n" +"2" + "\n" +"10" + "\n" +
            "2022-09-08" + "\n" + "m" + "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput =
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "This portfolio already exists.\n" +
            "If you want to override this portfolio, then press 'y'. If you want to enter another name, press 'n'. If you want to go main screen, press 'b'.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "If you want to override this portfolio, then press 'y'. If you want to enter another name, press 'n'. If you want to go main screen, press 'b'.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "If you want to override this portfolio, then press 'y'. If you want to enter another name, press 'n'. If you want to go main screen, press 'b'.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "If you want to override this portfolio, then press 'y'. If you want to enter another name, press 'n'. If you want to go main screen, press 'b'.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "If you want to override this portfolio, then press 'y'. If you want to enter another name, press 'n'. If you want to go main screen, press 'b'.\n" +
            "\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST90 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Not a valid input. Please enter the correct stock.\n" +
            "If you want to go back to main menu, press 'm'.\n" +
            "\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Tesla\n" +
            "Symbol: TSLA\n" +
            "Time: 2022-03-03\n" +
            "Price: $878.7700\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "\n" +
            "CONTROLLERTEST90 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "You don't own any stocks before this date\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "The value of portfolio on 2022-09-09 is $0.00\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST90 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-09-08\t Total Value\n" +
            "\n" +
            "Tesla (TSLA) \t 3\t $281.3\t $843.9\n" +
            "\n" +
            "Total Portfolio Value is on 2022-09-08: $843.9\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  /**
   * This test displays value portfolio on certain date for past date.
   *
   */
  @Test
  public void testZK(){
    String userInput = "2" + "\n" + "10" + "\n" + "2014-01-01" + "\n" + "m"+ "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "The value of portfolio on 2014-01-01 is $0.00\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  //39
  /**
   * This test creates portfolio with combination of purchases and sells in different dates.
   *
   */
  @Test
  public void testZM(){
    String userInput = "1" + "\n" + "1" + "\n" +"controllerTest91" + "\n" + "1" + "\n" + "8" + "\n"
            + "2014-03-03" + "\n" +"100" + "\n" +"y"+ "\n" + "10" + "\n" + "2018-01-01"
            + "\n" + "50" + "\n" + "y" + "\n" + "1" + "\n" + "2000-02-02" + "\n" + "150"
            + "\n" + "n" + "\n" +"5" + "\n" + "11" + "\n" + "2019-01-01" + "\n" + "1" + "\n" +
            "20" + "\n" + "5" + "\n" + "11" + "\n" + "2022-03-03" + "\n" + "3" + "\n" + "30"
            + "\n" + "4" + "\n" +"10" + "\n" +"10" + "\n" +"2022-02-14" + "\n" +"7" + "\n" +"n"
            + "\n" + "5" + "\n" +"11" + "\n" +"2022-02-15"+ "\n" + "2" + "\n" +"6" + "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST91 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Amazon\n" +
            "Symbol: AMZN\n" +
            "Time: 2014-03-03\n" +
            "Price: $358.7350\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2018-01-01\n" +
            "Price: $99.4000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2000-02-02\n" +
            "Price: $102.4400\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST91 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2019-01-01\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tAmazon (AMZN) \n" +
            "2.\tWalmart (WMT) \n" +
            "3.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Amazon\n" +
            "Symbol: AMZN\n" +
            "Time: 2019-01-01\n" +
            "Price: $1510.8000\n" +
            "\n" +
            "You can sell only 100 shares of this stock on 2019-01-01\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-03-03\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tAmazon (AMZN) \n" +
            "2.\tWalmart (WMT) \n" +
            "3.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-03-03\n" +
            "Price: $302.8900\n" +
            "\n" +
            "You can sell only 150 shares of this stock on 2022-03-03\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "\n" +
            "Select the portfolio to which you would like to add the stock.\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-02-14\n" +
            "Price: $135.3300\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "Stock successfully added to the portfolio...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-02-15\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tAmazon (AMZN) \n" +
            "2.\tWalmart (WMT) \n" +
            "3.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-02-15\n" +
            "Price: $134.7400\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2022-02-15\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //40
  /**
   * This test displays performance by year
   *
   */
  @Test
  public void testZN(){
    String userInput = "6" + "\n" + "11" + "\n"+"1" + "\n"+"2010" + "\n"+"10" + "\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start year in format (YYYY) from year 2000 to 2017:\n" +
            "Enter the number of years (5 to 12): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST91 from 2010 to 2019\n" +
            "\n" +
            "2010: *****\n" +
            "2011: ****\n" +
            "2012: ****\n" +
            "2013: ****\n" +
            "2014: ********************************************\n" +
            "2015: *******************************************\n" +
            "2016: *******************************************\n" +
            "2017: ********************************************\n" +
            "2018: **************************************************\n" +
            "2019: ******************************************\n" +
            "\n" +
            "Scale: * = $786\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //41
  /**
   * This test creates portfolio.
   *
   */
  @Test
  public void testZO(){
    String userInput = "1" + "\n" + "1" + "\n" +"controllerTest92" + "\n" + "1" + "\n" + "8" + "\n"
            + "2014-03-03" + "\n" +"100" + "\n" +"y"+ "\n" + "10" + "\n" + "2014-01-01"
            + "\n" + "50" + "\n" + "y" + "\n" + "1" + "\n" + "2014-02-02" + "\n" + "150"
            + "\n" + "n" + "\n" +"5" + "\n" + "12" + "\n" + "2014-01-01" + "\n" + "1" + "\n" +
            "20" + "\n" + "5" + "\n" + "12" + "\n" + "2014-03-03" + "\n" + "3" + "\n" + "30"
            + "\n" + "4" + "\n" +"10" + "\n" +"10" + "\n" +"2014-02-14" + "\n" +"7" + "\n" +"n"
            + "\n" + "5" + "\n" +"12" + "\n" +"2014-02-15"+ "\n" + "2" + "\n" +"6" + "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST92 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Amazon\n" +
            "Symbol: AMZN\n" +
            "Time: 2014-03-03\n" +
            "Price: $358.7350\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2014-01-01\n" +
            "Price: $78.6600\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2014-02-02\n" +
            "Price: $36.9500\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST92 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2014-01-01\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tWalmart (WMT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2014-01-01\n" +
            "Price: $78.6600\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2014-01-01\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2014-03-03\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tAmazon (AMZN) \n" +
            "2.\tWalmart (WMT) \n" +
            "3.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2014-03-03\n" +
            "Price: $37.9200\n" +
            "\n" +
            "You can sell only 150 shares of this stock on 2014-03-03\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "\n" +
            "Select the portfolio to which you would like to add the stock.\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2014-02-14\n" +
            "Price: $75.4000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "Stock successfully added to the portfolio...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2014-02-15\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tWalmart (WMT) \n" +
            "2.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2014-02-15\n" +
            "Price: $37.3900\n" +
            "\n" +
            "You can sell only 120 shares of this stock on 2014-02-15\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //42
  /**
   * This test displays by month
   *
   */
  @Test
  public void testZP(){
    String userInput = "6" + "\n" + "12" + "\n"+"2" + "\n"+"2014-01" + "\n"+"5" + "\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start month in format (YYYY-MM) from year 2000 to JUNE 2022:\n" +
            "Enter the number of months (5 to 30): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST92 from JANUARY 2014 to MAY 2014\n" +
            "\n" +
            "JAN 2014: **\n" +
            "FEB 2014: *********\n" +
            "MAR 2014: **************************************************\n" +
            "APR 2014: ********************************************\n" +
            "MAY 2014: ********************************************\n" +
            "\n" +
            "Scale: * = $815\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //43
  /**
   * This test displays by day
   *
   */
  @Test
  public void testZQ(){
    String userInput = "6" + "\n" + "12" + "\n"+"3" + "\n"+"2014-01-01" + "\n"+"5" + "\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    LocalDate today = LocalDate.now();
    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start date in format (YYYY-MM-DD) from year 2000 to "+ today.minusDays(5).getYear() + "-"
            + today.minusDays(5).getMonth() + "-"
            + today.minusDays(5).getDayOfMonth()+":\n" +
            "Enter the number of days (5 to 30): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST92 from 1 JANUARY 2014 to 5 JANUARY 2014\n" +
            "\n" +
            "JAN 01 2014: **************************************************\n" +
            "JAN 02 2014: **************************************************\n" +
            "JAN 03 2014: **************************************************\n" +
            "JAN 04 2014: **************************************************\n" +
            "JAN 05 2014: **************************************************\n" +
            "\n" +
            "Scale: * = $47\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //44
  /**
   * This test creates portfolio for day
   *
   */
  @Test
  public void testZR(){
    deleteFileInDirectory("controllerTest93.csv");
    String userInput = "1" + "\n" + "1" + "\n" +"controllerTest93" + "\n" + "1" + "\n" + "8" + "\n"
            + "2015-03-03" + "\n" +"100" + "\n" +"y"+ "\n" + "10" + "\n" + "2015-03-01"
            + "\n" + "50" + "\n" + "y" + "\n" + "1" + "\n" + "2015-03-02" + "\n" + "150"
            + "\n" + "n" + "\n" +"5" + "\n" + "13" + "\n" + "2015-03-01" + "\n" + "1" + "\n" +
            "20" + "\n" + "5" + "\n" + "13" + "\n" + "2015-03-03" + "\n" + "3" + "\n" + "30"
            + "\n" + "4" + "\n" +"10" + "\n" +"10" + "\n" +"2015-03-14" + "\n" +"7" + "\n" +"n"
            + "\n" + "5" + "\n" +"13" + "\n" +"2015-03-15"+ "\n" + "2" + "\n" +"6" + "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST93 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Amazon\n" +
            "Symbol: AMZN\n" +
            "Time: 2015-03-03\n" +
            "Price: $383.9500\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2015-03-01\n" +
            "Price: $83.7200\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2015-03-02\n" +
            "Price: $43.6700\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST93 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2015-03-01\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tWalmart (WMT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2015-03-01\n" +
            "Price: $83.7200\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2015-03-01\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2015-03-03\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tAmazon (AMZN) \n" +
            "2.\tWalmart (WMT) \n" +
            "3.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2015-03-03\n" +
            "Price: $43.5600\n" +
            "\n" +
            "You can sell only 150 shares of this stock on 2015-03-03\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Select the portfolio to which you would like to add the stock.\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2015-03-14\n" +
            "Price: $81.9500\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "Stock successfully added to the portfolio...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2015-03-15\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tAmazon (AMZN) \n" +
            "2.\tWalmart (WMT) \n" +
            "3.\tMicrosoft (MSFT) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2015-03-15\n" +
            "Price: $81.9500\n" +
            "\n" +
            "You can sell only 30 shares of this stock on 2015-03-15\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //45
  /**
   * This test displays by day
   *
   */
  @Test
  public void testZS(){
    String userInput = "6" + "\n" + "12" + "\n"+"3" + "\n"+"2015-03-01" + "\n"+"30" + "\n"+ "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    LocalDate today = LocalDate.now();
    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start date in format (YYYY-MM-DD) from year 2000 to "
            + today.minusDays(5).getYear() + "-"
            + today.minusDays(5).getMonth() + "-"
            + today.minusDays(5).getDayOfMonth()+":\n" +
            "Enter the number of days (5 to 30): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST92 from 1 MARCH 2015 to 30 MARCH 2015\n" +
            "\n" +
            "MAR 01 2015: *************************************************\n" +
            "MAR 02 2015: ************************************************\n" +
            "MAR 03 2015: *************************************************\n" +
            "MAR 04 2015: *************************************************\n" +
            "MAR 05 2015: *************************************************\n" +
            "MAR 06 2015: *************************************************\n" +
            "MAR 07 2015: **************************************************\n" +
            "MAR 08 2015: *************************************************\n" +
            "MAR 09 2015: ************************************************\n" +
            "MAR 10 2015: *************************************************\n" +
            "MAR 11 2015: ************************************************\n" +
            "MAR 12 2015: *****************************************\n" +
            "MAR 13 2015: *****************************************\n" +
            "MAR 14 2015: *********************************************\n" +
            "MAR 15 2015: *********************************************\n" +
            "MAR 16 2015: ***********************************************\n" +
            "MAR 17 2015: ***********************************************\n" +
            "MAR 18 2015: ***********************************************\n" +
            "MAR 19 2015: ************************************************\n" +
            "MAR 20 2015: ************************************************\n" +
            "MAR 21 2015: ************************************************\n" +
            "MAR 22 2015: ************************************************\n" +
            "MAR 23 2015: *************************************************\n" +
            "MAR 24 2015: ************************************************\n" +
            "MAR 25 2015: *************************************************\n" +
            "MAR 26 2015: *************************************************\n" +
            "MAR 27 2015: *************************************************\n" +
            "MAR 28 2015: *************************************************\n" +
            "MAR 29 2015: **************************************************\n" +
            "MAR 30 2015: *************************************************\n" +
            "\n" +
            "Scale: * = $919\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //46
  /**
   * This test displays performance invalid year,or month or day or invalid range.
   *
   */
  @Test
  public void testZT(){
    String userInput = "6" + "\n" + "10" + "\n" + "1" + "\n" + "2018" +"\n" + "b" + "\n" +
            "10" + "\n" + "2" +"\n" + "2022-07" + "\n" + "b" + "\n" + "10" +"\n" + "2022-11-10"
            +"\n" + "b"+"\n" + "m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start year in format (YYYY) from year 2000 to 2017:\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start month in format (YYYY-MM) from year 2000 to JUNE 2022:\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'm' to go back to main menu.\n" +
            "\n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'm' to go back to main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //47
  /**
   * This test displays performance with # when no value
   */
  @Test
  public void testZU(){
    String userInput = "6" + "\n" + "2"+ "\n" + "1" + "\n" + "2000" + "\n" + "5" + "\n"
                     + "6" + "\n" + "2" + "\n" + "2" + "\n" + "2000-02" + "\n" + "30" + "\n"
                     + "6" + "\n" +"2" + "\n" + "2" + "\n" + "2000-02"+ "\n" +  "30" + "\n" + "e" ;
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start year in format (YYYY) from year 2000 to 2017:\n" +
            "Enter the number of years (5 to 22): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST2 from 2000 to 2004\n" +
            "\n" +
            "2000: #\n" +
            "2001: #\n" +
            "2002: #\n" +
            "2003: #\n" +
            "2004: #\n" +
            "\n" +
            "Scale: * = $0\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start month in format (YYYY-MM) from year 2000 to JUNE 2022:\n" +
            "Enter the number of months (5 to 30): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST2 from FEBRUARY 2000 to JULY 2002\n" +
            "\n" +
            "FEB 2000: #\n" +
            "MAR 2000: #\n" +
            "APR 2000: #\n" +
            "MAY 2000: #\n" +
            "JUN 2000: #\n" +
            "JUL 2000: #\n" +
            "AUG 2000: #\n" +
            "SEP 2000: #\n" +
            "OCT 2000: #\n" +
            "NOV 2000: #\n" +
            "DEC 2000: #\n" +
            "JAN 2001: #\n" +
            "FEB 2001: #\n" +
            "MAR 2001: #\n" +
            "APR 2001: #\n" +
            "MAY 2001: #\n" +
            "JUN 2001: #\n" +
            "JUL 2001: #\n" +
            "AUG 2001: #\n" +
            "SEP 2001: #\n" +
            "OCT 2001: #\n" +
            "NOV 2001: #\n" +
            "DEC 2001: #\n" +
            "JAN 2002: #\n" +
            "FEB 2002: #\n" +
            "MAR 2002: #\n" +
            "APR 2002: #\n" +
            "MAY 2002: #\n" +
            "JUN 2002: #\n" +
            "JUL 2002: #\n" +
            "\n" +
            "Scale: * = $0\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start month in format (YYYY-MM) from year 2000 to JUNE 2022:\n" +
            "Enter the number of months (5 to 30): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST2 from FEBRUARY 2000 to JULY 2002\n" +
            "\n" +
            "FEB 2000: #\n" +
            "MAR 2000: #\n" +
            "APR 2000: #\n" +
            "MAY 2000: #\n" +
            "JUN 2000: #\n" +
            "JUL 2000: #\n" +
            "AUG 2000: #\n" +
            "SEP 2000: #\n" +
            "OCT 2000: #\n" +
            "NOV 2000: #\n" +
            "DEC 2000: #\n" +
            "JAN 2001: #\n" +
            "FEB 2001: #\n" +
            "MAR 2001: #\n" +
            "APR 2001: #\n" +
            "MAY 2001: #\n" +
            "JUN 2001: #\n" +
            "JUL 2001: #\n" +
            "AUG 2001: #\n" +
            "SEP 2001: #\n" +
            "OCT 2001: #\n" +
            "NOV 2001: #\n" +
            "DEC 2001: #\n" +
            "JAN 2002: #\n" +
            "FEB 2002: #\n" +
            "MAR 2002: #\n" +
            "APR 2002: #\n" +
            "MAY 2002: #\n" +
            "JUN 2002: #\n" +
            "JUL 2002: #\n" +
            "\n" +
            "Scale: * = $0\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //48
  /**
   * This test displays performance for a portfolio by month.
   *
   */
  @Test
  public void testZV(){
    String userInput = "6" + "\n" + "11"+ "\n" + "2"+ "\n" + "2022-01"+ "\n" + "10" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start month in format (YYYY-MM) from year 2000 to JUNE 2022:\n" +
            "Enter the number of months (5 to 10): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST91 from JANUARY 2022 to OCTOBER 2022\n" +
            "\n" +
            "JAN 2022: ************************************************\n" +
            "FEB 2022: **************************************************\n" +
            "MAR 2022: ********************************************\n" +
            "APR 2022: ******************************************\n" +
            "MAY 2022: *********************************************\n" +
            "JUN 2022: **********************************************\n" +
            "JUL 2022: *********************************************\n" +
            "AUG 2022: *********************************************\n" +
            "SEP 2022: ************************************************\n" +
            "OCT 2022: *********************************************\n" +
            "\n" +
            "Scale: * = $5879\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //49
  /**
   * This test displays performance by year.
   *
   */
  @Test
  public void testZW(){
    String userInput = "6" + "\n" + "11"+ "\n" + "1"+ "\n" + "2010"+ "\n" + "7" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start year in format (YYYY) from year 2000 to 2017:\n" +
            "Enter the number of years (5 to 12): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST91 from 2010 to 2016\n" +
            "\n" +
            "2010: *****\n" +
            "2011: *****\n" +
            "2012: *****\n" +
            "2013: *****\n" +
            "2014: **************************************************\n" +
            "2015: *************************************************\n" +
            "2016: *************************************************\n" +
            "\n" +
            "Scale: * = $700\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //50
  /**
   * This test displays performance by day.
   *
   */
  @Test
  public void testZX(){
    String userInput = "6" + "\n" + "11"+ "\n" + "3"+ "\n" + "2010-01-01"+ "\n" + "30" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    LocalDate today = LocalDate.now();
    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start date in format (YYYY-MM-DD) from year 2000 to "
            + today.minusDays(5).getYear() + "-"
            + today.minusDays(5).getMonth() + "-"
            + today.minusDays(5).getDayOfMonth()+":\n" +
            "Enter the number of days (5 to 30): \n" +
            "\n" +
            "Performance of portfolio CONTROLLERTEST91 from 1 JANUARY 2010 to 30 JANUARY 2010\n" +
            "\n" +
            "JAN 01 2010: ***********************************************\n" +
            "JAN 02 2010: ***********************************************\n" +
            "JAN 03 2010: ************************************************\n" +
            "JAN 04 2010: *************************************************\n" +
            "JAN 05 2010: *************************************************\n" +
            "JAN 06 2010: *************************************************\n" +
            "JAN 07 2010: ************************************************\n" +
            "JAN 08 2010: *************************************************\n" +
            "JAN 09 2010: *************************************************\n" +
            "JAN 10 2010: ************************************************\n" +
            "JAN 11 2010: *************************************************\n" +
            "JAN 12 2010: ************************************************\n" +
            "JAN 13 2010: ************************************************\n" +
            "JAN 14 2010: ************************************************\n" +
            "JAN 15 2010: ***********************************************\n" +
            "JAN 16 2010: ***********************************************\n" +
            "JAN 17 2010: ***********************************************\n" +
            "JAN 18 2010: ***********************************************\n" +
            "JAN 19 2010: ***********************************************\n" +
            "JAN 20 2010: ***********************************************\n" +
            "JAN 21 2010: ***********************************************\n" +
            "JAN 22 2010: ***********************************************\n" +
            "JAN 23 2010: ***********************************************\n" +
            "JAN 24 2010: ***********************************************\n" +
            "JAN 25 2010: ***********************************************\n" +
            "JAN 26 2010: ************************************************\n" +
            "JAN 27 2010: *************************************************\n" +
            "JAN 28 2010: **************************************************\n" +
            "JAN 29 2010: *************************************************\n" +
            "JAN 30 2010: ************************************************\n" +
            "\n" +
            "Scale: * = $94\n" +
            "# - either no stocks or 0 value in portfolio.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //51
  /**
   * This test displays the operations of back and main menu.
   *
   */
  @Test
  public void testZY(){
    String userInput = "6" + "\n" + "11"+ "\n" + "3"+ "\n" + "2010-01-01"+ "\n" + "35" + "\n" +"m"+ "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    LocalDate today = LocalDate.now();

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the choice of timestamps\n" +
            "\n" +
            "1. View by year\n" +
            "2. View by month\n" +
            "3. View by date\n" +
            "Enter the start date in format (YYYY-MM-DD) from year 2000 to "
            + today.minusDays(5).getYear() + "-"
            + today.minusDays(5).getMonth() + "-"
            + today.minusDays(5).getDayOfMonth()+":\n" +
            "Enter the number of days (5 to 30): \n" +
            "Not a valid input. Please enter the correct option.\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //52
  /**
   * This test displays the operations of back and main menu.
   *
   */
  @Test
  public void testZZ(){
    String userInput = "1" + "\n" + "1" + "\n" + "ram" + "\n" + "1" + "\n"
            + "0" + "\n" + "90" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "RAM Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Not a valid input. Please enter the correct stock.\n" +
            "If you want to go back to main menu, press 'm'.\n" +
            "\n" +
            "Not a valid input. Please enter the correct stock.\n" +
            "If you want to go back to main menu, press 'm'.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //53
  /**
   * This test displays the total investment on various dates.
   *
   */
  @Test
  public void testZZA(){
    String userInput = "7" + "\n" +  "11" + "\n" + "2022-01-01" + "\n" + "b" + "\n" +
            "11" + "\n" + "2014-03-03" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "COST BASIS OF CONTROLLERTEST91 PORTFOLIO\n" +
            "\n" +
            "Total Money invested in stocks: $56209.0\n" +
            "Commission cost per transaction is: $1.27\n" +
            "Total number of transactions till date is: 4\n" +
            "Total commission charges: 5.08\n" +
            "Total Money spent: $56203.92\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "COST BASIS OF CONTROLLERTEST91 PORTFOLIO\n" +
            "\n" +
            "Total Money invested in stocks: $51239.0\n" +
            "Commission cost per transaction is: $1.27\n" +
            "Total number of transactions till date is: 2\n" +
            "Total commission charges: 2.54\n" +
            "Total Money spent: $51236.46\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //54
  /**
   * This test displays value portfolio on full composition on various dates.(inflexible)
   *
   */
  @Test
  public void testZZB(){

    deleteFileInDirectory("controllerTest94.csv");
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest94" + "\n" +"1"+ "\n" + "1"+ "\n"
            + "2022-11-12"+ "\n" + "50"+ "\n" + "y" + "\n" +"2"
            + "\n" +"2022-11-13"+ "\n" + "100"+ "\n" + "n"+ "\n" +"5"+ "\n" + "14"+ "\n"
            + "2022-11-14" + "\n" +
            "1"+ "\n" + "15" + "\n" + "3"+ "\n" + "14" + "\n" +"2022-11-13"+ "\n"
            + "b"+ "\n" + "14" + "\n" +"2022-11-14"+ "\n"+ "m" + "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST94 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-12\n" +
            "Price: $242.9900\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Meta\n" +
            "Symbol: META\n" +
            "Time: 2022-11-13\n" +
            "Price: $109.2300\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST94 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-11-14\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tMicrosoft (MSFT) \n" +
            "2.\tMeta (META) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-14\n" +
            "Price: $241.9850\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2022-11-14\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST94 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-11-13\t Total Value\n" +
            "\n" +
            "Microsoft (MSFT) \t 35\t $242.99\t $8504.65\n" +
            "Meta (META) \t 100\t $109.23\t $10923.0\n" +
            "\n" +
            "Total Portfolio Value is on 2022-11-13: $19427.65\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST94 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-11-14\t Total Value\n" +
            "\n" +
            "Microsoft (MSFT) \t 35\t $241.98\t $8469.29\n" +
            "Meta (META) \t 100\t $110.99\t $11099.0\n" +
            "\n" +
            "Total Portfolio Value is on 2022-11-14: $19568.29\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  //55
  /**
   * This test displays the value portfolio (option 2 on various dates)
   *
   */
  @Test
  public void testZZC(){

    deleteFileInDirectory("controllerTest95.csv");
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest95" + "\n" +"1"+ "\n" + "1"+ "\n"
            + "2022-11-12"+ "\n" + "50"+ "\n" + "y" + "\n" +"2"
            + "\n" +"2022-11-13"+ "\n" + "100"+ "\n" + "n"+ "\n" +"5"+ "\n" + "15"+ "\n"
            + "2022-11-14" + "\n" +
            "1"+ "\n" + "15" + "\n" + "2" + "\n" + "15" + "\n" + "2022-02-14"+ "\n" + "b"
            + "\n" +"15" + "\n" +"2022-02-15"+ "\n" + "b"+ "\n" + "15" + "\n" +
                "2022-11-11"+ "\n" + "m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST95 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-12\n" +
            "Price: $242.9900\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Meta\n" +
            "Symbol: META\n" +
            "Time: 2022-11-13\n" +
            "Price: $109.2300\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST95 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-11-14\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tMicrosoft (MSFT) \n" +
            "2.\tMeta (META) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-14\n" +
            "Price: $241.9850\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2022-11-14\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "The value of portfolio on 2022-02-14 is $0.00\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "The value of portfolio on 2022-02-15 is $0.00\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "The value of portfolio on 2022-11-11 is $0.00\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  /**
   * This test displays Value of portfolio on full composition on certain date for past date.
   *
   */
  @Test
  public void testZZD(){
    deleteFileInDirectory("controllerTest96.csv");
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest96" + "\n" +"1"+ "\n" + "1"+ "\n"
            + "2022-11-12"+ "\n" + "50"+ "\n" + "y" + "\n" +"2"
            + "\n" +"2022-11-13"+ "\n" + "100"+ "\n" + "n"+ "\n" +"5"+ "\n" + "16"+ "\n"
            + "2022-11-14" + "\n" +
            "1"+ "\n" + "16" + "\n" +"3" + "\n" + "16" + "\n" + "2010-01-31" + "\n"
            + "m"+ "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST96 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-12\n" +
            "Price: $242.9900\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Meta\n" +
            "Symbol: META\n" +
            "Time: 2022-11-13\n" +
            "Price: $109.2300\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST96 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-11-14\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tMicrosoft (MSFT) \n" +
            "2.\tMeta (META) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-14\n" +
            "Price: $241.9850\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2022-11-14\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "Value of CONTROLLERTEST96 PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2010-01-31\t Total Value\n" +
            "\n" +
            "Microsoft (MSFT) \t 34\t $29.9\t $1016.59\n" +
            "Meta (META) \t 100\t $42.05\t $4205.0\n" +
            "\n" +
            "Total Portfolio Value is on 2010-01-31: $5221.59\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }


  /**
   * This test displays no investments for previous dates.
   *
   */
  @Test
  public void testZZE(){
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest97" + "\n" +"1"+ "\n" + "1"+ "\n"
            + "2022-11-12"+ "\n" + "50"+ "\n" + "y" + "\n" +"2"
            + "\n" +"2022-11-13"+ "\n" + "100"+ "\n" + "n"+ "\n" +"5"+ "\n" + "17"+ "\n"
            + "2022-11-14" + "\n" +
            "1"+ "\n" + "17" + "\n" + "\n"+"7" + "\n" + "17" + "\n" + "2022-01-01" + "\n" + "b"
            + "\n" + "17" + "\n" + "2022-02-02" + "\n" + "b" + "\n" + "17" + "\n" + "2022-03-03"
            + "\n" + "b" + "\n" + "17" + "\n" + "2022-11-11" + "\n" + "m" + "\n" +"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST97 Portfolio\n" +
            "\n" +
            "1. Buy a stock\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-12\n" +
            "Price: $242.9900\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "\n" +
            "1. Microsoft (MSFT)\n" +
            "2. Meta (META)\n" +
            "3. Google (GOOG)\n" +
            "4. Apple (AAPL)\n" +
            "5. Tesla (TSLA)\n" +
            "6. JPMorgan Chase (JPM)\n" +
            "7. Johnson (JNJ)\n" +
            "8. Amazon (AMZN)\n" +
            "9. UnitedHealth (UNH)\n" +
            "10. Walmart (WMT)\n" +
            "\n" +
            "Which stock would you like to buy?\n" +
            "Enter the date on which you would like to purchase the stock (YYYY-MM-DD)\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Meta\n" +
            "Symbol: META\n" +
            "Time: 2022-11-13\n" +
            "Price: $109.2300\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST97 PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "17. CONTROLLERTEST97\n" +
            "\n" +
            "Select the portfolio to sell stocks from.\n" +
            "Enter the date on which you would like to sell the stock (YYYY-MM-DD)\n" +
            "\n" +
            "List of stocks available on date: 2022-11-14\n" +
            "\n" +
            "S.No\tName (Symbol) \n" +
            "\n" +
            "1.\tMicrosoft (MSFT) \n" +
            "2.\tMeta (META) \n" +
            "\n" +
            "Which stock would you like to sell?\n" +
            "\n" +
            "STOCK DETAILS\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-11-14\n" +
            "Price: $241.9850\n" +
            "\n" +
            "You can sell only 50 shares of this stock on 2022-11-14\n" +
            "How many share would you like to sell?\n" +
            "\n" +
            "Shares successfully sold.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "17. CONTROLLERTEST97\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "There are no investments until 2022-01-01\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "17. CONTROLLERTEST97\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "There are no investments until 2022-02-02\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "17. CONTROLLERTEST97\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "There are no investments until 2022-03-03\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "11. CONTROLLERTEST91\n" +
            "12. CONTROLLERTEST92\n" +
            "13. CONTROLLERTEST93\n" +
            "14. CONTROLLERTEST94\n" +
            "15. CONTROLLERTEST95\n" +
            "16. CONTROLLERTEST96\n" +
            "17. CONTROLLERTEST97\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "There are no investments until 2022-11-11\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  /**
   * This test displays total amount invested on certain date.
   *
   */
  @Test
  public void testZZF(){
    String userInput = "1" + "\n" + "1" + "\n" + "controllerTest98" + "\n" +"1"+ "\n" + "1"+ "\n"
            + "2022-11-12"+ "\n" + "50"+ "\n" + "y" + "\n" +"2"
            + "\n" +"2022-11-13"+ "\n" + "100"+ "\n" + "n"+ "\n" +"5"+ "\n" + "18"+ "\n"
            + "2022-11-14" + "\n" +
            "1"+ "\n" + "18" + "\n" + "\n"+ "\n"+
            "7" + "\n" + "9" + "\n" + "2022-11-12" + "\n" + "m" +"\n"+"e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput ="\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1\n" +
            "2. CONTROLLERTEST2\n" +
            "3. CONTROLLERTEST3\n" +
            "4. CONTROLLERTEST4\n" +
            "5. CONTROLLERTEST5\n" +
            "6. CONTROLLERTEST6\n" +
            "7. CONTROLLERTEST7\n" +
            "8. CONTROLLERTEST8\n" +
            "9. CONTROLLERTEST9\n" +
            "10. CONTROLLERTEST90\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "\n" +
            "COST BASIS OF CONTROLLERTEST9 PORTFOLIO\n" +
            "\n" +
            "Total Money invested in stocks: $7922.0\n" +
            "Commission cost per transaction is: $1.27\n" +
            "Total number of transactions till date is: 2\n" +
            "Total commission charges: 2.54\n" +
            "Total Money spent: $7919.46\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. Value portfolio on certain date\n" +
            "3. Value of portfolio on full composition\n" +
            "4. Add a stock to portfolio\n" +
            "5. Sell a stock from portfolio\n" +
            "6. Performance of portfolio\n" +
            "7. Total amount invested on certain date\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

}
