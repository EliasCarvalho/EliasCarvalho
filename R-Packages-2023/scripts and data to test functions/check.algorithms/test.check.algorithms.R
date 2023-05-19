# test.check.algorithms.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("~/your working directory")
# Load packages
library(bnpa)
# Set what BN learning algorithms will be used
bn.learn.algorithms <- c("gs", "hc")
# Check these algorithms
check.algorithms(bn.learn.algorithms)
