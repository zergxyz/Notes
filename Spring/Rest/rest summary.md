The key concept is the resource. Anything can be accepted or manipulated should be considered as the resource.
URI templates: it's used to let the client know the structure of uri.
Content negotiation: the client can request different format of resource like Json, xml or PDF.  
Client should use the accept header to inform the type of format it can accept for the resource representation.
Spring use content type rad to determine the Json or xml format. 

Http request will return
* Meta data: header information
* Resource: your rest data
Get: read only operation.
Head: when checking for existence, get is heavyweight while head is good for.  It will only retrieve your resource meta data.  Small bandwidth and lightweight
Patch: is used to do partial update in the http request.

"root context", in terms of a web application, means the main context that's loaded and used by the webapp. Typically, you'll start the root context with a ContextLoaderListener.
The root context isn't really a "kind" of context. It's just a role that a context plays. You have one root context in a webapp. Other contexts are not the root context. They're usually children of the root context.
A namespace here refers to the scope of an instance of Spring's DispatcherServlet. All it's saying right there is that if you name your servlet "test" in your web.xml, then by convention, Spring will look for a file named "test-servlet.xml" to use as that dispatcher's context. Incidentally, each context like this which is created for a dispatcher becomes a child of the root context.

ApplicationContext instances in Spring can be scoped. In the Web MVC framework, each DispatcherServlet has its own WebApplicationContext, which inherits all the beans already defined in the rootWebApplicationContext. These inherited beans can be overridden in the servlet-specific scope, and you can define new scope-specific beans local to a given Servlet instance.
```java
* @EnableTransactionManagement
* @EnableAsync
* @EnableScheduling
* @EnableLoadTimeWeaving
* @EnableWebMvc
```
The above are available annotations which can be used in java config instead of xml tags

Restful service
* different than traditional mvc app to rely on view technology to render data to HTML, rest app will return an object 
(in Jason or xml format)
* @Restcontroller annotation must be used to let spring know every method in the controller return a domain object.
It’s equal to have (@Controller and @ResonseBody)
* Spring includes jackson2 in the class path so it will automatically use MappingJackson2HttpMessageConverter to return a JSON response.
Spring can provide producer and consumer functions for restful service.  the basic dependencies to build rest service are as followed [1] :
 ```xml
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.2.5.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
   <build>

        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```
you can change the version if needed. But spring boot is the most convenient way to build up the modern spring applications. The benefits of using spring boot (with maven plugin ) is that it will collect all the dependent libs and generate a runnable uber-jar for you and let you directly start your app/web.

All HTTP requests are handled by the controllers, you can use @Resrcontroller to let spring build rest web services [1].

Get
```java
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}

@RequestMapping(method=GET) is used to specify the type of http request, in the above example we handle get request.
//@RequestParam is used to bind the query string value “name” to the name parameter. 
//The query string parameter is not required so the default value “World” will be used if name 
//value is not passed in to the greeting request.
//you can have another way like following example to make it work [3]:
 @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

please notice that you can have 2 way of api representation for the get request [6]:
@RequestMapping("person/{id}”) // url  = http://xxx.xxx/person/a
    @ResponseBody
    public Person getById(@PathVariable Long id) {
        return personService.getById(id);
    }
    // same as above method, url  = http://xxx.xxx/person?id=a
    @RequestMapping(value="person", params="id")
    @ResponseBody
    public Person getByIdFromParam(@RequestParam Long id) {
        return personService.getById(id);
    }
```
POST: This example [2] is used to demo how to handle http post request in the spring rest controller.
```java
@RequestMapping(value = "/user", method = RequestMethod.POST)
    public User getUserbyUsername(@RequestBody User u) {
        User p = uServ.login(u.getUsername(), u.getPassword());
        if(!p.getUsername().equalsIgnoreCase("invalid,please try again")){
            p.setStatus("success");
            p.setMessage("Logged in successfully.");
        }
        else{
            p.setStatus("error");
            p.setMessage("Invalid credentials");
        }
        return p;
    }
```
You can tell in the post request, spring will consider the request body object and map it to the
domain object and return with your target JSON response.

