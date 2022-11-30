package view;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;

import controller.Features;

public class JFrameViewImpl extends JFrame implements GUIView {
  private ViewControllerInteract vciObj;
  JDatePickerImpl datePicker;

  private JComboBox comboBoxMainMenu, comboBoxTypeOfPortfolio, comboBoxSupportedStocks,
          comboBoxBuyOrInvest, comboBoxListOfPortfolios, comboBoxStocksAvailableForSale;
  private JComboBox comboCommon, combo2, combo3, combo4, combo5;
  private JPanel cards;  // default screen
  private CardLayout c1;
  private JLabel display1, display2, display3, display4,
          display5, display6, display7, display8, display9, display10,
          display11, display12;


  private JTextField inputName, howManyShares;
  private JTextField inputDate;
  JDatePanelImpl datePanel;
  private JButton exitButton, submitButton1, buyButton, sellButton, submitButton4,
          checkPrice, buyAnotherButton, selectButton, goWithThisDateButton;
  private JButton mainMenuButton;
  private JButton pfPerformanceButton;
  private JButton pfPerformanceButtonSubmit;
  private JTextField inputRemainderNumberForPfPerformance;
  private JButton pfPerformanceButtonGetData;

  private JButton dollarCostMainButton, dollarCostOnGoingButton, dollarCostEndDateButton,
          dollarCostFrequencyButton, dollarCostHowManySharesButton, getDollarFinalSubmit;
  private JComboBox[] comboSupportStocksArray;
  private JSpinner[] spinner;
  private JButton compMainButton, compFlexDateButton, valueOnFullMainButton,
          totalValueInvestedMainButton;

  private String[] stocksAvailableForSale;
  public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
      return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
      if (value != null) {
        Calendar cal = (Calendar) value;
        return dateFormatter.format(cal.getTime());
      }

