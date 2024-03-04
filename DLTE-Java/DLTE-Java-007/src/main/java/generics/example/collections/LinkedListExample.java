package generics.example.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListExample {
    public static void main(String[] args) {
         LinkedList<String> stringLinkedList=new LinkedList<>();
         stringLinkedList.add(0,"Two");
         stringLinkedList.add(1,"Three");
        System.out.println(stringLinkedList);
        System.out.println("Add element in first");
        stringLinkedList.addFirst("One");
        System.out.println("After add "+stringLinkedList);
        stringLinkedList.addLast("Four");
        System.out.println("After add "+stringLinkedList);
        System.out.println("Fist Element "+stringLinkedList.peek()+" same as "+stringLinkedList.peekFirst());//peek and peekFirst same
        stringLinkedList.offer("Five");//at last
        System.out.println("Retrive and remove-poll "+stringLinkedList.poll());
        System.out.println(stringLinkedList);
        stringLinkedList.addFirst("One");
        System.out.println(stringLinkedList);
        System.out.println("Pops first "+stringLinkedList.pop());
        stringLinkedList.add("Five");
        System.out.println(stringLinkedList);
        System.out.println("Last index of five"+stringLinkedList.lastIndexOf("Five"));
        ListIterator<String> stringListIterator=stringLinkedList.listIterator();
        while (stringListIterator.hasNext()){
            System.out.println(stringListIterator.next());
        }
//        ListIterator<String> stringListIterator1=stringLinkedList.listIterator();
//        Collections.sort(stringLinkedList);
//        while (stringListIterator1.hasNext()){
//            System.out.println(stringListIterator.next());
//        }
        //System.out.println(stringLinkedList.get(2));
    }
}
