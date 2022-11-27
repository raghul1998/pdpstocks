package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Features;

public class JFrameViewImpl extends JFrame implements GUIView {
  JComboBox comboBoxMainMenu, comboBoxTypeOfPortfolio, comboBoxSupportedStocks;
  JPanel cards = new JPanel();  // default screen
  JPanel card1 = new JPanel();  // main menu screen which is the default screen
  JPanel card2 = new JPanel();  // type of portfolio screen
  JPanel card3 = new JPanel();
  CardLayout c1 = new CardLayout();
  private JLabel display1, display2, display3, display4,display5, display6, display7;
  private JTextField inputName, setPurchaseDate, howManyShares;
  private JButton exitButton1, exitButton2, submitButton1, submitButton2, backButton1;

  // constructor
  public JFrameViewImpl(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(500, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    cards.setLayout(c1);

    // card1
    // main menu dropdown box
    display1 = new JLabel("Main Menu");
    String[] mainMenuOptions = {"Create Portfolio",
            "Value and Composition of Portfolio",
            "Value of portfolio on full composition",
            "Add a stock to portfolio",
            "Sell a stock from portfolio",
            "Performance of portfolio",
            "Total amount invested on certain date",
            "Configure the commission cost"};
    comboBoxMainMenu = new JComboBox(mainMenuOptions);
    comboBoxMainMenu.setSelectedIndex(-1);


    exitButton1 = new JButton("Exit");
    submitButton1 = new JButton("Submit");
    submitButton2 = new JButton("Submit");
    backButton1 = new JButton("Back");

    card1.setLayout(new FlowLayout());


    card1.add(display1);
    card1.add(comboBoxMainMenu);
    card1.add(exitButton1);
    cards.add(card1, "mainMenu");

    c1.show(cards, "mainMenu");

    this.add(cards);
    //this.pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(Features feature) {
    comboBoxMainMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(comboBoxMainMenu.getSelectedIndex()==0) {
          feature.createAPortfolioGUI();
        }
        else if(comboBoxMainMenu.getSelectedIndex()==1){
          feature.valueAndCompositionGUI();
        }
//        else if(comboBoxMainMenu.getSelectedIndex()==2){
//          c1.show(cards,"listOfPortfolioScreen");
//        }
//        else if(comboBoxMainMenu.getSelectedIndex()==3){
//          c1.show(cards,"listOfPortfolioScreenWithoutInflex");
//        }
//        else if(comboBoxMainMenu.getSelectedIndex()==4){
//          c1.show(cards,"listOfPortfolioScreenWithoutInflex");
//        }
//        else if(comboBoxMainMenu.getSelectedIndex()==5){
//          c1.show(cards,"listOfPortfolioScreenWithoutInflex");
//        }
//        else if(comboBoxMainMenu.getSelectedIndex()==6){
//          c1.show(cards,"listOfPortfolioScreenWithoutInflex");
//        }
//        else {
//          c1.show(cards,"commissionCostScreen");
//        }
      }
    });
    //comboBoxMainMenu.addActionListener(evt -> feature.createAPortfolioGUI());
    submitButton1.addActionListener(evt -> feature.CreatePortfolioScreenSubmit(comboBoxTypeOfPortfolio.getSelectedIndex(),
            inputName.getText(),comboBoxSupportedStocks.getSelectedIndex()));
    submitButton2.addActionListener(evt -> feature.buyStockSubmit());
    exitButton1.addActionListener(evt -> feature.exitProgram());
    //backButton1.addActionListener(evt -> c1.show(cards,"screen2"));
    //exitButton2.addActionListener(evt -> feature.exitProgram());
  }

  @Override
  public void displayTypeOfPortfolioFlexibleOrInFlexibleScreen() {
    // card2
    // type of portfolio
    display2 = new JLabel("Select Type of Portfolio");
    String[] typeOfPortfolio = {"Flexible",
            "Inflexible"};
    comboBoxTypeOfPortfolio = new JComboBox(typeOfPortfolio);
    comboBoxTypeOfPortfolio.setSelectedIndex(-1);

    display3 = new JLabel("write the name of the portfolio");
    inputName = new JTextField(10);

    display4 = new JLabel("Select options");

    String[] options = {"Buy a Stock",
    "Invest by dollar cost averaging"};

    comboBoxSupportedStocks = new JComboBox(options);
    comboBoxSupportedStocks.setSelectedIndex(-1);
    exitButton2 = new JButton("Exit");
    card2.setLayout(new FlowLayout());
    card2.add(display2);
    card2.add(comboBoxTypeOfPortfolio);
    card2.add(display3);
    card2.add(inputName);
    card2.add(display4);
    card2.add(comboBoxSupportedStocks);
    card2.add(submitButton1);
    card2.add(exitButton2);
    cards.add(card2, "screen2");
    c1.show(cards, "screen2");
  }

  @Override
  public void errorReenterName() {
    JOptionPane.showMessageDialog(cards,
            "Name cannot be empty. Please Re-enter name",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }

  @Override
  public int jOptionPortfolioAlreadyExists() {
    Object[] options = {"Yes, please",
            "No way!"};
    int yesToOverride = JOptionPane.showOptionDialog(cards,
            "The portfolio with this name already exists. Do you want to override?",
            "Question",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
    return yesToOverride;

  }


  @Override
  public void screen3() {
    // card 6
    // Flexible Screen
    //displayEnterDate = new JLabel("Please Enter Date");
    display5 = new JLabel("Enter date");
    setPurchaseDate = new JTextField(10);
    display6 = new JLabel("Select stock from supported list of stocks");
    // review -  hardcoded
    // todo - read it from csv and store in supportedStocks
    String[] supportedStocks = {"1. Microsoft (MSFT)",
            "2. Meta (META)",
            "3. Google (GOOG)",
            "4. Apple (AAPL)",
            "5. Tesla (TSLA)",
            "6. JPMorgan Chase (JPM)",
            "7. Johnson (JNJ)",
            "8. Amazon (AMZN)",
            "9. UnitedHealth (UNH)",
            "10. Walmart (WMT)"};
    comboBoxSupportedStocks = new JComboBox(supportedStocks);
    display7 = new JLabel("How many shares would you like to buy");
    howManyShares = new JTextField(10);
    submitButton2 = new JButton("Submit");
    backButton1 = new JButton("Back");
    card3.add(display5);
    card3.add(setPurchaseDate);
    card3.add(display6);
    card3.add(comboBoxSupportedStocks);
    card3.add(display7);
    card3.add(howManyShares);
    card3.add(submitButton2);
    card3.add(backButton1);
    cards.add(card3,"screen3");
    c1.show(cards,"screen3");
  }

  @Override
  public void resetFlexibleScreen() {
    comboBoxTypeOfPortfolio.setSelectedIndex(-1);
    inputName.setText("");
    comboBoxSupportedStocks.setSelectedIndex(-1);
  }
}
