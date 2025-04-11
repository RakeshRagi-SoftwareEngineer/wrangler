package io.cdap.wrangler.api.parser;

public class ByteSize implements Token {
  private final long bytes;

  public ByteSize(String value) {
    // Normalize input (e.g., "10KB", "1.5MB")
    value = value.trim().toUpperCase();

    double number = Double.parseDouble(value.replaceAll("[^0-9.]", ""));
    if (value.endsWith("KB")) {
      this.bytes = (long) (number * 1024);
    } else if (value.endsWith("MB")) {
      this.bytes = (long) (number * 1024 * 1024);
    } else if (value.endsWith("GB")) {
      this.bytes = (long) (number * 1024 * 1024 * 1024);
    } else if (value.endsWith("TB")) {
      this.bytes = (long) (number * 1024L * 1024L * 1024L * 1024L);
    } else {
      throw new IllegalArgumentException("Unknown byte size unit in: " + value);
    }
  }

  public long getBytes() {
    return bytes;
  }

  @Override
  public String toString() {
    return bytes + " bytes";
  }
}
