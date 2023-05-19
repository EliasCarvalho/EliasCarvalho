# test.outcome.predictor.var.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("To your working directory")
# Load packages
library(bnpa)
library(bnlearn)
# Load data sets from package
data(dataQuantC)
# Show first lines
head(dataQuantC)
# Create an empty list or fill it before start
black.list <- ""
# Setting the type of var as typical "outcome" what means it will not point to any var
type.var <- "o"
# Setting variable "A" as "outcome" will create a black from this variable to all others
var.name <- "A"
# Creating the black list
black.list <- outcome.predictor.var(dataQuantC, var.name, type.var, black.list)
black.list
# Setting the type of var as typical "predictor" it will not be pointed from any other var
type.var <- "p"
# Setting variable "D" as "predictor" will create a blacklist from all others to it
var.name <- "D"
# Creating the black list
black.list <- outcome.predictor.var(dataQuantC, var.name, type.var, black.list)
black.list
