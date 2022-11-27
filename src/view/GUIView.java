package view;

import controller.ControllerViewInteract;
import controller.Features;

public interface GUIView {
  void addFeatures(Features feature);
  void displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
  void errorReenterName();
  void errorNotNumber();
  int jOptionPortfolioAlreadyExists();
  void flexiblePortfolioScreenWithDateInput();
  void resetFlexibleScreen();


  void displayStockDataUsingJOption(String stockName, String symbol, String time, String pric);

}
