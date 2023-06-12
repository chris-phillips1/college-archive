# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import numpy as np

# Determines the number of temperatures to convert
def numElements(min, max, incr):
    num = int((max - min) / incr + 1)
    return num

# Create an array storing temperatures in Celsius
def createCelsiusArray(min, max, incr):
    cels = np.arange(min, max + 1, incr)
    return cels

# Convert one value from celsius to fahrenheit
def convertToFahrenheit(celsius):
    fahrenheit = (9.0 / 5.0) * celsius + 32.0
    return fahrenheit

# Create the fahrenheit array
def createFahrenheitArray(celsius):
    fahr = np.zeros(numElements(-40, 100, 5))
    for i in range(numElements(-40, 100, 5)):
        fahr[i] = convertToFahrenheit(celsius[i])
    
    return fahr

# Test the numElements method
print(numElements(-40, 100, 5))

# Test the createCelsiusArray method
cels = createCelsiusArray(-40, 100, 5)
print(cels)

# Test the conversion method
print(convertToFahrenheit(0))

# Test the createFahrenheitArray method
print(createFahrenheitArray(cels))