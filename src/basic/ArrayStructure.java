package basic;

public class ArrayStructure {

  public static void main(String[] args) {
     // É estático, ou seja, o tamanho é imutável
    double[] foos = new double[3];

    // Save(Insert)/Update
    foos[0] = 8;
    foos[1] = 4.2;
    foos[2] = 8.4;

    // Find all
    for (double foo : foos) { // Foreach
      System.out.println(foo);
    }

    // Save
    String[] bars = {"Weverton", "Paula", "Jaqueline"};
    for (int i = 0; i < bars.length; i++) { // For
      System.out.println(bars[i]);
    }


    // Matriz
    var totalStudents = 2;
    var totalGrades = 2;
    var studentGrades = new double[totalStudents][totalGrades];

    studentGrades[0][0] = 5.0;
    studentGrades[0][1] = 7.0;
    studentGrades[1][0] = 8.0;
    studentGrades[1][1] = 10;

    for (double[] students : studentGrades) {
      for (double grade : students) {
        System.out.println(grade);
      }
    }
  }

}
