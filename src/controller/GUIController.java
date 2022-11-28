package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

import model.PortfolioPerformanceDataImpl;
import model.StockCompositionData;
import model.StockCompositionDataImpl;
import model.StockNameMap;
import model.StockNameMapImpl;
import model.TypeofAction;
import view.GUIView;

public class GUIController extends ControllerViewInteractImpl implements Features {
  private GUIView viewGUI;
  private PortfolioPerformanceDataImpl ppd;
  //private ViewControllerInteract vciObj;
  //private ControllerModelInteract cmiObj;

  /**
   * Constructor for the controller that interacts with the view that takes in two arguments and
   * initializes them to the global variables.
   *
   * @param input  the input stream
   * @param output the output stream
   */
  public GUIController(InputStream input, PrintStream output) {
    super(input, output);
    ppd = new PortfolioPerformanceDataImpl();
  }


  @Override
  public void setView(GUIView guiView) {
    viewGUI = guiView;
    viewGUI.addFeatures(this);
  }

  public void createAPortfolioGUI() {
    viewGUI.displayTypeOfPortfolioFlexibleOrInFlexibleScreen();
  }

  @Override
  public void valueAndCompositionGUI() {

  }

  @Override
  public void createPortfolioScreenSubmit(int type, String name, int optionSelected) {

    // reenter if not entered anything
    // if the user didn't enter a string, display the error message and then the same screen
    if (name.length() == 0) {
      //viewGUI.errorReenterName();
      viewGUI.displayErrorMessage("Name cannot be empty. Please Re-enter name");
      return;
    }
    // override

    String typeStr = null;
    if (type == 0) {
      typeStr = "1";
    } else if (type == 1) {
      typeStr = "2";
    }

    if (checkIfPortfolioExists(name)) {
      int yesToOverride = viewGUI.jOptionPortfolioAlreadyExists();
      if (yesToOverride == 0) {
        String[] args = new String[2];
        args[0] = name;
        args[1] = typeStr;
        super.currentPortfolioName = name;
        createPortfolioNameScreenAction(null, args);
      } else if (yesToOverride == 1) {
        viewGUI.resetFlexibleScreen();
        return;
      }

    }
    viewGUI.flexiblePortfolioScreenWithDateInput();
  }

  @Override
  public void checkCurrentPrice(String date, int stockSelected) {
    String[] stock = new String[2];

    StockNameMap snp = new StockNameMapImpl();
    Map<String, String> map = snp.getMap();
    String[] stockSymbolIndexArray = new String[snp.getMapSize()];

    int index = 0;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      stockSymbolIndexArray[index++] = entry.getKey();
    }
    stock[0] = stockSymbolIndexArray[stockSelected - 1];
    stock[1] = date;
    super.cmiObj.controllerModelInteract(TypeofAction.GET_STOCK_DATA, stock, 2);

    String line;
    String splitBy = ",";
    BufferedReader stockData = null;
    String[] splitStockData = new String[4];
    try {
      stockData = new BufferedReader(new FileReader("data/StockData.csv"));
    } catch (Exception e) {
      System.out.println("Supported stocks file not found " + e.getMessage());
    }