      return "";
    }

  }


  // constructor
  public JFrameViewImpl(String caption) {


    super(caption);

    setSize(900, 500);
    setLocation(500, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //cards.add(datePicker);
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    datePanel = new JDatePanelImpl(model, p);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    datePicker.setBounds(110, 100, 200, 25);
    model.setSelected(true);
    datePicker.setVisible(true);

    cards = new JPanel();
    JPanel card1 = new JPanel();
    c1 = new CardLayout();
    pfPerformanceButton = new JButton("Next");
    dollarCostMainButton = new JButton("Next");
    mainMenuButton = new JButton("Main Menu");
    dollarCostOnGoingButton = new JButton("Next");
    pfPerformanceButtonSubmit = new JButton("Next");
    dollarCostEndDateButton = new JButton("Next");
    dollarCostFrequencyButton = new JButton("Next");
    dollarCostHowManySharesButton = new JButton("Next");
    getDollarFinalSubmit = new JButton("Buy Shares");
    compMainButton = new JButton("Next");
    compFlexDateButton = new JButton("Next");
    valueOnFullMainButton = new JButton("Next");
    totalValueInvestedMainButton = new JButton("Next");
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
            "Add stocks to portfolio using Dollar-Cost strategy"};

    comboBoxMainMenu = new JComboBox(mainMenuOptions);
    comboBoxMainMenu.setSelectedIndex(-1);

    checkPrice = new JButton("Check Price");
    exitButton = new JButton("Exit");
    submitButton1 = new JButton("Submit");
    buyButton = new JButton("Buy");
    sellButton = new JButton("Sell");
    submitButton4 = new JButton("Submit");
    selectButton = new JButton("Select");
    goWithThisDateButton = new JButton("Go");
    pfPerformanceButtonGetData = new JButton("Submit");
    buyAnotherButton = new JButton("Buy Another");
    mainMenuButton = new JButton("Main Menu");
    howManyShares = new JTextField(10);
    card1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));

    card1.add(display1);
    card1.add(comboBoxMainMenu);
    card1.add(exitButton);
    cards.add(card1, "mainMenu");
    c1.show(cards, "mainMenu");
    this.add(cards);
    //this.pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(Features feature) {
    totalValueInvestedMainButton.addActionListener(evt -> {
      datePanel.setVisible(false);
      feature.totalCostInvestedScreenOne(comboCommon.getSelectedIndex(),
              datePicker.getJFormattedTextField().getText());
    });

    valueOnFullMainButton.addActionListener(evt -> {
      datePanel.setVisible(false);
      feature.valueOnFullScreenOne(comboCommon.getSelectedIndex(),
              datePicker.getJFormattedTextField().getText());
    });

    compFlexDateButton.addActionListener(evt -> {
      datePanel.setVisible(false);
      feature.valueAndCompositionFlexDateScreen(datePicker.getJFormattedTextField().getText());
    });

    compMainButton.addActionListener(evt -> {
      feature.valueAndCompositionScreenOne(comboCommon.getSelectedIndex());
    });

    getDollarFinalSubmit.addActionListener(evt -> {
      feature.dollarValueScreenSixBuyStocks(inputDate.getText(), spinner, comboSupportStocksArray);
    });

    dollarCostHowManySharesButton.addActionListener(evt ->
            feature.dollarValueScreenFiveHowManyShares(inputDate.getText()));

    dollarCostFrequencyButton.addActionListener(evt ->
            feature.dollarValueScreenFourFrequency(inputDate.getText()));

    dollarCostEndDateButton.addActionListener(evt -> {
      feature.dollarValueScreenThreeEndDate(inputDate.getText());
    });

    dollarCostOnGoingButton.addActionListener(evt -> {
      feature.dollarValueScreenTwo(combo3.getSelectedIndex());
    });

    dollarCostMainButton.addActionListener(e -> {
      feature.dollarValueScreenOne(comboCommon.getSelectedIndex(), inputDate.getText(),
              combo2.getSelectedIndex());
    });

    mainMenuButton.addActionListener(e -> {
      datePanel.setVisible(false);
      feature.resetMainMenu();
    });

    comboBoxMainMenu.addActionListener(e -> {
      if (comboBoxMainMenu.getSelectedIndex() == 0) {
        feature.createAPortfolioGUI();
      } else if (comboBoxMainMenu.getSelectedIndex() == 1) {
        feature.valueAndCompositionGUIMainScreen();
      } else if (comboBoxMainMenu.getSelectedIndex() == 2) {
        feature.valueOnFullCompMainScreen();
      } else if (comboBoxMainMenu.getSelectedIndex() == 3 ||
              comboBoxMainMenu.getSelectedIndex() == 4) {
        feature.selectPortfolio();
      } else if (comboBoxMainMenu.getSelectedIndex() == 5) {
        feature.performanceOfPortfolioMain();
      } else if (comboBoxMainMenu.getSelectedIndex() == 6) {
        feature.totalCostInvestedByDateMainMenu();
      } else if (comboBoxMainMenu.getSelectedIndex() == 7) {
        feature.addStocksUsingDollarMain();
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
    buyButton.addActionListener(evt -> feature.buyStockSubmit(datePicker.getJFormattedTextField().getText(),
            comboBoxSupportedStocks.getSelectedIndex(), howManyShares.getText(), inputName.getText()));
    submitButton4.addActionListener(evt -> feature.sellStock(datePicker.getJFormattedTextField().getText(),
            comboBoxSupportedStocks.getSelectedIndex(),
            howManyShares.getText(),
            comboBoxListOfPortfolios.getSelectedIndex()));
    // datePicker.getJFormattedTextField().getText()


    exitButton.addActionListener(evt -> feature.exitProgram());
    selectButton.addActionListener(evt ->
            feature.selectStockSubmit(comboBoxMainMenu.getSelectedIndex(), comboBoxListOfPortfolios.getSelectedIndex()));

    // String date, int stockSelected, String noOfStocks, int pfNumber

    buyAnotherButton.addActionListener(evt -> feature.buyAnotherSubmitButton());

    checkPrice.addActionListener(evt ->
            feature.checkCurrentPrice(comboBoxTypeOfPortfolio.getSelectedIndex(),
                    datePicker.getJFormattedTextField().getText(),
                    comboBoxSupportedStocks.getSelectedIndex() + 1));

//    datePicker.addActionListener(
//            evt -> {stocksAvailableForSale = feature.getStocksAvailableForSaleAsPerDate(datePicker.getJFormattedTextField().getText(),
//                    comboBoxListOfPortfolios.getSelectedIndex());

    goWithThisDateButton.addActionListener(evt->
            feature.getStocksAvailableForSaleAsPerDate(datePicker.getJFormattedTextField().getText(),
            comboBoxListOfPortfolios.getSelectedIndex()));
// String date, int stockSelected, String noOfStocks, int pfNumber
    sellButton.addActionListener(evt->
            feature.sellStock(datePicker.getJFormattedTextField().getText(),
            comboBoxStocksAvailableForSale.getSelectedIndex(),howManyShares.getText(),
            comboBoxListOfPortfolios.getSelectedIndex()));


    howManyShares.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent ke) {
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

    pfPerformanceButton.addActionListener(evt ->
            feature.performancePortfolioMainMenu(comboCommon.getSelectedIndex(),
                    combo2.getSelectedIndex()));

    pfPerformanceButtonSubmit.addActionListener(evt ->
            feature.performancePortfolioSubmit(inputDate.getText(), combo2.getSelectedIndex()));

    pfPerformanceButtonGetData.addActionListener(evt ->
            feature.performanceOfPortfolioGetData(inputRemainderNumberForPfPerformance.getText()));

    //backButton1.addActionListener(evt -> c1.show(cards,"screen2"));
  }

  @Override
  public void buyAnotherReset(){
    resetFlexiblePortfolioScreen();
    c1.previous(cards);
  }
  // card2
  @Override
  public void displayTypeOfPortfolioFlexibleOrInFlexibleScreen() {

    // type of portfolio
    JLabel display2 = new JLabel("Select Type of Portfolio");
    String[] typeOfPortfolio = {"Flexible",
            "Inflexible"};
    comboBoxTypeOfPortfolio = new JComboBox(typeOfPortfolio);
    comboBoxTypeOfPortfolio.setSelectedIndex(-1);

    display3 = new JLabel("Name of the portfolio");
    inputName = new JTextField(10);

    display4 = new JLabel("Select options");

    String[] options = {"Buy a Stock",
            "Invest by dollar cost averaging"};

    comboBoxBuyOrInvest = new JComboBox(options);
    comboBoxBuyOrInvest.setSelectedIndex(-1);
    JPanel card2 = new JPanel();
    card2.setLayout(new FlowLayout());
    card2.add(display2);
    card2.add(comboBoxTypeOfPortfolio);
    card2.add(display3);
    card2.add(inputName);
    card2.add(display4);
    card2.add(comboBoxBuyOrInvest);
    card2.add(submitButton1);
    card2.add(mainMenuButton);
    cards.add(card2, "screen2");
    c1.show(cards, "screen2");
  }

  // card 3
  @Override
  public void flexiblePortfolioScreenWithDateInput(String[] supportedStocks, String name) {
    // Flexible Screen
    //displayEnterDate = new JLabel("Please Enter Date");

    // For add stocks
    if (comboBoxTypeOfPortfolio == null) {
      String[] typeOfPortfolio = {"Flexible",
              "Inflexible"};
      comboBoxTypeOfPortfolio = new JComboBox(typeOfPortfolio);
      comboBoxTypeOfPortfolio.setSelectedIndex(0);
    }

    if (inputName == null) {
      inputName = new JTextField(10);
      inputName.setText(name);
    }


    //cards.add(datePicker);
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    datePanel = new JDatePanelImpl(model, p);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    datePicker.setBounds(110, 100, 200, 25);
    model.setSelected(true);
    datePicker.setVisible(true);

    // display8 = new JLabel(datePicker.getJFormattedTextField().getText());
    //String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    //cards.add(datePicker);
    display5 = new JLabel("Enter date");
    //setPurchaseDate = new JTextField(10);
    display6 = new JLabel("Select stock from supported list of stocks");
    // review -  hardcoded
    comboBoxSupportedStocks = new JComboBox(supportedStocks);
    display7 = new JLabel("How many shares would you like to buy");
    //howManyShares = new JTextField(10);
    comboBoxSupportedStocks.setSelectedIndex(-1);

    JPanel card3 = new JPanel();
    //card3.add(display8);
    card3.add(display5);
    card3.add(datePicker);
    card3.add(display6);
    card3.add(comboBoxSupportedStocks);
    card3.add(checkPrice);
    card3.add(display7);
    card3.add(howManyShares);
    card3.add(buyButton);
    card3.add(mainMenuButton);
    cards.add(card3, "screen3");
    c1.show(cards, "screen3");

  }

  // card 4

  @Override
  public void displayBoughtSuccessfulAndWouldLikeToBuyAgainButtonWindow(String portfolioName, String showSuccessMsg) {
    display8 = new JLabel(showSuccessMsg);
    display9 = new JLabel("Stocks bought successfully.");
    JPanel card4 = new JPanel();
    card4.add(display8);
    card4.add(display9);
    card4.add(buyAnotherButton);
    card4.add(mainMenuButton);
    cards.add(card4, "screen4");
    c1.show(cards, "screen4");
  }

  // card 5

  @Override
  public void inflexiblePortfolioScreen(String[] supportedStocks) {
    // inflexible Screen without date field

    display10 = new JLabel("Select stock from supported list of stocks");
    // review -  hardcoded
    comboBoxSupportedStocks = new JComboBox(supportedStocks);
    display11 = new JLabel("How many shares would you like to buy");
    howManyShares = new JTextField(10);
    comboBoxSupportedStocks.setSelectedIndex(-1);

    JPanel card5 = new JPanel();
    card5.add(display10);
    card5.add(comboBoxSupportedStocks);
    card5.add(checkPrice);
    card5.add(display11);
    card5.add(howManyShares);
    card5.add(buyButton);
    card5.add(mainMenuButton);
    cards.add(card5, "screen5");
    c1.show(cards, "screen5");

  }


//  @Override
//  public void resetDateInput() {
//    datePicker.getJFormattedTextField().getText().setText("");
//  }

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
    //setPurchaseDate.setText("");
    comboBoxSupportedStocks.setSelectedIndex(-1);
    howManyShares.setText("");
  }

  @Override
  public void resetHowManyShares() {
    howManyShares.setText("");
  }

  public void resetDollarValueMainScreen() {
    comboCommon.setSelectedIndex(-1);
    combo2.setSelectedIndex(-1);
    inputDate.setText("");
  }

  @Override
  public void dollarValueOnGoingScreen() {
    display2 = new JLabel("Ongoing Strategy?");
    String[] onGoing = {"Yes", "No"};
    combo3 = new JComboBox(onGoing);
    combo3.setSelectedIndex(-1);

    JPanel cardCommon = new JPanel();

    cardCommon.add(display2);
    cardCommon.add(combo3);
    cardCommon.add(dollarCostOnGoingButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Dollar Cost Ongoing Screen");
    c1.show(cards, "Dollar Cost Ongoing Screen");
  }

  @Override
  public void dollarValueEndDateScreen(String str) {
    display5 = new JLabel(str);
    inputDate = new JTextField(10);

    JPanel cardCommon = new JPanel();
    cardCommon.add(display5);
    cardCommon.add(inputDate);
    cardCommon.add(dollarCostEndDateButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Dollar Cost End Date Screen");
    c1.show(cards, "Dollar Cost End Date Screen");
  }

  @Override
  public void dollarValueFrequencyEnterScreen(String str) {
    display6 = new JLabel(str);
    inputDate = new JTextField(10);

    JPanel cardCommon = new JPanel();
    cardCommon.add(display6);
    cardCommon.add(inputDate);
    cardCommon.add(dollarCostFrequencyButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Dollar Cost Frequency Input Screen");
    c1.show(cards, "Dollar Cost Frequency Input Screen");
  }

  @Override
  public void dollarValueHowManyStocksScreen(String buyStr) {
    display7 = new JLabel(buyStr);
    inputDate = new JTextField(10);

    JPanel cardCommon = new JPanel();
    cardCommon.add(display7);
    cardCommon.add(inputDate);
    cardCommon.add(dollarCostHowManySharesButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Dollar Cost How Many Shares Screen");
    c1.show(cards, "Dollar Cost How Many Shares Screen");
  }

  @Override
  public void dollarValueStockProportionScreen(int numberOfStocks, String[] supportedStocks) {
    display4 = new JLabel("Select the stocks and enter proportion");
    JPanel cardCommon = new JPanel();
    cardCommon.add(display4);

    SpinnerModel[] model = new SpinnerModel[numberOfStocks];
    spinner = new JSpinner[numberOfStocks];

    comboSupportStocksArray = new JComboBox[10];
    for (int i = 0; i < numberOfStocks; i++) {
      model[i] = new SpinnerNumberModel(0, 0, 100, 0.1);
      comboSupportStocksArray[i] = new JComboBox(supportedStocks);
      comboSupportStocksArray[i].setSelectedIndex(-1);
      cardCommon.add(comboSupportStocksArray[i]);
      spinner[i] = new JSpinner(model[i]);
      cardCommon.add(spinner[i]);
    }

    display7 = new JLabel("How much money you would like to invest?");
    inputDate = new JTextField(10);
    cardCommon.add(display7);
    cardCommon.add(inputDate);

    cardCommon.add(getDollarFinalSubmit);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Dollar Cost Proportion Screen");
    c1.show(cards, "Dollar Cost Proportion Screen");
  }

  @Override
  public void valueAndCompScreenOne(String[] displayString) {
    JLabel displayCommon = new JLabel("Select the portfolio");
    comboCommon = new JComboBox(displayString);
    comboCommon.setSelectedIndex(-1);

    JPanel cardCommon = new JPanel();
    cardCommon.add(displayCommon);
    cardCommon.add(comboCommon);
    cardCommon.add(compMainButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Composition Main Screen");
    c1.show(cards, "Composition Main Screen");
  }

  @Override
  public void valueAndCompScreenInflexibleResult(String title, String[] column,
                                                 String[][] data, String subText) {
    display7 = new JLabel(title);
    JTable table = new JTable(data, column);
    table.setBounds(30, 40, 400, 300);
    table.setEnabled(false);

    JScrollPane scrollPane = new JScrollPane(table);
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.add(display7);
    panel.add(scrollPane);
    display4 = new JLabel(subText);
    panel.add(display4);

    frame.add(panel);
    frame.setSize(600, 550);
    frame.setLocation(500, 200);
    frame.setVisible(true);
  }

  @Override
  public void valueAndCompFlexDateScreen() {
    display4 = new JLabel("Select the date");
    UtilDateModel model = new UtilDateModel();
    Properties prop = new Properties();
    prop.put("text.today", "Today");
    prop.put("text.month", "Month");
    prop.put("text.year", "Year");
    datePanel = new JDatePanelImpl(model, prop);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    datePicker.setBounds(110, 100, 200, 25);
    model.setSelected(true);
    datePicker.setVisible(true);

    JPanel cardCommon = new JPanel();
    cardCommon.add(display4);
    cardCommon.add(datePicker);
    cardCommon.add(compFlexDateButton);
    cardCommon.add(mainMenuButton);
    cards.add(cardCommon, "Composition Flex Date Screen");
    c1.show(cards, "Composition Flex Date Screen");
  }

  @Override
  public void displayValueCompForCost(String title, String[] costData) {
    display10 = new JLabel(title);
    JLabel[] display = new JLabel[costData.length];
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(display10);
    panel.add(Box.createRigidArea(new Dimension(150, 10)));
    panel.setSize(500, 300);

    for (int i = 0; i < costData.length; i++) {
      display[i] = new JLabel(costData[i]);
      panel.add(display[i]);
    }

    frame.add(panel);
    frame.setSize(500, 300);
    frame.setLocation(500, 200);
    frame.setVisible(true);
  }

  @Override
  public void displayValueCompForOthers(String title, String[] column,
                                        String[][] data, String footer) {
    display7 = new JLabel(title);
    JTable table = new JTable(data, column);
    table.setBounds(30, 40, 400, 300);
    table.setEnabled(false);

    JScrollPane scrollPane = new JScrollPane(table);
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.add(display7);
    panel.add(scrollPane);
    display4 = new JLabel(footer);
    panel.add(display4);
    panel.setSize(400, 300);

    frame.add(panel);
    frame.setSize(600, 550);
    frame.setLocation(500, 200);
    frame.setVisible(true);
  }

  @Override
  public void valueOnFullCompScreenOne(String[] displayString) {
    JLabel displayCommon = new JLabel("Select the portfolio");
    comboCommon = new JComboBox(displayString);
    comboCommon.setSelectedIndex(-1);

    display6 = new JLabel("Select the date");
    UtilDateModel model = new UtilDateModel();
    Properties prop = new Properties();
    prop.put("text.today", "Today");
    prop.put("text.month", "Month");
    prop.put("text.year", "Year");
    datePanel = new JDatePanelImpl(model, prop);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    datePicker.setBounds(110, 100, 200, 25);
    model.setSelected(true);
    datePicker.setVisible(true);

    JPanel cardCommon = new JPanel();
    cardCommon.add(displayCommon);
    cardCommon.add(comboCommon);

    cardCommon.add(display6);
    cardCommon.add(datePicker);

    cardCommon.add(valueOnFullMainButton);
    cardCommon.add(mainMenuButton);

    cards.add(cardCommon, "Full Composition Main Screen");
    c1.show(cards, "Full Composition Main Screen");
  }

  @Override
  public void displayListOfPortfolioScreen(String[] getListOfPortfolioNames) {
    display12 = new JLabel("Select from below list of portfolios");
    // read from list of portfolios
    String[] listOfPortfolios = getListOfPortfolioNames;
    comboBoxListOfPortfolios = new JComboBox(listOfPortfolios);
    JPanel card6_showListOfStocks = new JPanel();
    //comboBoxTypeOfPortfolio.setSelectedIndex(-1);
    card6_showListOfStocks.add(display12);
    card6_showListOfStocks.add(comboBoxListOfPortfolios);
    card6_showListOfStocks.add(selectButton);
    card6_showListOfStocks.add(mainMenuButton);
    cards.add(card6_showListOfStocks, "screen6");
    c1.show(cards, "screen6");
  }

  @Override
  public void displaySellScreen() {
    display5 = new JLabel("Enter date");
    UtilDateModel model = new UtilDateModel();
    Properties p = new Properties();
    p.put("text.today", "Today");
    p.put("text.month", "Month");
    p.put("text.year", "Year");
    datePanel = new JDatePanelImpl(model, p);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    datePicker.setBounds(110, 100, 200, 25);
    model.setSelected(true);
    datePicker.setVisible(true);

    JPanel card7 = new JPanel();
    card7.add(display5);
    card7.add(datePicker);

    card7.add(goWithThisDateButton);
    cards.add(card7, "screen7");
    c1.show(cards, "screen7");
  }

  @Override
  public void displaySellScreen2(String[] stocksAvailableForSale){
    display6 = new JLabel("Select stock you want to sell");
    comboBoxStocksAvailableForSale = new JComboBox(stocksAvailableForSale);
    comboBoxStocksAvailableForSale.setSelectedIndex(-1);
    display7 = new JLabel("How many shares would you like to sell");
    howManyShares = new JTextField(10);

    JPanel card8 = new JPanel();
    card8.add(display6);
    card8.add(comboBoxStocksAvailableForSale);
    card8.add(display7);
    card8.add(howManyShares);

    card8.add(sellButton);
    card8.add(mainMenuButton);
    cards.add(card8, "screen8");
    c1.show(cards, "screen8");

  }

  @Override
  public void displayAddStocksUsingDollarStrategyMain(String[] displayString) {
    JPanel cardCommon = new JPanel();

    if(displayString != null) {
      JLabel displayCommon = new JLabel("Select the portfolio");
      comboCommon = new JComboBox(displayString);
      comboCommon.setSelectedIndex(-1);
      cardCommon.add(displayCommon);
      cardCommon.add(comboCommon);
    }

    display3 = new JLabel("Enter the date on which you would like to purchase the stock (YYYY-MM-DD)"
            + "(from year 2000 to current day)");
    inputDate = new JTextField(10);


    display8 = new JLabel("Recurring?");
    String[] recur = {"Yes", "No"};
    combo2 = new JComboBox(recur);
    combo2.setSelectedIndex(-1);

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
  public void totalCostInvestedByDateScreenOne(String[] listOfPortfolioNames) {
    JLabel displayCommon = new JLabel("Select the portfolio");
    comboCommon = new JComboBox(listOfPortfolioNames);
    comboCommon.setSelectedIndex(-1);

    display6 = new JLabel("Select the date");
    UtilDateModel model = new UtilDateModel();
    Properties prop = new Properties();
    prop.put("text.today", "Today");
    prop.put("text.month", "Month");
    prop.put("text.year", "Year");
    datePanel = new JDatePanelImpl(model, prop);
    datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    datePicker.setBounds(110, 100, 200, 25);
    model.setSelected(true);
    datePicker.setVisible(true);

    JPanel cardCommon = new JPanel();
    cardCommon.add(displayCommon);
    cardCommon.add(comboCommon);

    cardCommon.add(display6);
    cardCommon.add(datePicker);

    cardCommon.add(totalValueInvestedMainButton);
    cardCommon.add(mainMenuButton);

    cards.add(cardCommon, "Total Value Invested Main Screen");
    c1.show(cards, "Total Value Invested Main Screen");
  }

  @Override
  public void displayErrorMessage(String error) {
    JOptionPane.showMessageDialog(cards,
            error,
            "Error",
            JOptionPane.ERROR_MESSAGE);
  }

}
