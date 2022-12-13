package view;

/**
 * Class that interacts with the controller and provides access
 * to relevant methods in view package.
 */
public class MVCCommandView implements MVCView {

  PortfolioCompositionExaminer examiner = new PortfolioCompositionExaminer();
  PortfolioPerformanceGraph performanceGraph = new PortfolioPerformanceGraph();
  TotalValueCounter tvc = new TotalValueCounter();

  public void examinePortfolioComposition(String path, String date) {
    examiner.examinePortfolioComposition(path, date);
  }

  public void displayTotalValueOfPortfolio(String path, String date) {
    tvc.displayTotalValueOfPortfolio(path, date);
  }

  public void viewPerformance(String path, String date) {
    performanceGraph.viewPerformance(path, date);
  }
}
