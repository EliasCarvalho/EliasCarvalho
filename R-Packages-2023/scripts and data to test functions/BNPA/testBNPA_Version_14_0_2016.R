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
data<-data1
head(data)
# Generates a BN and PA information
bn.pa<-gera.bn.structure(data, bnhc=1)
# Add a white list (conections to add)
bn.pa<-gera.bn.structure(data, bnhc=1, wl="D-E")
# Add a black list (connections to exclude)
bn.pa<-gera.bn.structure(data, bnhc=1, wl="D-E", bl="D-F")

bn.pa<-gera.bn.structure(data, bnhc=1, wl="D-G", bl="D-F", param.in.graph=1)


# Setting an outcome var will automatically create a black list from
# outcome var to others. i.e.: variables are A,B,C,D,E,F, outcome var
# is "G" then the function will create a black list like:
# "G-B, G-C, G-D, G-E, G-F", what means variable "G" will never
# point to another variable
# ps: you can mount a initial black list and function will add the
# outcome blacklist at the end or you can pass an empty blacklist
wl="D-E"
bl="D-F"
outcomeVar <- "E"
bl <- outcome.var.bl(data, outcomeVar, bl)
bn.pa<-gera.bn.structure(data, bnhc=1, wl, bl)

# Test MMHC algorithm  =========================================
bn.pa<-gera.bn.structure(data, bnmmhc=1)

# Test rsmax2 algorithm  =======================================
bn.pa<-gera.bn.structure(data, bnrsmax=1)

#======= Example 2 ==============================================
# Load Nominal Data
data<-dataQualiN

# Generates a BN and PA information
bn.pa<-gera.bn.structure(data, bnhc=1)
# Add a white list (conections to add)
bn.pa<-gera.bn.structure(data, bnhc=1, wl="A-T")
# Add a black list (connections to exclude)
bn.pa<-gera.bn.structure(data, bnhc=1, wl="A-T,A-B", bl="S-B")

# Test MMHC algorithm  =========================================
bn.structure<-gera.bn.structure(data, bnmmhc=1)

# Test rsmax2 algorithm  =======================================
bn.structure<-gera.bn.structure(data, bnrsmax=1)


