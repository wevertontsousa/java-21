package basic;

class Casting {

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
    Dog animal = Animal();

    // Explícito seguro
    if (animal instanceof dog) {
      Dog animalOther = new Animal();
    }

    // Explícito seguro
    try {
       Dog animalOther= new Animal();
    } catch (ClassCastException e) {
    }
  }

}


class Animal { }
class Dog extends Animal { }