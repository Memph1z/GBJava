import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 10; i++) {
            orangeBox.boxAddFruit(new Orange());
        }

        for (int i = 0; i < 15; i++) {
            appleBox.boxAddFruit(new Apple());
        }

        for (int i = 0; i < 49; i++) {
            appleBox1.boxAddFruit(new Apple());
        }

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox1.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(appleBox.compare(orangeBox));
        appleBox1.transferToAnotherBox(appleBox);
        appleBox.transferToAnotherBox(appleBox);
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox1.getWeight());


    }

    public static <T extends Object> void changeElement(int from, int to, T[] arr) {
        T toValue = arr[to];
        arr[to] = arr[from];
        arr[from] = toValue;
    }


    public static <T extends Object> ArrayList<T> changeToArray(T[] arr) {
        ArrayList<T> arrayList = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add((T)arr[i]);
        }
        return arrayList;
    }
}