# test.check.types.R
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
# Show first lines of data set
head(dataQuantC)
# Check and return a numeric value
show.message <- 1
bnpa::check.types(dataQuantC, show.message)
# Adding random data to dataQuantC, function will return TRUE
dataQuantC$Z <- round(runif(500, min=0, max=1000),2)
# Converting the numeric variable into factor
dataQuantC$Z <- factor(dataQuantC$Z)
# Check and return a numeric value correspondig to: 1=integer, 2=numeric, 3=factor, 4=integer and
# numeric, 5=integer and  factor, 6=numeric and factor or 7=integer, numeric and factor.
show.message <- 1
bnpa::check.types(dataQuantC, show.message)
# Supressing the message
show.message <- 0
bnpa::check.types(dataQuantC, show.message)
