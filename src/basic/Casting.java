package basic;

class Casting {

  @SuppressWarnings(value = "unused")
  public static void main(String[] args) {
    // Implícito - Widening ou Upcasting
    int foo = 10;
    double bar = foo;

    // Explícito - Narrowing ou Downcasting
    bar = 9.78;
    foo = (int) bar; // 9

    // Implícito - Upcasting
    Animal dog = new Dog();
    
    // Explícito - Downcasting
    Dog animal = (Dog) new Animal();

    // Explícito seguro
    if (animal instanceof Dog) {
      Dog animalOther = (Dog) new Animal();
    }

    // Explícito seguro
    try {
       Dog animalOther = (Dog) new Animal();
    } catch (ClassCastException e) {
    }
  }

}


class Animal {
  Animal() {
  }
}

class Dog extends Animal {
  Dog() {
  }
}
