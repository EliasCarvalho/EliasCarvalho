# Clean environment
closeAllConnections()
rm(list=ls())

# Load BNPA package
library(bnpa)

# Check if some packages area installed and loaded if not the function will do it
check.package.files()

# Set enviroment
setwd("~/Documents/R-Packages/scripts and data to test functions/imputMissingDataChooseByMode.R")

#  Load dataset - READ data_17_08_2016_V5.csv          # IF "" OR " " REPLACE WITH NA
data <- read.csv(file.choose(),header = TRUE, na.strings=c("", " ", "NA")) # Keep orginal data saved

# Load the data of work
data.to.work <- data

# Check the dataset
summary(data.to.work)

# Check if the types are correctly
str(data.to.work) ### Observe the NAs

# Convert factor with more than N levels to numeric
level.max <- 10 # N levels
dataset.name <- "data.to.work"
check.levels(data.to.work, level.max, dataset.name)

# Convert Continuous Int to Numeric
data.to.work <- convert.continuous.int.to.numeric (data.to.work)

# scan all variables from the data set and check and remove outliers for each one
ask.before = "N"
data.to.work <- check.outliers(data.to.work, ask.before) # Look at Plots tab on bottom, right side window

# Checking the Nas
check.na(data.to.work)

# Remove variables with very High Missing
data.to.work$IMC <- NULL
data.to.work$CC  <- NULL

# Impute NAs with MICE
data.to.work <- imputMissingDataChooseByMode(data.to.work)    ################################ Here is the function

# scan all variables from the data set and check and remove outliers for each one
ask.before = "N"
data.to.work <- check.outliers(data.to.work, ask.before)


# Check the dataset
summary(data.to.work)
