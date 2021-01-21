import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"1", "2"},
                {"1", "2"},
                {"1", "2"},
                {"1", "A"}
        };
        
        sumStringArray(array);
    }
    public static void sumStringArray(String[][] array){
        new Summirizer().sumArray(array);
    }
}
