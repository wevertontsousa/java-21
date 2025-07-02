package basic;

import java.math.BigDecimal;

public class DataType {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    // String
    String firstName = "Weverton";
    String fullName = firstName.concat(" Teixeira");

    boolean areSame = "uva".equals("uva");

    String normalizedValue = "  Paula Raianna ".strip();

    boolean isBlank = "".isBlank(); // true
    isBlank = " ".isBlank(); // true

    boolean isEmpty = "".isEmpty(); // true
    isEmpty = " ".isEmpty(); // false

    StringBuilder name = new StringBuilder("Maria"); // Performance na concatenação
    name.append("de");
    name.append("Fátima");
    name.append("Cardoso");
    name.append("Melo");


    // Wrappers
    Integer number = 10000;
    number.toString().length();
  }

}


@SuppressWarnings(value = "unused")
class Foo {
  // Primitivos
  private byte byteType;
  private short shortType;
  private int intType;
  private long longType;

  private float floatType;
  private double doubleType;

  private boolean booleanType;

  private char charType;

  // Objetos
  private BigDecimal bigDecimalType;
  private String stringType;

  // Wrappers
  private Byte byteWrapperType;
  private Short shortWrapperType;
  private Integer intWrapperType;
  private Long longWrapperType;
  private Float floatWrapperType;
  private Double doubleWrapperType;
  private Boolean booleanWrapperType;
  private Character charWrapperType;
}
