package basic;

import java.util.HashMap;
import java.util.Map;

public class MapStructure {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {

    // Semelahnte ao dicionário Python
    Map<String, Integer> foos = new HashMap<>();

    // Save/Update
    foos.put("João", 25);
    foos.put("Maria", 30);
    foos.put("Ana", 20);

    // Find all, semelhante ao items no Python
    for (Map.Entry<String, Integer> foo : foos.entrySet()) {
      String key = foo.getKey();
      Integer value = foo.getValue();
    }

    foos.entrySet().forEach((foo) -> System.out.println(foo.getValue()));

    // Find by key
    int age = foos.get("Ana");

    // Delete by key
    age = foos.remove("João");

    // Delete by key and value
    boolean wasRemoved = foos.remove("Maria", 30);


    // Save
    Map<String, String> baz = Map.of("1", "uva", "2", "laranja");
  }

}
