# test.gera.pa.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("To your working directory")
# Load packages
library(bnpa)
# load data sets from package
data(dataQualiN)
# Show first lines
head(dataQualiN)
# Learn BN structure
bn.structure <- bnlearn::hc(dataQualiN)
bnlearn::graphviz.plot(bn.structure)
# Set variables
pa.name<-"docPAHC"
pa.imgname<-"imgPAHC"
bn.algorithm<-"hc"
bn.score.test<-"aic-g"
outcome.var<-"D"
# Generates the PA model from bn structure
gera.pa(bn.structure, dataQualiN, pa.name, pa.imgname, bn.algorithm, bn.score.test, outcome.var)
