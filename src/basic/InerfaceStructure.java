package basic;

public interface InerfaceStructure {

  public abstract void foo();

  public static void bar(String baz) { // O método estático deve ser concreto
    System.out.println("Olá " + baz + ".");
  }

}
