# lambda의 기본 문법
```python
lambda [매개변수] : [식]

square = lambda x: x**2;

print(square(2))
```


```python
def reduce(function, iterable, initializer=None):
  it = iter(iterable)
  if initializer is None:
    value = next(it)
  else:
    value = initializer
  for element in it:
    value = function(value, element)
  return value
```
