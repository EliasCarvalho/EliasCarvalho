# test.check.levels.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/your working directory")

# Load packages
library(bnpa)

# Use working datasets from package
data.to.work <- dataQualiN
head(data.to.work)

###################################################################################
# check.levels.R - Check if factor need to be converted in numeric.
###################################################################################

# Adding dichotomic data to data.to.work, function will return TRUE
data.to.work$Z <- round(runif(500, min=0, max=1000),2)

# Converting the numeric variable into factor
data.to.work$Z <- factor(data.to.work$Z)

# Showing the data structure
str(data.to.work)

# Identify all variables with more than 2 factors and convert it to numeric (the 'Z' variable)
level.max <- 2
dataset.name <- "data.to.work"
check.levels(data.to.work, level.max, dataset.name)

# Showing the data structure
str(data.to.work)
