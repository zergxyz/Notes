My personal problem solving and learning notes/summaries in JDK 7, 8 learning and usage.

how to deal with the date manipulation in Java
to get the previous date based on current date, you can use following way:
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //

yyyy-MM-DD HH:mm:ss

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
reference: http://stackoverflow.com/questions/11425236/get-yesterdays-date-using-date

How to run jar file from a java program/code?
http://stackoverflow.com/questions/4936266/execute-jar-file-from-a-jva-program
how to let process wait until its finished?
use proc.waitFor();
then close everything here
http://stackoverflow.com/questions/12448882/java-wait-for-exec-process-till-it-exits
use process builder as a better solution: http://stackoverflow.com/questions/17985036/run-a-jar-file-from-java-program

how to write exception information to the file/email?
http://stackoverflow.com/questions/9923673/write-exceptions-to-file

how to kill the subprocess if it's hanging there with process.waitFor() method there?
http://stackoverflow.com/questions/3107044/preparedstatement-with-list-of-parameters-in-a-in-clause

how to get previous date?
http://stackoverflow.com/questions/11425236/get-yesterdays-date-using-date

Why is Java Vector class considered obsolete or deprecated?
Vector synchronizes on each individual operation. That's almost never what you want to do.Generally you want to synchronize a whole sequence of operations. Synchronizing individual operations is both less safe (if you iterate over a Vector, for instance, you still need to take out a lock to avoid anyone else changing the collection at the same time, which would cause a ConcurrentModificationException in the iterating thread) but also slower (why take out a lock repeatedly when once will be enough)?Of course, it also has the overhead of locking even when you don't need to.Basically, it's a very flawed approach to synchronization in most situations. As MrSpandex pointed out, you can decorate a collection using the calls such as Collections.synchronizedList - the fact that Vector combines both the "resized array" collection implementation with the "synchronize every operation" bit is another example of poor design; the decoration approach gives cleaner separation of concerns.

How to convert mysql datetime type to java date type in jdk 7?
http://stackoverflow.com/questions/21162753/jdbc-resultset-i-need-a-getdatetime-but-there-is-only-getdate-and-gettimestamp

How to modify a meta data file/config file inside a jar file?
use jar uf jar-file input-file
http://docs.oracle.com/javase/tutorial/deployment/jar/update.html

How to run jar file from windows task scheduler?
http://stackoverflow.com/questions/15783553/run-a-jar-file-using-windows-scheduler
you need to configure your user group in group policy and add "Logon as a batch job" permission to your account: https://technet.microsoft.com/en-us/library/cc755659(v=ws.10).aspx
* Go to the Start menu.
* Run.
* Type secpol.msc. ...
* The Local Security Policy manager opens.
* Go to Security Settings - Local Policies - User Rights Assignment node.
* Double click Log on as a batch job on the right side.
how to do encryption in java?
http://blog.sortedset.com/how-to-encrypt-decrypt-a-password-stored-in-a-properties-file-with-java-jasypt-apache-commons-configuration/

How to get previous date/time based on current date and time?
              DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
              Date hospDT = df.parse( cl.getHospDT());
              Calendar cal = Calendar. getInstance();
               cal.setTime( hospDT);
               cal.add(Calendar. YEAR , -1);
              String checkDT = df.format( cal.getTime());
// you can also get previous date only by using calendar instances:
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd" );
String eDate = dateFormat .format(new Date());
Calendar cal = Calendar. getInstance();
cal.add(Calendar. DATE , -1);
Date startDT = cal.getTime();
String sDate = dateFormat .format(startDT );

asdasdasda

how to get 2 year previous date for the java datetime ?
http://stackoverflow.com/questions/14946886/store-current-date-and-date-1-year-from-current-in-java

JDBC query for int replacement:
https://www.mkyong.com/spring/jdbctemplate-queryforint-is-deprecated/

datetime to calendar conversion
https://www.mkyong.com/java/java-convert-date-to-calendar-example/



how to deal with inner class and builder pattern in Java ?
http://stackoverflow.com/questions/18825718/accessing-private-instance-variable-of-inner-class-from-outer-class
https://en.wikipedia.org/wiki/Builder_pattern
https://blog.codecentric.de/en/2016/05/reducing-boilerplate-code-project-lombok/

how to set up number of digits after dot in java double/float data type? 
http://stackoverflow.com/questions/7415733/how-to-convert-double-to-2-number-after-the-dot

get java8 datetime duration: using duration periods and chronunit 
https://www.mkyong.com/java8/java-8-period-and-duration-examples/

new datetime format for jdk8 api: https://www.mkyong.com/java8/java-8-how-to-format-localdatetime/

Java 8 using flatMap to convert list of list to a single list: 
https://stackoverflow.com/questions/23112874/java-8-merge-lists-with-stream-api

how to convert sql timestamp to localDatetime in java8?
https://stackoverflow.com/questions/23263490/how-to-convert-java-sql-timestamp-to-localdate-java8-java-time

String decimal and bigdecimal conversion: 

You want to try String.format("%f", d), which will print your double in decimal notation. Don't use BigDecimal at all.

Regarding the precision issue: You are first storing 47.48 in the double c, then making a new BigDecimalfrom that double. The loss of precision is in assigning to c. You could do

BigDecimal b = new BigDecimal("47.48")
to avoid losing any precision.



We are generating our jpa access layers with MyEclipse. Afterwards we have the generated models and data layer access services. We ran into some problems for some fields with a defined precision.

Entity:

@Entity
public class TestEntity{
   @Column(name="DECTEST", scale = 3, precision = 13)
   BigDecimal decTest;

}
way 1: 
TestEntity te = new TestEntity();
BigDecimal decTest = new BigDecimal(1.2);
te.setDecTest(decTest.setScale(3,RoundingMode.HALF_UP);
TestEntityService.save(te);

way:2
public void setDecTest(BigDecimal decTest) {
    this.decTest = decTest.setScale(3, RoundingMode.HALF_UP));
}

reference: http://stackoverflow.com/questions/12395281/convert-double-to-bigdecimal-and-set-bigdecimal-precision

http://stackoverflow.com/questions/13166386/parsing-string-to-double-java





