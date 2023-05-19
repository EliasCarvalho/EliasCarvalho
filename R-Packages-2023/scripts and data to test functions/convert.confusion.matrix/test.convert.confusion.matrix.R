# test.convert.confusion.matrix.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/your working directory")
# Load package
library(bnpa)
# Creates a confusion matrix
confusion.matrix <-matrix(c(12395, 4, 377, 1), nrow=2, ncol=2, byrow=T)
# Creates a vector with the position of VP, FP, FN, VN
cm.position <- c(4,3,2,1)
# Shows the original confusion matrix
confusion.matrix
# Converts the confusion matrix
confusion.matrix <- convert.confusion.matrix(confusion.matrix, cm.position)
# Shows the converted confusion matrix
confusion.matrix
