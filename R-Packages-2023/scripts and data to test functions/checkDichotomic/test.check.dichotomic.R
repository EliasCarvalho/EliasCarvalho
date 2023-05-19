# test.check.dichotomic.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/your working directory")

# Load package
library(bnpa)

# Use working datasets from package
data.to.work <- dataQuantC
head(data.to.work)

###################################################################################
# check.dichotomic.R - Verify if some variables of a dataset is dichotomic
###################################################################################

# Show the structure of dataset
str(data.to.work)

# dataset has not dichotomic variables and function will return FALSE
check.dichotomic(data.to.work)

# Adding dichotomic data to data.to.work
data.to.work$Z <- round(runif(500, min=0, max=1),0)

# Show the new structure of dataset
str(data.to.work)

# Now dataset has dichotomic variables and function will return TRUE
check.dichotomic(data.to.work)
