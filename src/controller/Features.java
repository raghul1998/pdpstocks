package controller;

import view.GUIView;

public interface Features {
  void setView(GUIView guiView);
  void createAPortfolioGUI();
  void valueAndCompositionGUI();
  void createPortfolioScreenSubmit(int type, String name, int optionSelected);
  void checkCurrentPrice(String name, int stockSelected);
  void exitProgram();


}