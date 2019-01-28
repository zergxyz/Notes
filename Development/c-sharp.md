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


In c# you are not allowed to override a class method unless it's marked as virtual: 
```c#
public class Animal
{
    public virtual void Greet()
    {
        Console.WriteLine("Hello, I'm some sort of animal!");
    }
}

public class Dog : Animal
{
    public override void Greet()
    {
        Console.WriteLine("Hello, I'm a dog!");
    }
}
```
