package controller;

import model.ModelControllerInteract;
import model.ModelControllerInteractImpl;
import model.TypeofAction;

public class ControllerModelInteractImpl implements ControllerModelInteract {
  ModelControllerInteract mciObj = new ModelControllerInteractImpl();

  @Override
  public void controllerModelInteract(TypeofAction type, String[] args, int length) {
    switch (type) {
      case BUY_STOCKS: {
        // Buy a stock
        mciObj.modelControllerInteract(type);
        break;
      }
      default: {
        break;
      }
    }
  }
}
