import collections

d1 = {}
d1['a'] = 'A'
d1['b'] = 'B'
d1['c'] = 'C'

d2 = {}
d2['c'] = 'C'
d2['b'] = 'B'
d2['a'] = 'A'

print('d1:', d1)
print('d2:', d2)
print('dict: d1 == d2:', d1 == d2)

d1 = collections.OrderedDict()
d1['a'] = 'A'
d1['b'] = 'B'
d1['c'] = 'C'

d2 = collections.OrderedDict()
d2['c'] = 'C'
d2['b'] = 'B'
d2['a'] = 'A'

print('d1:', d1)
print('d2:', d2)
print('OrderedDict: d1 == d2:', d1 == d2)
