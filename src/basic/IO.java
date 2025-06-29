package basic;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class IO {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite o seu nome: ");
    String name = scanner.nextLine();

    System.out.print("Digite a sua idade: ");
    int age = scanner.nextInt();

    scanner.close();

    System.out.println("%s | %d ".formatted(name, age));


    // Para funcionar no WSL2 é preciso instalar algumas bibliotecas
    String value = JOptionPane.showInputDialog("Digite um número: ");
    Double number = Double.parseDouble(value);
    System.out.println(number);
  }

}
