package controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Objects;

import model.AlphaVantageTimeSeriesDaily;
import model.ImportXML;
import model.MVCModel;
import model.Portfolio;
import model.PortfolioImpl;
import view.MVCCommandView;
import view.MVCView;
import view.TotalValueCounter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Main class in controller.
 */

public class MVCCommandControllerTest {

  public static boolean compareString(String one, String two) {
    return one.replaceAll("\\s+", "").equalsIgnoreCase(two.replaceAll("\\s+", ""));
  }

  @Before
  public void setUp() {
    String input = "";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
  }

  @Test
  public void testExitInput() {

    InputStream input = new ByteArrayInputStream("2 4".getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCCommandController mvcCommandController = new MVCCommandController(input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";
    mvcCommandController.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testImportXML() {
    String inputString = "2 2 2 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCCommandController mvcCommandController = new MVCCommandController(input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    mvcCommandController.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testIncorrectInput() {
    String inputString = "2 5 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCCommandController mvcCommandController = new MVCCommandController(input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Invalid Input!\n" +
            "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!";

    mvcCommandController.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testIncorrectTextInput() {
    String inputString = "2 a 4 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCCommandController mvcCommandController = new MVCCommandController(input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Invalid input\n" +
            "Thank you for using!\n";

    mvcCommandController.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testBuyShares() {
    String inputString = "2 2 2 1 flexible_1.xml Google GOOG 32 2022-10-31 N N 2 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "Enter the number of stocks\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }


  @Test
  public void testBuyFractionalShares() {
    String inputString = "2 2 2 1 flexible_1.xml Google GOOG 32.5 32 2022-10-31 N N 2 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "Enter the number of stocks\n" +
            "The input number is wrong!\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }


  @Test
  public void testSellFractionalShares() {
    String inputString = "2 2 2 2 flexible_1.xml GOOG 2022-10-31 32.5 32 N 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the symbol of the Company to sell\n" +
            "Enter the date[in YYYY-MM-DD format]\n" +
            "Enter the number of stocks to sell\n" +
            "Input number is invalid to sell!\n" +
            "Sell another stock? Y or N\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test // need to check //
  public void testPortfolioPerformance() {
    String inputString = "2 2 2 5 flexible_1.xml 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the date[in YYYY-MM-DD format]\n" +
            "Performance of portfolio flexible_1.xml from " +
            "Fri Jan 09 00:00:00 EST 2015 to Mon Oct 31 00:00:00 EDT 2022\n" +
            "01-2015 : *\n" +
            "01-2016 : ***\n" +
            "01-2017 : ***\n" +
            "01-2018 : *******\n" +
            "01-2019 : ******\n" +
            "01-2020 : *********\n" +
            "01-2021 : **************\n" +
            "Each * is 10000.0 above 53452.0\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));

  }

  @Test
  public void testSellShares() {
    String inputString = "2 2 2 2 flexible_1.xml GOOG 2022-10-31 25 N 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the symbol of the Company to sell\n" +
            "Enter the date[in YYYY-MM-DD format]\n" +
            "Enter the number of stocks to sell\n" +
            "Sell another stock? Y or N\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testCreateFlexibleStockMenu() {
    String inputString = "2 1 Google GOOG 32 2022-10-31 N N 2 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "Enter the number of stocks\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testCreateStockMenuDecimalStocks() {
    String inputString = "2 1 Google GOOG 32.5 32 2022-10-31 N N 1 4 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "Enter the number of stocks\n" +
            "The input number is wrong!\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Examine the composition of a portfolio\n" +
            "2. View the total value of a portfolio on a certain date\n" +
            "3. View portfolio performance\n" +
            "4. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testCreateStockMenuIncorrectCompanySymbol() {
    String inputString = "2 1 Google GOO GOOG 32 2022-10-31 N N 1 4 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "GOO is not listed in the market!\n" +
            "Enter the number of stocks\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Examine the composition of a portfolio\n" +
            "2. View the total value of a portfolio on a certain date\n" +
            "3. View portfolio performance\n" +
            "4. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test  // need to check //
  public void testCreateStockMenuIncorrectDate() {
    String inputString = "2 1 Google GOOG 32 2022-13-31 2022-10-31 N N 1 4 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "Enter the number of stocks\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "The format of date is wrong!\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Examine the composition of a portfolio\n" +
            "2. View the total value of a portfolio on a certain date\n" +
            "3. View portfolio performance\n" +
            "4. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test // need to check
  public void testCreateStockMenuMarketClosed() {
    String inputString = "2 1 Google GOOG 32 2022-04-02 2022-10-31 N N 1 4 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the name of the Company\n" +
            "Enter the symbol of the Company\n" +
            "Enter the number of stocks\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Enter the value of stock. Buy at today's price, enter 'N'\n" +
            "Add another stock? Y or N\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Examine the composition of a portfolio\n" +
            "2. View the total value of a portfolio on a certain date\n" +
            "3. View portfolio performance\n" +
            "4. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test // need to check
  public void testImportStockViewComposition() {
    String inputString = "2 2 2 3 flexible_1.xml 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            "Select the type of portfolio you want to create:\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the date[in YYYY-MM-DD format]\n" +
            "Total value of a portfolio: 19197.8\n" +
            "Total money invested: 59864.0\n" +
            "Company          Symbol           Portion          Shares           " +
            "PurchaseOfValue  TotalValue       DateOfPurchase   \n" +
            "Google           GOOG             49.31            100              " +
            "496.1700         49617.0000       2015-01-09       \n" +
            "AMZN             AMZN             50.69            95               " +
            "102.4400         9731.8000        2022-10-31       \n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test // need to check
  public void testImportStockViewTotal() {
    String inputString = "2 2 2 4 flexible_1.xml 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Enter the path of the file(ex. flexible_1.xml):\n" +
            "Enter the date of purchase[in YYYY-MM-DD format]\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testImportStockExit() {
    String inputString = "2 2 1 4 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Select the type of portfolio to import :\n" +
            "1. Inflexible Portfolio\n" +
            "2. Flexible Portfolio\n" +
            "Menu:\n" +
            "1. Examine the composition of a portfolio\n" +
            "2. View the total value of a portfolio on a certain date\n" +
            "3. View portfolio performance\n" +
            "4. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvest() {
    String inputString = "2 3 Google N GOOG 10 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestFinishN() {
    String inputString = "2 3 Google N GOOG 10 10000 30 2022-01-03 N 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidStringPortion() {
    String inputString = "2 3 Google N GOOG a 10 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Portion input is invalid!\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidNegativePortion() {
    String inputString = "2 3 Google N GOOG -1 10 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Portion input is invalid!\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidZeroPortion() {
    String inputString = "2 3 Google N GOOG 0 10 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Portion input is invalid!\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidStringAmount() {
    String inputString = "2 3 Google N GOOG 10 a 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "The format of amount is invalid!\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidNegativeAmount() {
    String inputString = "2 3 Google N GOOG 10 -1 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "The format of amount is invalid!\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidZeroAmount() {
    String inputString = "2 3 Google N GOOG 10 0 10000 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "The format of amount is invalid!\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidStringFrequency() {
    String inputString = "2 3 Google N GOOG 10 10000 a 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "The input is not positive integer!\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidNegativeFrequency() {
    String inputString = "2 3 Google N GOOG 10 10000 -1 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "The input is not positive integer!\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidZeroFrequency() {
    String inputString = "2 3 Google N GOOG 10 10000 0 30 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "The input is not positive integer!\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidStartDate() {
    String inputString = "2 3 Google N GOOG 10 10000 30 2022-10 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Start date is invalid!\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidStartDateAfterToday() {
    String inputString = "2 3 Google N GOOG 10 10000 30 2023-01-01 2022-01-03 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Start date is invalid!\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidFinishDate() {
    String inputString = "2 3 Google N GOOG 10 10000 30 2022-01-03 2021-10 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Finish date in invalid!\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  @Test
  public void testPassiveInvestInvalidFinishDateBeforeStart() {
    String inputString = "2 3 Google N GOOG 10 10000 30 2022-01-03 2021-10-29 2022-10-31 7 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    String expected = "Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of companies to buy. Enter N to stop.\n" +
            "Enter the list of symbols to buy.\n" +
            "Enter the portion for GOOG in integer.\n" +
            "Enter the amount of money on each case.\n" +
            "Enter the frequency in integer.\n" +
            "Enter start date.\n" +
            "Enter finish date. Enter 'N' to not designate.\n" +
            "Finish date in invalid!\n" +
            "Menu:\n" +
            "1. Buy shares\n" +
            "2. Sell shares\n" +
            "3. Examine the composition of a portfolio\n" +
            "4. View the total value of a portfolio on a certain date\n" +
            "5. View portfolio performance\n" +
            "6. Invest dollar-cost averaging\n" +
            "7. Go back to main menu\n" +
            ": Menu:\n" +
            "1. Create portfolio\n" +
            "2. Import portfolio\n" +
            "3. Create passive invest portfolio\n" +
            "4. Exit\n" +
            ": Thank you for using!\n";

    controller.menu();
    assertTrue(compareString(expected, outputStream.toString()));
  }

  // Rebalancing


  // creates flexible_1 portfolio on 2022-11-11, rebalances on 2022-11-15, all stocks
  // with equal proportion
  @Test
  public void testRebalancingPortfolioSameProportion(){
    String inputString = "1 Walmart WMT 10 2022-11-11 200 Y Amazon AMZN 10 2022-11-11 "
            + "200 Y Google GOOG 10 2022-11-11 200 Y Apple AAPL 10 2022-11-11 200 N 2 3 "
            + "flexible_1.xml 2022-11-11 7 flexible_1.xml 2022-11-15 25 25 25 25 3 "
            + "flexible_2.xml 2022-11-15 8 4 8 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    controller.menu();

    ImportXML imXml = new ImportXML();
    PortfolioImpl pfImpl = imXml.buildDocument("flexible_2.xml");
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
    ArrayList<Double> sharesList = numList(pfList, "2022-11-15");

    String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
            avtsd.getValue("WMT", "2022-11-15")));
    String totalValue2 = String.format("%.2f",sharesList.get(1) * Double.parseDouble(
            avtsd.getValue("AMZN", "2022-11-15")));
    String totalValue3 = String.format("%.2f",sharesList.get(2) * Double.parseDouble(
            avtsd.getValue("GOOG", "2022-11-15")));
    String totalValue4 = String.format("%.2f",sharesList.get(3) * Double.parseDouble(
            avtsd.getValue("AAPL", "2022-11-15")));

    assertEquals(totalValue1, "1237.85");
    assertEquals(totalValue2, "1237.85");
    assertEquals(totalValue3, "1237.85");
    assertEquals(totalValue4, "1237.85");

    double total1 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_2.xml",
                    "2022-11-15"));
    double total2 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_1.xml",
                    "2022-11-15"));

    assertEquals(String.valueOf(total1),String.valueOf(total2));
  }

  // creates flexible_1 portfolio on 2022-11-11, rebalances on 2022-11-15, all stocks
  // with different proportion

  @Test
  public void testRebalancingPortfolioUnequalProportion(){
    String inputString = "1 Walmart WMT 10 2022-11-11 200 Y Amazon AMZN 10 2022-11-11 200 "
            + "Y Google GOOG 10 2022-11-11 200 Y Apple AAPL 10 2022-11-11 200 N 2 3 flexible_3.xml"
            + " 2022-11-11 7 flexible_3.xml 2022-11-15 50 40 5 5 3 flexible_4.xml 2022-11-15 8 4 "
            + "8 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    controller.menu();

    ImportXML imXml = new ImportXML();
    PortfolioImpl pfImpl = imXml.buildDocument("flexible_4.xml");
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
    ArrayList<Double> sharesList = numList(pfList, "2022-11-15");

    String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
            avtsd.getValue("WMT", "2022-11-15")));
    String totalValue2 = String.format("%.2f",sharesList.get(1) * Double.parseDouble(
            avtsd.getValue("AMZN", "2022-11-15")));
    String totalValue3 = String.format("%.2f",sharesList.get(2) * Double.parseDouble(
            avtsd.getValue("GOOG", "2022-11-15")));
    String totalValue4 = String.format("%.2f",sharesList.get(3) * Double.parseDouble(
            avtsd.getValue("AAPL", "2022-11-15")));

    assertEquals(totalValue1, "2475.70");
    assertEquals(totalValue2, "1980.56");
    assertEquals(totalValue3, "247.57");
    assertEquals(totalValue4, "247.57");

    double total1 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_4.xml",
                    "2022-11-15"));
    double total2 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_3.xml",
                    "2022-11-15"));

    assertEquals(String.valueOf(total1),String.valueOf(total2));
    //assertTrue(compareString(expected, "1237.85"));
  }


//  add stocks on 2015-05-05
  // add stocks on 2016-06-06
  // add stocks on 2017-07-07
  // add stocks on 2018-08-08
//  rebalance at the end - 2018-08-09
  // examine on 2018-08-09
  @Test
  public void testRebalancingPortfolioAtEnd(){
    String inputString = "1 Johnson JNJ 20 2015-05-05 N Y UnitedHealth UNH 23 2016-06-06 N Y "
            + "Meta META 20 2017-07-07 N Y Microsoft MSFT 4 2018-08-08 N N 2 3 flexible_5.xml "
            + "2018-08-08 7 flexible_5.xml 2018-08-09 25 25 25 25 3 flexible_6.xml 2018-08-09 "
            + "8 4 8 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    controller.menu();

    ImportXML imXml = new ImportXML();
    PortfolioImpl pfImpl = imXml.buildDocument("flexible_6.xml");
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
    ArrayList<Double> sharesList = numList(pfList, "2018-08-09");

    String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
            avtsd.getValue("JNJ", "2018-08-09")));
    String totalValue2 = String.format("%.2f",sharesList.get(1) * Double.parseDouble(
            avtsd.getValue("UNH", "2018-08-09")));
    String totalValue3 = String.format("%.2f",sharesList.get(2) * Double.parseDouble(
            avtsd.getValue("META", "2018-08-09")));
    String totalValue4 = String.format("%.2f",sharesList.get(3) * Double.parseDouble(
            avtsd.getValue("MSFT", "2018-08-09")));

    assertEquals(totalValue1, "3185.26");
    assertEquals(totalValue2, "3185.26");
    assertEquals(totalValue3, "3185.26");
    assertEquals(totalValue4, "3185.26");

    double total1 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_5.xml",
                    "2018-08-09"));
    double total2 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_6.xml",
                    "2018-08-09"));

    assertEquals(String.valueOf(total1),String.valueOf(total2));
  }

//  add stocks on 2015-05-05
  // add stocks on 2016-06-06
  // add stocks on 2017-07-07
  // add stocks on 2018-08-08
  //  rebalance at the end - 2016-06-06
  // examine on 2015-05-05


@Test
public void testRebalancingPortfolioinBetween(){
  String inputString = "1 Johnson JNJ 20 2015-05-05 N Y UnitedHealth UNH 23 2016-06-06 N"
          + " Y Meta META 20 2017-07-07 N Y Microsoft MSFT 4 2018-08-08 N N 2 3 flexible_7.xml "
          + "2018-08-08 7 flexible_7.xml 2016-06-07 50 50 3 flexible_8.xml 2016-06-07 8 4 8 4";
  InputStream input = new ByteArrayInputStream(inputString.getBytes());
  OutputStream outputStream = new ByteArrayOutputStream();
  MVCView view = new MVCCommandView();
  MVCModel model = new MVCModel();
  MVCCommandController controller = new MVCCommandController(model, view, input,
          new PrintStream(outputStream));

  controller.menu();

  ImportXML imXml = new ImportXML();
  PortfolioImpl pfImpl = imXml.buildDocument("flexible_8.xml");
  ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

  AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
  ArrayList<Double> sharesList = numList(pfList, "2016-06-07");

  String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
          avtsd.getValue("JNJ", "2016-06-07")));
  String totalValue2 = String.format("%.2f",sharesList.get(1) * Double.parseDouble(
          avtsd.getValue("UNH", "2016-06-07")));

