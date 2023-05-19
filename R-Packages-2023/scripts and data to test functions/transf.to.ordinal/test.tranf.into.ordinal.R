# test.transf.into.ordinal.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("to your working directory")
# Load packages
library(bnpa)
#Load Data
data(dataQualiN)
# Transform all variables into ordinal
dataQualiN <- bnpa::transf.into.ordinal(dataQualiN)
str(dataQualiN)
