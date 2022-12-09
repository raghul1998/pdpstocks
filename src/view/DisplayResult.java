package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Validation;
import model.AlphaVantageTimeSeriesDaily;
import model.ImportXML;
import model.MVCModel;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * Class to display results of all the functions in import.
 */
public class DisplayResult extends JFrame {
  PortfolioImpl portfolios = new PortfolioImpl();
  private JPanel panel1;
  private JTextField textField1;
  private JTextField textField2;
  private JButton submitButton;
  private JTextArea textFieldData;
  private JTextField tfSymbol;
  private JTextField tfNo;
  private JLabel txtSymbol;
  private JLabel txtNo;
  private JButton sellButton;
  private JButton yesButton;
  private JButton noButton;
  private JLabel txtSell;
  private JButton goBackToTheButton;


  DisplayResult(int type) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(panel1);
    textFieldData.setVisible(false);
    txtSymbol.setVisible(false);
    tfSymbol.setVisible(false);
    txtNo.setVisible(false);
    tfNo.setVisible(false);
    sellButton.setVisible(false);
    txtSell.setVisible(false);
    yesButton.setVisible(false);
    noButton.setVisible(false);
    this.setTitle("Result");
    this.pack();
    this.setSize(800, 600);
    this.setLocationRelativeTo(null);
    submitButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the submit button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        String path = textField2.getText();
        String date = textField1.getText();
        if (validateData(path, date)) {
          if (type == 0) {
            JFrame buy = new StockDetails();
            buy.setVisible(true);
            DisplayResult.this.dispose();
          } else if (type == 1) {
            txtSymbol.setVisible(true);
            tfSymbol.setVisible(true);
            txtNo.setVisible(true);
            tfNo.setVisible(true);
            sellButton.setVisible(true);
          } else if (type == 2) {
            double total = Double.parseDouble(
                    new TotalValueCounter().determineTotalValueOfPortfolio(path, date));
            if (total == 0) {
              displayMessage("You don't have any shares on " + date);
            } else {
              viewCompo(path, date, total);
            }
          } else if (type == 3) {
            String total = new TotalValueCounter().determineTotalValueOfPortfolio(path, date);
            textFieldData.setVisible(true);
            textFieldData.setText("The total value of the portfolio is - " + total);
          } else if (type == 4) {
            JPanel chart = new BarChart(path, date);
            chart.setVisible(true);
          } else if (type == 5) {
            JFrame passive = new PassiveInvestment(path);
            passive.setVisible(true);
            DisplayResult.this.dispose();
          } else if (type == 6) {
            // Re-balance portfolio
            double total = Double.parseDouble(
                    new TotalValueCounter().determineTotalValueOfPortfolio(path, date));
            if (total == 0) {
              displayMessage("You don't have any shares on " + date);
            } else {
              viewCompo(path, date, total);
              Rebalance rb = new Rebalance(path, date, total);
              rb.setBalance();
            }
          }
        }
      }
    });
    sellButton.addActionListener(new ActionListener() {
      /**
       * Invoked when the sell button is clicked.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        portfolios = new MVCModel().buildDocument(textField2.getText());
        String symbol = tfSymbol.getText();
        String noOfStocks = tfNo.getText();
        String date = textField1.getText();
        if (validateSell(symbol, noOfStocks, date)) {
          int num = Integer.parseInt(noOfStocks);
          String str = portfolios.sellPortfolio(symbol, num, date);
          displayMessage("You sold all shares of " + symbol);
          txtSell.setVisible(true);
          yesButton.setVisible(true);
          noButton.setVisible(true);
        }
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
        JFrame res = new DisplayResult(1);
        res.setVisible(true);
        DisplayResult.this.dispose();
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
        JFrame flex = new FlexibleMenu();
        flex.setVisible(true);
        DisplayResult.this.dispose();
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
        DisplayResult.this.dispose();
      }
    });
  }

  private void viewCompo(String path, String date, double total) {
    ImportXML imXml = new ImportXML();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    PortfolioImpl pfImpl = imXml.buildDocument(path);
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();
    double portion;
    StringBuilder result = new StringBuilder();
    String totalInvestment = String.valueOf(pfImpl.getInvested());
    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();

    result.append("Total value of a portfolio: ").append(total);
    result.append("\n" + "Total money invested: ").append(totalInvestment).append("\n");


    String[] order = new String[7];
    order[0] = "Company";
    order[1] = "Symbol";
    order[2] = "Portion";
    order[3] = "Shares";
    order[4] = "PurchaseOfValue";
    order[5] = "TotalValue";
    order[6] = "DateOfPurchase";
    String[] output = new String[7];

    for (int i = 0; i < 7; i++) {
      result.append(String.format("%-17s", order[i]));
    }
    result.append("\n");

    ArrayList<Double> sharesList = numList(pfList, date);
    int k = 0;

    for (Portfolio portfolio : pfList) {
      if(sharesList.get(k) == 0) {
        k++;
        continue;
      }
      try {
        Date inputDate = dateFormat.parse(date);
        Date pfDate = dateFormat.parse(portfolio.getDateOfPurchase());
        portion =
                Double.parseDouble(portfolio.getNum()) * Double.parseDouble(
                        pfImpl.getValue(portfolio.getSymbol(), date))
                        / total * 100;
        output[0] = portfolio.getCompany();
        output[1] = portfolio.getSymbol();
        output[2] = String.format("%.2f", portion);
        output[3] = String.format("%.2f", sharesList.get(k));
        output[4] = portfolio.getValueOfPurchase();
        output[5] = String.format("%.2f", sharesList.get(k)
                * Double.parseDouble(avtsd.getValue(portfolio.getSymbol(), date)));
        output[6] = portfolio.getDateOfPurchase();

        if (pfDate.after(inputDate)) {
          continue;
        }
        for (int i = 0; i < 7; i++) {
          result.append(String.format("%-19s", output[i]));
        }
        result.append("\n");
        k++;
      } catch (ParseException el) {
        throw new RuntimeException(el);
      }
    }
    result.append("\n");
    System.out.println(result.toString());
    textFieldData.setText(result.toString());
    textFieldData.setVisible(true);
  }

  private ArrayList<Double> numList(ArrayList<Portfolio> portfolios, String date) {
    ArrayList<Double> ret = new ArrayList<>();
    double num;

    for (int i=0; i<portfolios.size(); i++) {
      num = 0;
      for (int j=0; j<portfolios.get(i).getDateNumsList().size(); j++) {
        if (date.compareTo(portfolios.get(i).getDateNumsList().get(j).getDate()) >= 0) {
          num += Double.parseDouble(portfolios.get(i).getDateNumsList().get(j).getNum());
        }
      }
      ret.add(num);
    }

    return ret;
  }


  private boolean validateData(String path, String date) {
    if (!new Validation().validateXmlPath(path)) {
      displayMessage("Path is wrong!");
      return false;
    } else if (!new Validation().validateDate("GOOG", date)) {
      displayMessage("Invalid Date!");
      return false;
    } else {
      return true;
    }
  }

  private void displayMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  private boolean validateSell(String symbol, String noOfStocks, String date) {
    Portfolio portfolio = new Validation().validateSymbolForSell(portfolios, symbol);
    if (new Validation().validateSymbolForSell(portfolios, symbol) == null) {
      displayMessage(symbol + " is not in your portfolio");
      return false;
    } else if (!new Validation().validateNumForSell(portfolio, date, noOfStocks)) {
      displayMessage("Input number is invalid to sell!");
      return false;
    } else {
      return true;
    }
  }

}
