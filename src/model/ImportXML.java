package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * This class provides methods to import xml files from local disk.
 */
public class ImportXML implements ImportPortfolio {

  /**
   * This method build document instances from the stored xml files.
   *
   * @param path path of file
   * @return portfolio lists
   */
  @Override
  public PortfolioImpl buildDocument(String path) {
    Document doc = null;
    int type = 1;
    if (path.indexOf("inflexible") == -1) {
      type = 2;
    }
    try {
      File inputFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();
    } catch (Exception e) {
      System.out.println("Invalid File Path");
    }
    return importData(doc, type);
  }

  private PortfolioImpl importData(Document doc, int type) {
    PortfolioImpl pfImpl = new PortfolioImpl();
    ArrayList<Portfolio> data = new ArrayList<>();

    NodeList nList = doc.getElementsByTagName("total_invested");
    Node nNode = nList.item(0);
    Element eElement = (Element) nNode;

    try {
      nList = doc.getElementsByTagName("stock");
      for (int temp = 0; temp < nList.getLength(); temp++) {
        nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          eElement = (Element) nNode;

          Portfolio pf = new Portfolio();

          pf.setCompany(eElement.getAttribute("company"));
          pf.setSymbol(eElement.getElementsByTagName("ticker_symbol")
                  .item(0)
                  .getTextContent());
          pf.setNum(eElement.getElementsByTagName("no_of_shares")
                  .item(0)
                  .getTextContent());
          pf.setDateOfPurchase(eElement.getElementsByTagName("date_of_purchase")
                  .item(0)
                  .getTextContent());
          pf.setValueOfPurchase(eElement.getElementsByTagName("current_rate")
                  .item(0)
                  .getTextContent());
          pf.setTotalValueOfPurchase();
          if (type == 2) {
            pf.setDateNumsStr(eElement.getElementsByTagName("buying_history")
                    .item(0)
                    .getTextContent());
            pf.toDateNumList(pf.getDateNumsStr());
          }

          data.add(pf);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    pfImpl = new PortfolioImpl(data);

    nList = doc.getElementsByTagName("total_invested");
    nNode = nList.item(0);
    eElement = (Element) nNode;

    pfImpl.setInvested(Double.parseDouble(eElement.getTextContent()));

    return pfImpl;
  }
}
