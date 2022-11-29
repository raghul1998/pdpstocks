package view;

import java.util.Map;

import controller.Features;

public interface GUIView {
  void addFeatures(Features feature);
  void displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
  int jOptionPortfolioAlreadyExists();
  void flexiblePortfolioScreenWithDateInput(String name);
  void inflexiblePortfolioScreen();
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
  void resetDateInput();
  void resetHowManyShares();
 //  void displayAddScreen();
  void displaySellScreen();
  void displayListOfPortfolioScreen(String[] getListOfPortfolioNames);
  void displayAddStocksUsingDollarStrategyMain(String[] displayString);
}
