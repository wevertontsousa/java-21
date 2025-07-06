package pattern;

class Builder {

  public static void main(String[] args) {
    Foo foo = new Foo.FooBuilder()
      .bar("xpto")
      .baz(9.78)
      .build();

    foo.show();
  }

}


class Foo {

  private String bar;
  private double baz;

  private Foo(FooBuilder fooBuilder) {
    this.bar = fooBuilder.bar;
    this.baz = fooBuilder.baz;
  }

  void show() {
    System.out.println(this.bar + " " + this.baz);
  }


  static class FooBuilder {

    private String bar;
    private double baz;

    FooBuilder bar(String bar) {
      this.bar = bar;
      return this;
    }

    FooBuilder baz(double baz) {
      this.baz = baz;
      return this;
    }

    Foo build() {
      return new Foo(this);
    }

  }

}
