package basic;

public class ThisKeyword {

  @SuppressWarnings(value = "unused")
  private int foo;

  public ThisKeyword() {
    this(3); // Acessa um construtor através de outro construtor
  }

  public ThisKeyword(int foo) {
    this.foo = foo; // Acessa atributos de instância
  }

}
