import collections

d = collections.deque(range(10))
print('Normal: ', d)

d = collections.deque(range(10))
d.rotate(2)
print('Right Rotation: ', d)

d = collections.deque(range(10))
d.rotate(-2)
print('Left Rotation: ', d)
