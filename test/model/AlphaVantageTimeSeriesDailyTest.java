package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test class for AlphaVantageTimeSeriesDailyTest class.
 */
public class AlphaVantageTimeSeriesDailyTest {

  @Test
  public void testGetValueOnADate() {
    String expected = "94.6600";
    String actual = new AlphaVantageTimeSeriesDaily().getValue("GOOG", "2022-10-31");
    assertEquals(expected, actual);
  }

  @Test
  public void testGetValueOnANonWorkingDay() {
    String actual = new AlphaVantageTimeSeriesDaily().getValue("GOOG", "2022-10-30");
    assertNull(actual);
  }

  @Test
  public void testOpenResourcesForSupportedSymbols() {
    StringBuilder actual = new AlphaVantageTimeSeriesDaily().openResource("GOOG");
    assertNotNull(actual);
  }

  @Test
  public void testOpenResourcesForNotSupportedSymbols() {
    StringBuilder actual = new AlphaVantageTimeSeriesDaily().openResource("CVS");
    assertNull(actual);
  }

  @Test
  public void testOpenResourcesForIllegalSymbols() {
    StringBuilder actual = new AlphaVantageTimeSeriesDaily().openResource("ABCDEG");
    assertNull(actual);
  }

  @Test
  public void testTimeSeriesDaily() {
    StringBuilder actual = new AlphaVantageTimeSeriesDaily().timeSeriesDaily("GOOG");
    assertNotNull(actual);
  }
}