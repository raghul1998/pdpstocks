package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import model.ImportXML;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * This class creates a view for the performance graph of the portfolio on a certain date.
 */
public class PortfolioPerformanceGraph {

  TotalValueCounter valueCounter = new TotalValueCounter();
  ImportXML imXml = new ImportXML();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * Displays the performance of the portfolio as a graph.
   *
   * @param path Path of the portfolio file.
   * @param date date on which the value of portfolio is needed.
   */
  public void viewPerformance(String path, String date) {
    PortfolioImpl pfImpl = imXml.buildDocument(path);
    ArrayList<Portfolio> pfList = pfImpl.getPortfolios();
    Date minDate;
    Date maxDate;
    Date inputDate;
    double minValue;
    double maxValue;
    try {
      minDate = dateFormat.parse(pfList.get(0).getDateOfPurchase());
      maxDate = minDate;
      minValue = Double.parseDouble(pfList.get(0).getTotalValueOfPurchase());
      maxValue = minValue;
      inputDate = dateFormat.parse(date);
    } catch (ParseException e) {
      throw new RuntimeException();
    }
    for (Portfolio portfolio : pfList) {
      try {
        Date dateOfPurchase = dateFormat.parse(portfolio.getDateOfPurchase());

        if (dateOfPurchase.after(inputDate)) {
          continue;
        }
        if (dateOfPurchase.before(minDate)) {
          minDate = dateOfPurchase;
        }
        if (dateOfPurchase.after(maxDate)) {
          maxDate = dateOfPurchase;
        }
        double stockValue = Double.parseDouble(portfolio.getTotalValueOfPurchase());
        if (minValue > stockValue) {
          minValue = stockValue;
        }
        if (maxValue < stockValue) {
          maxValue = stockValue;
        }
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }

    System.out.println("Performance of portfolio " + path + " from " + minDate
        + " to " + inputDate + "\n");
    int label;

    long mill = Math.abs(maxDate.getTime() - minDate.getTime());
    long days = TimeUnit.DAYS.convert(mill, TimeUnit.MILLISECONDS);

    if (days <= 30) { //days
      label = 0;
    } else if (days <= 365) {  //1 month
      label = 1;
    } else if (days <= (365 * 5)) { //3 months
      label = 2;
    } else { //1 Year
      label = 3;
    }
    printGraph(label, minDate, path, days);
  }

  private void printGraph(int label, Date minDate, String path, long diff) {
    Calendar min = Calendar.getInstance();
    min.setTime(minDate);
    int start_date = min.get(Calendar.DATE);
    int start_month = min.get(Calendar.MONTH) + 1;
    int start_year = min.get(Calendar.YEAR);
    int value_date = start_date;
    int value_month = start_month;
    int value_year = start_year;
    long diff2;
    List<Double> values;
    double minPort;
    double maxPort;
    double scale;
    double baseScale;

    Calendar cal = Calendar.getInstance();
    cal.setTime(minDate);
    int max = cal.getActualMaximum(Calendar.DATE);

    switch (label) {
      case 0:
        diff2 = diff;
        values = new ArrayList<>();
        while (diff2 >= 0) {
          String dateString = "" + value_date;
          String monthString = "" + value_month;
          if (value_date < 10) {
            dateString = "0" + value_date;
          }
          if (value_month < 10) {
            monthString = "0" + value_month;
          }
          String newDate = "" + value_year + "-" + monthString + "-" + dateString;
          try {
            values.add(Double.parseDouble(
                valueCounter.determineTotalValueOfPortfolio(path, newDate)));
            value_date++;
          } catch (Exception e) {
            value_date++;
          }
          diff2--;
        }
        minPort = Collections.min(values);
        maxPort = Collections.max(values);

        baseScale = Math.floor(minPort);
        if ((maxPort - minPort) <= 5000) {
          scale = 100;
        } else if ((maxPort - minPort) <= 50000) {
          scale = 1000;
        } else {
          scale = 10000;
        }

        while (diff >= 0) {
          String dateString = "" + start_date;
          String monthString = "" + start_month;
          if (start_date < 10) {
            dateString = "0" + start_date;
          }
          if (start_month < 10) {
            monthString = "0" + start_month;
          }
          String newDate = "" + start_year + "-" + monthString + "-" + dateString;
          try {
            if (checkStars(path, newDate)) {
              System.out.print(monthString + "-" + dateString + "-" + start_year + " : ");
            }
            printStars(path, newDate, scale, baseScale);
          } catch (Exception e) {
            start_date++;
          }
          start_date++;
          if (start_date > 30) {
            start_month++;
            start_date = 1;
          }
          diff--;
        }
        System.out.println("Each * is " + scale + " above " + baseScale);
        System.out.println();
        break;

      case 1:
        diff = diff / 30;
        diff2 = diff;
        values = new ArrayList<>();

        while (diff2 >= 0) {
          String monthString = "" + value_month;
          if (value_month < 10) {
            monthString = "0" + value_month;
          }
          String newDate = "" + value_year + "-" + monthString + "-" + max;
          int i = 0;
          while (true) {
            try {
              values.add(Double.parseDouble(
                  valueCounter.determineTotalValueOfPortfolio(path, newDate)));
              break;
            } catch (Exception e) {
              i++;
              newDate = "" + value_year + "-" + monthString + "-" + (max - i);
            }
          }
          value_month++;
          if (value_month >= 12) {
            value_year++;
            value_month = 1;
          }
          diff2--;
        }
        minPort = Collections.min(values);
        maxPort = Collections.max(values);

        baseScale = Math.floor(minPort);

        if ((maxPort - minPort) <= 5000) {
          scale = 100;
        } else if ((maxPort - minPort) <= 50000) {
          scale = 1000;
        } else {
          scale = 10000;
        }

        while (diff >= 0) {
          String monthString = "" + start_month;
          if (start_month < 10) {
            monthString = "0" + start_month;
          }
          System.out.print(monthString + "-" + start_year + " : ");
          //replace with last date of each month and add market close check
          String newDate = "" + start_year + "-" + monthString + "-" + max;
          int i = 0;
          while (true) {
            try {
              printStars(path, newDate, scale, baseScale);
              break;
            } catch (Exception e) {
              i++;
              newDate = "" + start_year + "-" + monthString + "-" + (max - i);
            }
          }
          start_month++;
          if (start_month >= 12) {
            start_year++;
            start_month = 1;
          }
          diff--;
        }
        System.out.println("Each * is " + scale + " above " + baseScale);
        System.out.println();
        break;

      case 2:
        diff = diff / 90;
        diff2 = diff;
        values = new ArrayList<>();

        while (diff2 >= 0) {
          String monthString = "" + value_month;
          if (value_month < 10) {
            monthString = "0" + value_month;
          }
          String newDate = "" + value_year + "-" + monthString + "-" + max;
          int i = 0;
          while (true) {
            try {
              values.add(Double.parseDouble(
                  valueCounter.determineTotalValueOfPortfolio(path, newDate)));
              break;
            } catch (Exception e) {
              i++;
              newDate = "" + value_year + "-" + monthString + "-" + (max - i);
            }
          }
          value_month += 2;
          if (value_month >= 12) {
            value_year++;
            value_month = 1;
          }
          diff2--;
        }
        //diff between max and min in values.
        minPort = Collections.min(values);
        maxPort = Collections.max(values);

        baseScale = Math.floor(minPort);

        if ((maxPort - minPort) <= 5000) {
          scale = 100;
        } else if ((maxPort - minPort) <= 50000) {
          scale = 1000;
        } else {
          scale = 10000;
        }

        while (diff >= 0) {
          String monthString = "" + start_month;
          if (start_month < 10) {
            monthString = "0" + start_month;
          }
          System.out.print(monthString + "-" + start_year + " : ");
          //replace with last date of each month and add market close check
          String newDate = "" + start_year + "-" + monthString + "-" + max;
          int i = 0;
          while (true) {
            try {
              printStars(path, newDate, scale, baseScale);
              break;
            } catch (Exception e) {
              i++;
              newDate = "" + start_year + "-" + monthString + "-" + (max - i);
            }
          }
          start_month += 2;
          if (start_month >= 12) {
            start_year++;
            start_month = 1;
          }
          diff--;
        }
        System.out.println("Each * is " + scale + " above " + baseScale);
        System.out.println();
        break;

      case 3:
        diff = diff / 365;
        diff2 = diff;
        values = new ArrayList<>();
        while (diff2 >= 0) {
          String monthString = "" + value_month;
          if (value_month < 10) {
            monthString = "0" + value_month;
          }
          String newDate = "" + value_year + "-" + monthString + "-" + max;
          int i = 0;
          while (true) {
            try {
              values.add(Double.parseDouble(
                  valueCounter.determineTotalValueOfPortfolio(path, newDate)));
              break;
            } catch (Exception e) {
              i++;
              newDate = "" + value_year + "-" + monthString + "-" + (max - i);
            }
          }
          value_year++;
          diff2--;
        }
        minPort = Collections.min(values);
        maxPort = Collections.max(values);

        baseScale = Math.floor(minPort);

        if ((maxPort - minPort) <= 5000) {
          scale = 100;
        } else if ((maxPort - minPort) <= 50000) {
          scale = 1000;
        } else {
          scale = 10000;
        }
        while (diff >= 1) {
          String monthString = "" + start_month;
          if (start_month < 10) {
            monthString = "0" + start_month;
          }
          System.out.print(monthString + "-" + start_year + " : ");
          String newDate = "" + start_year + "-" + monthString + "-" + max;
          int i = 0;
          while (true) {
            try {
              printStars(path, newDate, scale, baseScale);
              break;
            } catch (Exception e) {
              i++;
              newDate = "" + start_year + "-" + monthString + "-" + (max - i);
            }
          }
          start_year += 1;
          diff--;
        }
        System.out.println("Each * is " + scale + " above " + baseScale);
        System.out.println();
        break;
      default:
        break;
    }
  }

  private boolean checkStars(String path, String newDate) {
    try {
      double value = Double.parseDouble(
          valueCounter.determineTotalValueOfPortfolio(path, newDate));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private void printStars(String path, String newDate, double scale, double baseScale) {
    double value = Double.parseDouble(
        valueCounter.determineTotalValueOfPortfolio(path, newDate));
    int stars = (int) Math.abs((value - baseScale) / scale);
    while (stars >= 0) {
      System.out.print("*");
      stars--;
    }
    System.out.println();
  }

}
