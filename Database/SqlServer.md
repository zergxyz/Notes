convert datetime to date in SQL: cast(getdate() as date )  https://stackoverflow.com/questions/8901161/convert-a-sql-server-datetime-to-a-shorter-date-format 

How to get the next month day 1 based on current datetime? https://stackoverflow.com/questions/22623971/first-day-of-the-next-month 
``` sql 
DATEADD(d, 1, EOMONTH(current_timestamp))  # sql server 2012+ 
DATEADD(m, DATEDIFF(m, -1, current_timestamp), 0)   # sql 2008 and older 
```
