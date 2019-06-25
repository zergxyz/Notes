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


try to test with picture uploading : 
![](https://i.imgur.com/anjnuhD.png)

in python multiple inheritence will be interesting as the following example showed: 
``` python 
class A(object):
    def __init__(self):
        self.a = 1
    def x(self):
        print("A.x")
    def y(self):
        print("A.y")
    def z(self):
        print("A.z")

class B(A):
    def __init__(self):
        A.__init__(self)
        self.a = 2
        self.b = 3
    def y(self):
        print("B.y")
    def z(self):
        print("B.z")

class C(object):
    def __init__(self):
        self.a = 4
        self.c = 5
    def y(self):
        print("C.y")
    def z(self):
        print("C.z")

class D(C, B):
    def __init__(self):
        C.__init__(self)
        B.__init__(self)
        self.d = 6
    def z(self):
        print("D.z")
obj = D() 
print(obj.a)  # 2
obj.y()   # C.x
```
The data attributes were set by initialisation so the last wins. The method attributes were inherited so the first one found stops the search. That means D class will inherit both C and B methods and they will not overwrite with each other. 

in numpy we can use the following way to add a new column: 
https://stackoverflow.com/questions/8486294/how-to-add-an-extra-column-to-a-numpy-array  

in python how to use a function to return the string's unicode value? 
https://stackoverflow.com/questions/2352018/cant-use-unichr-in-python-3-1/54354701  use char() in python 3

the usage of map, reduce and filter: http://book.pythontips.com/en/latest/map_filter.html 
