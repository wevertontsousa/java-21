package basic;

public record RecordType(String foo, int bar) {

  public static final String DEFAULT_MESSAGE = "Olá";

  public void showValues() {
    System.out.println(foo + " " + bar);
  }

  public static void showMessage() {
    System.out.println(DEFAULT_MESSAGE);
  }

}
