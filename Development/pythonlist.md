```python
# The first line creates a new list and associates the name a with it.
a = [7, 4, 8]

# The next line uses append to add a 9 to the end of a. Note that this worked by modifying the list in memory. 
# But also recall that append will return None. So while the value associated with a is our updated list, 
# the value associated with the name b is now None.
b = a.append(9)
print(a)
print(b)

# The next thing we see is an error message! In particular, we see:
# AttributeError: 'NoneType' object has no attribute 'append' 
# Note that, as we have seen with other error messages, this tells us something useful: 
# we were trying to use append, but the thing we were trying to append to was 
# of the wrong type (it was None, and it doesn't make sense to append things to None!).
c = b.append(9)

# Note also that, when this error happens, Python will stop running; so it will never get to 
# the last three print statements.
print(a)
print(b)
print(c)
```





