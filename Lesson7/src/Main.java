import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Plate plate = new Plate(100);
        Plate newPlate = new Plate(100);
        Cat cat = new Cat("Barsik", 55);
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Tigidic", random.nextInt(101));
        cats[1] = new Cat("Behemoth", random.nextInt(101));
        cats[2] = new Cat("Mur-Nyash", random.nextInt(101));
        cats[3] = new Cat("Baron", random.nextInt(101));
        cats[4] = new Cat("Lapusik", random.nextInt(101));

        plate.info();
        cat.isFull();
        cat.eat(plate);
        plate.info();
        cat.isFull();
        plate.addFood(20);
        plate.info();

        newPlate.info();
        checkCats(cats);
        feedCats(cats, newPlate);
        newPlate.info();
        checkCats(cats);

    }
    public static void feedCats(Cat[] cats, Plate plate){
        System.out.println();
        for (Cat cat : cats) {
            cat.eat(plate);
        }
    }
    public static void checkCats(Cat[] cats){
        System.out.println();
        for (Cat cat : cats) {
            cat.isFull();
        }
    }
}
