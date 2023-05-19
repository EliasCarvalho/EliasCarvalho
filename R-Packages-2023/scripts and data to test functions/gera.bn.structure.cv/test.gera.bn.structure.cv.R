# test.gera.bn.structure.cv.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set environment
setwd("To your working directory")
# Load packages
library(bnpa)
# Load Data
data.to.work <- bnpa::dataQualiN # Pre-Loaded
# Set variables to work
number.of.runs=10
number.of.splits=10
df.cv.data <- data.frame(matrix(ncol = 4,nrow = (number.of.runs*number.of.splits)+3))
names(df.cv.data)[1]<-"Runs"
names(df.cv.data)[2]<-"Splits"
names(df.cv.data)[3]<-"gs--jt"
names(df.cv.data)[4]<-"hc--aic"
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
gera.bn.structure.cv(data.to.work, df.cv.data, white.list, black.list, number.of.runs, number.of.splits,
                     cb.algorithms, sb.algorithms, cb.tests, sb.tests, optimized.option, outcome.var, build.pa)
