# test.check.dichotomicone.var.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("to your working directory")
# Load packages
library(bnpa)
# Use working data sets from package
data(dataQuantC)
head(dataQuantC)
# Show the structure of data set
str(dataQuantC)
# Set variable name
variable.name = "A"
# data set has not dichotomic variables and function will return FALSE
check.dichotomic.one.var(dataQuantC, variable.name)
# Adding dichotomic data to dataQuantC
dataQuantC$Z <- round(runif(500, min=0, max=1),0)
# Show the new structure of data set
str(dataQuantC)
# Set variable name
variable.name = "Z"
# Now data set has dichotomic variables and function will return TRUE
check.dichotomic.one.var(dataQuantC, variable.name)
