import java.util.*;

public class Main {
    public static void main(String[] args) {
        String src = "this is a very complicated sentence I love very much" +
                " and it is the best sentence I could come up with while I was" +
                " eating a doughnut and a very hot pizza";
        splitAndCount(src);
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", 1234567);
        phonebook.add("Иванов", 9464838);
        phonebook.add("Иванов", 6372920);
        phonebook.add("Петров", 7492027);
        phonebook.add("Петров", 3627290);
        phonebook.add("Сидоров", 4639027);

        System.out.println(phonebook.get("Иванов"));
        System.out.println(phonebook.get("Петров"));
        System.out.println(phonebook.get("Сидоров"));
        System.out.println(phonebook.get("Мармышкин"));
    }
    public static void splitAndCount(String src){
        String[] rslt = src.split(" +");
        TreeMap<String, Integer> map = new TreeMap<>();
        for(String word : rslt){
            map.put(word, map.getOrDefault(word,0) + 1);
        }
        System.out.println(map);
    }
}
