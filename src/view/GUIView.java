package view;

import controller.ControllerViewInteract;
import controller.Features;

public interface GUIView {
  void addFeatures(Features feature);
  void displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
  void errorReenterName();
  int jOptionPortfolioAlreadyExists();
  void screen3();
}
