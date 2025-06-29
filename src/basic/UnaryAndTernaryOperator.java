package basic;

public class UnaryAndTernaryOperator {

  public static void main(String[] args) {
    var note = 4.0;

    // Unários
    note++; // note + note
    ++note; // note + note
    note--; // note - note
    --note; // note - note
    System.out.println(note); // 4.0


    // Ternário
    boolean approve = note >= 7 ? true : false;
    System.out.println(approve); // false
  }

}
