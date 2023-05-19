# testBNPA.R - a script to test the execution of package
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")

# Load packages
library(bnpa)

######data <- read.csv(file.choose(),header = TRUE)

# Show data sets
data1<-dataQuantC # Pre-Loaded
data2<-dataQualiN # Pre-Loaded
head(data1)
head(data2)

# Define here the data set and variables to remove
dataset.name = "data1"
vars.to.remove = c("A","C","XXX")

# Remove two variables
remove.variables(dataset.name, vars.to.remove)

#======= Example 1 ==============================================
# Load Continuous Data
data.work<-data1

# data <- read.csv(file.choose(),header = TRUE)

# par(mfrow=c(3,3))
# average.bn(data)

# Generates a BN and PA information
bn.pa<-gera.bn.structure(data.work, bnhc=1)
# Add a white list (conections to add)
bn.pa<-gera.bn.structure(data.work, bnhc=1, wl="D-E")
# vlist <-"v1-v4"

# Add a black list (connections to exclude)
bn.pa<-gera.bn.structure(data.work, bnhc=1, wl="D-E", bl="D-F")

# Test MMHC algorithm  =========================================
bn.pa<-gera.bn.structure(data.work, bnmmhc=1)

# Test rsmax2 algorithm  =======================================
bn.pa<-gera.bn.structure(data.work, bnrsmax=1)

#======= Example 2 ==============================================
# Load Nominal Data
data.work<-dataQualiN

# Generates a BN and PA information
bn.pa<-gera.bn.structure(data.work, bnhc=1)
# Add a white list (conections to add)
bn.pa<-gera.bn.structure(data.work, bnhc=1, wl="A-T")
# Add a black list (connections to exclude)
bn.pa<-gera.bn.structure(data.work, bnhc=1, wl="A-T,A-B", bl="S-B")

# Test MMHC algorithm  =========================================
bn.structure<-gera.bn.structure(data.work, bnmmhc=1)

# Test rsmax2 algorithm  =======================================
bn.structure<-gera.bn.structure(data.work, bnrsmax=1)


