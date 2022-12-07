package model;


import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents for creating xml file.
 */
public class CreateXML implements CreatePortfolio {

  private static Document doc;

  /**
   * This method saves the user entered stock data in xml format.
   *
   * @param pfImpl portfolio to save
   */
  @Override
  public void saveFile(PortfolioImpl pfImpl, int type) {
    ArrayList<Portfolio> userData = pfImpl.getPortfolios();

    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.newDocument();

      Element rootElement = doc.createElement("portfolio");
      doc.appendChild(rootElement);

      Element totalInvested = doc.createElement("total_invested");
      totalInvested.appendChild(doc.createTextNode(String.format("%.4f", pfImpl.getInvested())));
      rootElement.appendChild(totalInvested);

      for (Portfolio stockData : userData) {
        Element stock = doc.createElement("stock");
        rootElement.appendChild(stock);

        Attr attr = doc.createAttribute("company");
        attr.setValue(stockData.getCompany()); // Change
        stock.setAttributeNode(attr);

        Element tickerSymbol = doc.createElement("ticker_symbol");
        tickerSymbol.appendChild(doc.createTextNode(stockData.getSymbol())); // Change
        stock.appendChild(tickerSymbol);

        Element noOfShares = doc.createElement("no_of_shares");
        noOfShares.appendChild(doc.createTextNode(stockData.getNum())); // Change
        stock.appendChild(noOfShares);

        Element dateOfPurchase = doc.createElement("date_of_purchase");
        dateOfPurchase.appendChild(doc.createTextNode(stockData.getDateOfPurchase())); // Change
        stock.appendChild(dateOfPurchase);

        Element currentRate = doc.createElement("current_rate");
        currentRate.appendChild(doc.createTextNode(stockData.getValueOfPurchase())); // Change
        stock.appendChild(currentRate);

        Element totalValue = doc.createElement("total_value");
        totalValue.appendChild(doc.createTextNode(stockData.getTotalValueOfPurchase())); // Change
        stock.appendChild(totalValue);

        if (type == 2) {
          Element buyingHistory = doc.createElement("buying_history");
          buyingHistory.appendChild(doc.createTextNode(stockData.getDateNumsStr()));
          stock.appendChild(buyingHistory);
        }
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource domSource = new DOMSource(doc);

      String pf = null;
      String flexible;
      if (type == 1) {
        flexible = "inflexible_";
      }
      else {
        flexible = "flexible_";
      }
      int i;
      for (i = 1; i <= 100; i++) {
        pf = flexible + i + ".xml";
        if (!new File(pf).exists()) {
          break;
        }
      }

      StreamResult streamResult = new StreamResult(new File(pf));
      transformer.transform(domSource, streamResult);
      System.out.println("Portfolio created successfully with XML file!");
      System.out.println("The name of XML file: " + pf);
      System.out.println();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}