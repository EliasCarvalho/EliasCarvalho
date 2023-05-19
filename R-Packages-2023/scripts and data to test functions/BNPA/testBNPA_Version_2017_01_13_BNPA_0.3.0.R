# testBNPA.R - a script to test the execution of package
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")

# Load packages
library(bnpa)

# Set a data set to work
data.to.work <- dataQuantC
head(data.to.work)


# Setting an outcome var will automatically create a black list from
# outcome var to others. i.e.: variables are A,B,C,D,E,F, outcome var
# is "G" then the function will create a black list like:
# "G-B, G-C, G-D, G-E, G-F", what means variable "G" will never
# point to another variable
# ps: you can mount a initial black list and function will add the
# outcome blacklist at the end or you can pass an empty blacklist

# Create an empty list or fill it before start
bl <- ""

# or Add a white/black list (mandatory connections)
wl="A-G"
bl="A-C"

# Setting the type of var as typical "outcome" type what means it will not point to any variable
type.var <- "o"

# Setting variable "A" as "outcome" will create a black from this variable to all others
var.name <- "A"

# Creating the black list
bl <- outcome.predictor.var(data.to.work, var.name, type.var, bl)
bl

# Choose the Bayesian Networks learning algorithm
bn.learn.algorithms <- ("hc")

# With both "white" and "black list"
bn.pa<-gera.bn.pa(data.to.work, bn.learn.algorithms, wl, bl)


mymodel <- lm(A ~ B + C, data=data.to.work)
summary(mymodel)
mymodel$coefficients[[2]]
mymodel$coefficients[[3]]

