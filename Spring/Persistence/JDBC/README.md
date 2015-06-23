Spring JDBC Summary

You need to use autowired and resource annotation to let spring pick up the data source 
and create the db connection with jdbc template automatically.

you need to have a static final Mapper class for the domain so that you don't need 
to specifically create the anoymous inner class every time in your CRUD operations.
