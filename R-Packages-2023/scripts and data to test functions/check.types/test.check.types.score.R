# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/your working directory")

# Load packages
library(bnpa)

####################################################################################
# check.types.score.R - Return the type of score needed to be used with BN learning algorithms.
###################################################################################

# Load quantitative data
data.to.work <- dataQuantC
head(data.to.work)

# Check and return a numeric value
type.variable <- check.types(data.to.work)

# Check the type of score
check.type.score(type.variable)

# Load qualitative data
data.to.work <- dataQualiN
head(data.to.work)

# Check and return a numeric value
type.variable <- check.types(data.to.work)

# Check the type of score
check.type.score(type.variable)
