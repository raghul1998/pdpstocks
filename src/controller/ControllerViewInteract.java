package controller;

import view.TypeofViews;
import view.ViewControllerInteractImpl;

public interface ControllerViewInteract {
  void controllerViewInteract(ViewControllerInteractImpl viewControllerInteract, char options, TypeofViews type, String[] args, int length);
}
