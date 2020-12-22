public class Dog extends Animal {
    private int runDistance = 200;
    private int swimDistance = 10;
    private static int count;

    public Dog(int age) {
        super(age);
        count++;
        animalCount++;
    }

    public Dog() {
        count++;
        animalCount++;
    }

    @Override
    public void run(int i) {
        if (i > runDistance){
            System.out.println("Собаня не может пробежать " + i + " метров...");
        }else System.out.println("Собаня пробежал " + i + " метров!");
    }

    @Override
    public void swim(int i) {
        if (i > swimDistance){
            System.out.println("Собаня не может проплыть " + i + " метров...");
        }else System.out.println("Собаня проплыл " + i + " метров!");
    }

    public static void printCount(){
        System.out.println("Создано " + count + " песелей");
    }
}
