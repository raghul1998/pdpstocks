package view;

import java.util.Map;

import controller.Features;

/**
 * This interface represents the controller that interacts with the model and the set of methods
 * that can be called upon to interact with the model.
 */
public interface GUIView {

  /**
   * This method adds features to the components of the panel such as buttons and text fields.
   * It consists of collection of actionListeners of all the components and
   * performs action based on the type of action provided.
   *
   * @param feature type of feature provided as per the component.
   */
  void addFeatures(Features feature);

  /**
   * This method gets the type of portfolio(Flexible or Inflexible), name of the portfolio
   * from the user and asks the user to opt for buying or investing in the portfolio.
   * When the user selects to create portfolio, this is the first screen that gets displayed
   * by the view.
   *
   */
  void displayTypeOfPortfolioFlexibleOrInFlexibleScreen();

  /**
   * This method pops up a dialog box to get input from the user if the user wants
   * to override the portfolio or not.
   *
   */
  int jOptionPortfolioAlreadyExists();

  /**
   * This method views the user to select stocks from the supported list of stocks and
   *
   * @param supportedStocks   the type of action for the model to perform
   * @param name   arguments to be passed to the model
   */
  void flexiblePortfolioScreenWithDateInput(String[] supportedStocks, String name);

  /**
   * This method helps in 
   *
   * @param supportedStocks   the type of action for the model to perform
   */
  void inflexiblePortfolioScreen(String[] supportedStocks);

  /**
   * This method helps in 
   *
   * @param displayString   the type of action for the model to perform
   */
  void displayPerformanceOfPortfolio(String[] displayString);

  /**
   * This method helps in 
   *
   */
  void resetMainMenu();

  /**
   * This method helps in 
   *
   * @param timestampType   the type of action for the model to perform
   */
  void performanceDateEnter(int timestampType);

  /**
   * This method helps in 
   *
   * @param error   the type of action for the model to perform
   */
  void displayErrorMessage(String error);

  /**
   * This method helps in 
   *
   * @param info   the type of action for the model to perform
   */
  void displayInformationalMessage(String info);

  /**
   * This method helps in 
   *
   * @param str   the type of action for the model to perform
   */
  void displayPerformanceNumberOfDaysInput(String str);

  /**
   * This method helps in 
   *
   * @param pfPerformance   the type of action for the model to perform
   * @param pfName   arguments to be passed to the model
   */
  void getGraph(Map<String, Double> pfPerformance, String pfName);

  /**
   * This method helps in 
   *
   */
  void resetCreatePortfolioScreen();

  /**
   * This method helps in 
   *
   */
  void resetFlexiblePortfolioScreen();

  /**
   * This method helps in 
   *
   * @param showSuccessMsg   the type of action for the model to perform
   */
  void displayBoughtSuccessfulAndWouldLikeToBuyAgainButtonWindow(String showSuccessMsg);

  /**
   * This method helps in 
   *
   */
  void resetHowManyShares();

  /**
   * This method helps in 
   *
   */
  void displaySellScreen();

  /**
   * This method helps in 
   *
   * @param getListOfPortfolioNames   the type of action for the model to perform
   */
  void displayListOfPortfolioScreen(String[] getListOfPortfolioNames);

  /**
   * This method helps in 
   *
   * @param displayString   the type of action for the model to perform
   */
  void displayAddStocksUsingDollarStrategyMain(String[] displayString);

  /**
   * This method helps in 
   *
   */
  void resetDollarValueMainScreen();

  /**
   * This method helps in 
   *
   */

  void dollarValueOnGoingScreen();

  /**
   * This method helps in 
   *
   * @param str   the type of action for the model to perform
   */
  void dollarValueEndDateScreen(String str);

  /**
   * This method helps in 
   *
   * @param str   the type of action for the model to perform
   */
  void dollarValueFrequencyEnterScreen(String str);

  /**
   * This method helps in 
   *
   * @param buyStr   the type of action for the model to perform
   */
  void dollarValueHowManyStocksScreen(String buyStr);

  /**
   * This method helps in 
   *
   * @param numberOfStocks   the type of action for the model to perform
   * @param supportedStocks   arguments to be passed to the model
   *
   */
  void dollarValueStockProportionScreen(int numberOfStocks, String[] supportedStocks);

  /**
   * This method helps in 
   *
   * @param displayString   the type of action for the model to perform
   */
  void valueAndCompScreenOne(String[] displayString);

  /**
   * This method helps in 
   *
   * @param title   the type of action for the model to perform
   * @param column   arguments to be passed to the model
   * @param data length of the arguments
   * @param subText length of the arguments
   */
  void valueAndCompScreenInflexibleResult(String title, String[] column,
                                          String[][] data, String subText);

  /**
   * This method helps in 
   *
   */
  void valueAndCompFlexDateScreen();

  /**
   * This method helps in 
   *
   * @param title   the type of action for the model to perform
   * @param costData   arguments to be passed to the model
   */
  void displayValueCompForCost(String title, String[] costData);

  /**
   * This method helps in 
   *
   * @param title   the type of action for the model to perform
   * @param column   arguments to be passed to the model
   * @param data length of the arguments
   * @param footer length of the arguments
   */
  void displayValueCompForOthers(String title, String[] column, String[][] data, String footer);

  /**
   * This method helps in 
   *
   * @param displayString   the type of action for the model to perform
   */
  void valueOnFullCompScreenOne(String[] displayString);

  /**
   * This method helps in 
   *
   * @param listOfPortfolioNames   the type of action for the model to perform
   */
  void totalCostInvestedByDateScreenOne(String[] listOfPortfolioNames);

  /**
   * This method helps in 
   *
   * @param listOfAvailableStocksForSale   the type of action for the model to perform
   */
  void displaySellScreen2(String[] listOfAvailableStocksForSale);

  /**
   * This method helps in 
   *
   */
  void buyAnotherReset();

  /**
   * This method helps in 
   *
   */
  void resetStockSellScreenAfterSell();
}
