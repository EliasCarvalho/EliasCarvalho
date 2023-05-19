# test.gera.bn.cv.pa.R
# Author: Elias Carvalho - ecacarva@gmail.com
#
# Testing Quantitative Data
# Clean environment
closeAllConnections()
rm(list=ls())
# Set environment
setwd("To your working directory")
# Load packages
library(bnpa)
# Load Data
data.to.work <- dataQuantC # Pre-Loaded
# Set variables to work
white.list <- "B-E"
black.list <- "D-A"
number.of.runs <- 2
number.of.splits <- 2
cb.algorithms <- c("gs")
sb.algorithms <- c("hc")
cb.tests <- c("cor")
sb.tests <- c("aic-g")
optimized.option = FALSE
outcome.var = "F"
build.pa = 0
# Learn a BN structure from data to work and builds a PA model
gera.bn.cv.pa(data.to.work, white.list, black.list, number.of.runs, number.of.splits, cb.algorithms,
              sb.algorithms, cb.tests, sb.tests, optimized.option, outcome.var, build.pa)
# Testing Qualitative Data
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documents/00.2019/88.R-Packages/scripts and data to test functions/gera.bn.cv.pa")
# Load packages
library(bnpa)
# Load Datal
data.to.work <- dataQualiN # Pre-Loaded
# Set variables to work
white.list <- "D-E"
black.list <- NULL
number.of.runs <- 10
number.of.splits <- 10
cb.algorithms <- c("gs")
sb.algorithms <- c("hc")
cb.tests <- c("jt")
sb.tests <- c("aic")
optimized.option = FALSE
outcome.var = "D"
build.pa = 0
# Changing some variables to ordered
data.to.work$A <- as.ordered(data.to.work$A)
data.to.work$B <- as.ordered(data.to.work$B)
data.to.work$D <- as.ordered(data.to.work$D)
# Learn a BN structure from data to work and builds a PA model
gera.bn.cv.pa(data.to.work, white.list, black.list, number.of.runs, number.of.splits, cb.algorithms,
              sb.algorithms, cb.tests, sb.tests, optimized.option, outcome.var, build.pa)
