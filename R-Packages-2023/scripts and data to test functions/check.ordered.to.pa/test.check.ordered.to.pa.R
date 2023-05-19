# test.check.ordered.to.pa.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("~/your working directory")
# Load packages
library(bnpa)
# Load the dataset
data(dataQualiN) # Pre-Loaded
# Build the BN structure
bn.structure<-bnlearn::hc(dataQualiN)
# Show the BN structure learned
bnlearn::graphviz.plot(bn.structure)
# Tranforms variables A and B in ordered factor
dataQualiN$A <- as.ordered(dataQualiN$A)
dataQualiN$B <- as.ordered(dataQualiN$B)
# Generates a list with variables to be ordered and exogenous variables
cat.var.to.use.in.pa <- bnpa::check.ordered.to.pa(bn.structure, dataQualiN)
# Show the variables
cat.var.to.use.in.pa
