package view;

/*import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;*/

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.*;

import controller.Features;

public class JFrameViewImpl extends JFrame implements GUIView {
  private ViewControllerInteract vciObj;
  private JComboBox comboBoxMainMenu, comboBoxTypeOfPortfolio, comboBoxSupportedStocks,
          comboBoxBuyOrInvest, comboBoxListOfPortfolios, comboBoxSupportedStocks1;
  private JComboBox comboCommon, combo2;
  private JPanel cards;  // default screen
  private JPanel card1;  // main menu screen which is the default screen
  private JPanel card2;  // type of portfolio screen
  private JPanel card3;
  private JPanel card4;
  private JPanel card5;
  private JPanel card6_showListOfStocks;
  private JPanel card7_showAddScreen;
  private CardLayout c1 = new CardLayout();
  private JLabel display1, display2, display3, display4,
          display5, display6, display7, display8, display9, display10,
          display11, display12, display13, display14, display15;
  ;
  private JTextField inputName, setPurchaseDate, howManyShares, howManyShares1;
  private JTextField inputDate, date;
  private JButton exitButton1, exitButton2, submitButton1, submitButton2, submitButton3,
          backButton1, checkPrice, checkPrice1, buyAnotherButton, goBackMainMenu, selectButton,
          addButton;
  private JButton mainMenuButton;
  private JButton pfPerformanceButton;
  private JButton pfPerformanceButtonSubmit;
  private JTextField inputRemainderNumberForPfPerformance;
  private JButton pfPerformanceButtonGetData;

  private JButton dollarCostMainButton;

  //  UtilDateModel model1 = new UtilDateModel();
  //  Properties p = new Properties();
  //  p.put("text.today", "Today");
  //  p.put("text.month", "Month");
  //  p.put("text.year", "Year");
  //  JDatePanelImpl datePanel = new JDatePanelImpl(model1, p);
  //  // Don't know about the formatter, but there it is...
  //  JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
  // cards.add(datePicker);


