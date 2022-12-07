package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.ImportXML;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * This class creates a view for total value of a portfolio on a certain date.
 */
public class TotalValueCounter {

  /**
   * This method determines total value of portfolio on the date provided
   * by the user.
   *
   * @param path path to portfolio
   * @param date date to display
   * @return total value
   */
  public String determineTotalValueOfPortfolio(String path, String date) {
    double total = 0.0;
    double num = 0;
    ImportXML imXml = new ImportXML();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    PortfolioImpl pfImpl = imXml.buildDocument(path);
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();

    for (Portfolio portfolio : pfList) {
      try {
        Date inputDate = dateFormat.parse(date);
        for (int i = 0; i < portfolio.getDateNumsList().size(); i++) {
          if (date.compareTo(portfolio.getDateNumsList().get(i).getDate()) > 0) {
            num += Double.parseDouble(portfolio.getDateNumsList().get(i).getNum());
          }
        }
        total +=
            num * Double.parseDouble(pfImpl.getValue(portfolio.getSymbol(), date));
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }

    return String.format("%.4f", total);
  }

  /**
   * This method displays total value of the portfolio on a certain date.
   *
   * @param path input path
   * @param date input date
   */
  public void displayTotalValueOfPortfolio(String path, String date) {
    String total = determineTotalValueOfPortfolio(path, date);

    System.out.println();
    System.out.println("Total Value on " + date + ": " + total);
    System.out.println();
  }
}