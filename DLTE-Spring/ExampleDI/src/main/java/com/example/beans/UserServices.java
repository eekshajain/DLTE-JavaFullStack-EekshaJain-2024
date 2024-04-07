package com.example.beans;

import java.util.ArrayList;
import java.util.List;

public class UserServices {

   private static   List<User> list = new ArrayList<>();

         User u1=new User("Eeksha");
         User u2=new User("Atheesh");
         User u3=new User("Spandana");

public UserServices(){
         list.add(u1);
         list.add(u2);
         list.add(u3);
}

public List<User> getList(){
    return list;
}
}