  // constructor
  public JFrameViewImpl(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(500, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    cards = new JPanel();
    card1 = new JPanel();
    card2 = new JPanel();
    card3 = new JPanel();
    card4 = new JPanel();
    card5 = new JPanel();
    card6_showListOfStocks = new JPanel();
    card7_showAddScreen = new JPanel();
    c1 = new CardLayout();
    pfPerformanceButton = new JButton("Next");
    dollarCostMainButton = new JButton("Next");
    mainMenuButton = new JButton("Main Menu");
    pfPerformanceButtonSubmit = new JButton("Next");
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
            "Configure the commission cost",
            "Add stocks to portfolio using Dollar-Cost strategy"};

    comboBoxMainMenu = new JComboBox(mainMenuOptions);
    comboBoxMainMenu.setSelectedIndex(-1);

    checkPrice = new JButton("Check Price");
    checkPrice1 = new JButton("Check Price");
    exitButton1 = new JButton("Exit");
    exitButton2 = new JButton("Exit");
    submitButton1 = new JButton("Submit");
    submitButton2 = new JButton("Submit");
    submitButton3 = new JButton("Submit");
    selectButton = new JButton("Select");
    backButton1 = new JButton("Back");
    addButton = new JButton("Add");
    pfPerformanceButtonGetData = new JButton("Submit");
    buyAnotherButton = new JButton("Buy Another");
    goBackMainMenu = new JButton("Go Back to Main Menu");
    howManyShares = new JTextField(10);
    howManyShares1 = new JTextField(10);
    card1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
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
    dollarCostMainButton.addActionListener(e-> {
      feature.dollarValueScreenOne(comboCommon.getSelectedIndex(), inputDate.getText(),
              combo2.getSelectedIndex());
    });

    mainMenuButton.addActionListener(e -> {
      resetMainMenu();
      c1.first(cards);
    });

    comboBoxMainMenu.addActionListener(e -> {
      if (comboBoxMainMenu.getSelectedIndex() == 0) {
        feature.createAPortfolioGUI();
      } else if (comboBoxMainMenu.getSelectedIndex() == 1) {
        feature.valueAndCompositionGUI();
      }
//        else if(comboBoxMainMenu.getSelectedIndex()==2){
//          c1.show(cards,"listOfPortfolioScreen");
//        }
      else if(comboBoxMainMenu.getSelectedIndex()==3) {
        feature.selectPortfolio();
      }
//        }
//        else if(comboBoxMainMenu.getSelectedIndex()==4){
//          c1.show(cards,"listOfPortfolioScreenWithoutInflex");
//        }
      else if (comboBoxMainMenu.getSelectedIndex() == 5) {
        feature.performanceOfPortfolioMain();
      } else if (comboBoxMainMenu.getSelectedIndex() == 8) {
        feature.performanceOfPortfolioMain();
      }
//        else if(comboBoxMainMenu.getSelectedIndex()==6){
//          c1.show(cards,"listOfPortfolioScreenWithoutInflex");
//        }
//        else {
//          c1.show(cards,"commissionCostScreen");
//        }
    });
    //comboBoxMainMenu.addActionListener(evt -> feature.createAPortfolioGUI());
    submitButton1.addActionListener(evt -> feature.createPortfolioScreenSubmit(
            comboBoxTypeOfPortfolio.getSelectedIndex(),
            inputName.getText(), comboBoxBuyOrInvest.getSelectedIndex()));
    submitButton2.addActionListener(evt -> feature.buyStockSubmit(setPurchaseDate.getText(),
            comboBoxSupportedStocks.getSelectedIndex(), howManyShares.getText(), inputName.getText()));

    exitButton1.addActionListener(evt -> feature.exitProgram());
    exitButton2.addActionListener(evt -> feature.exitProgram());
    selectButton.addActionListener(evt-> feature.selectStockSubmit(comboBoxListOfPortfolios.getName()));
    addButton.addActionListener(evt-> feature.addStockSubmit(date.getText(),
            comboBoxSupportedStocks1.getSelectedIndex(), howManyShares1.getText(),comboBoxListOfPortfolios.getName()));


    // selectButton.addActionListener(evt->feature.sellStock());
    //buyAnotherButton.addActionListener(evt-> feature.buyAnotherStockButton(inputName.getText()));
    buyAnotherButton.addActionListener(evt -> {
      resetFlexiblePortfolioScreen();
      c1.previous(cards);
    });
    /*buyAnotherButton.addActionListener(evt-> feature.createPortfolioScreenSubmit(
            comboBoxTypeOfPortfolio.getSelectedIndex(),
            inputName.getText(),comboBoxBuyOrInvest.getSelectedIndex()));*/

    checkPrice.addActionListener(evt ->
            feature.checkCurrentPrice(comboBoxTypeOfPortfolio.getSelectedIndex(),
                    setPurchaseDate.getText(), comboBoxSupportedStocks.getSelectedIndex() + 1));

    checkPrice1.addActionListener(evt ->
            feature.checkCurrentPrice(comboBoxTypeOfPortfolio.getSelectedIndex(),
                    setPurchaseDate.getText(), comboBoxSupportedStocks.getSelectedIndex() + 1));
    howManyShares.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent ke) {
        //String value = howManyShares.getText();
        //int l = value.length();
      }

