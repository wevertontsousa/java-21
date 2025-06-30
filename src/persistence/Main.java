package persistence;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
  public static void main(String[] args) throws Exception {
    ProductDao productDao = new ProductDao();

    // Save
    try {
      productDao.save(new Product(
        "Galaxy S24",
        "Smartphone Samsung Galaxy S24 6,2\""
          + " Galaxy AI 256GB Cinza 5G 8GB RAM Câm."
          + " Tripla 50MP + Selfie 12MP Bateria 4000mAh Dual Chip",
        new BigDecimal("3999.00"),
        (short) 0
      ));

    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    try {
      productDao.save(new Product(
        "Galaxy S25",
        "Smartphone Samsung Galaxy S25 6,2\""
          + " Galaxy AI 256GB Cinza 5G 8GB RAM Câm."
          + " Tripla 50MP + Selfie 12MP Bateria 4000mAh Dual Chip",
        new BigDecimal("3999.00"),
        (short) 0)
      );

    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    try {
      productDao.save(new Product(
        "Galaxy S26",
        "Smartphone Samsung Galaxy S26 6,2\""
          + " Galaxy AI 256GB Cinza 5G 8GB RAM Câm."
          + " Tripla 50MP + Selfie 12MP Bateria 4000mAh Dual Chip",
        new BigDecimal("3999.00"),
        (short) 0
      ));

    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    // Find all
    productDao.findAll(5, 0).forEach((userModel_) -> System.out.println(userModel_));

    // Find by id
    var product = productDao.findById(3L);

    if (product != null) {
      System.out.println("READ one: " + product.toString());

      // Update
      product.setName("Galaxy S29");
      product.setDescription(product.getDescription().replace("S26", "S29"));
      product.setUpdatedAt();

      try {
        productDao.update(product);
      } catch (RuntimeException e) {
        System.out.println(e.getMessage());
      }

      System.out.println("UPDATE: " + productDao.findById(3L));

      // Delete by id
      try {
        productDao.removeById(productDao.findById(2L).getId());
      } catch (RuntimeException e) {
        System.out.println(e.getMessage());
      }

    } else {
      System.out.println("Recurso não encontrado.");
    }

    // Find all
    productDao.findAll(5, 0).forEach((userModel_) -> System.out.println(userModel_));
  }

}


class JdbcConnectionFactory {
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


class Product {
  private long id;
  private String name;
  private String description;
  private BigDecimal price;
  private short discount;
  private Instant createdAt;
  private Instant updatedAt;

  public Product(String name, String description, BigDecimal price, short discount) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.discount = discount;
    this.createdAt = Instant.now();
  }

  public Product(long id, String name, String description, BigDecimal price, short discount, Instant createdAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.discount = discount;
    this.createdAt = createdAt;
    this.updatedAt = Instant.now();
  }

  public Product(
    long id,
    String name,
    String description,
    BigDecimal price,
    short discount,
    Instant createdAt,
    Instant updatedAt
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.discount = discount;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public short getDiscount() {
    return discount;
  }

  public void setDiscount(short discount) {
    this.discount = discount;
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public Instant getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt() {
    this.updatedAt = Instant.now();
  }

  @Override
  public String toString() {
    return "<Product("
      + "id=" + id
      + ", name=" + name
      + ", description=" + description.substring(0, 30).strip()
      + ", price=" + price
      + ", discount=" + discount
      + ")>";
  }

}


class ProductDao {

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

}
