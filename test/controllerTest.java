import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

import model.ModelControllerInteract;
import model.StockCompositionData;
import model.TypeofAction;

import static org.junit.Assert.assertEquals;


public class controllerTest {


  class MockModel implements ModelControllerInteract{
    private StringBuilder log;
    MockModel(StringBuilder log){
      this.log=log;
    }
    @Override
    public void modelControllerInteract(TypeofAction type, String[] args, int length) {
      log.append("modelControllerInteract called with inputs: "+a+" "+b+"\n");
    }
  }
  private String readStockDateFromCsv(String portfolioName, int lineNumber) throws IOException {
    String filename = "userdata/user1/" + "pf_"+ portfolioName + ".csv";
    String[] cols= null;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    String dateLastKnown = null;
    int l = 3+lineNumber;

    while((line=br.readLine())!=null && l>=0) {
      cols = line.split(",");
      if(l==1) {
        dateLastKnown = cols[2];
      }
      l--;
    }
    return dateLastKnown;
  }

  private String readStockPriceFromCsv(String portfolioName, int lineNumber) throws IOException {
    String filename = "userdata/user1/" + "pf_"+ portfolioName + ".csv";
    String[] cols= null;
    FileReader fr = new FileReader(filename);
    BufferedReader br = new BufferedReader(fr);
    String line;
    String price = null;
    int l = 3+lineNumber;

    while((line=br.readLine())!=null && l>=0) {
      cols = line.split(",");
      if(l==1) {
        price = cols[6];
      }
      l--;
    }
    return price;
  }
  @Test
  public void testControllerCreatePortfolio() throws IOException {
    String userInput = "1" + "\n" + "controllerTest1_health" + "\n" + "1" + "\n" + "1" + "\n" + "1"
            + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST1_HEALTH Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: " + readStockDateFromCsv("controllerTest1_health",1) + "\n" +
            "Price: $" + readStockPriceFromCsv("controllerTest1_health",1)+ "\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST1_HEALTH PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerCreatePortfolioAndViewPortfolio() throws IOException {
    String userInput = "1" + "\n" + "controllerTest2_bharati" + "\n" + "1" + "\n" + "10" + "\n" + "11"
            + "\n" + "y" + "\n" + "5" + "\n" + "3" + "\n" + "y" + "\n" + "7" + "\n" + "100" + "\n" + "n"
            + "\n" + "2" + "\n" + "2" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST2_BHARATI Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: " + readStockDateFromCsv("controllerTest2_bharati",1) + "\n" +
            "Price: $" + readStockPriceFromCsv("controllerTest2_bharati",1)+ "\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Tesla\n" +
            "Symbol: TSLA\n" +
            "Time: " + readStockDateFromCsv("controllerTest2_bharati",2) + "\n" +
            "Price: $" + readStockPriceFromCsv("controllerTest2_bharati",2)+ "\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Johnson\n" +
            "Symbol: JNJ\n" +
            "Time: " + readStockDateFromCsv("controllerTest2_bharati",3) + "\n" +
            "Price: $" + readStockPriceFromCsv("controllerTest2_bharati",3)+ "\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST2_BHARATI PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "\nCONTROLLERTEST2_BHARATI PORTFOLIO COMPOSITION - Created on 2022-10-31\n" +

            "\nName (Symbol) 	 Quantity 	 Price of each share 	 Total Value\n" +

            "\nWalmart (WMT) 	 11	 $"+ readStockPriceFromCsv("controllerTest2_bharati",1)+	 "$1568.6\n" +
            "Tesla (TSLA) 	 3	 $"+ readStockPriceFromCsv("controllerTest2_bharati",2)+	 "$685.08\n" +
            "Johnson (JNJ) 	 100	 $"+ readStockPriceFromCsv("controllerTest2_bharati",3)+	 "$17420.0\n" +

            "\nTotal Portfolio Value as on 2022-10-31: $19673.68\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerWithoutBuyingAnythingNoPortfolioCreated() {
    String userInput = "1" + "\n" + "Neha" + "\n" + "1" + "\n" + "10" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "NEHA Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-10-28 19:26:00\n" +
            "Price: $142.6000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerClubTwoStocksOfSameTickerComposition() {
    String userInput = "1" + "\n" + "controllerTest3_bharat" + "\n" + "1" + "\n" + "3" + "\n" + "3"
            + "\n" + "y" + "\n" + "3" + "\n" + "2" + "\n" + "n" + "\n" + "2" + "\n" + "3" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST3_BHARAT Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Google\n" +
            "Symbol: GOOG\n" +
            "Time: 2022-10-28 19:56:00\n" +
            "Price: $96.5500\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Google\n" +
            "Symbol: GOOG\n" +
            "Time: 2022-10-28 19:56:00\n" +
            "Price: $96.5500\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST3_BHARAT PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "\nCONTROLLERTEST3_BHARAT PORTFOLIO COMPOSITION - Created on 2022-10-31\n" +

            "\nName (Symbol) 	 Quantity 	 Price of each share 	 Total Value\n" +

            "\nGoogle (GOOG) \t 5\t $96.55\t $482.75\n" +

            "\nTotal Portfolio Value as on 2022-10-31: $482.75\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerOverrideSamePortfolio() {
    String userInput = "1" + "\n" + "controllerTest3_bharat" + "\n" + "y" + "\n" + "1" + "\n" + "8" + "\n" + "7" + "\n"
            + "n" + "\n" + "2" + "\n" + "3" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "This portfolio already exists.\n" +
            "If you want to override this portfolio, then press 'y'. " +
            "If you want to enter another name, press 'n'. " +
            "If you want to go main screen, press 'b'.\n" +
            "\n" +
            "\nCREATE PORTFOLIO MENU\n" +
            "\nCONTROLLERTEST3_BHARAT Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Amazon\n" +
            "Symbol: AMZN\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $102.4000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST3_BHARAT PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "\nCONTROLLERTEST3_BHARAT PORTFOLIO COMPOSITION - Created on 2022-10-31\n" +

            "\nName (Symbol) 	 Quantity 	 Price of each share 	 Total Value\n" +

            "\nAmazon (AMZN) \t 7\t $102.4\t $716.8\n" +

            "\nTotal Portfolio Value as on 2022-10-31: $716.8\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInputDate() {
    String userInput = "3" + "\n" + "4" + "\n" + "2022-10-10" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): " +
            "\n" +
            "Value of ESHA PORTFOLIO\n" +

