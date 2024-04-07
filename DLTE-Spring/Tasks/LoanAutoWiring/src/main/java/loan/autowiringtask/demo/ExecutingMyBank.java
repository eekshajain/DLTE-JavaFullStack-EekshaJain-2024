package loan.autowiringtask.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutingMyBank {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext=new AnnotationConfigApplicationContext();//bean without xml
        configApplicationContext.scan("loan.autowiringtask.demo");
        configApplicationContext.refresh();
        MyBank myBank=configApplicationContext.getBean(MyBank.class);
        System.out.println(myBank.callFindAll());
    }
}
