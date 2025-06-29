package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

  private Connection connection;

  public ProductDao() {
    this.connection = JdbcConnectionFactory.getConnection();

    // Só para testar.
    this.dropTable();
    this.createSchema();
    this.createTable();
    this.truncateTable();
  }

  public void save(Product product) {
    String sqlDml = """
    INSERT INTO jdbc.products (name, description, price, discount, created_at)
    VALUES (?, ?, ?, ?, ?)
    """;

    try {
      PreparedStatement preparedStatement = this.connection.prepareStatement(sqlDml);

      preparedStatement.setString(1, product.getName());
      preparedStatement.setString(2, product.getDescription());
      preparedStatement.setBigDecimal(3, product.getPrice());
      preparedStatement.setShort(4, product.getDiscount());
      preparedStatement.setTimestamp(5, Timestamp.from(product.getCreatedAt()));

      preparedStatement.executeUpdate();

      preparedStatement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DML.");
    }
  }

  public Product findById(long id) {
    List<Product> products = new ArrayList<>();

    try {
      String sqlDql = "SELECT * FROM jdbc.products WHERE id = ?";
      PreparedStatement preparedStatement = this.connection.prepareStatement(sqlDql);

      preparedStatement.setLong(1, id);

      ResultSet resultSet = preparedStatement.executeQuery(); // DQL

      while (resultSet.next()) {
        Timestamp updatedAt = resultSet.getTimestamp("updated_at");

        products.add(new Product(
          resultSet.getInt("id"),
          resultSet.getString("name"),
          resultSet.getString("description"),
          resultSet.getBigDecimal("price"),
          resultSet.getShort("discount"),
          resultSet.getTimestamp("created_at").toInstant(),
          updatedAt != null ? updatedAt.toInstant() : null
        ));
      }

      preparedStatement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DQL.");
    }

    return products.getFirst();
  }

  public List<Product> findAll(int limit, int offset) {
    List<Product> products = new ArrayList<>();

    try {
      String sqlDql = "SELECT * FROM jdbc.products LIMIT ? OFFSET ?";
      PreparedStatement preparedStatement = this.connection.prepareStatement(sqlDql);

      preparedStatement.setInt(1, limit);
      preparedStatement.setInt(2, offset);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        products.add(new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getString("description"),
            resultSet.getBigDecimal("price"),
            resultSet.getShort("discount"),
            resultSet.getTimestamp("created_at").toInstant()));
      }

      preparedStatement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DQL.");
    }

    return products;
  }

  public void update(Product product) {
    String sqlDml = """
    UPDATE jdbc.products
    SET name = ?
      , description = ?
      , price = ?
      , discount = ?
      , updated_at = ?
    WHERE id = ?
    """;

    try {
      PreparedStatement preparedStatement = this.connection.prepareStatement(sqlDml);

      preparedStatement.setString(1, product.getName());
      preparedStatement.setString(2, product.getDescription());
      preparedStatement.setBigDecimal(3, product.getPrice());
      preparedStatement.setShort(4, product.getDiscount());
      preparedStatement.setTimestamp(5, Timestamp.from(product.getUpdatedAt()));
      preparedStatement.setLong(6, product.getId());

      @SuppressWarnings(value = "unused")
      int totalRows = preparedStatement.executeUpdate(); // DDL/DML

      preparedStatement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DML.");
    }
  }

  public void removeById(long id) {
    String sqlDml = "DELETE FROM jdbc.products WHERE id = ?";

    try {
      PreparedStatement preparedStatement = this.connection.prepareStatement(sqlDml);
      preparedStatement.setLong(1, id);

      if (preparedStatement.executeUpdate() == 0) {
        throw new RuntimeException("Recurso não encontrado.");
      }

      preparedStatement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DML.");
    }
  }

  public void createSchema() {
    String ddlSql = "CREATE SCHEMA IF NOT EXISTS jdbc";

    try (Statement statement = this.connection.createStatement()) {
      statement.executeUpdate(ddlSql);
      statement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DDL.");
    }
  }

  private void createTable() {
    String ddlSql = """
    CREATE TABLE IF NOT EXISTS jdbc.products(
      id          BIGSERIAL,
      name        VARCHAR(20)              NOT NULL,
      description VARCHAR                  NOT NULL,
      price       DECIMAL(6,2)             NOT NULL,
      discount    SMALLINT                 NOT NULL,
      created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
      updated_at  TIMESTAMP WITH TIME ZONE NULL,
      CONSTRAINT pk_products      PRIMARY KEY(id),
      CONSTRAINT uk_products_name UNIQUE(name)
    );
    """;

    try (Statement statement = this.connection.createStatement()) {
      statement.executeUpdate(ddlSql);

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DDL.");
    }
  }

  public void dropTable() {
    String ddlSql = "DROP TABLE IF EXISTS jdbc.products";

    try (Statement statement = this.connection.createStatement()) {
      statement.executeUpdate(ddlSql);
      statement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DDL.");
    }
  }

  public void truncateTable() {
    String ddlSql = "TRUNCATE TABLE jdbc.products";

    try (Statement statement = this.connection.createStatement()) {
      statement.executeUpdate(ddlSql);
      statement.close();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao tentar executar operação SQL DDL.");
    }
  }

  // .execute - DDL, DML e DQL - return boolean - usar quando o SQL for gerado dinâmico

}
