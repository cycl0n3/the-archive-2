import time
import datetime


o = 733114
print('o: ', o)
print('fromordinal(o): ', datetime.date.fromordinal(o))

t = time.time()
print('t: ', t)
print('fromtimestamp(t): ', datetime.date.fromtimestamp(t))
