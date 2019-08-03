How to use default dict in python to convert list of tuples to dictionary: 
```python
from collections import defaultdict, Counter
a= [('the', 1143), ('and', 966), ('to', 762), ('of', 669), ('i', 631),
 ('you', 554),  ('a', 546), ('my', 514), ('hamlet', 471), ('in', 451)]
# topK=[k for k,v in a]
# #print(topK) 
# word_representations={} 
# for k in topK:
#     word_representations[k]=defaultdict(float)
# print(word_representations)

b = defaultdict(float) 
for k, v in a:
  b[k]=v
print(b['hamlet'])

```
