package controller;

import javax.swing.*;

import view.GUIView;

public interface Features {
  void setView(GUIView guiView);
  void createAPortfolioGUI();
  void valueAndCompositionGUIMainScreen();
  void createPortfolioScreenSubmit(int type, String name, int optionSelected);
  void performanceOfPortfolioMain();
  void performancePortfolioMainMenu(int pfIndex, int timestampType);
  void checkCurrentPrice(int type, String name, int stockSelected);
  void exitProgram();
  void performancePortfolioSubmit(String date, int timestampType);
  void performanceOfPortfolioGetData(String text);
  void buyStockSubmit(String date, int stockSelected, String noOfStocks, String portfolioName);
  void buyAnotherStockButton(String portfolioName);
  void checkHowManyShares();
  void selectPortfolio();
  //void addStockSubmit(String date, int stockSelected, String noOfStocks, String portfolioName);
  void sellStock(String date, int stockSelected, String noOfStocks, int pfNumber);
  void selectStockSubmit(int buyOrSell, int portfolioName);
  void addStocksUsingDollarMain();
  void dollarValueScreenOne(int pfIndex, String date, int recurIndex);

  void dollarValueScreenTwo(int onGoingIndex);

  void dollarValueScreenThreeEndDate(String endDate);

  void dollarValueScreenFourFrequency(String frequency);

  void dollarValueScreenFiveHowManyShares(String numberOfStocks);

  void dollarValueScreenSixBuyStocks(String price, JSpinner[] spinner,
                                     JComboBox[] comboSupportStocksArray);
  void valueAndCompositionScreenOne(int pfIndex);

  void resetMainMenu();
  void valueAndCompositionFlexDateScreen(String date);

  void valueOnFullCompMainScreen();

  void valueOnFullScreenOne(int pfIndex, String date);

  void totalCostInvestedByDateMainMenu();

  void totalCostInvestedScreenOne(int pfIndex, String date);
}