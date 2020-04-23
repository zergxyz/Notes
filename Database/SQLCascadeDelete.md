How to enable cascade deletion in sql? 
``` sql

ALTER TABLE dbo.T2
   DROP CONSTRAINT FK_T1_T2   -- or whatever it's called

ALTER TABLE dbo.T2
   ADD CONSTRAINT FK_T1_T2_Cascade
   FOREIGN KEY (EmployeeID) REFERENCES dbo.T1(EmployeeID) ON DELETE CASCADE
   
```
https://stackoverflow.com/questions/6260688/how-do-i-use-cascade-delete-with-sql-server 
