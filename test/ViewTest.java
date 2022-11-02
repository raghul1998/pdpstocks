import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import view.TypeofViews;
import view.ViewControllerInteract;
import view.ViewControllerInteractImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the View.
 */

public class ViewTest extends TestParentClass {

  @Test
  public void testMainScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.MAIN, null, 0);

    String expected = "\nMENU\n" + "\n"
            + "1. Create a portfolio\n"
            + "2. View portfolio\n"
            + "3. Value of portfolio\n"
            + "e. Exit\n" + "\n"
            + "ENTER YOUR CHOICE: \n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");
    assertEquals(expected, result);
  }

  @Test
  public void testNoPortfolio() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.NO_PORTFOLIO, null, 0);

    String expected = "You dont have any portfolio.\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testThisPortfolioExists() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_ALREADY_EXISTS, null, 0);

    String expected = "This portfolio already exists.\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testNotValidInputScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.NOT_VALID_INPUT_SCREEN, null, 0);

    String expected = "Not a valid input. Please enter the correct option.\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testCreatePortfolioNameScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO_NAME_SCREEN, null, 0);

    String expected = "Enter the name for this portfolio.\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowBuyStockValueScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_VALUE, null, 0);

    String expected = "\nHow many shares would you like to buy?\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testWouldYouLikeToBuyAnotherStockScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.BUY_ANOTHER_STOCK, null, 0);

    String expected = "Would you like to buy another stock? (Y|N)\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowPortfolioNameReenter() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_NAME_REENTER, null, 0);

    String expected = "Cannot create a portfolio with empty name. Enter a valid name.\n"
            + "If you want to go back to main menu, press '0'.\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowStockBuyReenter() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.STOCK_BUY_REENTER, null, 0);

    String expected = "Not a valid input. Please enter the correct stock.\n"
            + "If you want to go back to main menu, press 'm'.\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowStockBuyInvalidRetryScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.BUY_STOCKS_INVALID_RETRY, null, 0);

    String expected = "Not a valid input. Please enter number of shares as natural numbers.\n"
            + "Press 'b' to go back to the previous menu, 'm' to main menu.\n\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowPortfolioReviewScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.REVIEW_STOCK, null, 0);

    String expected = "Press 'b' to go back and 'm' for main menu.\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testPortfolioInvalidEntryScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INVALID_ENTRY, null, 0);

    String expected = "Not a valid input. Please enter the correct portfolio.\n"
            + "Press 'b' to go back to the previous menu.\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testPfReenterDuplicateName() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PF_REENTER_DUPLICATE_NAME, null, 0);

    String expected = "If you want to override this portfolio, then press 'y'. "
            + "If you want to enter another name, press 'n'. If you want to go main screen, "
            + "press 'b'.\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testCorrectDateScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.DATE_RENTER, null, 0);

    String expected = "Not a valid input. Please enter the correct date.\n"
            + "Press 'b' to go back\n" + "\n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testCreatePortfolio() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    String[] name = {"esha"};
    String s = Arrays.toString(name).replaceAll("\\[|\\]|,", "");
    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.CREATE_PORTFOLIO, name, 0);

    String expected = "\nCREATE PORTFOLIO MENU\n" + "\n"
            + s.toUpperCase() + " Portfolio\n" + "\n"
            + "1. Buy a stock\n"
            + "2. Main Menu\n"
            + "e. Exit\n" + "\n"
            + "ENTER YOUR CHOICE: \n";

    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowDisplayPortFolioCreated() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    String[] name = {"esha"};
    String s = Arrays.toString(name).replaceAll("\\[|\\]|,", "");
    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.DISPLAY_PORTFOLIO_CREATED, name, 0);

    String expected = "\n" + s.toUpperCase() + " PORTFOLIO CREATED...!!!\n";


    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testListOfSupportedStocksScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.LIST_OF_STOCKS, null, 0);

    String expected = "\n" + "\n1. Microsoft (MSFT)\n"
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
            + "Which stock would you like to buy?\n";


    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }


  @Test
  public void testShowPortfolioIndividualScreen2() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    String[] option = {"3"};
    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST, option, 0);

    String expected = "\nCONTROLLERTEST11 PORTFOLIO COMPOSITION - Created on "
            + super.readStockDataFromPortfolioCsv("controllerTest11", 1,
            0, true) + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n" + "\n"
            + "Tesla (TSLA) \t 28\t $"
            + Math.floor(Double.parseDouble(
            super.readStockDataFromPortfolioCsv(
                    "controllerTest11", 1,
                    6, false)) * 100) / 100 + "\t $"
            + 28 * Math.floor(Double.parseDouble(
            super.readStockDataFromPortfolioCsv(
                    "controllerTest11", 1,
                    6, false)) * 100) / 100 + "\n"
            + "\nTotal Portfolio Value as on "
            + super.readStockDataFromPortfolioCsv("controllerTest11", 1,
            0, true)
            + ": $" + 28
            * Math.floor(Double.parseDouble(
            super.readStockDataFromPortfolioCsv(
                    "controllerTest11", 1,
                    6, false)) * 100) / 100 + "\n" + "\n";


    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowPortFolioCompositionScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    String[] args = {"E", "ES"};
    int length = 2;
    StringBuilder s = new StringBuilder();
    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_COMPOSITION, args, length);
    for (int i = 0; i < length; i++) {
      s.append(i + 1 + ". " + args[i].toUpperCase() + "\n");
    }
    String expected = "\nLIST OF PORTFOLIO\n" + "\n"
            + s
            + "\nWhich portfolio would you like to check?\n";


    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowStockDataScreen() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.SHOW_STOCK_DATA, null, 0);

    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      output.println("Supported stocks file not found " + e.getMessage());
    }

    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
    } catch (Exception e) {
      output.println("Error in reading Supported stocks csv file.");
    }


    String expected = "\nCURRENT STOCK PRICE"
            + "\nStockName: " + splitStockData[0]
            + "\nSymbol: " + splitStockData[2]
            + "\nTime: " + splitStockData[3]
            + "\nPrice: $" + splitStockData[1] + "\n";


    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

  @Test
  public void testShowPortfolioIndividualWithDateScreen() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes);
    String[] args = {"3", "2022-10-29"};

    ViewControllerInteract vciObj = new ViewControllerInteractImpl(output);
    vciObj.viewControllerInteract(TypeofViews.PORTFOLIO_INDIVIDUAL_LIST, args, 0);

    String expected = "\nCONTROLLERTEST11 PORTFOLIO COMPOSITION - Created on "
            + super.readStockDataFromPortfolioCsv("controllerTest11", 1,
            0, true) + "\n"
            + "\nName (Symbol) \t Quantity \t Price of each share \t Total Value\n" + "\n"
            + "Tesla (TSLA) \t 28\t $"
            + Math.floor(Double.parseDouble(
            super.readStockDataFromPortfolioCsv(
                    "controllerTest11", 1,
                    6, false)) * 100) / 100
            + "\t $"
            + 28
            * Math.floor(Double.parseDouble(
            super.readStockDataFromPortfolioCsv(
                    "controllerTest11", 1,
                    6, false)) * 100) / 100 + "\n"
            + "\nTotal Portfolio Value as on "
            + super.readStockDataFromPortfolioCsv("controllerTest11", 1,
            0, true)
            + ": $"
            + 28
            * Math.floor(Double.parseDouble(
            super.readStockDataFromPortfolioCsv(
                    "controllerTest11", 1,
                    6, false)) * 100) / 100 + "\n" + "\n";


    String result = bytes.toString();
    result = result.replace("\r\n", "\n");

    assertEquals(expected, result);
  }

}
