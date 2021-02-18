import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box<T extends Fruit> {


    private List<T> box = new ArrayList<>();

    public float getWeight() {
        float boxWeight = 0;
        for (T fruit : box) {
            boxWeight += fruit.getWeight();
        }
        return boxWeight;
    }

    public boolean compare (Box box) {
        return this.getWeight() - box.getWeight() == 0;

    }

    public void transferToAnotherBox(Box<T> fromBox) {
        if (this == fromBox) {
            System.out.println("Cannot transfer to itself, this is not some magic trick!");
        } else {
            this.box.addAll(fromBox.box);
            fromBox.box.clear();
        }
    }

    public void boxAddFruit(T fruit) {
        box.add(fruit);
    }
}