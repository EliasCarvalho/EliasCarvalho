# test.check.variables.to.be.ordered.R
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
# Show first lines of data set
head(dataQualiN)
# Insert categorical variables with more than 2 levels
dataQualiN$test.variable[dataQualiN$A == "yes"] <- "low"
dataQualiN$test.variable[dataQualiN$B == "yes"] <- "medium"
dataQualiN$test.variable[dataQualiN$X == "yes"] <- "high"
# Transform it to factor variable
dataQualiN$test.variable <- as.factor(dataQualiN$test.variable)
# Check the necessity to transform in ordered variables
bnpa::check.variables.to.be.ordered(dataQualiN)
