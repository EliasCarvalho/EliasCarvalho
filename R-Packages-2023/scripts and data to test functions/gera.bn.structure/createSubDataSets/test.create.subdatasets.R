# test.create.subdatasets.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")
# Load packages
library(bnpa)
# load data sets from package
data.to.work <-dataQualiN
#########################################################################################################################
# create.subdatasets.R - Creates sub data sets.
#########################################################################################################################
# Set variables to create a new dataset
variables <- c( "A","S","L","E","D")
# Set the new dataset name
dataset.name <- "data.work.new"
# Creates the subdataset
create.subdatasets(data.to.work, variables, dataset.name)
