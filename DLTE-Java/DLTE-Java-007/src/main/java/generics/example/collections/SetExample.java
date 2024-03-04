package generics.example.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetExample {
    public static void main(String[] args) {
        HashSet<Integer> integerHashSet=new HashSet<>();
        TreeSet<Integer>integerTreeSet =new TreeSet<>();
        LinkedHashSet<Integer> integerLinkedHashSet=new LinkedHashSet<>();
       integerHashSet.add(3);
       integerHashSet.add(2);
       integerHashSet.add(1);
       integerHashSet.add(3);//duplicates not add does not give any error(compile or runtime) just replace older value with new one
        integerHashSet.add(5);
        integerHashSet.add(4);
        System.out.println("HashSet:"+integerHashSet);
        integerTreeSet.add(3);
        integerTreeSet.add(2);
        integerTreeSet.add(1);
        integerTreeSet.add(3);
        System.out.println("TreeSet "+integerTreeSet);
        integerLinkedHashSet.add(3);
        integerLinkedHashSet.add(2);
        integerLinkedHashSet.add(1);
        integerLinkedHashSet.add(3);
        integerLinkedHashSet.add(5);
        System.out.println("LinkedHashSet: "+integerLinkedHashSet);
        Transaction transaction1=new Transaction(new Date("2/28/2024"),2300,"Spandana","Family");
        Transaction transaction2=new Transaction(new Date("2/28/2024"),2300,"Spandana","Family");
        //Transaction transaction2=new Transaction(new Date("1/10/2024"),500,"Divija","Friend");
        Transaction transaction3=new Transaction(new Date("2/12/2023"),200,"Sujatha","Bills");
        Transaction transaction4=new Transaction(new Date("1/2/2024"),2500,"Bhanu","Emergency");//date format - month date year
        Set<Transaction> transactions=new LinkedHashSet<>();
        System.out.println("LinkedHashSet");
        transactions= (Set<Transaction>) Stream.of(transaction1,transaction2,transaction3,transaction4).collect(Collectors.toSet());
        transactions.forEach(System.out::println);
        TreeSet<Transaction> treeSetTransactions=new TreeSet<>();
        System.out.println("TreeSet");
        treeSetTransactions.addAll(Stream.of(transaction1,transaction2,transaction3,transaction4).collect(Collectors.toSet()));
        treeSetTransactions.forEach(System.out::println);
    }
}
