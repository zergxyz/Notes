C# arbitrary paramerters methods/functions example
```c#
class Program
    {
        static void Main(string[] args)
        {
            GreetPersons(0);
            GreetPersons(25, "John", "Jane", "Tarzan");
            Console.ReadKey();
        }


        static void GreetPersons(int someUnusedParameter, params string[] names)
        {
            foreach (string name in names)
            {
                Console.WriteLine("Hello, " + name);
            }

        }
    }
```
