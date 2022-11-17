[1mdiff --git a/test/ControllerTest.java b/test/ControllerTest.java[m
[1mindex cfbb2d8..fcfe6f9 100644[m
[1m--- a/test/ControllerTest.java[m
[1m+++ b/test/ControllerTest.java[m
[36m@@ -1,3 +1,4 @@[m
[32m+[m[32mimport org.junit.AfterClass;[m
 import org.junit.FixMethodOrder;[m
 import org.junit.Test;[m
 import org.junit.runners.MethodSorters;[m
[36m@@ -2318,7 +2319,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
   // 29[m
 [m
   /**[m
[31m-   * This test throws a message when stock purchased on a specifc date[m
[32m+[m[32m   * This test throws a message when stock purchased on a specific date[m
    * and sold the same stock at a previous date than that of the purchase.[m
    */[m
   @Test[m
[36m@@ -2409,7 +2410,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
 [m
   @Test[m
   public void testZDBuyAndSell3() {[m
[31m-    deleteFileInDirectory("controllerTest90.csv");[m
[32m+[m[32m    deleteFileInDirectory("pf_controllerTest90.csv");[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest90" + "\n" + "1" + "\n" + "5"[m
             + "\n" + "2022-03-03" + "\n" + "100" + "\n" + "y" + "\n" + "6" + "\n" + "2022-01-01"[m
             + "\n" + "50" + "\n" + "y" + "\n" + "10" + "\n" + "2022-02-02" + "\n" + "150"[m
[36m@@ -2797,7 +2798,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * for portfolio controllerTest90.[m
    */[m
   @Test[m
[31m-  public void testZFddtoExistingPortfolioAndSell2() {[m
[32m+[m[32m  public void testZFAddtoExistingPortfolioAndSell2() {[m
     String userInput = "3" + "\n" + "10" + "\n" + "2022-01-01" + "\n" + "m" + "\n" + "3" + "\n"[m
             + "10" + "\n" + "2022-02-02" + "\n" + "m" + "\n" + "3" + "\n" + "10" + "\n"[m
             + "2022-03-03" + "\n" + "m" + "\n" + "e";[m
[36m@@ -2922,7 +2923,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test adds stock to the current portfolio.[m
    */[m
   @Test[m
[31m-  public void testZG() {[m
[32m+[m[32m  public void testZGAddStockToCurrentPortfolio() {[m
     String userInput = "4" + "\n" + "10" + "\n" + "6" + "\n" + "2022-11-11" + "\n" + "500" + "\n"[m
             + "y" + "\n" + "4" + "\n" + "10" + "\n" + "5" + "\n" + "2022-11-12" + "\n" + "40" + "\n"[m
             + "n" + "\n" + "2" + "\n" + "10" + "\n" + "2022-11-11" + "\n" + "m" + "\n" + "e";[m
[36m@@ -3044,7 +3045,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test sells stocks from current portfolio.[m
    */[m
   @Test[m
[31m-  public void testZH() {[m
[32m+[m[32m  public void testZHSellStockFromCurrentPortfolio() {[m
     deleteFileInDirectory("pf_controllerTest90.csv");[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest90" + "\n" + "1" + "\n" + "5"[m
             + "\n" + "2022-03-03" + "\n" + "100" + "\n" + "y" + "\n" + "6" + "\n" + "2022-01-01"[m
[36m@@ -3415,7 +3416,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks value portfolio on certain date for past date.[m
    */[m
   @Test[m
[31m-  public void testZK() {[m
[32m+[m[32m  public void testZKValueOfPortfolioOnCertainDate() {[m
     String userInput = "2" + "\n" + "10" + "\n" + "2014-01-01" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
     ByteArrayOutputStream bytes = new ByteArrayOutputStream();[m
[36m@@ -3466,7 +3467,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test creates portfolio with combination of purchases and sells in different dates.[m
    */[m
   @Test[m
[31m-  public void testZM() {[m
[32m+[m[32m  public void testZMPurchasesAndSells() {[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest91" + "\n" + "1" + "\n" + "8"[m
             + "\n" + "2014-03-03" + "\n" + "100" + "\n" + "y" + "\n" + "10" + "\n" + "2018-01-01"[m
             + "\n" + "50" + "\n" + "y" + "\n" + "1" + "\n" + "2000-02-02" + "\n" + "150"[m
[36m@@ -3736,7 +3737,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks performance by year.[m
    */[m
   @Test[m
[31m-  public void testZN() {[m
[32m+[m[32m  public void testZNPerformanceByYear() {[m
     String userInput = "6" + "\n" + "11" + "\n" + "1" + "\n" + "2010" + "\n" + "10"[m
             + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -3810,7 +3811,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test creates portfolio.[m
    */[m
   @Test[m
[31m-  public void testZO() {[m
[32m+[m[32m  public void testZOCreationOfPortfolioFlexible() {[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest92" + "\n" + "1" + "\n" + "8"[m
             + "\n" + "2014-03-03" + "\n" + "100" + "\n" + "y" + "\n" + "10" + "\n" + "2014-01-01"[m
             + "\n" + "50" + "\n" + "y" + "\n" + "1" + "\n" + "2014-02-02" + "\n" + "150"[m
[36m@@ -4082,7 +4083,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks by month.[m
    */[m
   @Test[m
[31m-  public void testZP() {[m
[32m+[m[32m  public void testZPPerformanceByMonth() {[m
     String userInput = "6" + "\n" + "12" + "\n" + "2" + "\n" + "2014-01"[m
             + "\n" + "5" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -4151,7 +4152,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks by day.[m
    */[m
   @Test[m
[31m-  public void testZQ() {[m
[32m+[m[32m  public void testZQPerformanceByDay() {[m
     String userInput = "6" + "\n" + "12" + "\n" + "3" + "\n" + "2014-01-01" + "\n"[m
             + "5" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -4221,10 +4222,11 @@[m [mpublic class ControllerTest extends TestParentClass {[m
   //44[m
 [m
   /**[m
[31m-   * This test creates portfolio for day.[m
[32m+[m[32m   * This test creates portfolio where the buying and selling takes place within days.[m
[32m+[m[32m   * This testcase is written for showing performance for DAY.[m
    */[m
   @Test[m
[31m-  public void testZR() {[m
[32m+[m[32m  public void testZRCreationOfPortfolioForShowingPerformanceForDay() {[m
     deleteFileInDirectory("controllerTest93.csv");[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest93" + "\n" + "1" + "\n" + "8"[m
             + "\n" + "2015-03-03" + "\n" + "100" + "\n" + "y" + "\n" + "10" + "\n" + "2015-03-01"[m
[36m@@ -4501,7 +4503,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks by day.[m
    */[m
   @Test[m
[31m-  public void testZS() {[m
[32m+[m[32m  public void testZSPerformanceByDay2() {[m
     String userInput = "6" + "\n" + "12" + "\n" + "3" + "\n" + "2015-03-01"[m
             + "\n" + "30" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -4599,7 +4601,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks performance invalid year,or month or day or invalid range.[m
    */[m
   @Test[m
[31m-  public void testZT() {[m
[32m+[m[32m  public void testZTChecksInvalidInputForPerformanceByYearMonthDay() {[m
     String userInput = "6" + "\n" + "10" + "\n" + "1" + "\n" + "2045" + "\n" + "b" + "\n"[m
             + "10" + "\n" + "2" + "\n" + "2023-07" + "\n" + "b" + "\n" + "10" + "\n" + "2024-11-10"[m
             + "\n" + "b" + "\n" + "m" + "\n" + "e";[m
[36m@@ -4716,7 +4718,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks performance with # when no value.[m
    */[m
   @Test[m
[31m-  public void testZU() {[m
[32m+[m[32m  public void testZUPerformanceToShowHash_noValue() {[m
     String userInput = "6" + "\n" + "2" + "\n" + "1" + "\n" + "2000" + "\n" + "5" + "\n" + "m"[m
             + "\n" + "6" + "\n" + "2" + "\n" + "2" + "\n" + "2000-02" + "\n" + "30" + "\n"[m
             + "m" + "\n" + "6" + "\n" + "2" + "\n" + "2" + "\n" + "2000-02" + "\n" + "30"[m
[36m@@ -4923,7 +4925,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks performance for a portfolio by month.[m
    */[m
   @Test[m
[31m-  public void testZV() {[m
[32m+[m[32m  public void testZVPerformanceByMonth() {[m
     String userInput = "6" + "\n" + "11" + "\n" + "2" + "\n" + "2022-01"[m
             + "\n" + "10" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -4998,7 +5000,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks performance by year.[m
    */[m
   @Test[m
[31m-  public void testZW() {[m
[32m+[m[32m  public void testZWPerformanceByYear() {[m
     String userInput = "6" + "\n" + "11" + "\n" + "1" + "\n" + "2010"[m
             + "\n" + "7" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -5072,7 +5074,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks performance by day.[m
    */[m
   @Test[m
[31m-  public void testZX() {[m
[32m+[m[32m  public void testZXPerformanceByDay3() {[m
     String userInput = "6" + "\n" + "11" + "\n" + "3" + "\n"[m
             + "2010-01-01" + "\n" + "30" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -5167,10 +5169,10 @@[m [mpublic class ControllerTest extends TestParentClass {[m
   //51[m
 [m
   /**[m
[31m-   * This test checks the operations of back and main menu.[m
[32m+[m[32m   * This test checks invalid input for performance.[m
    */[m
   @Test[m
[31m-  public void testZY() {[m
[32m+[m[32m  public void testZYInvalidInputForPerformance() {[m
     String userInput = "6" + "\n" + "11" + "\n" + "3" + "\n"[m
             + "2010-01-01" + "\n" + "35" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -5234,7 +5236,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks the operations of back and main menu.[m
    */[m
   @Test[m
[31m-  public void testZZ() {[m
[32m+[m[32m  public void testZZInvalidInput() {[m
     String userInput = "1" + "\n" + "1" + "\n" + "test1" + "\n" + "1" + "\n"[m
             + "0" + "\n" + "90" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -5283,13 +5285,17 @@[m [mpublic class ControllerTest extends TestParentClass {[m
     assertEquals(expectedOutput, result);[m
   }[m
 [m
[32m+[m[32m  @AfterClass[m
[32m+[m[32m  public void testZZZZ() {[m
[32m+[m[32m    deleteDirectory();[m
[32m+[m[32m  }[m
   //53[m
 [m
   /**[m
    * This test checks the total investment on various dates.[m
    */[m
   @Test[m
[31m-  public void testZZA() {[m
[32m+[m[32m  public void testZZATotalInvestmentOnVariousDates() {[m
     String userInput = "7" + "\n" + "11" + "\n" + "2022-01-01" + "\n" + "b" + "\n"[m
             + "11" + "\n" + "2014-03-03" + "\n" + "m" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
[36m@@ -5380,9 +5386,9 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks value portfolio on full composition on various dates.(inflexible)[m
    */[m
   @Test[m
[31m-  public void testZZB() {[m
[32m+[m[32m  public void testZZBFullCompositionInflexible() {[m
 [m
[31m-    deleteFileInDirectory("controllerTest94.csv");[m
[32m+[m[32m    deleteFileInDirectory("pf_controllerTest94.csv");[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest94" + "\n" + "1" + "\n" + "1"[m
             + "\n" + "2022-11-12" + "\n" + "50" + "\n" + "y" + "\n" + "2"[m
             + "\n" + "2022-11-13" + "\n" + "100" + "\n" + "n" + "\n" + "5" + "\n" + "14" + "\n"[m
[36m@@ -5578,7 +5584,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks the value portfolio (option 2 on various dates).[m
    */[m
   @Test[m
[31m-  public void testZZC() {[m
[32m+[m[32m  public void testZZCValueOfPortfolioOption2() {[m
 [m
     deleteFileInDirectory("controllerTest95.csv");[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest95" + "\n" + "1" + "\n" + "1"[m
[36m@@ -5788,8 +5794,8 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks Value of portfolio on full composition on certain date for past date.[m
    */[m
   @Test[m
[31m-  public void testZZD() {[m
[31m-    deleteFileInDirectory("controllerTest96.csv");[m
[32m+[m[32m  public void testZZDValueOfPortfolioOnCertainPastDate() {[m
[32m+[m[32m    deleteFileInDirectory("pf_controllerTest96.csv");[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest96" + "\n" + "1" + "\n" + "1"[m
             + "\n" + "2022-11-12" + "\n" + "50" + "\n" + "y" + "\n" + "2"[m
             + "\n" + "2022-11-13" + "\n" + "100" + "\n" + "n" + "\n" + "5" + "\n" + "16" + "\n"[m
[36m@@ -5955,7 +5961,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks total investments on previous dates.[m
    */[m
   @Test[m
[31m-  public void testZZE() {[m
[32m+[m[32m  public void testZZEChecksInvestmentsOnPreviousDates() {[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest97" + "\n" + "1" + "\n" + "1"[m
             + "\n" + "2022-11-12" + "\n" + "50" + "\n" + "y" + "\n" + "2"[m
             + "\n" + "2022-11-13" + "\n" + "100" + "\n" + "n" + "\n" + "5" + "\n"[m
[36m@@ -6205,7 +6211,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks total amount invested on certain date.[m
    */[m
   @Test[m
[31m-  public void testZZF() {[m
[32m+[m[32m  public void testZZFInvestmentsOnCertainDate() {[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest98" + "\n" + "1" + "\n" + "1"[m
             + "\n" + "2022-11-12" + "\n" + "50" + "\n" + "y" + "\n" + "2"[m
             + "\n" + "2022-11-13" + "\n" + "100" + "\n" + "n" + "\n" + "5" + "\n" + "18" + "\n"[m
[36m@@ -6371,10 +6377,12 @@[m [mpublic class ControllerTest extends TestParentClass {[m
 [m
 [m
   /**[m
[31m-   * This test checks purchase sell, sell in between.[m
[32m+[m[32m   * This test checks when a purchase and sell takes place of a particular stock[m
[32m+[m[32m   * and there are no stocks left to be sold. This test prints a message[m
[32m+[m[32m   * when no stocks are left.[m
    */[m
   @Test[m
[31m-  public void testZZG() {[m
[32m+[m[32m  public void testZZGPurchasedAndSoldAlreadySoldStocks() {[m
     String userInput = "1" + "\n" + "1" + "\n" + "controllerTest99" + "\n"[m
             + "1" + "\n" + "8" + "\n" + "2022-11-01" + "\n"[m
             + "600" + "\n" + "n" + "\n" + "5" + "\n" + "19" + "\n"[m
[36m@@ -6525,8 +6533,8 @@[m [mpublic class ControllerTest extends TestParentClass {[m
    * This test checks inflexible portfolio.[m
    */[m
   @Test[m
[31m-  public void testZZH() {[m
[31m-    String userInput = "1" + "\n" + "2" + "\n" + "controllerTest990" + "\n" + "1" + "\n"[m
[32m+[m[32m  public void testZZHInflexiblePortfolio() {[m
[32m+[m[32m    String userInput = "1" + "\n" + "2" + "\n" + "controllerTest9900" + "\n" + "1" + "\n"[m
             + "10" + "\n" + "80" + "\n" + "n" + "\n" + "e";[m
     InputStream input = new ByteArrayInputStream(userInput.getBytes());[m
     ByteArrayOutputStream bytes = new ByteArrayOutputStream();[m
[36m@@ -6546,7 +6554,7 @@[m [mpublic class ControllerTest extends TestParentClass {[m
             + "\n"[m
             + "CREATE PORTFOLIO MENU\n"[m
             + "\n"[m
[31m-            + "CONTROLLERTEST990 Portfolio\n"[m
[32m+[m[32m            + "CONTROLLERTEST9900 Portfolio\n"[m
             + "\n"[m
             + getBuyStockScreen()[m
             + "\n"[m
[36m@@ -6561,14 +6569,14 @@[m [mpublic class ControllerTest extends TestParentClass {[m
             + "StockName: Walmart\n"[m
             + "Symbol: WMT\n"[m
             + "Time: " + readStockDateFromStockDataCsv() + "\n"[m
[31m-            + "Price: $145.6100\n"[m
[32m+[m[32m            + "Price: $" + readStockPriceFromStockDataCsv() + "\n"[m
             + "\n"[m
             + "How many shares would you like to buy?\n"[m
             + "Press 'b' to go back to the previous menu, 'm' to main menu.\n"[m
             + "\n"[m
             + "Would you like to buy another stock? (Y|N)\n"[m
             + "\n"[m
[31m-            + "CONTROLLERTEST990 PORTFOLIO CREATED...!!!\n"[m
[32m+[m[32m            + "CONTROLLERTEST9900 PORTFOLIO CREATED...!!!\n"[m
             + getMainScreen()[m
             + "\n"[m
             + "ENTER YOUR CHOICE: \n"[m
[36m@@ -6579,45 +6587,13 @@[m [mpublic class ControllerTest extends TestParentClass {[m
     result = result.replace("\r\n", "\n");[m
 [m
     assertEquals(expectedOutput, result);[m
[32m+[m[32m    try {[m
[32m+[m[32m      input.close();[m
[32m+[m[32m      output.close();[m
[32m+[m[32m      bytes.close();[m
[32m+[m[32m    } catch (Exception e) {[m
[32m+[m[32m      //[m
[32m+[m[32m    }[m
   }[m
 [m
[31m-  private String getMainScreen() {[m
[31m-    return "\nMENU\n"[m
[31m-            + "\n"[m
[31m-            + "1. Create a portfolio\n"[m
[31m-            + "2. Value and Composition of portfolio\n"[m
[31m-            + "3. Value of portfolio on full composition\n"[m
[31m-            + "4. Add a stock to portfolio\n"[m
[31m-            + "5. Sell a stock from portfolio\n"[m
[31m-            + "6. Performance of portfolio\n"[m
[31m-            + "7. Total amount invested on certain date\n"[m
[31m-            + "e. Exit\n";[m
[31m-  }[m
[31m-[m
[31m-  private String getSupportedStocks() {[m
[31m-    return "1. Apple (AAPL)\n"[m
[31m-            + "2. Amazon (AMZN)\n"[m
[31m-            + "3. Google (GOOG)\n"[m
[31m-            + "4. Johnson (JNJ)\n"[m
[31m-            + "5. JPMorgan Chase (JPM)\n"[m
[31m-            + "6. Meta (META)\n"[m
[31m-            + "7. Microsoft (MSFT)\n"[m
[31m-            + "8. Tesla (TSLA)\n"[m
[31m-            + "9. UnitedHealth (UNH)\n"[m
[31m-            + "10. Walmart (WMT)";[m
[31m-  }[m
[31m-[m
[31m-  private String getBuyStockScreen() {[m
[31m-    return "1. Buy a stock\n"[m
[31m-            + "2. Main Menu\n"[m
[31m-            + "e. Exit\n";[m
[31m-  }[m
[31m-[m
[31m-  private String getFlexibleInflexibleScreen() {[m
[31m-    return "What type of portfolio would you like to create?\n"[m
[31m-            + "\n"[m
[31m-            + "1. Flexible / Customizable Portfolio\n"[m
[31m-            + "2. Inflexible / Non Customizable Portfolio\n";[m
[31m-  }[m
[31m-[m
[31m-}[m
[32m+[m[32m}[m
\ No newline at end of file[m
