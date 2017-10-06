we usually use mysql/sqlserver/db2 databases as our datasources for my projects and I will list all these sample code here:
mysql datasource example with java config (this example put all values into spring boot’s property files)
``` java
@Bean(name = "certainDB")
public DataSource dataSource() {
DriverManagerDataSource dataSource = new DriverManagerDataSource();
dataSource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
dataSource.setUrl(propertyResolver.getProperty("url"));
dataSource.setUsername(propertyResolver.getProperty("username"));
dataSource.setPassword(propertyResolver.getProperty("password"));
return dataSource;
}
```

sql server example with java config: 
``` java 
@Bean(name = "icuDEV")
	public DataSource getICUDEVSource() throws SQLException {
		final String strPssword = "xxxxxxx";
		EncryptionService.setKey(strPssword);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(icuDriver);
		dataSource.setUrl(icuDEV);
		EncryptionService.decrypt(acc64);
		dataSource.setUsername(EncryptionService.getDecryptedString());
		EncryptionService.decrypt(acc64pwd);
		dataSource.setPassword(EncryptionService.getDecryptedString());
		return dataSource;
	}
```

db2 datasource example: 
we need to get 2 jars from IBM's product, in your folder:C:\Program Files\IBM\SQLLIB\java
find the following 2 items and add them to your IDE build path.

	* db2jcc4.jar
	* db2jcc4_license_cu.jar

If you are using maven, please add my personal repository and these dependencies in your pom. 
examples like this: 
pom examle here

The configuration for ibm db2 will be similar like this:
``` xml
<bean id="dataSource_EDT"
    class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.ibm.db2.jcc.DB2Driver" />
    <property name="url" value="jdbc:db2:dbservername/dbname" />
    <property name="username" value="xxx" />
    <property name="password" value="xxx" />
</bean>
```
Then you can start using this connection in your java/spring application
Reference:

* [1] (http://razorsql.com/docs/help_db2.html) 
* [how to deal with the type 4 connection in db2?](http://stackoverflow.com/questions/46664/ibm-db2-type-4-driver)
* [3] (http://www-01.ibm.com/support/knowledgecenter/SSEPGG_10.1.0/com.ibm.db2.luw.apdv.java.doc/src/tpc/imjcc_cjvjcskb.html?lang=en
)
	
