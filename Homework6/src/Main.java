

public class Main {

    public static void main(String[] args) {

        Dog bobik = new Dog();
        Dog jack = new Dog();
        Dog mark = new Dog();
        bobik.swim(150);
        bobik.run(120);

        Cat murzik = new Cat();
        Cat busya = new Cat();
        murzik.swim(20);
        murzik.run(250);

        Dog.printCount();
        Cat.printCount();
        Animal.printCount();
    }
}
