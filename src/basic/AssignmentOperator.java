package basic;

public class AssignmentOperator {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    int variable = 5;
    final double CONSTANT = 3.14159;
    var typeInference = "Weverton"; // A palavra-chave `var`, requer a inicialização do valor.

    // Referência, quando variáveis apontam para o mesmo local na memória
    Comment comment = new Comment();
    Comment reference = comment;
    Comment otherComment = new Comment();

    System.out.println(comment == reference); // true
    System.out.println(comment == otherComment); // false
  }

}
