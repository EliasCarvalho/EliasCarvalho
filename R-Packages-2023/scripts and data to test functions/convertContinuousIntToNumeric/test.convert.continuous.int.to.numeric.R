# test.convert.continuous.int.to.numeric.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")
# Load packages
library(bnpa)
# Load data sets from package
data.to.work <- dataQuantC # Pre-Loaded'
head(data.to.work)
# Converting some variables to int just to test the function
data.to.work$A <- as.integer(data.to.work$A )
data.to.work$C <- as.integer(data.to.work$C )
data.to.work$G <- as.integer(data.to.work$G )
#########################################################################################################################
# convert.continuous.int.to.numeric.R - Convert int data into numeric data.
#########################################################################################################################
data.to.work <- convert.continuous.int.to.numeric(data.to.work)
