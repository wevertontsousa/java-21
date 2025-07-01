package basic;

public class Reflection {

  @SuppressWarnings(value = "unused")
  private void foo() {
    Class<String> bar = String.class; // Exatamente String
    Class<?> baz = String.class; // Qualquer tipo, diferente do uso de `var`, não requer inicialização de valor
    Class<? extends Exception> xpto = RuntimeException.class; // Do mesmo tipo ou sub-tipos
  }

}
