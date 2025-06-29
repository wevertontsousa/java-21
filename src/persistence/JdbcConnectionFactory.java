package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnectionFactory {

  public static Connection getConnection() {
    Properties properties = new Properties();
    String path = "/resources/application.properties";

    try (InputStream inputStream = JdbcConnectionFactory.class.getResourceAsStream(path)) {
      properties.load(inputStream);

      final String url = properties.getProperty("database.url");
      final String username = properties.getProperty("database.username");
      final String password = properties.getProperty("database.password");

      return DriverManager.getConnection(url, username, password);

    } catch (IOException e) {
      throw new RuntimeException("Arquivo properties não encontrado.");

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar conectar-se ao banco de dados.");
    }
  }

}
