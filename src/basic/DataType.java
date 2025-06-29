package basic;

import java.math.BigDecimal;

@SuppressWarnings(value = "unused")
public class DataType {

  // Primitivos
  private byte byteType; // Valor padrão 0
  private short shortType;
  private int intType;
  private long longType;

  private float floatType; // Valor padrão 0.0
  private double doubleType;

  private boolean booleanType; // Valor padrão false

  private char charType; // Valor padrão \u0000 (primeiro valor da tabela Unicode)

  // Objetos - Valor padrão null - NullPointException
  private BigDecimal bigDecimalType; // Precisão exata; mais lento que os Primitivos; ideal para Bancos
  private String stringType; // Imutável

  // Wrappers - Primitivo em forma de Objeto
  private Byte byteWrapperType;
  private Short shortWrapperType;
  private Integer intWrapperType;
  private Long longWrapperType;
  private Float floatWrapperType;
  private Double doubleWrapperType;
  private Boolean booleanWrapperType;
  private Character charWrapperType;

  public void pointNotation() {
    // String
    String firstName = "Weverton";
    String fullName = firstName.concat(" Teixeira");

    boolean areSame = "uva".equals("uva"); // Nunca usar == para Objetos

    String normalizedValue = "  Paula Raianna ".strip();

    boolean isBlank = "".isBlank(); // true
    isBlank = " ".isBlank(); // true

    boolean isEmpty = "".isEmpty(); // true
    isEmpty = " ".isEmpty(); // false

    var name = new StringBuilder("Maria"); // Performance na concatenação
    name.append("de");
    name.append("Fátima");
    name.append("Cardoso");
    name.append("Melo");


    // Wrappers
    Integer number = 10000;
    number.toString().length();
  }

}
