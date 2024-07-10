#----------- Pacotes

library(tidyverse)
library(microdatasus)
library(DescTools)

#----------- Obtendo dados

zika <- fetch_datasus(year_start = 2020,
                      year_end = 2020,
                      information_system = "SINAN-ZIKA-FINAL")

#----------- Estatística descritiva

Desc(zika$CLASSI_FIN)

#----------- Rotulando a classificação final 
# (1 = Confirmado, 2 = Descartado, 8 = Inconclusivo)

zika <- zika %>% 
  mutate(CLASSI_FIN = case_when(CLASSI_FIN == "1" ~ "Confirmado",
                                CLASSI_FIN == "2" ~ "Descartado",
                                CLASSI_FIN == "8" ~ "Inconclusivo"))

#----------- Rotulando o critério diagnóstico
# (1 = Laboratorial, 2 = Clínico-Epidemiológico)

zika <- zika %>% 
  mutate(CRITERIO = case_when(CRITERIO == "1" ~ "Laboratorial",
                              CRITERIO == "2" ~ "Epidemiológico"))

#----------- Tudo num só pipe

zika <- zika %>% 
  mutate(CLASSI_FIN = case_when(CLASSI_FIN == "1" ~ "Confirmado",
                                CLASSI_FIN == "2" ~ "Descartado",
                                CLASSI_FIN == "8" ~ "Inconclusivo"),
         CRITERIO = case_when(CRITERIO == "1" ~ "Laboratorial",
                              CRITERIO == "2" ~ "Epidemiológico"))
