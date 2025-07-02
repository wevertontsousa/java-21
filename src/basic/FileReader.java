package basic;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileReader {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {

    // Maneiras de carregando um arquivo
    try {
      Path textFile = Paths.get("src/resources/files/foo.txt");
      textFile = Paths.get("src/resources/files", "foo.txt");
      textFile = Paths.get("src", "/resources", "/files", "foo.txt");
      textFile = Paths.get("src", "resources", "files", "foo.txt");

      Path filename = textFile.getFileName();
      String filenameStr = filename.toString();
      System.out.println("Arquivo carregado: " + filenameStr);

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");
    }

    System.out.println("");


    // Carregar vários arquivos
    try {
      Path filesPath = Paths.get("src/resources/files");
      List<String> filenames = Files.list(filesPath).map(Path::getFileName).map(Path::toString).toList();
      System.out.println("Arquivos carregados: " + filenames);

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");

    } catch (IOException e) {
      System.out.println("Não foi possível carregar os arquivos do diretório '/files'");
    }

    System.out.println("");


    // Caminho em que o arquivo está sendo executado "no terminal"
    // NÃO é o caminho onde o arquivo está em si
    try {
      Path relativeCurrentPath = Paths.get("");
      Path absoluteCurrentPath = relativeCurrentPath.toAbsolutePath();
      System.out.println("Absolue Path (java run): " + absoluteCurrentPath);

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");

    } catch (SecurityException e) {
      System.out.println("Não foi possível pegar o Absolute Path");
    }

    System.out.println("");


    // Criar um diretório.
    try {
      Path uploadsDirectory = Paths.get("src/resources/uploads");
      Path dir = Files.createDirectory(uploadsDirectory);
      System.out.println("Criado dir: " + dir.toString());

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");

    }catch (IOException e) {
      System.out.println("Diretório existente");
    }

    System.out.println("");


    // Criar diretórios e subdiretórios.
    try {
      Path pngFilesPath = Paths.get("src/resources/uploads/images/png");
      Path dirs = Files.createDirectories(pngFilesPath);
      System.out.println("Criado dir e sub: " + dirs.toString());

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");

    }catch (IOException e) {
      System.out.println("Diretório existente");
    }

    System.out.println("");


    // Criar um arquivo
    String imageName = "foo.jpeg";
    Path imagePath = null;
    try {
      Path imagesPaths = Paths.get("src/resources/uploads/images/jpeg");
      imagePath = Paths.get(imagesPaths.toString(), imageName);
      Path dir = Files.createDirectories(imagesPaths);
      Path file = Files.createFile(imagePath);
      System.out.println("Criado arquivo: " + file.toString());

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");

    } catch (FileAlreadyExistsException e) {

      System.out.println("ERRO! A imagem " + imageName + " já existe.");

    } catch (IOException e) {
      System.out.println("ERRO! O diretório " + imagePath.toString() + " não existe.");
    }

    System.out.println("");


    // Copiar um arquivo
    Path source = Paths.get("src/resources/uploads/images/jpeg/foo.jpeg");
    Path target = Paths.get(source.getParent().toString(), "foo-2.jpeg");
    System.out.println("Copiado arquivo 'foo.jpeg' para: " + target.toString());
    try {
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.out.println("Não foi possível copiar");
    }

    System.out.println("");


    // Normalizar
    try {
      Path pathNormalize = Paths.get("/home/appuser/java-21", "../../bar.png").normalize(); // /home/bar.png
      System.out.println("Normalizado ('/home/appuser/java-21', '../../bar.png'): " + pathNormalize.toString());
      pathNormalize = Paths.get("/home/./appuser/./java-21").normalize(); // /home/appuser/java-21
      System.out.println("Normalizado ('/home/./appuser/./java-21'): " + pathNormalize.toString());

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");
    }

    System.out.println("");


    // Resolver (unir dois caminhos separados)
    try {
      Path downloadsRelativePath = Paths.get("downloads");
      Path fileRelativePath = Paths.get("baz.txt");
      Path downloadsPathResolve = downloadsRelativePath.resolve(fileRelativePath); // downloads/baz.txt
      System.out.println("Resolvido ('downloads') ('baz.txt'): " + downloadsPathResolve.toString());
      downloadsPathResolve = downloadsPathResolve.toAbsolutePath(); // /home/appuserton/java/downloads/baz.txt
      System.out.println("Absolute Path: " + downloadsPathResolve.toString());

    } catch (InvalidPathException e) {
      System.out.println("Não foi possível pegar/montar o Path");

    } catch (SecurityException e) {
      System.out.println("Não foi possível pegar o Absolute Path");
    }
  }

}
