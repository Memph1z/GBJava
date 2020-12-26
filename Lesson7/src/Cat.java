public class Cat {

    private String name;
    private int appetite;
    private boolean full = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }
    public void eat(Plate p) {
        if (p.getFood() - appetite >= 0) {
            p.decreaseFood(appetite);
            this.full = true;
        }
    }
    public void isFull() {
        if (this.full) {
            System.out.printf("%s is full %n", this.name);
        } else {
            System.out.printf("%s is hungry %n", this.name);
        }
    }
}
