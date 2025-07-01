package basic;

public class EnumType {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    Role roleNameType = Role.ADMIN; // USER

    String roleNameString = Role.ADMIN.name(); // USER
    roleNameString = Role.ADMIN.toString(); // USER

    String roleDescription = Role.USER.description(); // usuário

    Role role = Role.valueOf("user".toUpperCase());
  }

}


enum Role {
  ADMIN("administrador"),
  USER("usuário");

  private String description;

  Role(String description) {
    this.description = description;
  }

  public String description() {
    return this.description;
  }
}
