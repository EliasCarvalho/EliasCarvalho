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
# Adding random data to dataQuantC, function will return TRUE
dataQuantC$Z <- round(runif(500, min=0, max=1000),2)
# Converting the numeric variable into factor
dataQuantC$Z <- factor(dataQuantC$Z)
# Check and return a numeric value correspondig to the variable type
# Set the variable name
variable.name = "A"
# identify the type
check.type.one.var(dataQuantC, show.message=1, variable.name)
# Set the variable name
variable.name = "Z"
# identify the type
check.type.one.var(dataQuantC, show.message=0, variable.name)
