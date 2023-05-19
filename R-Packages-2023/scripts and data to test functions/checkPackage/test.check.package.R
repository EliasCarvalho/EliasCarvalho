# test.check.package.R
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())
# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")
# Load packages
library(bnpa)
###################################################################################
# check.package.R - Check and install packages if necessary.
###################################################################################
# choose the package name
package.name <- c("gtools")
# Check and install if not installed
check.package(package.name)
