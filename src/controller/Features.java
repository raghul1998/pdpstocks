package controller;

import view.GUIView;

public interface Features {
  void setView(GUIView guiView);
  void createAPortfolioGUI();
  void flexibleScreenSubmit(int type, String name, int stockSelected);
  void exitProgram();


}