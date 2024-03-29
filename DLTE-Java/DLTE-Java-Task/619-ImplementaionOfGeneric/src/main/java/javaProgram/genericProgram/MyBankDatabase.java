package javaProgram.genericProgram;

public class MyBankDatabase<T> implements Activity<T> {
 //   @Override
    T[] newObjects;
    public String insertRecord(T objects) {
     for(int index=0;index<newObjects.length;index++){
         if(newObjects[index]==null){
             newObjects[index]=objects;
             return "Objects is inserted";
         }
     }
        return "Object not inserted";
    }

    @Override
    public T read(int index) {
        if(index>=0 && index<newObjects.length)
            return newObjects[index];
        return null;
    }

    @Override
    public String delete(int index) {
        if(index>=0 && index<newObjects.length && newObjects[index]!=null){
            T object=newObjects[index];
            newObjects[index]=null;
            return "objects is deleted";
        }
        return null;
    }

    @Override
    public void update(int index,T object) {
        if(index>=0&&index< newObjects.length){
            newObjects[index]=object;
            System.out.println("Object is updated ");
        }
        else
            System.out.println("objects is not updated");
    }
    }

