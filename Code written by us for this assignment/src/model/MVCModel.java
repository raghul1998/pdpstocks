package model;

/**
 * This class represents the model of MVC pattern. It is the start point of the model.
 */
public class MVCModel implements CreatePortfolio, ImportPortfolio {

  public PortfolioImpl portfolioImpl;
  public CreateXML createXML;
  public ImportXML importXML;

  /**
   * A constructor of MVC Model and calls constructors of model classes.
   */
  public MVCModel() {
    portfolioImpl = new PortfolioImpl();
    createXML = new CreateXML();
    importXML = new ImportXML();
  }

  public String saveFile(PortfolioImpl pfImpl, int type) {
    return createXML.saveFile(pfImpl, type);
  }

  public PortfolioImpl buildDocument(String path) {
    return importXML.buildDocument(path);
  }
}
