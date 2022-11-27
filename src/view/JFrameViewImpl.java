package view;

import java.awt.*;

import javax.swing.*;

import controller.Features;

public class JFrameViewImpl extends JFrame implements GUIView {
  JComboBox comboBoxMainMenu, comboBoxTypeOfPortfolio, comboBoxSupportedStocks;
  JPanel cards = new JPanel();  // default screen
  JPanel card1 = new JPanel();  // main menu screen which is the default screen
  JPanel card2 = new JPanel();  // type of portfolio screen
  JPanel card3 = new JPanel();
  CardLayout c1 = new CardLayout();
  private JLabel display1, display2, display3, display4;
  private JTextField inputName, setPurchaseDate;
  private JButton exitButton1, exitButton2, submitButton, submitButton2;

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
    submitButton = new JButton("Submit");
    submitButton2 = new JButton("Submit");

    card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));
    comboBoxMainMenu.setPreferredSize(new Dimension(1, 10));

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
    comboBoxMainMenu.addActionListener(evt -> feature.createAPortfolioGUI());
    submitButton.addActionListener(evt -> feature.flexibleScreenSubmit(comboBoxTypeOfPortfolio.getSelectedIndex(),
            inputName.getText(),comboBoxSupportedStocks.getSelectedIndex()));
    exitButton1.addActionListener(evt -> feature.exitProgram());
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

    display3 = new JLabel("write the name of the portfolio");
    inputName = new JTextField(10);

    display4 = new JLabel("Select a stock from below stocks");
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

    // String[] supportedStocks = readSupportedStocksFromCSV();
    comboBoxSupportedStocks = new JComboBox(supportedStocks);
    exitButton2 = new JButton("Exit");
    card2.setLayout(new FlowLayout());
    card2.add(comboBoxTypeOfPortfolio);
    card2.add(inputName);
    card2.add(comboBoxSupportedStocks);
    card2.add(submitButton);
    card2.add(exitButton2);
    cards.add(card2, "screen2");
    c1.show(cards, "screen2");
  }

  @Override
  public void errorReenter() {
    JOptionPane.showMessageDialog(cards,
            "Name cannot be empty. Please Re-enter name",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }

  @Override
  public void screen3() {
    display4 = new JLabel("Enter date");
        // card 6
    // Flexible Screen
    //displayEnterDate = new JLabel("Please Enter Date");
    setPurchaseDate = new JTextField(10);
    submitButton2 = new JButton("Submit");
    card3.add(display4);
    card3.add(setPurchaseDate);
    card3.add(submitButton2);
    cards.add(card3,"screen3");
    c1.show(cards,"screen3");
  }


}
