#### PERGUNTA: 
# Qual município de MT apresentou maior número de óbitos
# por infarto agudo do miocardio em 2020?

#------------- Pacotes

library(tidyverse)
library(microdatasus)

#------------- Download de Dados - Óbitos

DO <- fetch_datasus(information_system = "SIM-DO",
                    year_start = 2020, 
                    year_end = 2020,
                    uf = "MT")

DO <- process_sim(DO)

#------------- Explorar o dataset

glimpse(DO)

DescTools::Desc(DO$CAUSABAS, plotit = FALSE)

#------------- Filtro por IAM

DO <- DO %>% filter(CAUSABAS == "I219")

#------------- Ranking de Município de Residência

DO %>% 
  group_by(munResNome) %>% 
  summarise(Obitos = length(CAUSABAS)) %>% 
  arrange(desc(Obitos))

#------------ Script completo

DO %>% 
  filter(CAUSABAS == "I219") %>% 
  group_by(munResNome) %>% 
  summarise(Obitos = length(CAUSABAS)) %>% 
  arrange(desc(Obitos))
