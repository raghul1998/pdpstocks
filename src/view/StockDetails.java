package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Validation;
import model.AlphaVantageTimeSeriesDaily;
import model.MVCModel;
import model.PortfolioImpl;

/**
 * Class to create a portfolio.
 */
public class StockDetails extends JFrame {
  PortfolioImpl portfolios = new PortfolioImpl();
  private JTextField textFieldCName;
  private JTextField textFieldSymbol;
  private JTextField textFieldShares;
  private JTextField textFieldDate;
  private JTextField textFieldRate;
  private JButton yesButton;
  private JButton noButton;
  private JButton inflexibleButton;
  private JButton flexibleButton;
  private JPanel stockDetailPanel;
  private JLabel choiceLabel;
  private JLabel labelAdd;
  private JButton submitButton;
  private JButton goBackToTheButton;

  StockDetails() {
    inflexibleButton.setVisible(false);
    flexibleButton.setVisible(false);
    choiceLabel.setVisible(false);
    labelAdd.setVisible(false);
    yesButton.setVisible(false);
    noButton.setVisible(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(stockDetailPanel);
    this.setTitle("Stock Details");
    this.pack();
    this.setSize(1000, 500);
    this.setLocationRelativeTo(null);
    yesButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the yes button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        textFieldCName.setText("");
        textFieldRate.setText("");
        textFieldShares.setText("");
        textFieldSymbol.setText("");
        textFieldDate.setText("");
      }
    });
    noButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the no button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        inflexibleButton.setVisible(true);
        flexibleButton.setVisible(true);
        choiceLabel.setVisible(true);
        textFieldCName.setEnabled(false);
        textFieldSymbol.setEnabled(false);
        textFieldShares.setEnabled(false);
        textFieldDate.setEnabled(false);
        textFieldRate.setEnabled(false);
        submitButton.setEnabled(false);
        yesButton.setEnabled(false);
      }
    });
    submitButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the submit button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (addData()) {
          labelAdd.setVisible(true);
          yesButton.setVisible(true);
          noButton.setVisible(true);
        }
      }
    });
    inflexibleButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the inflexible button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        new MVCModel().saveFile(portfolios, 1);
        displayMessage("Inflexible Portfolio created successfully!");
        JFrame inflexibleMenu = new InflexibleMenu();
        inflexibleMenu.setVisible(true);
        StockDetails.this.dispose();
      }
    });
    flexibleButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the flexible button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        new MVCModel().saveFile(portfolios, 2);
        displayMessage("Flexible Portfolio created successfully!");
        JFrame flexibleMenu = new FlexibleMenu();
        flexibleMenu.setVisible(true);
        StockDetails.this.dispose();
      }
    });
    textFieldRate.addFocusListener(new FocusAdapter() {
      /**
       * Invoked when a component gains the keyboard focus.
       *
       * @param e the event to be processed
       */
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
        textFieldRate.setText("");
      }
    });
    goBackToTheButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the back to menu button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame menu = new MainMenu();
        menu.setVisible(true);
        StockDetails.this.dispose();
      }
    });
  }

  private boolean validateData(String symbol, String shares, String date, String rate) {
    if (new AlphaVantageTimeSeriesDaily().openResource(symbol) == null) {
      displayMessage(symbol + " is not listed in the market.");
      return false;
    } else if (new Validation().validateNum(shares)) {
      displayMessage("The input number is wrong!");
      return false;
    } else if (!new Validation().validateDate(symbol, date)) {
      System.out.println(date);
      displayMessage("The input date is invalid!");
      return false;
    } else if (!rate.isEmpty() && !rate.equals("N") && Objects.equals(
            portfolios.checkValue(symbol, rate, date), "-1")) {
      displayMessage("Invalid value or The value is greater than the real value!");
      return false;
    } else {
      return true;
    }
  }

  private void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  private boolean addData() {
    String name = textFieldCName.getText();
    String symbol = textFieldSymbol.getText();
    String shares = textFieldShares.getText();
    String date = textFieldDate.getText();
    String rate = textFieldRate.getText();
    if (validateData(symbol, shares, date, rate)) {
      if (rate.isEmpty() || rate.equals("N")) {
        portfolios.insertPortfolioWithDate(name, symbol,
                shares, date);
      } else {
        portfolios.insertPortfolioWithDateAndValue(name, symbol,
                shares, date, rate);
      }
      displayMessage("Stock added!");
      return true;
    }
    return false;
  }

}
