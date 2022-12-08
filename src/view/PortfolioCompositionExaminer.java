package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.AlphaVantageTimeSeriesDaily;
import model.ImportXML;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * This class creates a view for examining a portfolio on a certain date.
 */
public class PortfolioCompositionExaminer {

  /**
   * Displays the composition of the portfolio on a certain date.
   *
   * @param path Path of the portfolio file.
   * @param date date on which the value of portfolio is needed.
   */
  public void examinePortfolioComposition(String path, String date) {
    String[] order = new String[7];
    order[0] = "Company";
    order[1] = "Symbol";
    order[2] = "Portion";
    order[3] = "Shares";
    order[4] = "PurchaseOfValue";
    order[5] = "TotalValue";
    order[6] = "DateOfPurchase";
    String[] output = new String[7];
    double total = Double.parseDouble(
            new TotalValueCounter().determineTotalValueOfPortfolio(path, date));
    double portion;
    ImportXML imXml = new ImportXML();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    PortfolioImpl pfImpl = imXml.buildDocument(path);
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();
    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();

    System.out.println();
    if (total == 0) {
      System.out.println("You don't have any shares on " + date);
      System.out.println();
      return;
    }

    System.out.println("Total value of a portfolio: " + total);
    System.out.println("Total money invested: " + pfImpl.getInvested());
    for (int i = 0; i < 7; i++) {
      System.out.print(String.format("%-17s", order[i]));
    }
    System.out.println();

    int k = 0;
    ArrayList<Double> sharesList = numList(pfList, date);
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
        output[5] = String.format("%.2f", sharesList.get(k) * Double.parseDouble(avtsd.getValue(portfolio.getSymbol(), date)));
        output[6] = portfolio.getDateOfPurchase();

        for (int i = 0; i < 7; i++) {
          System.out.printf("%-17s", output[i]);
        }
        System.out.println();
        k++;
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }
    System.out.println();
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
}
