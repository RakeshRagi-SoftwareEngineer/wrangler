package io.cdap.wrangler.api.parser;

public class TimeDuration implements Token {
  private final long milliseconds;

  public TimeDuration(String value) {
    value = value.trim().toLowerCase();

    double number = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
    if (value.endsWith("ms")) {
      this.milliseconds = (long) number;
    } else if (value.endsWith("s")) {
      this.milliseconds = (long) (number * 1000);
    } else if (value.endsWith("min")) {
      this.milliseconds = (long) (number * 60 * 1000);
    } else if (value.endsWith("hr")) {
      this.milliseconds = (long) (number * 60 * 60 * 1000);
    } else {
      throw new IllegalArgumentException("Unknown time duration unit in: " + value);
    }
  }

  public long getMilliseconds() {
    return milliseconds;
  }

  @Override
  public String toString() {
    return milliseconds + " ms";
  }
}
