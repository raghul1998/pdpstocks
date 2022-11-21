package view;

import java.util.Map;

/**
 * This interface represents the view that gets commands from the controller and the methods that
 * are called up based on the command provided by the controller.
 */
public interface ViewControllerInteract {
  /**
   * This method helps to dispatch the operations to the corresponding methods based on the
   * command received from the controller.
   *
   * @param type   the type of view to be rendered
   * @param args   helper arguments if any provided by the controller
   * @param length the length of the arguments
   */
  void viewControllerInteract(TypeofViews type, String[] args, int length);

  void portfolioPerformanceOverTime(String[] args, int length,
                                    Map<String, Double> pfPerformance,
                                    String[] scale, String getTitle);
}