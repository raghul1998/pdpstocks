package model;

/**
 * Interface that declares the method required to import stored portfolios.
 */
public interface ImportPortfolio {

  /**
   * This method build document instances from the stored xml files.
   *
   * @param path path of file
   * @return portfolio lists
   */
  PortfolioImpl buildDocument(String path);
}