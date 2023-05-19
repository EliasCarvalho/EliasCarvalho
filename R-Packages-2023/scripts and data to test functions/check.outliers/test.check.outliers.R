# test.check.ordered.to.pa.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("to your working directory")
# Load packages
library(bnpa)
# Load the data set
data(dataQuantC) # Pre-Loaded
# Set a variable to ask before remove outlier or not
ask.before = "Y" # or ask.before = "N"
# Call the procedure to check if there are outliers
dataQuantC <- check.outliers(dataQuantC, ask.before)
