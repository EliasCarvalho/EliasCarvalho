# test.check.ordered.one.var.R
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
# Transform variable A into ordered factor
dataQualiN$A <- ordered(dataQualiN$A)
# Check variable A and return TRUE
var.name <- "A"
check.ordered.one.var(dataQualiN, var.name)
# Check variable B and return FALSE
var.name <- "B"
check.ordered.one.var(dataQualiN, var.name)
