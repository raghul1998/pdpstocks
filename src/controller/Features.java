package controller;

import view.GUIView;

public interface Features {
  void setView(GUIView guiView);
  void createAPortfolioGUI();
  void valueAndCompositionGUI();
  void CreatePortfolioScreenSubmit(int type, String name, int stockSelected);
  void buyStockSubmit();
  void exitProgram();


}