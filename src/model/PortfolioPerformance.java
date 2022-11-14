package model;

import java.io.IOException;
import java.util.Map;

/**
 * This interface represents the portfolio and the data available in the portfolio. This interface
 * helps in obtaining the performance data from the portfolio.
 */
public interface PortfolioPerformance {
  /**
   * This method obtains the complete performance data of the portfolio on the given date.
   *
   * @param args   the arguments required for computing the portfolio performance
   * @param length the length of the arguments
   * @return the map of the date and value of the portfolio on that date
   * @throws IOException if there is an error in IO operation
   */
  Map<String, Double> computePerformanceData(String[] args, int length) throws IOException;
}