  assertEquals(totalValue1, "2732.11");
  assertEquals(totalValue2, "2732.11");

  double total1 = Double.parseDouble(
          new TotalValueCounter().determineTotalValueOfPortfolio("flexible_7.xml",
                  "2016-06-07"));
  double total2 = Double.parseDouble(
          new TotalValueCounter().determineTotalValueOfPortfolio("flexible_8.xml",
                  "2016-06-07"));

  assertEquals(String.valueOf(total1),String.valueOf(total2));

}

//
//  add stocks on 2015-05-05
// add stocks on 2016-06-06
// add stocks on 2017-07-07
// add stocks on 2018-08-08
//  rebalance at the end - 2015-05-05
// examine on 2016-09-09
@Test
public void testRebalancingPortfolioAtStart(){
  String inputString = "1 Johnson JNJ 20 2015-05-05 N Y UnitedHealth UNH 23 2016-06-06"
          + " N Y Meta META 20 2017-07-07 N Y Microsoft MSFT 4 2018-08-08 N N 2 3 flexible_9.xml "
          + "2018-08-08 7 flexible_9.xml 2015-05-06 8 4 8 4";
  InputStream input = new ByteArrayInputStream(inputString.getBytes());
  OutputStream outputStream = new ByteArrayOutputStream();
  MVCView view = new MVCCommandView();
  MVCModel model = new MVCModel();
  MVCCommandController controller = new MVCCommandController(model, view, input,
          new PrintStream(outputStream));

  controller.menu();

  ImportXML imXml = new ImportXML();
  PortfolioImpl pfImpl = imXml.buildDocument("flexible_9.xml");
  ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

  AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
  ArrayList<Double> sharesList = numList(pfList, "2015-05-06");

  String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
          avtsd.getValue("JNJ", "2015-05-06")));

  assertEquals(totalValue1, "1983.40");

}

