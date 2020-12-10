import java.util.Scanner;
import java.util.Random;
import java.util.StringJoiner;


public class Main {


    public static void main(String[] args) {

        guessNum();
        guessVeggie();
    }

    /**
     * Первое задание
     */

    public static void guessNum() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(10);
        System.out.println("Угадайте число от 0 до 9");
        int tries;
        for (tries = 0; tries < 3; tries++) {
            int input_number = scanner.nextInt();
            if (input_number == number) {
                System.out.println("Вы угадали!");
                break;
            } else if (input_number > number) {
                System.out.println("Загаданное число меньше");
            } else {
                System.out.println("Загаданное число больше");
            }
        }
        if (tries == 3) {
            System.out.println("Вы не угадали =(");
        }
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        repeatGame();
    }

    public static void repeatGame() {
        Scanner scanner = new Scanner(System.in);
        int repeat = scanner.nextInt();
        if (repeat == 1) {
            guessNum();
        } else if (repeat == 0) {
            System.out.println("Спасибо за игру!");
            scanner.close();
        } else {
            System.out.println("Доступные варианты ответа: 1 – да / 0 – нет");
            repeatGame();
        }
    }

    /**
     * Второе задание (да, я в курсе, что надо было делать только одно,
     * но, как говорят наши аналитики: "Больше - не меньше" xD)
     */

    public static void guessVeggie() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int number = random.nextInt(26) - 1;
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape",
                "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String word = words[number];
        System.out.println("Guess the word!");
        System.out.println("Theme: Edibles");
        while (true) {
            String input_word = scanner.next();
            if (input_word.equals(word)) {
                System.out.println("Вы угадали!");
                break;
            } else {
                System.out.println(checkWords(input_word, word));
            }
        }
        scanner.close();
    }

    public static String checkWords(String a, String b) {
        String[] buffer = new String[15];
        for (int i = 0; i < 15; i++){
            buffer[i] = "#";
        }
        int min = Integer.min(a.length(), b.length()) - 1;
            for (int i = 0; i <= min; i++) {
                if (Character.isUpperCase(a.charAt(i))){
                    return "Only lowercase letters are allowed!";
                }
                if (a.charAt(i) == b.charAt(i)) {
                    buffer[i] = String.valueOf(a.charAt(i));
                }
            }
        StringJoiner joiner = new StringJoiner("");
        for(int i = 0; i < buffer.length; i++) {
            joiner.add(buffer[i]);
        }
            String compared = joiner.toString();
            return compared;
        }
}
