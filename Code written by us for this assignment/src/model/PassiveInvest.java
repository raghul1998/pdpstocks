package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents passive investment. It will calculate dates to buy and create portfolio
 * with input.
 */
public class PassiveInvest implements IPassiveInvest {

  @Override
  public void buyPassive(String path, ArrayList<String> companyList, ArrayList<String> symbolList,
                         ArrayList<Integer> portionList, String amount, int freq,
                         ArrayList<String> range) {
    PortfolioImpl pfImpl = new PortfolioImpl();
    if (path != null) {
      pfImpl = new ImportXML().buildDocument(path);
    }

    Date date = new Date();
    String sDate;
    double num = 0;
    ArrayList<Date> dRange = new ArrayList<>();
    ArrayList<Double> amountList = eachAmount(portionList, amount);

    if (range.get(1).equals("N")) {
      range.remove(1);
      range.add(finishDate());
    }

    dRange = strListToDateList(range);
    date = dRange.get(0); // start date

    sDate = marketOpen(dateToString(date));
    date = stringToDate(sDate);
    while (date.before(dRange.get(1))) {
      for (int i = 0; i < symbolList.size(); i++) {
        num = getNumToBuy(symbolList.get(i), sDate, amountList.get(i));
        pfImpl.insertPortfolioWithDate(companyList.get(i), symbolList.get(i),
                String.valueOf(num), sDate);
      }
      sDate = nextDateToBuy(sDate, freq);
      date = stringToDate(sDate);
    }

    new CreateXML().saveFile(pfImpl, 2);
  }


  private double getNumToBuy(String symbol, String date, Double amount) {
    double num = 0;
    double value = 0;
    String str = null;
    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();

    str = avtsd.getValue(symbol, date);
    value = Double.parseDouble(str);
    num = amount / value;
    num = Math.round(num * 10) / 10.0;

    return num;
  }

  private ArrayList<Date> strListToDateList(ArrayList<String> sRange) {
    ArrayList<Date> range = new ArrayList<>();
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    for (int i = 0; i < sRange.size(); i++) {
      date = stringToDate(sRange.get(i));
      range.add(date);
    }

    return range;
  }

  private String finishDate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);

    return dateToString(cal.getTime());
  }

  private String nextDateToBuy(String sDate, int freq) {
    Calendar cal = Calendar.getInstance();
    Date date = stringToDate(sDate);

    cal.setTime(date);
    cal.add(Calendar.DATE, freq);
    sDate = dateToString(cal.getTime());
    sDate = marketOpen(sDate);

    return sDate;
  }

  private String marketOpen(String sDate) {
    Calendar cal = Calendar.getInstance();
    Date date = stringToDate(sDate);
    AlphaVantageTimeSeriesDaily avtsd = new AlphaVantageTimeSeriesDaily();

    cal.setTime(date);
    while (avtsd.getValue("GOOG", sDate) == null && stringToDate(sDate).before(
            stringToDate(finishDate()))) {
      cal.add(Calendar.DATE, 1);
      sDate = dateToString(cal.getTime());
    }

    return sDate;
  }

  private ArrayList<Double> eachAmount(ArrayList<Integer> portionList, String sAmount) {
    ArrayList<Double> amountList = new ArrayList<>();
    double amount = Double.parseDouble(sAmount);
    double each;
    double total = 0;

    for (Integer integer : portionList) {
      total += integer;
    }

    for (int i = 0; i < portionList.size(); i++) {
      each = amount * portionList.get(i) / total;
      amountList.add(each);
    }

    return amountList;
  }

  private Date stringToDate(String str) {
    Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    try {
      date = formatter.parse(str);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }

    return date;
  }

  private String dateToString(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    return formatter.format(date);
  }
}
