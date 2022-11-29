package controller;

import view.GUIView;

public interface Features {
  void setView(GUIView guiView);
  void createAPortfolioGUI();
  void valueAndCompositionGUI();
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
  void sellStock();
  void selectStockSubmit(int portfolioName);
  void addStocksUsingDollarMain();
  void dollarValueScreenOne(int pfIndex, String date, int recurIndex);
}