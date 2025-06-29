package basic;

import java.util.Optional;

public class OptionalType {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    Optional<String> fooOptional = Optional.of("Weverton");
    String foo = fooOptional.isPresent() == true ? fooOptional.get() : null;

    boolean isEmpty = Optional.of("").isEmpty(); // true

    String name = Optional.of("Weverton")
      .map((value) -> value.toUpperCase())
      .orElse(null);

    int bar = Optional.of(4)
      .map((value) -> value * 2)
      .orElseThrow(() -> new RuntimeException());

    bar = Optional.of(4)
      .map((value) -> value * 2)
      .orElseThrow(RuntimeException::new);
  }

}
