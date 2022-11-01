import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

import static org.junit.Assert.assertEquals;

public class controllerTest {
  @Test
  public void testControllerCreatePortfolio() {
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
            "Time: 2022-10-28 19:59:00\n" +
            "Price: $235.7000\n" +
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
  public void testControllerCreatePortfolioAndViewPortfolio() {
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
            "Time: 2022-10-28 19:26:00\n" +
            "Price: $142.6000\n" +
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
            "Time: 2022-10-28 20:00:00\n" +
            "Price: $228.3600\n" +
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
            "Time: 2022-10-28 18:40:00\n" +
            "Price: $174.2000\n" +
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

            "\nWalmart (WMT) 	 11	 $142.6	 $1568.6\n" +
            "Tesla (TSLA) 	 3	 $228.36	 $685.08\n" +
            "Johnson (JNJ) 	 100	 $174.2	 $17420.0\n" +

            "\nTotal Portfolio Value as on 2022-10-31: $19673.68\n"+
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
            + "\n" + "y" + "\n" + "3"+ "\n" + "2"+ "\n" + "n"+ "\n" + "2"+ "\n" + "3"+ "\n" + "m"+ "\n" + "e";
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

            "\nTotal Portfolio Value as on 2022-10-31: $482.75\n"+
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n"+
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n"+
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }
  @Test
  public void testControllerOverrideSamePortfolio() {
    String userInput = "1" + "\n" + "controllerTest3_bharat" + "\n" + "y"+ "\n" + "1" + "\n" + "8" + "\n" + "7"
             + "n"+ "\n" + "2"+ "\n" + "3"+ "\n" + "m"+ "\n" + "e";
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
            "This portfolio already exists.\n"+
            "If you want to override this portfolio, then press 'y'. "+
            "If you want to enter another name, press 'n'. " +
            "If you want to go main screen, press 'b'.\n"+
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
            "StockName: Amazon\n" +
            "Symbol: AMZN\n" +
            "Time: 2022-10-28 20:00:00\n" +
            "Price: $103.8700\n" +
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

            "\nAmazon (AMZN) \t 7 \t 5\t $103.87\t $727.09\n" +

            "\nTotal Portfolio Value as on 2022-10-31: $482.75\n"+
            "\n" +
            "Press 'b' to go back and 'm' for main menu.\n" +
            "\n"+
            "\nMENU\n" +
            "\n" +
            "1. Create a portfolio\n" +
            "2. View portfolio\n" +
            "3. Value of portfolio\n" +
            "e. Exit\n" +
            "\n" +
            "ENTER YOUR CHOICE: \n" +
            "\n"+
            "Exiting...\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expectedOutput, result);
  }

}
