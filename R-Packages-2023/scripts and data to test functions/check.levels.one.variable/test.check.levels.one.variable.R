# test.check.levels.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("to your working directory")
# Load packages
library(bnpa)
# Use working data sets from package
data(dataQualiN)
head(dataQualiN)
# Adding random data to dataQualiN, function will return TRUE
dataQualiN$Z <- round(runif(500, min=0, max=1000),2)
# Converting the numeric variable into factor
dataQualiN$Z <- factor(dataQualiN$Z)
# Set the variable name to a non categorical one
variable.name = "Z"
# Count the number o levels of a specific variable
number.of.levels <- check.levels.one.variable(dataQualiN, variable.name)
number.of.levels
# Set the variable name to a categorical variable
variable.name = "A"
# Count the number o levels of a specific variable
number.of.levels <- check.levels.one.variable(dataQualiN, variable.name)
number.of.levels
