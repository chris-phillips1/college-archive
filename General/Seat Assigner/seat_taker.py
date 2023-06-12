import random
import os

kids = {'Christopher', 'Kaitlyn', 'Adam'}
rand = list(kids)[random.randint(0, 2)]

current = open('/Users/Chris/Desktop/currentChild.txt', 'w')

current_idx = 0

for kid in kids:
    current_idx += 1
    text_to_write = kid + '\n' if current_idx == len(kids) else kid + ' '

    current.write(text_to_write)

current.write(rand)
current.close()
