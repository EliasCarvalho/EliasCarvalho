# test.boot.strap.bn.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("to your working directory")
# Load packages
library(bnpa)
# Use working data sets from package
data(dataQualiN)
# Start the cluster
cl <- bnpa::create.cluster()
# Set the number of replications
nreplicates=1000
# Set the algorithm to be used
bn.algorithm="hc"
# Executes a parallel bootstrap process
data.bn.boot.strap=bnlearn::boot.strength(data = dataQualiN, R = nreplicates, algorithm =
bn.algorithm, cluster=cl, algorithm.args=list(score="bic"), cpdag = FALSE)
# Release the cluster
parallel::stopCluster(cl)
head(data.bn.boot.strap)
