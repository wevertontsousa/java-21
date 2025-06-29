package persistence;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {

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
