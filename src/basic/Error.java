package basic;

public class Error {

  public static void main(String[] args) {
    try {
      // A IDE obriga a usar try_catch
      // Ou usar throws Exception na assinatura do método e passar para frente
      // Algum método vai ter que tratar na pilha de execução
      Error.throwCheckedException();
    } catch (Exception e) {
    }

    Error.throwUncheckedException(); // A IDE não obriga a usar try_catch
  }

  public static void throwCheckedException() throws Exception {
    throw new Exception();
  }

  public static void throwUncheckedException() {
    throw new RuntimeException();
  }

}
