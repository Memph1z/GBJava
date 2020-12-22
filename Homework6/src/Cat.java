public class Cat extends Animal{
    private int runDistance = 200;
    private static int count;

    public Cat(int age) {
        super(age);
        count++;
        animalCount++;
    }

    public Cat() {
        count++;
        animalCount++;
    }

    @Override
    public void run(int i) {
        if (i > runDistance){
            System.out.println("Котяня не может пробежать " + i + " метров...");
        }else System.out.println("Котяня пробежал " + i + " метров!");
    }

    @Override
    public void swim(int i) {
        System.out.println("Котяни не умеют плавать!!!");
    }

    public static void printCount(){
        System.out.println("Создано " + count + " котек");
    }
}
