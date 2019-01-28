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
look for the usage of interface of property and interfaces 
```c#
class Program
    {
        static void Main(string[] args)
        {
            List<Dog> dogs = new List<Dog>();
            dogs.Add(new Dog("Fido"));
            dogs.Add(new Dog("Bob"));
            dogs.Add(new Dog("Adam"));
            dogs.Sort();
            foreach (Dog dog in dogs)
                Console.WriteLine(dog.Describe());
            Console.ReadKey();
        }
    }

    interface IAnimal
    {
        string Describe();

        string Name
        {
            get;
            set;
        }
    }

    class Dog : IAnimal, IComparable
    {
        private string name;

        public Dog(string name)
        {
            this.Name = name;
        }

        public string Describe()
        {
            return "Hello, I'm a dog and my name is " + this.Name;
        }

        public int CompareTo(object obj)
        {
            if (obj is IAnimal)
                return this.Name.CompareTo((obj as IAnimal).Name);
            return 0;
        }

        public string Name
        {
            get { return name; }
            set { name = value; }
        }
    }
```
Since constants has to be declared immediately and can't be changed later on, the value you assign to a constant has to be a constant expression and the compiler must be able to evaluate the value already at compile time. This means that numbers, boolean values and strings can be used just fine for a constant, while e.g. a DateTime object can't be used as a constant.
