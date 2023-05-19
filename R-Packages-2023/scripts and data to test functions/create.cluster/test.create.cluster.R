# test.create.cluster.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")
# Load the "bnpa" package
library(bnpa)
# Use working datasets from package
data(dataQualiN)
# start the cluster
cl <- bnpa::create.cluster()
# Executes a parallel bootstrap process
data.bn.boot.strap = bnlearn::boot.strength(data = dataQualiN, R = 1000, algorithm = "hc", cluster = cl, algorithm.args = list(score = "bic"), cpdag = FALSE)
# Release the cluster
parallel::stopCluster(cl)
