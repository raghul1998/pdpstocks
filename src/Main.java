import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import controller.ControllerViewInteract;
import controller.ControllerViewInteractImpl;

public class Main {
  public static void main(String[] args) {
    ControllerViewInteract obj = new ControllerViewInteractImpl(System.in, System.out);
    obj.start();
  }
}
