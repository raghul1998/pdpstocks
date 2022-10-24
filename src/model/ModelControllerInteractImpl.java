package model;

public class ModelControllerInteractImpl implements ModelControllerInteract {
  @Override
  public void modelControllerInteract(TypeofAction type, String[] args, int length) {
    switch (type) {
      case BUY_STOCKS: {
        break;
      }
      case GET_STOCK_DATA: {
        getStockData(args[0]);
        break;
      }
    }
  }

  private void getStockData(String arg) {
/*    String[] stocks = new String[SupportedStocks.values().length];
    int i = 0;
    for (SupportedStocks stock : EnumSet.allOf(SupportedStocks.class)) {
      stocks[i] = String.valueOf(stock);
      i++;
    }
    System.out.println(Arrays.toString(stocks));*/
    GetStockData obj = new GetStockData();
    try {
      obj.getValue(arg);
    } catch (Exception e) {
      System.out.println("Get stock data failed");
    }
  }
}
