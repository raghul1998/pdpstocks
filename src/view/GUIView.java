package view;

import java.util.Map;

import controller.Features;

public interface GUIView {
  void addFeatures(Features feature);
  void displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
  int jOptionPortfolioAlreadyExists();
  void flexiblePortfolioScreenWithDateInput();
  void resetFlexibleScreen();
  void displayStockDataUsingJOption(String stockName, String symbol, String time, String price);
  void displayPerformanceOfPortfolio(String[] displayString);
  void resetMainMenu();
  void performanceDateEnter(int timestampType);

  void displayErrorMessage(String error);
  void displayInformationalMessage(String info);

  void displayPerformanceNumberOfDaysInput(String str);

  void getGraph(Map<String, Double> pfPerformance, String pfName);
}
