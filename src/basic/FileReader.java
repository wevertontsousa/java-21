package basic;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileReader {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {

    // Maneiras de carregando um arquivo
    Path textFile = Paths.get("/home/weverton/java/NIO-dependency.txt");
    textFile = Paths.get("/home/weverton/java", "NIO-dependency.txt");
    textFile = Paths.get("/home", "/weverton", "/java", "NIO-dependency.txt");
    textFile = Paths.get("/home", "weverton", "java", "NIO-dependency.txt");

    // Caminho em que o arquivo está sendo executado "no terminal"
    // NÃO é o caminho onde o arquivo está em si
    Path relativeCurrentPath = Paths.get("");
    Path absoluteCurrentPath = relativeCurrentPath.toAbsolutePath();
    System.out.println(absoluteCurrentPath);

    // Criar um diretório.
    Path uploadsDirectory = Paths.get("./uploads");
    try {
      Files.createDirectory(uploadsDirectory);
    } catch (IOException e) {
      System.out.println("Diretório existente");
    }

    // Criar diretórios e subdiretórios.
    Path pngFilesPath = Paths.get("./uploads/images/png");
    try {
      Files.createDirectories(pngFilesPath);
    } catch (IOException e) {
      System.out.println("Diretório existente");
    }

    // Criar um arquivo
    Path imagesPaths = Paths.get("./uploads/images/jpeg");
    String imageName = "foo.jpeg";
    Path imagePath = Paths.get(imagesPaths.toString(), imageName);
    try {
      Files.createDirectories(imagesPaths);
      Files.createFile(imagePath);
    } catch (FileAlreadyExistsException e) {
      System.out.println("ERRO! A imagem " + imageName + " já existe.");
    } catch (IOException e) {
      System.out.println("ERRO! O diretório " + imagePath.toString() + " não existe.");
    }

    // Copiar um arquivo
    Path source = Paths.get("./uploads/images/jpeg/foo.jpeg");
    Path target = Paths.get(source.getParent().toString(), "foo-2.jpeg");
    try {
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.out.println("Não foi possível copiar");
    }

    // Normalizar
    Path pathNormalize = Paths.get("/home/wever/api", "../../bar.png").normalize(); // /home/bar.png
    pathNormalize = Paths.get("/home/./wever/./api").normalize(); // /home/wever/api

    // Resolver (unir dois caminhos separados)
    Path downloadsRelativePath = Paths.get("downloads");
    Path fileRelativePath = Paths.get("baz.txt");
    Path downloadsPathResolve = downloadsRelativePath.resolve(fileRelativePath); // downloads/baz.txt
    downloadsPathResolve = downloadsPathResolve.toAbsolutePath(); // /home/weverton/java/downloads/baz.txt
  }

}
