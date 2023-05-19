# testBNPA.R - a script to test the execution of package
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Documentos/R-Packages/scripts and data to test functions")

# Load packages
library(bnpa)

# Identify main outcomes (bnpa package) and build a black list where these variables does not point to any other

outcomeVar <- "mpg"
blo <- ""
blo <- outcome.var.bl(mtcars, outcomeVar, blo)

# Identify variables that are predictors (bnpa package) and build a black list where all variables points to this variable.
# Means that it is a typical predictor like age or sex and nobody will point to it

blp1 <- ""

# predictorVar <- "B"
# blp1 <- predictor.var(data.test, predictorVar, blp1)


# Join all black lists created and add some more connections not allowed observed
# after building the firsts BNs
if (blp1 != "")
{
  bl <- paste(blo,",",blp1, sep="")
} else  {
  blo <- substr(blo,1,nchar(blo))
  bl <- paste(blo, sep="")
}

# Create white list (empty in the first momment)
wl=""

# Step 12 - Generates a BN and PA
# Parameters:
# data.test = is the data set where bn will be learned and pa will be built
# wl = white list where we force a conection
# bl = black list where we avoid the connection
# bnhc, bnmmhc, bnrsmax, bntabu = are algorithms to learn the bn structure
# nseq = the number os bn structure to be learned, it is useful if you have a looping that
# mount different data set and you wish to pass it to the function and rename the parameters
# with the respective sequence like:
#
# for x = 1 to 10
#   {
#      data <- paste("data",X, sep = "")
#      bn.pa <-gera.bn.structure(data, wl, bl, bnhc=1, nseq=x, param.type = "pa", nreplicates =500)

#   }
# param.type = if we will use and generate pa or a simple lm
# param.in.graph = if will appear a parameter in the BN sctucture edges
# nreplicates = the number of bns will be generated on bootstrapping
#

bn.pa <-gera.bn.structure(mtcars, wl, bl, bnhc=1, bnmmhc=1, bnrsmax=1, bntabu = 1, nseq=1, param.type = "pa", param.in.graph=1, nreplicates =500)


