#----------- Pacotes

library(tidyverse)
library(microdatasus)
library(DescTools)

#----------- Obtendo dados

zika <- fetch_datasus(year_start = 2020, year_end = 2020,
                      information_system = "SINAN-ZIKA-FINAL")

zika <- process_sinan_zika(zika)

#----------- Selecionando variáveis

glimpse(zika)

tcc <- zika %>% select(NU_ANO, CS_SEXO, EVOLUCAO, CLASSI_FIN)

Desc(zika$ID_AGRAVO)

tcc2 <- zika %>% select(-TP_NOT, -ID_AGRAVO)

#----------- Aplicando filtros

Desc(tcc$CLASSI_FIN)

tcc3 <- tcc %>% filter(CLASSI_FIN == "Confirmado")
tcc3 <- tcc3 %>% select(-CLASSI_FIN)
