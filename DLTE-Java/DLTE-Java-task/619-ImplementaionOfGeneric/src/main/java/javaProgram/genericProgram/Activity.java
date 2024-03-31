package javaProgram.genericProgram;

public interface Activity<T> {

     String insertRecord(T objects);
   default  void viewAll(){
        System.out.println();
    }
    T read(int index);
   String delete(int index);
   void update(int index,T object);
}
