package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStructure {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {

    // Tamanho dinâmico; aceita Wrappers
    List<String> foos = new ArrayList<>();

    // Save
    foos.add("Weverton");
    foos.add("Paula");
    foos.add("Jaqueline");

    // Find all
    for (String foo : foos) {
      System.out.println(foo);
    }

    foos.forEach((foo) -> System.out.println(foo));

    // Find by index
    String foo = foos.get(0);

    // Update by index
    foo = foos.set(0, "Maria");

    // Delete by index
    foo = foos.remove(1);

    // Delete by value
    boolean removedName = foos.remove("Jaqueline");

    // Exists by value
    boolean containsName = foos.contains("Paula");


    // Save
    List<String> bars = List.of("Weverton", "Paula");
    List<String> bazs = Arrays.asList("Weverton", "Paula");

    Integer[] xptos = {1, 5, 9};
    List<Integer> newXptos = Arrays.asList(xptos);
  }

}
