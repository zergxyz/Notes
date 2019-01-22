convert datetime to date in SQL: cast(getdate() as date )  https://stackoverflow.com/questions/8901161/convert-a-sql-server-datetime-to-a-shorter-date-format 

---
**How to get the next month day 1 based on current datetime?**
``` sql 
DATEADD(d, 1, EOMONTH(current_timestamp))  # sql server 2012+ 
DATEADD(m, DATEDIFF(m, -1, current_timestamp), 0)   # sql 2008 and older 
# example: get next 2 month's first day: 
select DATEADD(d, 1, EOMONTH(DATEADD(d, 1, EOMONTH(current_timestamp)))) 
```
reference here: https://stackoverflow.com/questions/22623971/first-day-of-the-next-month  

---
**Using T-sql to remove part of string before or after specific characters; to get the substring between some specific strings : 
the general query is like this:** 
``` sql 
#remove after "*"
SELECT LEFT(string_expression, CHARINDEX(expression_to_find, string_expression) - 1)
#remove before "*"
SELECT REPLACE(SUBSTRING(string_expression, CHARINDEX(expression_to_find, string_expression), LEN(string_expression)), string_pattern, string_replacement)

# my demo query: parse contnets started with "assessment / plan"
select 
b.*, 
REPLACE(SUBSTRING([NoteContents], 
CHARINDEX('ASSESSMENT / PLAN', [NoteContents]),
LEN([NoteContents])), 'ASSESSMENT / PLAN', '') AS Notes
```
more detialed can be found here:
* https://basitaalishan.com/2014/02/23/removing-part-of-string-before-and-after-specific-character-using-transact-sql-string-functions/ 
* https://stackoverflow.com/questions/18362260/a-sql-query-to-select-a-string-between-two-known-strings 

How to insert the auto current date when a new row is added into the table? 
https://www.itsupportguides.com/knowledge-base/sql-server/sql-server-how-to-set-a-datetime-field-to-automatically-use-current-datetime/ 
basically you can change the default value from the design mode from SSMS. 

the way of escaping single quote in sql-server: 
https://stackoverflow.com/questions/1586560/how-do-i-escape-a-single-quote-in-sql-server 
you use '' to escape single '

How to deal with pivot rows and columns? 
https://stackoverflow.com/questions/29569801/dynamic-pivot-needed-with-row-number 
