package view;

/**
 * Interface which declares all the required methods for view module.
 */
public interface MVCView {
  /**
   * Displays the composition of the portfolio on a certain date.
   *
   * @param path Path of the portfolio file.
   * @param date date on which the value of portfolio is needed.
   */
  void examinePortfolioComposition(String path, String date);

  /**
   * This method displays total value of the portfolio on a certain date.
   *
   * @param path input path
   * @param date input date
   */
  void displayTotalValueOfPortfolio(String path, String date);

  /**
   * Displays the performance of the portfolio as a graph.
   *
   * @param path Path of the portfolio file.
   * @param date date on which the value of portfolio is needed.
   */

  void viewPerformance(String path, String date);
}
