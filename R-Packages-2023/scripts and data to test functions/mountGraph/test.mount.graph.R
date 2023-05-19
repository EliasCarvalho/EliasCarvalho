# test.mount.graph.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")
# Load packages
library(bnpa)
library(bnlearn)

# load data sets from package
data1<-dataQuantC

###################################################################################
# mount.graph.R - Learn the Bayesian Network Parameters from a BN structure.
###################################################################################
# Load data.work
data.to.work <- data1

# Set the name of a text to save the bn parameters
param.name <- "docbnparamHC.txt"

# Learn a BN structure from data
bn.structure <- hc(data.to.work)

# Generates a BN parameters from the BN structure and write a text file with this
bn.param <- gera.bn.param(bn.structure, data.to.work, param.name)

# Set the graph name
graph.name <- "imgBNHC"

# Save the graph
mount.graph(bn.structure, bn.param, graph.name, data.to.work)
