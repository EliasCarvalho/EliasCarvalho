############# BAIXANDO DADOS DATASUS COM O PACOTE MICRODATASUS

#------------ Instalando o Pacote

install.packages("remotes")
remotes::install_github("rfsaldanha/microdatasus", force = TRUE)

#------------ Carregando o Pacote

library(tidyverse)
library(microdatasus)

#------------ Baixando Dados de Internação Hospitalar

SIH <- fetch_datasus(year_start = 2022,
                     year_end = 2022,
                     month_start = 1,
                     month_end = 1,
                     information_system = "SIH-RD",
                     uf = "MT")

#------------ Processando os Dados

SIH <- process_sih(SIH)

#------------ Explorando o Banco de Dados

glimpse(SIH)

#------------ Tabela de Frequência

table(SIH$SEXO)
