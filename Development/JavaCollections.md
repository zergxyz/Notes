how to implement the ArrayList contain method for user defined object? (https://www.mkyong.com/java/java-how-to-overrides-equals-and-hashcode/)

Basically you need to override Equal() and hashCode() method for your target class. For JDK 7 and above: you can use the new Objects class to generate the equals and hash code values. 
```java
import java.util.Objects;

public class User {
    private String name;
    private int age;
    private String passport;

	//getters and setters, constructor

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, passport);
    }

}
```
