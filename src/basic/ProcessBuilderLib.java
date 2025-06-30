package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProcessBuilderLib {

  public static void main(String[] args) {
    List<String[]> result = execute();

    if (result != null) {
      for (String[] strings : execute()) {
        for (String string : strings) {
          System.out.println(string);
        }
      }
    }
  }

  public static List<String[]> execute() {
    ProcessBuilder processBuilder = new ProcessBuilder("java", "--version");
    Process process = null;

    try {
      process = processBuilder.start();
    } catch (IOException e) {
      return null;
    }

    int exitCode = 0;

    try {
      exitCode = process.waitFor(); // Aguarda o processo terminal, ou seja, todas as linhas de retorno
    } catch (InterruptedException e) {
      return null;
    }

    if (exitCode != 0) {
      return null;
    }

    InputStream inputStream = process.getInputStream();
    byte[] allBytes = null;

    try {
      allBytes = inputStream.readAllBytes();
    } catch (Exception e) {
      return null;
    }

    String outputText = new String(allBytes, StandardCharsets.UTF_8);
    String[] outputLines = outputText.split("\n");
    List<String[]> lines = Stream.of(outputLines).map((line) -> line.split("\n")).toList();

    return lines;
  }

  public static List<String[]> executeWhithWhile() {
    ProcessBuilder processBuilder = new ProcessBuilder("java", "--version");
    Process process = null;

    try {
      process = processBuilder.start();
    } catch (IOException e) {
      return null;
    }

    InputStream inputStream = process.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    String line = null;

    try {
      line = bufferedReader.readLine();
    } catch (IOException e) {
      return null;
    }

    List<String[]> lines = new ArrayList<>();

    while (line != null) {
      lines.add(line.split("\n"));

      try {
        line = bufferedReader.readLine();
      } catch (IOException e) {}
    }

    int exitCode = 0;

    try {
      exitCode = process.waitFor(); // Aguarda o processo terminal, ou seja, todas as linhas de retorno
    } catch (InterruptedException e) {
      return null;
    }

    if (exitCode != 0) {
      return null;
    }

    return lines;
  }

}
