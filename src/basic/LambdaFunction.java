package basic;

public class LambdaFunction {

    public static void main(String[] args) {
      Foo sum = (x, y) -> x + y;
      double result = sum.execute(3, 2);
      System.out.println(result);

      Foo multiply = (x, y) -> x * y;
      result = multiply.execute(3, 2);
      System.out.println(result);
  }

}


interface Foo {

  /**
   * Interface para ser implementada através de um Função Lambda.
   * @param operatingLeft - operando a esquerda do operador.
   * @param operatingRight - operando a direita do operador.
   * @return um número decimal resultante da operação entre os operandos.
   */
  public abstract double execute(double operatingLeft, double operatingRight);

}
