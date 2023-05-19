# test.gera.bn.param.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions/geraBNPA")

# Load packages
library(bnpa)

# Show data sets
data1<-dataQuantC # Pre-Loaded
data2<-dataQualiN # Pre-Loaded
head(data1)
head(data2)

# Set a data set to work
data.to.work <- data1

# Creates a white and black list empty
wl=""
bl=""

# Set what BN learning algorithms will be used
bn.learn.algorithms <- c("hc", "rsmax2")
bn.learn.algorithms <- c("hc")

# Learn a BN structure from data to work and builds a PA model
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl="", bl="", nreplicates = 10)
