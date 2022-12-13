package view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for view module.
 */
public class TotalValueCounterTest {
  @Test
  public void testDetermineTotalValueOfPortfolio() {
    Double expected = Double.parseDouble("94.6600") * 246;
    TotalValueCounter tvc = new TotalValueCounter();
    assertEquals(String.format("%.4f", expected),
            tvc.determineTotalValueOfPortfolio("flexible_3.xml", "2022-10-31"));
  }

  @Test
  public void testDetermineTotalValueOfPortfolioWithMultipleStocks() {
    Double expected = Double.parseDouble("94.6600") * 123
            + Double.parseDouble("102.4400") * 234;
    TotalValueCounter tvc = new TotalValueCounter();
    assertEquals(String.format("%.4f", expected),
            tvc.determineTotalValueOfPortfolio("flexible_2.xml", "2022-10-31"));
  }

}