      @Override
      public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
          howManyShares.setEditable(true);
        } else if (ke.getKeyChar() == '\b') {
          feature.checkHowManyShares();
        } else {
          feature.checkHowManyShares();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });

    howManyShares1.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent ke) {
        //String value = howManyShares.getText();
        //int l = value.length();
      }

      @Override
      public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
          howManyShares1.setEditable(true);
        } else if (ke.getKeyChar() == '\b') {
          feature.checkHowManyShares();
        } else {
          feature.checkHowManyShares();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }
    });

    pfPerformanceButton.addActionListener(evt ->
            feature.performancePortfolioMainMenu(comboCommon.getSelectedIndex(),
                    combo2.getSelectedIndex()));

    pfPerformanceButtonSubmit.addActionListener(evt ->
            feature.performancePortfolioSubmit(inputDate.getText(), combo2.getSelectedIndex()));

    pfPerformanceButtonGetData.addActionListener(evt ->
            feature.performanceOfPortfolioGetData(inputRemainderNumberForPfPerformance.getText()));

    //backButton1.addActionListener(evt -> c1.show(cards,"screen2"));
  }

  // card2
  @Override
  public void displayTypeOfPortfolioFlexibleOrInFlexibleScreen() {

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

    comboBoxBuyOrInvest = new JComboBox(options);
    comboBoxBuyOrInvest.setSelectedIndex(-1);
    card2.setLayout(new FlowLayout());
    card2.add(display2);
    card2.add(comboBoxTypeOfPortfolio);
    card2.add(display3);
    card2.add(inputName);
    card2.add(display4);
    card2.add(comboBoxBuyOrInvest);
    card2.add(submitButton1);
    card2.add(exitButton2);
    cards.add(card2, "screen2");
    c1.show(cards, "screen2");
  }

  // card 3
  @Override
  public void flexiblePortfolioScreenWithDateInput() {
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
    //howManyShares = new JTextField(10);
    comboBoxSupportedStocks.setSelectedIndex(-1);

    card3.add(display5);
    card3.add(setPurchaseDate);
    card3.add(display6);
    card3.add(comboBoxSupportedStocks);
    card3.add(checkPrice);
    card3.add(display7);
    card3.add(howManyShares);
    card3.add(submitButton2);
    card3.add(backButton1);
    cards.add(card3, "screen3");
    c1.show(cards, "screen3");
  }

  // card 4

  @Override
  public void displayBoughtSuccessfulAndWouldLikeToBuyAgainButtonWindow(String portfolioName) {
    display8 = new JLabel(portfolioName + " Portfolio Created");
    display9 = new JLabel("Bought Successful");
    card4.add(display8);
    card4.add(display9);
    card4.add(buyAnotherButton);
    card4.add(goBackMainMenu);
    cards.add(card4, "screen4");
    c1.show(cards, "screen4");
  }

  // card 5

  @Override
  public void inflexiblePortfolioScreen() {
    // inflexible Screen

    display10 = new JLabel("Select stock from supported list of stocks");
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
    display11 = new JLabel("How many shares would you like to buy");
    //howManyShares = new JTextField(10);
    //  comboBoxSupportedStocks.setSelectedIndex(-1);

    card5.add(display10);
    card5.add(comboBoxSupportedStocks);
    card5.add(checkPrice);
    card5.add(display11);
    card5.add(howManyShares);
    card5.add(submitButton3);
    card5.add(backButton1);
    cards.add(card5, "screen5");
    c1.show(cards, "screen5");

  }


  @Override
  public void resetDateInput() {
    setPurchaseDate.setText("");
  }

  @Override
  public int jOptionPortfolioAlreadyExists() {
    Object[] options = {"Yes",
            "No"};
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
  public void displayPerformanceOfPortfolio(String[] displayString) {
    JLabel displayCommon = new JLabel("Select the portfolio");
    comboCommon = new JComboBox(displayString);
    comboCommon.setSelectedIndex(-1);

    display3 = new JLabel("Select the type of timestamp");
    String[] choice = {
            "Year", "Month", "Date"
    };
    combo2 = new JComboBox(choice);
    combo2.setSelectedIndex(-1);

    JPanel cardCommon = new JPanel();
    cardCommon.add(displayCommon);
    cardCommon.add(comboCommon);
    cardCommon.add(display3);
    cardCommon.add(combo2);
    cardCommon.add(pfPerformanceButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Performance Main Screen");
    c1.show(cards, "Performance Main Screen");
  }

  @Override
  public void displayInformationalMessage(String info) {
    JOptionPane.showMessageDialog(cards,
            info,
            "Information",
            JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void displayPerformanceNumberOfDaysInput(String str) {
    display8 = new JLabel(str);
    inputRemainderNumberForPfPerformance = new JTextField(10);
    JPanel cardCommon = new JPanel();
    cardCommon.add(display8);
    cardCommon.add(inputRemainderNumberForPfPerformance);
    cardCommon.add(pfPerformanceButtonGetData);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Performance Screen Remainder Entry");
    c1.show(cards, "Performance Screen Remainder Entry");
  }

  @Override
  public void getGraph(Map<String, Double> pfPerformance, String pfName) {
    BarChart chart = new BarChartImpl("Portfolio Performance", "Date", "Price");
    for (Map.Entry<String, Double> set : pfPerformance.entrySet()) {
      if (set.getValue() != null) {
        chart.createDataset(set.getKey(), set.getValue(), pfName + "Portfolio");
      }
    }
    chart.drawGraph();
  }

  public void resetCreatePortfolioScreen() {
    comboBoxTypeOfPortfolio.setSelectedIndex(-1);
    inputName.setText("");
    comboBoxBuyOrInvest.setSelectedIndex(-1);
  }

  @Override
  public void resetFlexiblePortfolioScreen() {
    setPurchaseDate.setText("");
    comboBoxSupportedStocks.setSelectedIndex(-1);
    howManyShares.setText("");
  }

  @Override
  public void resetHowManyShares() {
    howManyShares.setText("");
  }

  @Override
  public void displayBoughtSuccessfulScreenForAnotherBoughtStock() {
    //reset the fields.
//    display8.setText("");
//    display9.setText("Bought Successful");
//    card5.add(display8);
//    card5.add(display9);
//    card5.add(buyAnotherButton);
//    card5.add(goBackMainMenu);
//    cards.add(card5,"screen5");
//    c1.show(cards,"screen5");
    display8.setText("");
    display9.setText("");
  }

  @Override
  public void displayListOfPortfolioScreen(String[] getListOfPortfolioNames) {
    display12 = new JLabel("Select from below list of portfolios");
    // read from list of portfolios
    String[] listOfPortfolios = getListOfPortfolioNames;
    comboBoxListOfPortfolios = new JComboBox(listOfPortfolios);
    //comboBoxTypeOfPortfolio.setSelectedIndex(-1);
    card6_showListOfStocks.add(display12);
    card6_showListOfStocks.add(comboBoxListOfPortfolios);
    card6_showListOfStocks.add(selectButton);
    card6_showListOfStocks.add(goBackMainMenu);
    cards.add(card6_showListOfStocks, "screen6");
    c1.show(cards, "screen6");
  }

  @Override
  public void displayAddScreen() {
    display15 = new JLabel("Enter date");
    date = new JTextField(10);
    display13 = new JLabel("Select stock from supported list of stocks");
    // review -  hardcoded
    // todo - read it from csv and store in supportedStocks
    String[] supportedStocks1 = {"1. Microsoft (MSFT)",
            "2. Meta (META)",
            "3. Google (GOOG)",
            "4. Apple (AAPL)",
            "5. Tesla (TSLA)",
            "6. JPMorgan Chase (JPM)",
            "7. Johnson (JNJ)",
            "8. Amazon (AMZN)",
            "9. UnitedHealth (UNH)",
            "10. Walmart (WMT)"};
    comboBoxSupportedStocks1 = new JComboBox(supportedStocks1);
    comboBoxSupportedStocks1.setSelectedIndex(-1);

    display14 = new JLabel("How many shares would you like to buy");
    howManyShares1 = new JTextField(10);

    card7_showAddScreen.add(display15);
    card7_showAddScreen.add(date);
    card7_showAddScreen.add(display13);
    card7_showAddScreen.add(comboBoxSupportedStocks1);
    card7_showAddScreen.add(checkPrice1);
    card7_showAddScreen.add(display14);
    card7_showAddScreen.add(howManyShares1);
    card7_showAddScreen.add(addButton);
    card7_showAddScreen.add(backButton1);
    cards.add(card7_showAddScreen, "screen5");
    c1.show(cards, "screen5");

  }

  @Override
  public void displaySellScreen() {

  }

  @Override
  public void displayAddStocksUsingDollarStrategyMain(String[] displayString) {
    JLabel displayCommon = new JLabel("Select the portfolio");
    comboCommon = new JComboBox(displayString);
    comboCommon.setSelectedIndex(-1);

    display3 = new JLabel("Enter the date on which you would like to purchase the stock (YYYY-MM-DD)"
                          + "(from year 2000 to current day)");
    inputDate = new JTextField();

    display8 = new JLabel("Recurring?");
    String[] recur = {"Yes", "No"};
    combo2 = new JComboBox(recur);

    JPanel cardCommon = new JPanel();
    cardCommon.add(displayCommon);
    cardCommon.add(comboCommon);
    cardCommon.add(display3);
    cardCommon.add(inputDate);
    cardCommon.add(display8);
    cardCommon.add(combo2);
    cardCommon.add(dollarCostMainButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Dollar Cost Main Screen");
    c1.show(cards, "Dollar Cost Main Screen");
  }

  public void resetMainMenu() {
    comboBoxMainMenu.setSelectedIndex(-1);
    c1.first(cards);
  }

  @Override
  public void performanceDateEnter(int timestampType) {
    LocalDate today = LocalDate.now();
    String dis = "";
    if (timestampType == 0) {
      dis = "Enter the start year in format (YYYY) from year 2000 to "
              + today.minusYears(4).getYear();
    } else if (timestampType == 1) {
      dis = "Enter the start month in format (YYYY-MM) from year 2000 to "
              + today.minusMonths(4).getMonth() + " "
              + today.minusMonths(4).getYear();
    } else if (timestampType == 2) {
      dis = "Enter the start date in format (YYYY-MM-DD) from year 2000 to "
              + today.minusDays(4).getYear() + "-"
              + today.minusDays(4).getMonth() + "-"
              + today.minusDays(4).getDayOfMonth();
    }

    display3 = new JLabel(dis);
    inputDate = new JTextField(10);

    JPanel cardCommon = new JPanel();
    cardCommon.add(display3);
    cardCommon.add(inputDate);
    cardCommon.add(pfPerformanceButtonSubmit);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Performance Screen");
    c1.show(cards, "Performance Screen");
  }

  @Override
  public void displayErrorMessage(String error) {
    JOptionPane.showMessageDialog(cards,
            error,
            "Error",
            JOptionPane.ERROR_MESSAGE);
  }
}