Other requests (PUT and DELETE): There are some examples for put and delete requests [4] and [8]
```java
@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
  @ResponseStatus( HttpStatus.OK )
  public void update( @RequestParam( "id" ) Long id, @RequestBody Foo resource ){
      Preconditions.checkNotNull( resource );
      RestPreconditions.checkNotNull( service.getById( resource.getId() ) );
      service.update( resource );
  }
  @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
  @ResponseStatus( HttpStatus.OK )
  public void delete( @RequestParam( "id" ) Long id ){
      service.deleteById( id );
  }

Run a standalone application:
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
By using @SpringBootApplication, you can make a tomcat embedded standalone web app. It’s an equivalence to having following things:
* @Configuration: tag the class as a source of bean in the app
* @EnableAutoConfiguration: tells spring to start app based on bean definitions in the class path and property files.
* @EnableWebMvc: spring boot app will add this one when it sees the starter-web dependency in your maven pom.
* @ComponentScan: will find all the beans, components, services and controllers in your defined packages.
 By using spring boot app, you can have pure java-config based web app (no web.xml since it’s using servlet 3.x ).

How to deploy your app
* executable jar: This is the default build in the boot application.
* convert to a war file: If you want to do the traditional war deployment, follow the instructions from spring boot doc here [5]. Basically you need to extend the servlet initializer in your application class:
```java
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
     @Override
     protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
          return application.sources(Application.class);    }
     public static void main(String[] args) throws Exception {
          SpringApplication.run(Application.class, args);    }
}
```
and change the maven pom to build war instead of jar, finally make the tomcat dependency scope as provided.

Filters
You need to add CORS filter to make your rest service available to external app/clients. 
In order to make it work, you can refer this link: (https://github.com/zergxyz/adt/blob/master/src/main/java/certain/adt/config/CORSFilter.java )

Message Converter
how to return both json and xml based on the request header?

Asynchronous implementation

Rest services consumer/clients

App Monitor and Management
take a look at spring actuator project: (https://spring.io/guides/gs/actuator-service/)


Project Implementation: 

I will follow and finish 2 open source projects to make sure I got everything in the spring rest app
* bike2: (https://github.com/michael-simons/biking2) 
* trackr: (http://blog.techdev.de/trackr-an-angularjs-app-with-a-java-8-backend-part-i/) 


Older version (Spring 3)
As of Spring 3.1, the @Configuration are excluded from classpath scanning by default – see this JIRA issue. Before Spring 3.1 though, these classes should have been excluded explicitly:
excludeFilters = { @ComponentScan.Filter( Configuration.class ) }
The Spring framework supports 2 ways of creating RESTful services:
- using MVC with ModelAndView
- using http message converters
The new @EnableWebMvc annotation does a number of useful things – specifically, in the case of REST, it detect the existence of Jackson and JAXB 2 on the classpath and automatically creates and registers default JSON and XML converters. The functionality of the annotation is equivalent to the XML version.
The filters in the xml based version needs to be added in the web.xml like following part:
```xml
<filter>
     <filter-name>cors</filter-name>
     <filter-class>com.fanlei.controller.CorsFilter</filter-class>
</filter>
<filter-mapping>
     <filter-name>cors</filter-name>
     <url-pattern>/*</url-pattern>
</filter-mapping>
```
in your rest controllers, you can return ResponseEntity object which will give you full control over HTTP response including header methods.
sample code can be found here:

* It is a good idea to use api.xxx.com as your api service domain to avoid possible domain name collision. 
* Resource end points are preferred to use plural nouns 
* Uri may have hierarchy for resources
In One-to-Many and Many-to-One relationships, the Many part of the relationship is always the Owning side. That means :
```java
@ManyToOne
    @JoinColumn(name = "OPTION_ID")
    private Option option;
```
The vote class will be the owing side and will add the column option_id here.

Securing rest api:
* session based security: out dated and non-scalable
* http basic auth: stateless
* OAuth
    * Resource Owner: user with F/T/G account
    * Client: your target application which will use your F/T/G to login
    * Authorization Server: F/T/G account authentication
    * Resource Server: APIs from F/T/G

How to deal with response entity with a bad request?
http://stackoverflow.com/questions/16232833/how-to-respond-with-http-400-error-in-a-spring-mvc-responsebody-method-returnin




Reference:
* [1] get-started to build a spring rest service: https://spring.io/guides/gs/rest-service/
* [2] CERTAIN Patient resource api project:  give github link here
* [3] get-started spring actuator https://spring.io/guides/gs/actuator-service/
* [4] How to build a rest service with spring 4 and java config http://www.baeldung.com/2011/10/25/building-a-restful-web-service-with-spring-3-1-and-java-based-configuration-part-2/
* [5]: How to deploy a war in spring boot  http://docs.spring.io/spring-boot/docs/1.2.3.RELEASE/reference/htmlsingle/#howto-create-a-deployable-war-file
* [6] rest mvc with Json: http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
* [7] asynchronous call back and template: http://xpadro.blogspot.com/2014/01/migrating-spring-mvc-restful-web.html
* [8] rest api with spring boot: http://ryanjbaxter.com/2014/12/17/building-rest-apis-with-spring-boot/
* Spring Rest—Book code repository: https://github.com/bava/springrest-book/tree/master/Chapter4/final/quick-poll-ch4-final/src/main


Readings: 
* Spring Rest—Book:  https://github.com/bava/springrest-book
* Pro Spring —Book
* data rest with reactJS: https://spring.io/blog/2015/09/01/react-js-and-spring-data-rest-part-1-basic-features
* Spring Rest Courses: http://courses.baeldung.com/courses/rest-with-spring-starter


