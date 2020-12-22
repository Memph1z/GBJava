public abstract class Animal {
    private int age;
    protected static int animalCount;

    public Animal(int age) {
        this.age = age;
        animalCount++;
    }

    public Animal() {
    }

    public abstract void run(int i);
    public abstract void swim(int i);

    public static void printCount(){
        System.out.println("Создано " + animalCount + " животных");
    }
}
