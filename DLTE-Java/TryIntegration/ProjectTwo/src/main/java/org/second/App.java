package org.second;

import org.first.Basket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Basket basket=new Basket();
        String[] result=basket.display();
        for(String each:result) System.out.println(each+" ");
    }
}
