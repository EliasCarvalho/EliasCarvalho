# test.gera.pa.model.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("To your working directory")
# Load packages
library(bnpa)
library(bnlearn)
# load data sets from package
data(dataQualiN)
# Show first lines
head(dataQualiN)
# Learn BN structure
bn.structure <- hc(dataQualiN)
bnlearn::graphviz.plot(bn.structure)
# Set variables
# Generates the PA model from bn structure
pa.model <- gera.pa.model(bn.structure, dataQualiN)
pa.model