//  add stocks on 2015-05-05
  // add stocks on 2016-06-06
  // add stocks on 2017-07-07
  // add stocks on 2018-08-08
  //  rebalance at the end - 2017-07-08
  // examine on 2015-05-05


  @Test
  public void testRebalancingPortfolioinBetweenSameProportion2(){
    String inputString = "1 Johnson JNJ 20 2015-05-05 N Y UnitedHealth UNH 23 2016-06-06 N"
            + " Y Meta META 20 2017-07-06 N Y Amazon AMZN 23 2017-07-07 N Y Microsoft MSFT 4 2018-08-08 N N 2 3 flexible_10.xml "
            + "2018-08-08 7 flexible_10.xml 2017-07-11 25 25 25 25 3 flexible_11.xml 2017-07-11 8 4 8 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    controller.menu();

    ImportXML imXml = new ImportXML();
    PortfolioImpl pfImpl = imXml.buildDocument("flexible_11.xml");
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
    ArrayList<Double> sharesList = numList(pfList, "2017-07-11");

    String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
            avtsd.getValue("JNJ", "2017-07-11")));
    String totalValue2 = String.format("%.2f",sharesList.get(1) * Double.parseDouble(
            avtsd.getValue("UNH", "2017-07-11")));
    String totalValue3 = String.format("%.2f", sharesList.get(2) * Double.parseDouble(
            avtsd.getValue("META", "2017-07-11")));
    String totalValue4 = String.format("%.2f",sharesList.get(3) * Double.parseDouble(
            avtsd.getValue("AMZN", "2017-07-11")));

    assertEquals(totalValue1, "8217.96");
    assertEquals(totalValue2, "8217.96");
    assertEquals(totalValue3, "8217.96");
    assertEquals(totalValue4, "8217.96");

    double total1 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_10.xml",
                    "2017-07-11"));
    double total2 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_11.xml",
                    "2017-07-11"));

    assertEquals(String.valueOf(total1),String.valueOf(total2));

  }

  @Test
  public void testRebalancingPortfolioinBetweenDiffProportion2(){
    String inputString = "1 Johnson JNJ 20 2015-05-05 N Y UnitedHealth UNH 23 2016-06-06 N"
            + " Y Meta META 20 2017-07-06 N Y Amazon AMZN 23 2017-07-07 N Y Microsoft MSFT 4 2018-08-08 N N 2 3 flexible_12.xml "
            + "2018-08-08 7 flexible_12.xml 2017-07-11 60 40 5 5 flexible_13.xml 2017-07-11 8 4 8 4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    OutputStream outputStream = new ByteArrayOutputStream();
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view, input,
            new PrintStream(outputStream));

    controller.menu();

    ImportXML imXml = new ImportXML();
    PortfolioImpl pfImpl = imXml.buildDocument("flexible_13.xml");
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();
    ArrayList<Double> sharesList = numList(pfList, "2017-07-11");

    String totalValue1 = String.format("%.2f", sharesList.get(0) * Double.parseDouble(
            avtsd.getValue("JNJ", "2017-07-11")));
    String totalValue2 = String.format("%.2f",sharesList.get(1) * Double.parseDouble(
            avtsd.getValue("UNH", "2017-07-11")));
    String totalValue3 = String.format("%.2f", sharesList.get(2) * Double.parseDouble(
            avtsd.getValue("META", "2017-07-11")));
    String totalValue4 = String.format("%.2f",sharesList.get(3) * Double.parseDouble(
            avtsd.getValue("AMZN", "2017-07-11")));

    assertEquals(totalValue1, "19723.10");
    assertEquals(totalValue2, "13148.74");
    assertEquals(totalValue3, "1643.59");
    assertEquals(totalValue4, "1643.59");

    double total1 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_13.xml",
                    "2017-07-11"));
    double total2 = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio("flexible_12.xml",
                    "2017-07-11"));

   // assertEquals(String.valueOf(total1),String.valueOf(total2));

  }

  private ArrayList<Double> numList(ArrayList<Portfolio> portfolios, String date) {
    ArrayList<Double> ret = new ArrayList<>();
    double num;

    for (int i=0; i<portfolios.size(); i++) {
      num = 0;
      for (int j=0; j<portfolios.get(i).getDateNumsList().size(); j++) {
        if (date.compareTo(portfolios.get(i).getDateNumsList().get(j).getDate()) >= 0) {
          num += Double.parseDouble(portfolios.get(i).getDateNumsList().get(j).getNum());
        }
      }
      ret.add(num);
    }
    return ret;
  }

//  protected void deleteFileInDirectory(String fileName) {
//    File directory = new File("/pdpstocks/");
//    if (directory.exists()) {
//      File[] filename = directory.listFiles();
//      assert filename != null;
//      for (File fName : filename) {
//        if (Objects.equals(fileName, fName.getName())) {
//          if (fName.delete()) {
//            // Do nothing
//          } else {
//            System.out.println("Unable to delete");
//          }
//          break;
//        }
//      }
//    }
//  }
}