import controller.ControllerModelInteract;
import controller.ControllerModelInteractImpl;
import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;
import model.ModelControllerInteract;
import model.ModelControllerInteractImpl;
import view.ViewControllerInteract;
import view.ViewControllerInteractImpl;

public class Main {
  public static void main(String[] args) {
    ControllerViewInteract obj = new ControllerViewInteractImpl();
    obj.start();
  }
}
