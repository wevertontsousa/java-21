package basic;

public class Reflection {

  @SuppressWarnings(value = "unused")
  private void foo() {
    Class<String> bar = String.class; // Exatamente String
    Class<?> baz = String.class; // Qualquer tipo
    Class<? extends RuntimeException> xpto; // Subclasses de RuntimeException
  }

}
