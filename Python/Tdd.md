```python
import unittest

class TestArray(unittest. TestCase):
    def test_sum(self):
        instance = Array()
        result = instance.sum(6, '1 2 3 4 10 11')
        self.assertEqual(result, 31)

```
