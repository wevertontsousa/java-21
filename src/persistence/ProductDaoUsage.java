package persistence;

import java.math.BigDecimal;

public class ProductDaoUsage {
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