            "\nName (Symbol) \t Quantity\t Share Value on 2022-10-10\t Total Value\n" +
            "\nAmazon (AMZN) \t 7\t $107.8\t $754.6" +
            "\n" +
            "\nTotal Portfolio Value is on 2022-10-10: $754.6\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInputDate2() {
    String userInput = "3" + "\n" + "4" + "\n" + "2019-12-12" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): " +
            "\n" +
            "Value of ESHA PORTFOLIO\n" +

            "\nName (Symbol) \t Quantity\t Share Value on 2019-12-12\t Total Value\n" +
            "\nAmazon (AMZN) \t 7\t $107.8\t $754.6" +
            "\n" +
            "\nTotal Portfolio Value is on 2022-10-10: $754.6\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInvalidDate() {
    String userInput = "3" + "\n" + "4" + "\n" + "9999-98-88" + "\n" + "2022-02-02"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): " +
            "\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "Value of ESHA PORTFOLIO\n" +

            "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n" +
            "\nAmazon (AMZN) 	 7	 $119.1	 $833.7" +
            "\n" +
            "\nTotal Portfolio Value is on 2022-02-02: $833.7\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInvalidDate2() {
    String userInput = "3" + "\n" + "4" + "\n" + "123445" + "\n" + "2022-02-02"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): " +
            "\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "Value of ESHA PORTFOLIO\n" +

            "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n" +
            "\nAmazon (AMZN) 	 7	 $119.1	 $833.7" +
            "\n" +
            "\nTotal Portfolio Value is on 2022-02-02: $833.7\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInvalidDate3FutureDate() {
    String userInput = "3" + "\n" + "4" + "\n" + "2024-10-10" + "\n" + "2022-02-02"
            + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): " +
            "\n" +
            "Not a valid input. Please enter the correct date.\n" +
            "Press 'b' to go back\n" +
            "\n" +
            "Value of ESHA PORTFOLIO\n" +

            "\nName (Symbol) \t Quantity\t Share Value on 2022-02-02\t Total Value\n" +
            "\nAmazon (AMZN) 	 7	 $119.1	 $833.7" +
            "\n" +
            "\nTotal Portfolio Value is on 2022-02-02: $833.7\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerEnterWrongOptionForSelectingPortfolio() {
    String userInput = "2" + "\n" + "89" + "\n" + "b" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. ESHA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Not a valid input. Please enter the correct portfolio.\n" +
            "Press 'b' to go back to the previous menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInvalidInitialChoice() {
    String userInput = "7" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerInvalidInputs() {
    String userInput = "5" + "\n" + "1" + "\n" + "" + "\n" + "Riya" + "\n" + "6" + "\n" + "1" +
            "\n" + "11" + "\n" + "10" + "\n" + "-4" + "\n" + "4.3" + "\n" + "l" + "\n" + "m" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "Cannot create a portfolio with empty name. Enter a valid name.\n" +
            "If you want to go back to main menu, press '0'.\n" +
            "\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "RIYA Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
            "2. Main Menu\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "RIYA Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "If you want to go back to main menu, press '0'." +
            "\n" +
            "\n" +
            "\nCURRENT STOCK PRICE\n" +
            "StockName: Walmart\n" +
            "Symbol: WMT\n" +
            "Time: 2022-10-31 19:28:00\n" +
            "Price: $142.1200\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Not a valid input. Please enter number of shares as whole number.\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Not a valid input. Please enter number of shares as whole number.\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Not a valid input. Please enter number of shares as whole number.\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerShowSamePriceOnSameDate() {
    String userInput = "1" + "\n" + "controllerTest4_Anuja" + "\n" + "1" + "\n" + "1" + "\n" + "1"
            + "\n" + "n"
            + "\n" + "2" + "\n" + "5" + "\n" + "m" + "\n" + "3" + "\n" + "5" + "\n" + "2022-11-01"
            + "\n" + "m"+ "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST4_ANUJA Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $232.6000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST4_ANUJA PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. CONTROLLERTEST4_ANUJA\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "\n" +
            "CONTROLLERTEST6_ASH PORTFOLIO COMPOSITION - Created on 2022-11-01\n" +
            "\n" +
            "Name (Symbol) \t Quantity \t Price of each share \t Total Value\n" +
            "\n" +
            "Apple (AAPL) \t 3\t $153.46\t $460.38\n" +
            "Microsoft (MSFT) \t 0\t $232.6\t $0.0\n" +
            "\n" +
            "Total Portfolio Value as on 2022-11-01: $460.38\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "LIST OF PORTFOLIO\n" +
            "\n" +
            "1. CONTROLLERTEST1_HEALTH\n" +
            "2. CONTROLLERTEST2_BHARATI\n" +
            "3. CONTROLLERTEST3_BHARAT\n" +
            "4. CONTROLLERTEST4_ANUJA\n" +
            "5. CONTROLLERTEST6_ASH\n" +
            "6. CONTROLLERTEST7_SHUBHAM\n" +
            "7. E\n" +
            "8. ESHA\n" +
            "9. T3\n" +
            "10. TEST1\n" +
            "\n" +
            "Which portfolio would you like to check?\n" +
            "Enter the year in format (YYYY-MM-DD) (2000 to 2022): \n" +
            "Value of CONTROLLERTEST6_ASH PORTFOLIO\n" +
            "\n" +
            "Name (Symbol) \t Quantity\t Share Value on 2022-11-01\t Total Value\n" +
            "\n" +
            "Apple (AAPL) \t 3\t $153.46\t $460.38\n" +
            "Microsoft (MSFT) \t 0\t $232.6\t $0.0\n" +
            "\n" +
            "Total Portfolio Value is on 2022-11-01: $0.0\n" +
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }
  // this testcase is displaying profile, it should not display the profile
  @Test
  public void testControllerHowManyStocksIsZeroSoPortfolioNotCreated() {
    String userInput = "1" + "\n" + "controllerTest5_Meghna" + "\n" + "1" + "\n" + "1" + "\n"
            + "0" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\n\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST5_MEGHNA Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $232.6000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST5_MEGHNA PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }
  // this testcase should not display entry in the portfolio
  @Test
  public void testControllerHowManyStocksIsZeroSoStockNotDisplayedInPortfolio() {
    String userInput = "1" + "\n" + "controllerTest6_Ash" + "\n" + "1" + "\n" + "4" + "\n"
            + "3" + "\n" + "y" + "\n" + "1" + "\n"
            + "0" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST6_ASH Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Apple\n" +
            "Symbol: AAPL\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $153.4600\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $232.6000\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST6_ASH PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testController4StocksBoughtByTheUser() {
    String userInput = "1" + "\n" + "controllerTest7_Shubham" + "\n" + "1" + "\n"
            + "1" + "\n" + "10" + "\n" + "Y" + "\n"
            + "2" + "\n" + "10" + "\n" + "y" + "\n"
            + "3" + "\n" + "10" + "\n" + "y" + "\n"
            + "4" + "\n" + "10" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST7_SHUBHAM Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $232.6000\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Meta\n" +
            "Symbol: META\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $93.2500\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Google\n" +
            "Symbol: GOOG\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $94.9000\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Apple\n" +
            "Symbol: AAPL\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $153.4600\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST7_SHUBHAM PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n" +
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

  @Test
  public void testControllerCannotGetStocksMoreThan4ByTheUser() {
    String userInput = "1" + "\n" + "controllerTest8_Sharayu" + "\n" + "1" + "\n"
            + "1" + "\n" + "10" + "\n" + "Y" + "\n"
            + "2" + "\n" + "10" + "\n" + "y" + "\n"
            + "3" + "\n" + "10" + "\n" + "y" + "\n"
            + "4" + "\n" + "10" + "\n" + "n" + "\n"
            + "5" + "\n" + "10" + "\n" + "n" + "\n" + "e";
    InputStream input = new ByteArrayInputStream(userInput.getBytes());

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ControllerViewInteract obj = new ControllerViewInteractImpl(input, output);
    obj.start();

    String expectedOutput = "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Enter the name for this portfolio.\n" +
            "\n" +
            "CREATE PORTFOLIO MENU\n" +
            "\n" +
            "CONTROLLERTEST8_SHARAYU Portfolio\n" +
            "\n" +
            "1. Buy a share\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Microsoft\n" +
            "Symbol: MSFT\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $232.6000\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Meta\n" +
            "Symbol: META\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $93.2500\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Google\n" +
            "Symbol: GOOG\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $94.9000\n" +
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
            "\n" +
            "CURRENT STOCK PRICE\n" +
            "StockName: Apple\n" +
            "Symbol: AAPL\n" +
            "Time: 2022-10-31 20:00:00\n" +
            "Price: $153.4600\n" +
            "\n" +
            "How many shares would you like to buy?\n" +
            "Press 'b' to go back to the previous menu, 'm' to main menu.\n" +
            "\n" +
            "Would you like to buy another stock? (Y|N)\n" +
            "\n" +
            "CONTROLLERTEST8_SHARAYU PORTFOLIO CREATED...!!!\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "Invalid command. Enter the right option number.\n" +
            "\n" +
            "MENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
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
