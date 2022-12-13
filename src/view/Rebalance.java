package view;

import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import model.AlphaVantageTimeSeriesDaily;
import model.ImportXML;
import model.MVCModel;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * This class represents and responsible for implementing the re-balancing portfolio feature.
 */
public class Rebalance extends JFrame {
  PortfolioImpl portfolios;
  private final JPanel cards;
  private final CardLayout card1;
  private final String path;
  private final String date;
  private int numberOfStocksWithNonZeroShares = 0;
  private final JButton reBalanceButton;
  private ArrayList<Portfolio> pfList;
  private AlphaVantageTimeSeriesDaily avtsd;
  private ArrayList<Double> sharesList;
  private double totalPortfolioValue;
  private String[] proportionRequired;
  private JTextField[] input;
  private DisplayResult obj;

  /**
   * This method is the constructor for this class that initializes certain values and
   * creates the JPanel to display.
   *
   * @param path  path of the portfolio
   * @param date  date which user entered
   * @param total total value of the portfolio
   * @param obj   object of the previous screen
   */
  public Rebalance(String path, String date, double total, DisplayResult obj) {
    super();

    this.path = path;
    this.date = date;
    this.obj = obj;

    setSize(800, 450);
    setLocation(400, 200);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    reBalanceButton = new JButton("ReBalance");
    reBalanceButton.addActionListener(evt -> {
      rebalanceFinal();
    });

    portfolios = new MVCModel().buildDocument(path);

    cards = new JPanel();
    this.card1 = new CardLayout();
    cards.setLayout(this.card1);
    this.card1.show(cards, "ReBalance");
    this.add(cards);
    setVisible(true);
  }

  /**
   * This method is responsible for getting the user data to re-balance the portfolio.
   */
  public void setBalance() {
    totalPortfolioValue = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio(path, date));

    if (totalPortfolioValue == 0) {
      return;
    }

    double portion;
    ImportXML imXml = new ImportXML();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    PortfolioImpl pfImpl = imXml.buildDocument(path);
    pfList = pfImpl.getPortfolios();
    avtsd = new AlphaVantageTimeSeriesDaily();

    int k = 0;
    sharesList = numList(pfList, date);
    proportionRequired = new String[pfList.size()];

    if (pfList.size() == 1 || isThereOnlyOneStockOnThisDate(sharesList)) {
      String message = "You have only one stock on " + date + ". Hence already balanced.";
      JOptionPane.showMessageDialog(this, message);
      this.dispose();
      JFrame menu = new MainMenu();
      menu.setVisible(true);
      obj.dispose();
      return;
    }

    input = new JTextField[numberOfStocksWithNonZeroShares];
    JLabel[] stockName = new JLabel[numberOfStocksWithNonZeroShares];

    JPanel panel = new JPanel();

    JLabel label = new JLabel("Enter the proportion in % (upto 2 decimals) for all these stocks\n");
    panel.add(label);

    for (int i = 0; i < numberOfStocksWithNonZeroShares; i++) {
      Portfolio portfolio = pfList.get(i);
      stockName[i] = new JLabel(portfolio.getSymbol() + " : ");
      input[i] = new JTextField(10);
      panel.add(stockName[i]);
      panel.add(input[i]);
    }

