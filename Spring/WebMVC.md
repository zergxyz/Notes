Servlet 3.0+ Code-based Servlet container initialization (non spring boot version)
Traditional xml based config should be like this:
```
<servlet>
  <servlet-name>dispatcher</servlet-name>
  <servlet-class>
    org.springframework.web.servlet.DispatcherServlet
  </servlet-class>
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/spring/dispatcher-config.xml</param-value>
  </init-param>
  <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
  <servlet-name>dispatcher</servlet-name>
  <url-pattern>/</url-pattern>
</servlet-mapping>
```
In a Servlet 3.0+ environment, you have the option of configuring the Servlet container programmatically as an alternative or 
in combination with a web.xml file. Below is an example of registering a DispatcherServlet,
in this example, you still have your xml based configuration in the app: 
```java
import org.springframework.web.WebApplicationInitializer;
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");

        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

}
```
WebApplicationInitializer is an interface provided by Spring MVC that ensures your implementation is detected and automatically 
used to initialize any Servlet 3 container.

An abstract base class implementation of WebApplicationInitializer named AbstractDispatcherServletInitializer 
makes it even easier to register the DispatcherServlet by simply overriding methods to specify the servlet mapping and the location 
of the DispatcherServlet configuration. In the following example, you have no xml files at all. Everything is java configured. 
```java
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MyWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
```
You can find more explanations in the spring doc [1] and [2]. One more thing needed to mention here is 
that MyWebConfig class in the above example should be the equivalence class as your xml web config class. 
Implementation example can be found in the blog [4] or you can take a look at the view resolver section example code below.
you can customize your own view resolver in the spring 3+ applications and all you need to do is to extend the WebMvcConfigureAdapter class and write your own web configuration class, more details in [3]
```java
@Configuration
@ComponentScan(basePackages="com")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
```

Reference
* [servlet 3+ initialization in spring](http://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/mvc.html#mvc-container-config)
* [web initializer java dpi](http://docs.spring.io/spring/docs/4.0.x/javadoc-api/org/springframework/web/WebApplicationInitializer.html)
* [How to configure your own view resolver](http://stackoverflow.com/questions/14426947/viewresolver-using-java-annotation)
* [java conig blog](https://rajendersaini.wordpress.com/2013/07/01/step-by-step-spring-mvc-app-using-java-config/)

