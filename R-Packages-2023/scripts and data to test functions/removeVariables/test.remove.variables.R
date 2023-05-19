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
#########################################################################################################################
# gera.bn.param.R - Learn the Bayesian Network Parameters from a BN structure.
#########################################################################################################################
# Load data.work
data.to.work <- data1
# Choose what variables will remove from dataset
vars.to.remove = c('C','E')
# Inform the name of data set
dataset.name = "data.to.work"
# Call the function to remove it and update the dataset
data.to.work <- remove.variables(data,vars.to.remove)



# Set the name of a text to save the bn parameters
param.name <- "docbnparamHC.txt"
# Learn a BN structure from data
bn.structure <- hc(data.to.work)
# Generates a BN parameters from the BN structure and write a text file with this
bn.param <- gera.bn.param(bn.structure, data.to.work, param.name)
# set the graph name
graph.name <- "imgBNHC"
# Set the option to show or not the parameters on each edge
param.in.graph <- 1
# Save the graph
mount.graph(bn.structure, bn.param, graph.name, data.to.work, param.in.graph)