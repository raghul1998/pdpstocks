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
  void inflexiblePortfolioScreen();
  void resetCreatePortfolioScreen();

  void resetFlexiblePortfolioScreen();
  void displayStockDataUsingJOption(String stockName, String symbol, String time, String price);

  void displayBoughtSuccessfulAndWouldLikeToBuyAgainButtonWindow(String portfolioName);
  void invalidDate();
  void resetDateInput();

  void resetHowManyShares();

  void noOfSharesNotEntered();

  void displayBoughtSuccessfulScreenForAnotherBoughtStock();
}
