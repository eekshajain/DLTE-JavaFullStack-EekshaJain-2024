package generics.example.collections;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
        Hashtable<String,Integer> nameAge=new Hashtable<>();
        nameAge.put("Eeksha",21);
        nameAge.put("Spandana",19);
        nameAge.put("Atheesh",15);
        System.out.print("HashTable:");
        System.out.println(nameAge);
        LinkedHashMap<String,Integer> nameAgeLinkedMap=new LinkedHashMap<>();
        nameAgeLinkedMap.put("Eeksha",21);
        nameAgeLinkedMap.put("Spandana",19);
        nameAgeLinkedMap.put("Atheesh",15);
        System.out.print("LinkedHashMap");
        System.out.println(nameAgeLinkedMap);
        TreeMap<String ,Integer> nameAgeTreeMap=new TreeMap<>();
        nameAgeTreeMap.put("Eeksha",21);
        nameAgeTreeMap.put("Spandana",19);
        nameAgeTreeMap.put("Atheesh",15);
        System.out.println("TreeMap"+nameAgeTreeMap);
    }
}
