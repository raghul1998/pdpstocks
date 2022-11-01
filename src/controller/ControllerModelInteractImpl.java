package controller;

import model.ModelControllerInteract;
import model.ModelControllerInteractImpl;
import model.TypeofAction;

public class ControllerModelInteractImpl implements ControllerModelInteract {
  private final ModelControllerInteract mciObj = new ModelControllerInteractImpl();

  @Override
  public void controllerModelInteract(TypeofAction type, String[] args, int length) {
    switch (type) {
      case BUY_STOCKS:
      case GET_STOCK_DATA:
      case CREATE_PORTFOLIO:
      case DELETE_EMPTY_PORTFOLIO:
      case CREATE_SUPPORTED_STOCKS: {
        mciObj.modelControllerInteract(type, args, length);
        break;
      }
      default: {
        break;
      }
    }
  }
}
