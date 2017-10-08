**Old version (JDK <=7)** 
JBDC with SQLServer requirements.
you need these four jars in your classpath:
1. sqljdbc4.jar
2. msbase.jar
3. msutil.jar
4. mssqlserver.jar

http://alvinalexander.com/java/java-sql-server-jdbc-driver-class-url-connection


SQL date type conversion with java util type
```java 
public class MainClass {

  public static void main(String[] args) {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    System.out.println("utilDate:" + utilDate);
    System.out.println("sqlDate:" + sqlDate);

  }

}
``` 
http://www.java2s.com/Tutorial/Java/0040__Data-Type/ConvertfromajavautilDateObjecttoajavasqlDateObject.htm

String to date conversion in Java 
```java
DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
Date thisDate = dateFormat.parse("2012-07-10 14:58:00.000000");
```
other resources related to JDBC programming:
* multiple rows insertion: http://stackoverflow.com/questions/12012592/jdbc-insert-multiple-rows
* multiple optional parameters: http://stackoverflow.com/questions/22227407/have-multiple-optional-parameters
