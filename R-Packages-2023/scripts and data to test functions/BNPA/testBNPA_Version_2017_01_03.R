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

# Set a data set to work
data.to.work <- data1
data.to.work$A<-as.integer(data.to.work$A)
data.to.work$C<-as.integer(data.to.work$C)
data.to.work$E<-as.integer(data.to.work$E)
data.to.work$G<-as.integer(data.to.work$G)

# Generates a BN and PA information

wl=""
bl=""
bn.learn.algorithms <- c("hc", "mmhc", "rsmax2", "tabu")
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms)


wl="A-D"
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl)

bl="A-F"
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl, bl)


=================================================

bnmmhc=1
bn.pa<-gera.bn.structure(data.to.work, wl, bl, bnhc, bnmmhc)

bnrsmax=1
bn.pa<-gera.bn.structure(data.to.work, wl, bl, bnhc, bnmmhc, bnrsmax)

bntabu=1
bn.pa<-gera.bn.structure(data.to.work, wl, bl, bnhc, bnmmhc, bnrsmax, bntabu)


