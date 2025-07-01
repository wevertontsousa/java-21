package basic;

public interface InerfaceType {

  public abstract void foo();

  public static void bar(String baz) { // O método estático deve ser concreto
    System.out.println("Olá " + baz + ".");
  }

}
