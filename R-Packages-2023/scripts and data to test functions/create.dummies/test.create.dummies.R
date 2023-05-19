# test.create.dummies.R
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
# Show the structure before
str(dataQualiN)
# Set possible dummy variables
dummy.vars <- c("A", "B")
# Create dummies
dataQualiN <- bnpa::create.dummies(dataQualiN, dummy.vars)
# Show the structure before
str(dataQualiN)
