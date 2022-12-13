package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for PortfolioImpl class.
 */
public class PortfolioImplTest {

  @Test
  public void testGetValue() {
    Double expected = Double.parseDouble("94.6600");
    assertEquals(String.format("%.4f", expected),
            new PortfolioImpl().getValue("GOOG", "2022-10-31"));
  }

  @Test(expected = NullPointerException.class)
  public void testGetValueWrongSymbol() {
    new PortfolioImpl().getValue("GOOGLE", "2022-10-31");
  }

  @Test
  public void testCheckValuePortfolioImpl() {
    assertEquals("-1",
            new PortfolioImpl().checkValue("GOOG",
                    "92.660", "2022-10-31"));
  }

  @Test
  public void testCheckValueWithGreaterValue() {
    assertEquals("98.660",
            new PortfolioImpl().checkValue("GOOG",
                    "98.660", "2022-10-31"));
  }

  @Test
  public void testCheckValueWithIncorrectSymbol() {
    assertEquals("-1",
            new PortfolioImpl().checkValue("GOG",
                    "92.660", "2022-10-31"));
  }

}