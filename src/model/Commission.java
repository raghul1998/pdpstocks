package model;

/**
 * This class represents commission fee for trading stocks.
 */
public class Commission {
  private final int fee;

  /**
   * A constructor for Commission class and 1 is default value.
   */
  public Commission() {
    this.fee = 1;
  }

  /**
   * A constructor for Commission class and defines feel.
   *
   * @param fee how much commission fee is
   */
  public Commission(int fee) {
    this.fee = fee;
  }

  public int getFee() {
    return fee;
  }
}
