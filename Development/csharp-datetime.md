Datetime and String conversion in C# 
```c#
DateTime a = DateTime.Now;
var x = a.ToString("yyyy-MM-dd HH:mm:ss");
Console.WriteLine(x);
var s = Console.ReadLine();
var date = DateTime.ParseExact(s, "yyyy-MM-dd", CultureInfo.InvariantCulture);
//DateTime.TryParseExact() 
Console.WriteLine(date);
Console.ReadKey();
```
