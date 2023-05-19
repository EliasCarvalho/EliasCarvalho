# test.mount.wl.bl.list.R
# Author: Elias Carvalho - ecacarva@gmail.com
# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
# setwd("To your working directory")
# Load packages
library(bnpa)
library(bnlearn)
# Load data sets from package
data(dataQuantC)
# Show the first lines of data
head(dataQuantC)
# Learn the BN structure without black and white list
bn.structure <- hc(dataQuantC)
# Split graph panel in 2 columns
par(mfrow=c(1,2))
# Show the BN structure
bnlearn::graphviz.plot(bn.structure)
# Mounting the black list
black.list <- ("A-C,D-F")
black.list <- mount.wl.bl.list(black.list)
black.list
white.list <- ("A-B,D-G")
white.list <- mount.wl.bl.list(white.list)
white.list
# Learn the BN structure with black and white list
bn.structure <- hc(dataQuantC, whitelist = white.list, blacklist = black.list)
# Show the BN structure
bnlearn::graphviz.plot(bn.structure)
