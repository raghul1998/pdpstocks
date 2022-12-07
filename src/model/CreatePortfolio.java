package model;

/**
 * Interface that declares the method required to create
 * a new portfolio using user data.
 */
public interface CreatePortfolio {
  /**
   * This method saves the user entered stock data in xml format.
   *
   * @param pfImpl portfolio to save
   */
  void saveFile(PortfolioImpl pfImpl, int type);
}
