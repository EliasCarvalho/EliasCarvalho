# bnpa.functions.experiments.R - a script to test the functions of package
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")

# Load packages
library(bnpa)

# Show working data sets from package
data1<-dataQuantC # Pre-Loadedx'
head(data1)

###################################################################################
# check.dichotomic.R - Verify if some variables of a data set is dichotomic
###################################################################################
# data set has not dichotomic variables and function will return FALSE
data.to.work <- data1
check.dichotomic(data.to.work)
# adding dichotomic data to data.to.work, function will return TRUE
data.to.work$Z <- round(runif(500, min=0, max=1),0)
check.dichotomic(data.to.work)
