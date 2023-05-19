# test.check.package.files.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")
# Load packages
library(bnpa)
#########################################################################################################################
# check.package.files.R - Keep a list os packages necessary to bnpa and install if necessary by check.packages function.
#########################################################################################################################
# Check and install if not installed
check.package.files()
