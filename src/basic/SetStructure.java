package basic;

import java.util.HashSet;
import java.util.Set;

public class SetStructure {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    Set<String> foos = new HashSet<>();

    // Save/Update
    foos.add("Weverton");
    foos.add("Paula");
    foos.add("Jaqueline");

    // Find all
    for (String foo : foos) {
      System.out.println(foo);
    }

    foos.forEach((foo) -> System.out.println(foo));

    // Delete by value
    boolean removedName = foos.remove("Jaqueline");

    // Exists by value
    boolean containsName = foos.contains("Paula");

    // Save
    Set<String> bars = Set.of("uva", "laranja");
  }

}
