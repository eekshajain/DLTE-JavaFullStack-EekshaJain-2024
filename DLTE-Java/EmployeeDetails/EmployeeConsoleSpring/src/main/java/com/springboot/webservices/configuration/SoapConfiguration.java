package com.springboot.webservices.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ResourceBundle;
@EnableWs
@Configuration
public class SoapConfiguration {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("employee");
    // conversion xsd to wsdl
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,resourceBundle.getString("employee.url.mapping"));
    }

    // wsdl properties
    @Bean(name = "employees")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName(resourceBundle.getString("employee.port"));
        defaultWsdl11Definition.setTargetNamespace(resourceBundle.getString("employee.target.name.space"));
        defaultWsdl11Definition.setLocationUri(resourceBundle.getString("employee.location.uri"));
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public PayloadValidatingInterceptor payloadValidatingInterceptor() {
        PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
        interceptor.setValidateRequest(true);
        interceptor.setValidateResponse(true);
        interceptor.setXsdSchema(employeeSchema()); // Assuming you have defined XsdSchema bean
        return interceptor;
    }

    // identify the xsd
    @Bean
    public XsdSchema employeeSchema(){
        return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
    }
}
