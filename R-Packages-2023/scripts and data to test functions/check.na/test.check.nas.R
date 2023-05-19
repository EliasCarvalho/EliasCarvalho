# test.check.na.R
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
# Adding NAs to dataQuantC # credits for the random NA code for: https://goo.gl/Xj6caY
dataQuantC <- as.data.frame(lapply(dataQuantC, function(cc) cc[ sample(c(TRUE, NA),
                              prob = c(0.85, 0.15), size = length(cc), replace = TRUE) ]))
# Checking the Nas
check.na(dataQuantC)

