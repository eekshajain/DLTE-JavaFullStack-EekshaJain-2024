package transaction.jdbctemplate.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
       ApplicationContext applicationContext= SpringApplication.run(DemoApplication.class, args);
       for(String s:applicationContext.getBeanDefinitionNames()){
           System.out.println(s);
       }
    }

}
