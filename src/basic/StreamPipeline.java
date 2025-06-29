package basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamPipeline {

  public static void main(String[] args) {
    // O Stream itera implicitamente uma estrutura
    // Para quando precisa modificar os valores
    Stream.of("Weverton", "Paula", "Jaqueline")
      .forEach((value) -> System.out.println(value));


    String[] foos = {"Weverton", "Paula", "Jaqueline", "Alice", "Karol"};
    int indexStart = 2;
    int indexStop = 4;
    Arrays.stream(foos, indexStart, indexStop)
      .forEach((value) -> System.out.println(value));


    List.of("Weverton", "Paula", "Jaqueline", "Alice")
      .parallelStream() // Chama tudo junto e quem retorna primeiro ganha a vez, não segue a ordem original da Lista
      .forEach((value) -> System.out.println("Paralelo: " + value));


    // Map, retorna todos os valores
    Stream.of("Weverton", "Paula", "Jaqueline")
      .map((value) -> value.toUpperCase())
      .map((value) -> "OLÁ, " + value)
      .forEach((value) -> System.out.println(value));


    // Filter, retorna só o que for verdadeiro
    Stream.of(8.5, 4.1, 9.9)
      .filter((value) -> value < 5.0)
      .forEach((value) -> System.out.println(value));


    // Reduce, retorna um único valor, normalmente usado para acumular valores
    Stream.of(3, 1, 6)
      .reduce((accumulated, value) -> accumulated + value)
      .ifPresentOrElse((value) -> System.out.println(value), null); // 10

    int startValue = 2;
    int result = Stream.of(3, 1, 6)
      .reduce(startValue, (accumulated, value) -> accumulated + value);
    System.out.println(result); // 12

    result = List.of(3, 1, 6)
      .parallelStream()
      .reduce(startValue, (accumulated, value) -> accumulated + value);
    System.out.println(result); // 16, pois a cada valor é adicionado o startValue
  }

}
