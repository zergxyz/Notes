You need to use autowired and resource annotation to let spring pick up the data source 
and create the db connection with jdbc template automatically.

you need to have a static final Mapper class for the domain so that you don't need 
to specifically create the anoymous inner class every time in your CRUD operations.

SpringJDBC.java showed you how to use springjdbc template correctly.

AuthorityRepository is an example for you to knwo how use NamedParameterJdbcTemplate 
in your code. 

how to use lambda to check hastmap/table? 
https://stackoverflow.com/questions/30608360/java-8-extract-first-key-from-matching-value-in-a-map 
https://stackoverflow.com/questions/49261514/how-to-get-a-csv-list-from-jdbctemplate
https://stackoverflow.com/questions/6917906/return-type-for-jdbctemplate-queryforlistsql-object-classtype 