    try {
      assert stockData != null;
      line = stockData.readLine();
      splitStockData = line.split(splitBy);
      stockData.close();
    } catch (Exception e) {
      System.out.println("Error in reading Supported stocks csv file.");
    }
    // vciObj.viewControllerInteract(TypeofViews.SHOW_STOCK_DATA, null, 0);
    String data = "\nSTOCK DETAILS\nStockName:" + splitStockData[0] + "\nSymbol:"
            + splitStockData[2] + "\nTime:" + splitStockData[3] + "\nPrice: $" + splitStockData[1]
            + "Information";
    viewGUI.displayInformationalMessage(data);
  }

  @Override
  public void performanceOfPortfolioMain() {
    StockCompositionData obj = new StockCompositionDataImpl("FLEXIBLE");
    int numberOfPortFolio = obj.getNumberOfPortFolio();
    if (numberOfPortFolio == 0) {
      viewGUI.displayInformationalMessage("You dont have any portfolio");
      viewGUI.resetMainMenu();
      return;
    }

    String[] portfolioNames = obj.getPortFolioNames("FLEXIBLE");
    String[] args = new String[portfolioNames.length + 1];
    System.arraycopy(portfolioNames, 0, args, 0, portfolioNames.length);
    args[portfolioNames.length] = "FLEXIBLE";

    String[] displayString = getPortfolioNames(args, args.length);
    viewGUI.displayPerformanceOfPortfolio(displayString);
  }

  @Override
  public void performancePortfolioMainMenu(int pfIndex, int timestampType) {
    if (pfIndex == -1) {
      viewGUI.displayErrorMessage("Select a portfolio");
    }
    if (timestampType == -1) {
      viewGUI.displayErrorMessage("Select proper timestamp type");
    }
    ppd.pfIndex = String.valueOf((pfIndex + 1));
    StockCompositionData obj = new StockCompositionDataImpl("FLEXIBLE");
    super.currentPortfolioName = obj.getPortFolioNames("FLEXIBLE")[pfIndex];
    viewGUI.performanceDateEnter(timestampType);
  }

  private String[] getPortfolioNames(String[] portfolioNames, int numberOfPortFolio) {
    String[] result = new String[numberOfPortFolio - 1];
    StockCompositionData obj = new StockCompositionDataImpl("ALL");
    for (int i = 0; i < numberOfPortFolio - 1; i++) {
      if (Objects.equals(portfolioNames[numberOfPortFolio - 1], "ALL")) {
        String fileName = obj.getPortFolioFileNameByIndex(i, "ALL");
        String pfType;
        if (obj.isPortfolioOfGivenType(fileName, "FLEXIBLE")) {
          pfType = "FLEXIBLE";
        } else {
          pfType = "INFLEXIBLE";
        }
        result[i] = portfolioNames[i].toUpperCase() + " (" + pfType + ")";
      } else {
        result[i] = portfolioNames[i].toUpperCase();
      }
    }
    return result;
  }


  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void performancePortfolioSubmit(String date, int timestampType) {
    String format = null;
    int remainder = 0;
    String timestampTypeStr = "";

    if (timestampType == 0) {
      format = "yyyy";
      timestampTypeStr = "1";
    } else if (timestampType == 1) {
      format = "yyyy-MM";
      timestampTypeStr = "2";
    } else if (timestampType == 2) {
      format = "yyyy-MM-dd";
      timestampTypeStr = "3";
    }

    if (!super.validateDate(date, format, 4)) {
      viewGUI.displayErrorMessage("Not a valid date. Reenter.");
    } else {
      String str = "";
      if (timestampType == 0) {
        String tempDateStr = date + "-01-01";
        remainder = (int) ChronoUnit.YEARS.between(LocalDate.parse(tempDateStr),
                LocalDate.now().withDayOfMonth(1));
      } else if (timestampType == 1) {
        String tempDateStr = date + "-01";
        remainder = (int) ChronoUnit.MONTHS.between(LocalDate.parse(tempDateStr),
                LocalDate.now().withDayOfMonth(1));
      } else if (timestampType == 2) {
        remainder = (int) ChronoUnit.DAYS.between(LocalDate.parse(date), LocalDate.now());
      }

      ppd.date = date;
      ppd.timestampType = timestampType;
      ppd.timestampTypeStr = timestampTypeStr;
      ppd.format = format;

      if (remainder != 4) {
        remainder += 1;
        if (remainder > 30) {
          remainder = 30;
        }

        if (timestampType == 0) {
          str = "Enter the number of years (5 to " + remainder + ")";
        } else if (timestampType == 1) {
          str = "Enter the number of months (5 to " + remainder + ")";
        } else if (timestampType == 2) {
          str = "Enter the number of days (5 to " + remainder + ")";
        }

        ppd.remainder = remainder;
        // Ask user for input
        viewGUI.displayPerformanceNumberOfDaysInput(str);
      } else {
        ppd.number = "5";
        // Display graph
        drawGraph();
        viewGUI.resetMainMenu();
      }
    }
  }

  @Override
  public void performanceOfPortfolioGetData(String num) {
    if (!super.validateStockSelectOption(num, 5, ppd.remainder)) {
      viewGUI.displayErrorMessage("Not a valid number. Re-enter");
      return;
    }
    ppd.number = num;
    drawGraph();
    viewGUI.resetMainMenu();
  }

  private void drawGraph() {
    String[] dates;

    try {
      dates = super.getDatesFromUserInput(ppd.timestampTypeStr, ppd.date, ppd.number, ppd.format);
    } catch (Exception e) {
      viewGUI.displayErrorMessage("Error in parsing the dates. Try again");
      return;
    }

    dates[Integer.parseInt(ppd.number)] = super.currentPortfolioName;
    dates[Integer.parseInt(ppd.number) + 1] = ppd.pfIndex;
    dates[Integer.parseInt(ppd.number) + 2] = ppd.timestampTypeStr;
    cmiObj.controllerModelInteract(TypeofAction.GET_PORTFOLIO_PERFORMANCE, dates, dates.length);

    // Get the performance data
    Map<String, Double> pfPerformance;
    String portfolioType = "FLEXIBLE";
    try {
      StockCompositionData obj = new StockCompositionDataImpl(portfolioType);
      pfPerformance = obj.computePerformanceData(dates, dates.length, portfolioType);
      if (pfPerformance == null) {
        viewGUI.displayErrorMessage("Error in getting portfolio performance. Try again");
        return;
      }
    } catch (Exception e) {
      viewGUI.displayErrorMessage("Error in getting portfolio performance. Try again");
      return;
    }

    String[] scale = super.getScale(pfPerformance);
    String getTitle = "";
    try {
      getTitle = super.getTitle(pfPerformance, dates[dates.length - 1]);
    } catch (Exception e) {
      // Do nothing
    }

    viewGUI.getGraph(pfPerformance, super.currentPortfolioName);
    //vciObj.portfolioPerformanceOverTime(dates, dates.length, pfPerformance, scale, getTitle);
  }
}
