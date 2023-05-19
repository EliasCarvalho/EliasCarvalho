# test.gera.bn.structure.cv.R
# Author: Elias Carvalho - ecacarva@gmail.com
closeAllConnections()
rm(list=ls())
# Set environment
# setwd("To your working directory")
# Load packages
library(bnpa)
# Load Data
data(dataQualiN)
# Set variables to work
nreplicates = 1000
white.list <- NULL
black.list <- "L-T"
cb.algorithms = c("gs")
sb.algorithms = c("hc")
cb.tests = "jt"
sb.tests = "aic"
optimized.option="FALSE"
outcome.var = "E"
build.pa = 0
# Learn the BN from data and save results (data & images)
gera.bn.structure(dataQualiN, white.list, black.list, nreplicates, cb.algorithms,sb.algorithms,
                  cb.tests, sb.tests, optimized.option, outcome.var, build.pa)
