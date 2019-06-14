import java.util.Arrays;
import java.util.List;

public class TestingDefaultMethods {

    public static void main(String[] args){
        TestingDefaultMethods instance = new TestingDefaultMethods();
        instance.run();
    }

    private void run() {

        // forEach is a new default method added to the List interface
        System.out.println("Printing a list using the forEach method");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.forEach(i -> System.out.println(i));


        System.out.println("\nTesting an interface with a default method:");

        // will use default method
        Moveable moveable = new AnimalWithoutOverridingMovingMethod();
        moveable.move();

        // will override the move() method
        Moveable animal = new Animal();
        animal.move();
        Moveable dog = new Dog();
        dog.move();

        // we can use static methods in interfaces too
        Moveable.sayHello();

        // one difference between default methods in interfaces and abstract classes is that
        //   we can implement multiple interfaces but can only inherit one parent class
        Animal kangaroo = new Kangaroo();
        kangaroo.move();
        kangaroo.jump();
    }

    interface Moveable {
        default void move(){
            System.out.println("Thing is moving");
        }

        static void sayHello(){
            System.out.println("Hello!");
        }
    }

    interface Jumpable {
        default void jump(){
            System.out.println("Thing is jumping");
        }
    }

    // This is acceptable because the move() method has a default implementation
    class AnimalWithoutOverridingMovingMethod implements Moveable {}

    class Animal implements Moveable, Jumpable {
        @Override
        public void move(){
            System.out.println("Animal is moving");
        }
    }

    class Dog extends Animal {
        @Override
        public void move(){
            System.out.println("Dog is moving");
        }
    }

    class Kangaroo extends Animal {
        @Override
        public void move(){
            System.out.println("Kangaroo is moving");
        }

        @Override
        public void jump(){
            System.out.println("Kangaroo is jumping");
        }
    }



}
