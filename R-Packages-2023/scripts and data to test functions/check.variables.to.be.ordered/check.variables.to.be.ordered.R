# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/your working directory")

# Load package
library(bnpa)

# Use working datasets from package
data.to.work <- dataQualiN
head(data.to.work)

# Insert categorical variables with more than 2 levels
data.to.work$test.variable[data.to.work$A == "yes"] <- "low"
data.to.work$test.variable[data.to.work$B == "yes"] <- "medium"
data.to.work$test.variable[data.to.work$X == "yes"] <- "high"
# Transform it to factor variable
data.to.work$test.variable <- as.factor(data.to.work$test.variable)

####################################################################################
# check.variables.to.be.ordered.R - Check the variables to be ordered
####################################################################################
# Check the necessity to transform in ordered variables
bnpa::check.variables.to.be.ordered(data.to.work)