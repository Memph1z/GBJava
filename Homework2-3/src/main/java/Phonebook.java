import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Phonebook {
    Map<String, List<Integer>> entries;

    public Phonebook() {
        this.entries = new HashMap<>();
    }

    public void add(String surname, Integer phone){
        if (entries.containsKey(surname)){
            entries.get(surname).add(phone);
        }else{
            List<Integer> phones = new ArrayList<>();
            phones.add(phone);
            entries.put(surname, phones);
        }
    }

    public List<Integer> get(String surname){
            return entries.get(surname);
    }

    @Override
    public String toString() {
        return "Phonebook{" +
                "entries=" + entries +
                '}';
    }
}
