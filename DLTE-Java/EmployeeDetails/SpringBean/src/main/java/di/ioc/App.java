package di.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class App {
    public static void main( String[] args )
    {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("springsdispatcher.xml"));
        Branch akashBranch=beanFactory.getBean("branch4", Branch.class);
        System.out.println(akashBranch.getBranchContact()+" "+akashBranch.getBranchName());
        Branch elroyBranch=beanFactory.getBean("branch1",Branch.class);
        System.out.println(elroyBranch);
    }
}
