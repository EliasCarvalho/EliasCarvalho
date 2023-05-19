# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/your working directory")

# Load packages
library(bnpa)

# Use working datasets from package
data.to.work <- dataQuantC
head(data.to.work)
# IMPORTANT: use always data.to.work this function will do update only on data.to.work
# and keep your original data

####################################################################################
# check.outliers.R - Indentifies and gives an option to remove outliers.
####################################################################################

# scan all variables from the data set and check and remove outliers for each one
for (x in 1:length(names(data.to.work)))
{
  # Mount a variable to load each variable and pass to the check.outlier function
  commandAssign <- paste("variable.content <- data.to.work$", names(data.to.work)[x], sep = "")
  eval(parse(text=commandAssign))
  # Call the function
  outlier <- check.outliers(data.to.work, variable.content, names(data.to.work)[x])

  # The 'check.outlier' function ask for each variable if remove or not the outliers,
  # if you wish to stop watching the outliers just type <ENTER> and it will return
  # an empty variable (outlier == ""), so the next command will exit from the loop
  if (outlier == "")
  {
    break
  } #  if (outlier == "")
} # for (x in 1:length(names(data.to.work)))

