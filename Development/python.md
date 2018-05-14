how to deal with switch dictionary key and values with the following case? 
``` python
#from this form 
dict = {'Accurate': ['exact', 'precise'], 
        'exact': ['precise'], 
        'astute': ['Smart', 'clever'], 
        'smart': ['clever', 'bright', 'talented']}
        
# to this form
dict = {'precise': ['accurate', 'exact'], 
        'clever': ['astute', 'smart'], 
        'talented': ['smart'], 
        'bright': ['smart'],
        'exact': ['accurate'],
        'smart': ['astute']}
```
the answer code can be written as followed: 
```python
newdict = {}
for key, value in olddict.items():
    for string in value:
        newdict.setdefault(string, []).append(key)
```
reference link: https://stackoverflow.com/questions/35945473/how-to-reverse-a-dictionary-in-python 
