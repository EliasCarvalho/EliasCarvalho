# test.preprocess.outliers.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("to your working directory")
# Load packages
library(bnpa)
# Load data sets from package
data(dataQuantC)
# Set parameters to function
variable.content <- dataQuantC$A
variable.name <- "A"
# Preprocess information
preprocess.information <- preprocess.outliers(dataQuantC, variable.content, variable.name)
num.outliers <- preprocess.information[[1]]
variable.content <- preprocess.information[[2]]
mean.of.outliers <- preprocess.information[[3]]
