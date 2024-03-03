package generics.example.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IllustrationOfArrayList {
    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList();
        arrayList.add(6);
        arrayList.add("Eeksha");
        System.out.println(arrayList);
        ArrayList<String> stringArrayList = new ArrayList<String>();
        String deposits1="Fixed";
        String deposits2="RD";
        stringArrayList = (ArrayList<String>)Stream.of(deposits1,deposits2).collect(Collectors.<String>toList());
        System.out.println(stringArrayList);//direct
        Iterator<String> stringIterator=stringArrayList.iterator();
        while (stringIterator.hasNext()){     //iterator
            System.out.println(stringIterator.next());
        }
        stringArrayList.forEach(System.out::println);  //method reference
        stringArrayList.forEach(item-> System.out.println(item));   //lambda expression

    }


}
