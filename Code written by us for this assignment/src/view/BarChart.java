package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ImportXML;
import model.Portfolio;
import model.PortfolioImpl;

/**
 * Class to create a barchart of the portfolio performance.
 */
public class BarChart extends JPanel {
  TotalValueCounter valueCounter = new TotalValueCounter();
  ImportXML imXml = new ImportXML();
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private ArrayList values;
  private ArrayList labels;
  private String title;

  BarChart(String path, String date) {
    List<Object> data;
    data = viewPerformance(path, date);
    ArrayList values = (ArrayList) data.get(0);
    ArrayList<String> labels = (ArrayList<String>) data.get(1);
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Bar Chart Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    String title = "Performance of portfolio " + path + " till " + date;

    BarChart bc = new BarChart(values, labels, title);

    frame.add(bc);
    frame.setVisible(true);
  }

  /**
   * Constructor with values, labels and title as parameters.
   *
   * @param values values to chart
   * @param labels labels of the values
   * @param title  title of the bar chart.
   */
  public BarChart(ArrayList values, ArrayList labels, String title) {
    this.labels = labels;
    this.values = values;
    this.title = title;
  }

  /**
   * Method to paint the bar chart.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (values == null || values.size() == 0) {
      return;
    }

    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < values.size(); i++) {
      if (minValue > (Double) values.get(i)) {
        minValue = (Double) values.get(i);
      }
      if (maxValue < (Double) values.get(i)) {
        maxValue = (Double) values.get(i);
      }
    }

    Dimension dim = getSize();
    int panelWidth = dim.width;
    int panelHeight = dim.height;
    int barWidth = panelWidth / values.size();

    Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

    Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

    int titleWidth = titleFontMetrics.stringWidth(title);
    int stringHeight = titleFontMetrics.getAscent();
    int stringWidth = (panelWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, stringWidth, stringHeight);

    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue) {
      return;
    }
    double scale = (panelHeight - top - bottom) / (maxValue - minValue);
    stringHeight = panelHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);
    for (int j = 0; j < values.size(); j++) {
      int valueP = j * barWidth + 1;
      int valueQ = top;
      int height = (int) ((Double) values.get(j) * scale);
      if ((Double) values.get(j) >= 0) {
        valueQ += (int) ((maxValue - (Double) values.get(j)) * scale);
      } else {
        valueQ += (int) (maxValue * scale);
        height = -height;
      }

      g.fillRect(valueP, valueQ, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueP, valueQ, barWidth - 2, height);

      int labelWidth = labelFontMetrics.stringWidth((String) labels.get(j));
      stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
      g.drawString((String) labels.get(j), stringWidth, stringHeight);
    }
  }

  /**
   * Method to get the values and labels to chart.
   *
   * @param path path of the file.
   * @param date date to check on.
   * @return list of values.
   */
  public List<Object> viewPerformance(String path, String date) {
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
    return printGraph(label, minDate, path, days);
  }

  private List<Object> printGraph(int label, Date minDate, String path, long diff) {
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
    List<String> date = new ArrayList<>();
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
          date.add(newDate);
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

        return Arrays.asList(values, date, baseScale, scale);

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
          date.add(newDate);
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
        return Arrays.asList(values, date, baseScale, scale);


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
          date.add(newDate);
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
        return Arrays.asList(values, date, baseScale, scale);

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
          date.add(newDate);
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
        return Arrays.asList(values, date, baseScale, scale);
      default:
        return null;
    }
  }


}