    panel.add(reBalanceButton);
    cards.add(panel, "Rebalance Main Screen");
    card1.show(cards, "Rebalance Main Screen");
  }

  /**
   * This private helper method takes care of re-balancing the portfolio.
   */
  private void rebalanceFinal() {
    double totalProportion = 100;

    for (int i = 0; i < pfList.size(); i++) {
      if (sharesList.get(i) == 0) {
        // This means there are no shares on this date, so skip
        // In the final iteration, check if the proportion makes up to 100, else ask from beginning
        if ((i == pfList.size() - 1) && (totalProportion != 0)) {
          String message = "The proportions should make up to 100 percent. "
                  + "Enter again from beginning.";
          JOptionPane.showMessageDialog(this, message,
                  "ERROR", JOptionPane.ERROR_MESSAGE);
          return;
        }
        continue;
      }

      proportionRequired[i] = input[i].getText();

      try {
        double val = Double.parseDouble(proportionRequired[i]);
        totalProportion -= val;
        totalProportion = Math.floor(totalProportion * 100) / 100;
      } catch (Exception e) {
        String message = "Enter a valid number";
        JOptionPane.showMessageDialog(this, message,
                "ERROR", JOptionPane.ERROR_MESSAGE);
        return;
      }

      // In the final iteration, check if the proportion makes up to 100, else ask from beginning
      if ((i == pfList.size() - 1) && (totalProportion != 0)) {
        String message = "The proportions should make up to 100 percent. "
                + "Enter again from beginning.";
        JOptionPane.showMessageDialog(this, message,
                "ERROR", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }

    // Boolean: 1 - buy, 0 - sell
    // Double: amount to be bought or sold
    Map<Boolean, Double> finalData = new HashMap<>();

    for (int i = 0; i < pfList.size(); i++) {
      if (sharesList.get(i) == 0) {
        // No shares on this date, so skip
        continue;
      }
      Portfolio portfolio = pfList.get(i);
      double numberOfShares = sharesList.get(i);
      double singleShareValueOnGivenDate =
              Double.parseDouble(avtsd.getValue(portfolio.getSymbol(), date));
      double totalValueOfThisStockOnGivenDate = singleShareValueOnGivenDate * numberOfShares;
      double requiredValue = totalPortfolioValue
              * (Double.parseDouble(proportionRequired[i]) / 100);

      if (requiredValue > totalValueOfThisStockOnGivenDate) {
        // Shares to be bought
        double difference = requiredValue - totalValueOfThisStockOnGivenDate;
        double numberOfSharesToBeBought = difference / singleShareValueOnGivenDate;
        finalData.put(true, numberOfSharesToBeBought);
        portfolios.insertPortfolioWithDate(portfolio.getCompany(), portfolio.getSymbol(),
                String.valueOf(numberOfSharesToBeBought), date);
      } else {
        // Shares to be sold
        double difference = totalValueOfThisStockOnGivenDate - requiredValue;
        double numberOfSharesToBeSold = difference / singleShareValueOnGivenDate;
        finalData.put(true, numberOfSharesToBeSold);
        portfolios.sellPortfolio(portfolio.getSymbol(), numberOfSharesToBeSold, date);
      }
    }

    MVCModel model = new MVCModel();
    String newPfName = model.saveFile(portfolios, 2);
    String message = "A new balanced portfolio has been created."
            + "The new portfolio name is " + newPfName;
    JOptionPane.showMessageDialog(this, message,
            "INFORMATIONAL", JOptionPane.INFORMATION_MESSAGE);
    this.dispose();
    JFrame menu = new MainMenu();
    menu.setVisible(true);
    obj.dispose();
  }

  /**
   * This method tells whether there is one stock on a given date in a portfolio.
   *
   * @param sharesList share list of the portfolio
   * @return true if there is only one stock else false
   */
  private boolean isThereOnlyOneStockOnThisDate(ArrayList<Double> sharesList) {
    for (int i = 0; i < sharesList.size(); i++) {
      if (sharesList.get(i) != 0) {
        numberOfStocksWithNonZeroShares++;
      }
    }

    return numberOfStocksWithNonZeroShares == 1;
  }

  /**
   * This method helps in calculating the stock and its share details of a portfolio on a certain
   * date.
   *
   * @param portfolios the portfolio data
   * @param date       date which user entered
   * @return the array list of stocks on that date
   */
  private ArrayList<Double> numList(ArrayList<Portfolio> portfolios, String date) {
    ArrayList<Double> ret = new ArrayList<>();
    double num;

    for (int i = 0; i < portfolios.size(); i++) {
      num = 0;
      for (int j = 0; j < portfolios.get(i).getDateNumsList().size(); j++) {
        if (date.compareTo(portfolios.get(i).getDateNumsList().get(j).getDate()) >= 0) {
          num += Double.parseDouble(portfolios.get(i).getDateNumsList().get(j).getNum());
        }
      }
      ret.add(num);
    }

    return ret;
  }

}
