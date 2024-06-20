############## ESTATÍSTICA DESCRITIVA

#------- Pacotes

library(tidyverse)
library(microdatasus)
library(DescTools)
library(gtsummary)

#------- Dados de Dengue

SINAN <- fetch_datasus(information_system = "SINAN-CHIKUNGUNYA-PRELIMINAR",
                       year_start = 2021, year_end = 2021)

SINAN <- process_sinan_chikungunya(SINAN)

#------- Explorando o Dataset

glimpse(SINAN)

#------- Filtrando por casos confirmados

SINAN <- SINAN %>% filter(CLASSI_FIN == "Chikungunya")

#------- Estatística descritiva com DescTools

# Variáveis categóricas

Desc(SINAN$HOSPITALIZ)

# Variáveis numéricas

SINAN$IDADEanos <- as.numeric(SINAN$IDADEanos)

Desc(SINAN$IDADEanos)

#------- Estatística descritiva com gtsummary

# Tabela na tela no RStudio

SINAN %>%
  select(CS_SEXO, SG_UF, FEBRE, IDADEanos, HOSPITALIZ) %>%
  mutate(HOSPITALIZ = case_when(HOSPITALIZ == "1" ~ "Sim",
                                HOSPITALIZ == "2" ~ "Não"),
         FEBRE = case_when(FEBRE == "1" ~ "Presente",
                           FEBRE == "2" ~ "Ausente")) %>% 
  tbl_summary(label = list(CS_SEXO ~ "Sexo",
                           SG_UF ~ "UF de residência",
                           FEBRE ~ "Febre",
                           IDADEanos ~ "Idade (anos)"),
              missing_text = "Não informado",
              digits = everything() ~ c(0,2),
              by = HOSPITALIZ) %>% 
  modify_header(label = "Variáveis") %>% 
  bold_labels() %>% 
  modify_spanning_header(all_stat_cols() ~ "Hospitalização")

# Tabela no Word

SINAN %>%
  select(CS_SEXO, SG_UF, FEBRE, IDADEanos, HOSPITALIZ) %>%
  mutate(HOSPITALIZ = case_when(HOSPITALIZ == "1" ~ "Sim",
                                HOSPITALIZ == "2" ~ "Não"),
         FEBRE = case_when(FEBRE == "1" ~ "Presente",
                           FEBRE == "2" ~ "Ausente")) %>% 
  tbl_summary(label = list(CS_SEXO ~ "Sexo",
                           SG_UF ~ "UF de residência",
                           FEBRE ~ "Febre",
                           IDADEanos ~ "Idade (anos)"),
              missing_text = "Não informado",
              digits = everything() ~ c(0,2),
              by = HOSPITALIZ) %>% 
  modify_header(label = "Variáveis") %>% 
  bold_labels() %>% 
  modify_spanning_header(all_stat_cols() ~ "Hospitalização") %>% 
  as_flex_table() %>% flextable::save_as_docx(path = "tutoriais/tabela.docx")