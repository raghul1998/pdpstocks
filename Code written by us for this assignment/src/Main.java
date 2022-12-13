import controller.MVCCommandController;
import model.MVCModel;
import view.MVCCommandView;
import view.MVCView;

/**
 * This class represents main which is the start point of the program.
 */
public class Main {

  /**
   * This method calls model, view and controller to execute the program.
   *
   * @param args arguments for main method
   */
  public static void main(String[] args) {
    MVCView view = new MVCCommandView();
    MVCModel model = new MVCModel();
    MVCCommandController controller = new MVCCommandController(model, view);
    controller.chooseGUI();
  }
}
