package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Validation;
import model.AlphaVantageTimeSeriesDaily;
import model.PassiveInvest;

/**
 * Class for passive investment function.
 */
public class PassiveInvestment extends JFrame {
  ArrayList<String> companyList = new ArrayList<>();
  ArrayList<String> symbolList = new ArrayList<>();
  ArrayList<Integer> portionList = new ArrayList<>();
  ArrayList<String> range = new ArrayList<>();
  private JTextField textField2;
  private JTextField textField3;
  private JTextField textField4;
  private JTextField textField5;
  private JTextField textField6;
  private JTextField textField7;
  private JButton yesButton;
  private JButton noButton;
  private JButton submitButton;
  private JPanel panelInvest;
  private JLabel lAmount;
  private JLabel lStart;
  private JLabel lEnd;
  private JButton addButton;
  private JLabel lAdd;
  private JTextField textField1;
  private JLabel lFreq;
  private JButton goBackToMainButton;

  PassiveInvestment(String path) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(panelInvest);
    lAmount.setVisible(false);
    lStart.setVisible(false);
    lFreq.setVisible(false);
    lEnd.setVisible(false);
    lAdd.setVisible(false);
    yesButton.setVisible(false);
    noButton.setVisible(false);
    textField1.setVisible(false);
    textField5.setVisible(false);
    textField6.setVisible(false);
    textField7.setVisible(false);
    submitButton.setVisible(false);
    this.setTitle("Passive Investment");
    this.pack();
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
    noButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the no button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        lAmount.setVisible(true);
        lStart.setVisible(true);
        lEnd.setVisible(true);
        lFreq.setVisible(true);
        textField1.setVisible(true);
        textField5.setVisible(true);
        textField6.setVisible(true);
        textField7.setVisible(true);
        submitButton.setVisible(true);
        yesButton.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        textField4.setEnabled(false);
        addButton.setEnabled(false);
      }
    });
    yesButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the yes button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
      }
    });
    addButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the add button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = textField2.getText();
        String symbol = textField3.getText();
        String portion = textField4.getText();

        if (validateSymbolAndPortion(symbol, portion)) {
          companyList.add(name);
          portionList.add(Integer.parseInt(portion));
          symbolList.add(symbol);
          displayMessage("Stock added successfully!");
          lAdd.setVisible(true);
          yesButton.setVisible(true);
          noButton.setVisible(true);
        }
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
        String amount = textField5.getText();
        String start = textField6.getText();
        String end = textField7.getText();
        String frequency = textField1.getText();

        if (validateData(amount, start, end, frequency)) {
          range.add(start);
          range.add(end);

          PassiveInvest pi = new PassiveInvest();
          pi.buyPassive(path, companyList, symbolList, portionList, amount,
                  Integer.parseInt(frequency), range);
          displayMessage("Passive Investment Portfolio Created Successfully!");

          JFrame menu = new MainMenu();
          menu.setVisible(true);
          PassiveInvestment.this.dispose();
        }

      }
    });
    textField7.addFocusListener(new FocusAdapter() {
      /**
       * Invoked when a component gains the keyboard focus.
       *
       * @param e focus event variable.
       */
      @Override
      public void focusGained(FocusEvent e) {
        super.focusGained(e);
        textField7.setText("");
      }
    });
    goBackToMainButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the back to menu button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame menu = new MainMenu();
        menu.setVisible(true);
        PassiveInvestment.this.dispose();
      }
    });
  }

  private boolean validateSymbolAndPortion(String symbol, String portion) {
    if (new AlphaVantageTimeSeriesDaily().openResource(symbol) == null) {
      displayMessage(symbol + " is not listed in the market.");
      return false;
    } else if (!new Validation().validateDuplicatedSymbol(symbolList, symbol)) {
      displayMessage(symbol + " is already in your portfolio!");
      return false;
    } else if (new Validation().validateNum(portion)) {
      displayMessage("Invalid Portion!");
      return false;
    } else {
      return true;
    }
  }

  private boolean validateData(String amount, String start, String end, String frequency) {
    if (!new Validation().validateAmount(amount)) {
      displayMessage("Invalid Amount");
      return false;
    } else if (new Validation().validateNum(frequency)) {
      displayMessage("The frequency is invalid!");
      return false;
    } else if (!new Validation().validateDate("GOOG", start)) {
      displayMessage("Invalid Start Date!");
      return false;
    } else if (!end.equals("N") && !new Validation().validateDate("GOOG", end)) {
      displayMessage("Invalid End Date!");
      return false;
    } else if (!end.equals("N") && end.compareTo(start) < 0) {
      displayMessage("Finish date cannot be earlier than start date!");
      return false;
    } else {
      return true;
    }
  }

  private void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

}
