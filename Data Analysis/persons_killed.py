# -*- coding: utf-8 -*-
"""
Created on Tue Oct 23 23:27:47 2018

@author: Namrata Jha
"""

#Importing the libraries 
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

#Importing the dataset
dataset= pd.read_csv('data.csv')
X= dataset.iloc[:, 0:1].values
y= dataset.iloc[:,3].values

#Fitting Polynomial to the dataset
from sklearn.preprocessing import PolynomialFeatures
poly_reg= PolynomialFeatures(degree=3)
X_poly= poly_reg.fit_transform(X)

from sklearn.linear_model import LinearRegression
polyreg= LinearRegression()
polyreg.fit(X_poly,y)

#Visualising the Polynomial Regression results
X_grid= np.arange(min(X), max(X), 0.1)
X_grid= X_grid.reshape(len(X_grid), 1)
plt.scatter(X,y, color= 'red')
plt.plot(X_grid,polyreg.predict(poly_reg.fit_transform(X_grid)), color='blue')
plt.title('Polynomial Regression results')
plt.show()

#Making prediction
polyreg.predict(poly_reg.transform(2018))
polyreg.predict(poly_reg.transform(2019))
