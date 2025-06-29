package basic;

public class DataConversion {

  public static void main(String[] args) {
    String value = "52";
    Integer newValue = Integer.parseInt(value);

    if (newValue instanceof Integer) {
      System.out.println(newValue.getClass().getSimpleName()); // Integer
    }
  }

}
