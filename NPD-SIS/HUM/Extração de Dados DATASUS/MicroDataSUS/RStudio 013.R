############### MISSING DATA

library(tidyverse)
library(microdatasus)

#----------- Obtendo dados

zika <- fetch_datasus(year_start = 2020,
                      year_end = 2020,
                      information_system = "SINAN-ZIKA-FINAL")

#----------- Mapa de variáveis missing

Amelia::missmap(zika)
zika %>% Amelia::missmap()

#----------- Proporção de missing por pessoa

naniar::miss_case_summary(zika)


zika %>% 
  filter(CLASSI_FIN == "1") %>% 
  naniar::miss_case_summary()

#----------- Proporção de missing por variável

missing <- naniar::miss_var_summary(zika)


zika %>% 
  filter(CLASSI_FIN == "1") %>% 
  naniar::miss_var_summary()

#----------- Eliminando missing de variáveis específicas

DescTools::Desc(zika$CRITERIO, plotit = FALSE)

zika2 <- zika %>% drop_na(CRITERIO)
zika2 <- zika %>% drop_na()

#----------- Substituindo valores NA

zika2 <- zika %>% 
  mutate(CRITERIO = coalesce(CRITERIO, "9"))

DescTools::Desc(zika2$CRITERIO, plotit = FALSE)
