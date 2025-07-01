package basic;

public class StaticKeyword {
  public static int foo;

  static {
    foo = 10;
  }

  public static int bar(int otherValue) {
    return foo + otherValue;
  }

}
