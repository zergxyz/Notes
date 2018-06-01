How to deal with following error?
Error configuring: org.springframework.boot:spring-boot-maven-plugin. Reason: Unable to retrieve component configurator for plugin configuration
AN: you need to upgrade the maven to version 3.x to make the spring-boot maven plug-in work correctly.

Spring logging
You can use bom ( bill of materials ) and add it to your dependent management section to make sure all your spring dependencies including transitive ones are using the same  version.
Once you add the bom, you no longer need to specify the version of spring
Spring uses JCL for logging. In maven it's the common-logging module.
How to switch the comon-login?
“dependencies>
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-core</artifactId>
<version>4.2.4.RELEASE</version>
<exclusions>
<exclusion>
<groupId>commons-logging</groupId>
<artifactId>commons-logging</artifactId>
</exclusion>
</exclusions>
</dependency>
</dependencies>”

DI :
Constructor based
Setter based
In spring application, we have 2 ways of IOC, usually DI is preferred if it’s possible (for example Spring MVC will do this for you) but sometimes we have to use dependency lookup to programmatically get the dependency if you use spring as a standalone java program through bean factory.

application context is an extension of bean factory, not only provide DI but other services like AOP, messages service and event handling.
An example of using application context to register and get bean can be found in this note: Example of @EnableScheduling in Spring

@autowired == @inject == Resource (name = beanname).
@autowired and @value(sas) can be only applied to one constructor for DI if the class has more than one constructors.

Basic boot configuration
you just need to have spring boot starter in your maven pom as the parent dependency. Then add whatever you need it like[1]:
spring boot starter web for the rest and mvc
spring boot starter batch for the spring batch
starter-ampq for the rabbitMQ
In the main method, Class SpringApplication is a boot specific component and the default entry for most of the spring boot applications. It will take care of spring application context for us [1].
For maven package, it will generate 2 jars and The .original file is the output of the default Maven compiler, while the other one is the jar file enhanced by Spring Boot’s plugin.

About the property files, you can use application.properties/yaml to override the default app settings. More information can be found in the spring doc [2]. Default configs can be found in the org.springframework.boot.autoconfigure.* packages

Boot Actuator
provide basic information as followed [1]:
/health – returns “ok” as text/plain content which is useful for simple service monitoring
/env – check environment configuration, property file and command line argument overrides, active profiles
/metrics – basic statistics on your service endpoints (e.g. hit count, error count)
/dump – thread dump
/trace – the latest HTTP request/response pairs
Caveats:
logback and slf4j as the default logging libs. Use others may cause issues.
executable jar files added multi-layer nested jars inside [1]
JSP may have limitted support in the embedded server [3]
Use spring boot to render static html contents, you can refer to the spring boot static example here: https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-web-static

Reference:
[1] Experiences with Spring Boot: https://www.opencredo.com/2014/02/24/experiences-with-spring-boot/
[2] External your configuration files: http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
[3] github issue for JSP support in Spring boot: https://github.com/spring-projects/spring-boot/issues/367

how to load the xml configurations in spring boot app?
basically you need to use
@ImportResource("classpath:config.xml")
to import the xml file you want to involve and then use
 ConfigurableApplicationContext context = SpringApplication.run(Config.class);
DataSource dataSource = context.getBean("dataSource", DataSource.class);
to get your target bean.
a working example can be found here with spring batch: provide my source code for loc
reference: http://stackoverflow.com/questions/25495629/how-can-i-use-spring-boot-auto-configured-beans-in-xml-configuration-files

Send email with spring boot:
http://www.concretepage.com/spring/spring-gmail-smtp-send-email-with-attachment-using-annotation
Spring property file reference: http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

How to use component scan to include multiple packages?
<context:component-scan base-package="x.y.z.service, x.y.z.controller" />

How to get external properties from meta file ?
http://www.mkyong.com/spring/spring-propertysources-example/
you must register the propertysourcesplaceholderconfiguration method in order to make it work.

How to serve static contents in spring boot?
http://stackoverflow.com/questions/29018892/how-add-static-web-content-in-spring-boot
the key point is to put them under the resources/static folder

How to deal with this error--only one asyncannotationbeanpostprocessor-may-exist
https://stackoverflow.com/questions/5440429/springs-scheduled-error-only-one-asyncannotationbeanpostprocessor-may-exist
the problem is we can not initialize the < task:annotation-driven />
twice in one file when we happened to import another xml configuration file into the current file and both of them have above lines of code.

how to import multiple resource inside @ImportResource
https://stackoverflow.com/questions/15004674/spring-3-importresource-with-multiple-files
using {"config-1.xml", "config-2.xml"} to make it work. 


