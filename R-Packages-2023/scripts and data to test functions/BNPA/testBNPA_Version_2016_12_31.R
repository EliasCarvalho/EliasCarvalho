# testBNPA.R - a script to test the execution of package
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")

# Load packages
library(bnpa)

# Show data sets
data1<-dataQuantC # Pre-Loaded
data2<-dataQualiN # Pre-Loaded
head(data1)
head(data2)

# Set a data set to work
data.to.work <- data1

# Transform some variables into integer types
data.to.work$A<-as.integer(data.to.work$A)
data.to.work$C<-as.integer(data.to.work$C)
data.to.work$E<-as.integer(data.to.work$E)
data.to.work$G<-as.integer(data.to.work$G)

# Creates a white and black list empty
wl=""
bl=""

# Set what BN learning algorithms will be used
bn.learn.algorithms <- c("hc", "mmhc")

# Learn a BN structure from data to work and builds a PA model
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms)

# Add a white list (mandatory connections)
wl="A-G"
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl)

# Add a black list (connections to exclude)
bl="A-C"
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, bl)

# With both "white" and "black list"
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl, bl)

# Setting an outcome var will automatically create a black list from
# outcome var to others. i.e.: variables are A,B,C,D,E,F, outcome var
# is "G" then the function will create a black list like:
# "G-B, G-C, G-D, G-E, G-F", what means variable "G" will never
# point to another variable
# ps: you can mount a initial black list and function will add the
# outcome blacklist at the end or you can pass an empty blacklist

# Create an empty list or fill it before start
bl <- ""

# Setting the type of var as typical "outcome" type what means it will not point to any variable
type.var <- "o"

# Setting variable "A" as "outcome" will create a black from this variable to all others
var.name <- "A"

# Creating the black list
bl <- outcome.predictor.var(data.to.work, var.name, type.var, bl)
bl

bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl, bl)


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


