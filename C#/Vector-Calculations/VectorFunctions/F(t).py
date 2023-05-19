import numpy as np
import matplotlib.pyplot as plt
t = np.arange(0, 2.5, 0.1)
x = np.cos(t)
y = np.sin(t)
z = 2 * t
plt.plot(t, x, label='x')
plt.plot(t, y, label='y')
plt.plot(t, z, label='z')
plt.legend()
plt.show()
