package basic;

public class AllInOneFile {

  public static void main(String[] args) {
    FooInterface fooInterface = new FooClass();
    fooInterface.bar(2);

    FooRecord fooRecord = new FooRecord(4);
    System.out.println(fooRecord.bar());

    System.out.println(FooEnum.BAZ.toString());
  }

}


interface FooInterface {
  public void bar(int bar);
}


class FooClass implements FooInterface {

  @Override
  public void bar(int bar) {
    System.out.println(bar);
  }

}


record FooRecord(int bar) {
}


enum FooEnum {
  BAR,
  BAZ;
}
