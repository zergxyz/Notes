Default security configuration: 
After you include relative security libs into your application, the spring security will pop up default security for your application resources, you can use following parts if needed:
default username: user
password can be configured by writing 
     security.user.password=12345
into the application.properties file 

Reference: 
* [1] (http://stackoverflow.com/questions/22178868/oauth2-java-config-jdbc) 
* [2] (https://github.com/spring-projects/spring-security-oauth/blob/master/samples/oauth2/sparklr/src/main/java/org/springframework/security/oauth/examples/sparklr/config/OAuth2ServerConfig.java)
