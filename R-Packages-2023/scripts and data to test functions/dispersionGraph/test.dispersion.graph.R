# Script1 - Regressão Linear Simples
# Author: Elias Carvalho - ecacarva@gmail.com

# Clean environment
closeAllConnections()
rm(list=ls())

# Set enviroment
setwd("~/Doutorado/00.AAA_Estudar Estatísitica")

# Load packages
library(bnpa)
#Load packages neededz for the analysis
#All packages must be installes with install.packages() function

#  Load dataset - READ data_17_08_2016_V5.csv          # IF "" OR " " REPLACE WITH NA
data <- read.csv(file.choose(),header = TRUE, na.strings=c("", " ", "NA")) # Keep orginal data saved
data.to.work <- data

# Convert int variables to numeric
data.to.work <- convert.continuous.int.to.numeric (data.to.work)

# Check if all levels are correct
level.max <- 10
dataset.name <- "data.to.work"
check.levels(data.to.work, level.max, dataset.name)

attach(data.to.work)

# Análise de Regressão Linear Simples - duas variáveis numéricas

# 1 - Construir um GRÁFICO DE DISPERSAO para visualizar a relação entre as variáveis
# O parametro 'las' controla a direção das legendas dos eixos (las= 1, legendas
# escritas sempre na horizontal, las=3, legendas na vertical)

outcome.var <- "Satisfação"
dispersion.graph(data.to.work, outcome.var)
