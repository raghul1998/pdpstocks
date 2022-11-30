package view;

import java.util.Map;

import controller.Features;

public interface GUIView {
  void addFeatures(Features feature);
  void displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
  int jOptionPortfolioAlreadyExists();
  void flexiblePortfolioScreenWithDateInput(String[] supportedStocks, String name);
  void inflexiblePortfolioScreen(String[] supportedStocks);
  void displayPerformanceOfPortfolio(String[] displayString);
  void resetMainMenu();
  void performanceDateEnter(int timestampType);
  void displayErrorMessage(String error);
  void displayInformationalMessage(String info);
  void displayPerformanceNumberOfDaysInput(String str);
  void getGraph(Map<String, Double> pfPerformance, String pfName);
  void resetCreatePortfolioScreen();
  void resetFlexiblePortfolioScreen();
  void displayBoughtSuccessfulAndWouldLikeToBuyAgainButtonWindow(String portfolioName);
  //void resetDateInput();
  void resetHowManyShares();
 //  void displayAddScreen();
  void displaySellScreen();
  void displayListOfPortfolioScreen(String[] getListOfPortfolioNames);
  void displayAddStocksUsingDollarStrategyMain(String[] displayString);
  void resetDollarValueMainScreen();

  void dollarValueOnGoingScreen();

  void dollarValueEndDateScreen(String str);

  void dollarValueFrequencyEnterScreen(String str);

  void dollarValueHowManyStocksScreen(String buyStr);

  void dollarValueStockProportionScreen(int numberOfStocks, String[] supportedStocks);

  void valueAndCompScreenOne(String[] displayString);

  void valueAndCompScreenInflexibleResult(String title, String[] column,
                                          String[][] data, String subText);

  void valueAndCompFlexDateScreen();

  void displayValueCompForCost(String title, String[] costData);

  void displayValueCompForOthers(String title, String[] column, String[][] data, String footer);

  void valueOnFullCompScreenOne(String[] displayString);

  void totalCostInvestedByDateScreenOne(String[] listOfPortfolioNames);

  //void soldSuccessfulScreen();
}
